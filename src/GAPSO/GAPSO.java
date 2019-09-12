package GAPSO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import GAPSO.Vector;

public abstract class GAPSO 
{
	//attributes
	private int populationSize;
	private double mutationRate;
	private double socialComponent;
	private double cognitiveComponent;
	private double inertia;
	private OA currentAlgorithm;
	private double highRange;
	private double lowRange;

	private Vector bestPosition;
	private double bestEval;
	
	private int generation;

	private Particle[] particles;
	private double[][] GApopulation;
	
	private int dimension;
	
	Random rand = new Random();
	
	
	private ArrayList<String> data = new ArrayList<String>();
	private ArrayList<Double> MalleableData = new ArrayList<Double>();
	
	
	//constructor
	public GAPSO(	OA algorithm, double highRange, double lowRange, int populationSize, double mutationRate, double socialComponent, double cognitiveComponent, double inertia, int dimension	)
	{
		
		this.setPopulationSize(populationSize);
		this.setMutationRate(mutationRate);
		this.setSocialComponent(socialComponent);
		this.setCognitiveComponent(cognitiveComponent);
		this.setInertia(inertia);
		this.setCurrentAlgorithm(algorithm);
		this.setLowRange(lowRange);
		this.setHighRange(highRange);
		this.setDimension(dimension);
		
		data.add("Generation/Epoch,Fitness/n");
		// generate the population for first algorithm
		switch(currentAlgorithm)
		{
			case GA: 
				generatePopulation(populationSize, dimension);
			break;
			
			case PSO: 
				particles = initialize(populationSize);
	        	bestPosition = new Vector(highRange, highRange);
	        	bestEval = eval(bestPosition.clone().getDimensions());
			break;	
		}	
	}
	//-------------- methods ----------------//
	
	//abstract method eval needs to be defined in a seperate class	
	public abstract double eval(double[] x);
	
	//evolves the population if current Algorithm is GA
	//updates velocities if PSO if current Algorithm is PSO
	public void step()
	{
		//if the current algorithm is a GA then evolves current population
		//if the current algorithm is a PSO then will update velocities of population
		switch(currentAlgorithm)
		{
			case GA:	Evolve();
				break;
			case PSO:	updateVelocities();
				break;	
		}
		
		
		String firstData = (currentAlgorithm == OA.GA)? generation + "," + eval(bestChromosome()) + "\n" : generation + "," + bestEval+"\n";
		data.add(firstData);
		
		double secondData = (currentAlgorithm == OA.GA)? eval(bestChromosome()) : bestEval;
		MalleableData.add(secondData);

		
		generation++;
		
		//prints current iteration
		System.out.println(toString());

	}
	
	
	//generates a new population for GA
	public void generatePopulation(int populationSize, int dimension)
	{
		double[][] newPopulation = new double[populationSize][dimension];
		
		for(int i = 0; i < dimension; i++)
		{
			for(int j = 0; j < newPopulation.length; j++)
			{
				newPopulation[j][i] = randomBetween(lowRange, highRange);
			}
		}
		
		
		GApopulation = newPopulation;
	}

	private void Evolve()
	{
		double[][] newPopulation = new double[GApopulation.length][dimension];
		
		newPopulation[0] = bestChromosome();
		
		for(int i = 1; i < newPopulation.length; i++)
		{
			double[] parent1 = Selection();
			double[] parent2 = Selection();
		
			double[] child = CrossOver(parent1, parent2);
			double[] mutatedChild = Mutate(child);
			
			newPopulation[i] = mutatedChild;
		}
		
		GApopulation = newPopulation;
		
	}

	private double[] Selection() 
	{
		//tournament style
		double[] challenger1 = GApopulation[rand.nextInt(GApopulation.length)];
		double[] challenger2 = GApopulation[rand.nextInt(GApopulation.length)];
		
		return (eval(challenger1) < eval(challenger2)) ? challenger1 : challenger2;
		
	}

	private double[] CrossOver(double[] parent1, double[] parent2)
	{
		double[] child = new double[parent1.length];
		for(int i = 0; i < dimension; i++)
		{
			child[i] = parent1[i] + parent2[i] / 2;
		}
		return child;
	}
	
	private double[] Mutate(double[] child) 
	{
		double[] newChild = child;
		
		for(int i = 0; i < dimension; i++)
		{
			if(mutationRate >= Math.random())
			{
				int random = rand.nextInt(2);
				switch(random)
				{
				case 0: newChild[i] += rand.nextDouble(); 
					break;
				case 1:	newChild[i] -= rand.nextDouble(); 
					break;
				}
			}
		}
		return newChild;
	}
	
	public double[] bestChromosome()
	{
		double[] best = GApopulation[0];
		
		for(double[] i : GApopulation)
		{
			if(eval(i) < eval(best))
			{
				best = i;
			}
		}
		
		return best;
	}

	
	// ----- Particle Swarm Optimization Methods ----- //
    private Particle[] initialize (int numOfParticles) {
        Particle[] retParticles = new Particle[numOfParticles];
        for (int i = 0; i < numOfParticles; i++) 
        {
            Particle particle = new Particle(lowRange, highRange);
            retParticles[i] = particle;
            updateGlobalBest(particle);
        }
        return retParticles;
    }
    
    private void updateGlobalBest (Particle particle) {
        if (particle.getBestEval() < bestEval) {
            bestPosition = particle.getBestPosition().clone();
            bestEval = particle.getBestEval();
        }
    }
    
    public void updateVelocities()
    {
    	for(int i = 0; i < particles.length; i++)
    	{
    		particles[i].updatePersonalBest();
    		
    		updateGlobalBest(particles[i]);
    		updateVelocity(particles[i]);
    		particles[i].updatePosition();
    	}
    }

	
    private void updateVelocity (Particle particle) {
        Vector oldVelocity = particle.getVelocity();
        Vector pBest = particle.getBestPosition();
        Vector gBest = bestPosition.clone();
        Vector pos = particle.getPosition();

        Random random = new Random();
        double r1 = random.nextDouble();
        double r2 = random.nextDouble();

        Vector newVelocity = oldVelocity.clone();
        newVelocity.mul(inertia);
        pBest.sub(pos);
        pBest.mul(cognitiveComponent);
        pBest.mul(r1);
        newVelocity.add(pBest);
        gBest.sub(pos);
        gBest.mul(socialComponent);
        gBest.mul(r2);
        newVelocity.add(gBest);


       particle.setVelocity(newVelocity);
}
	
	
	// ----- PSO & GA Methods --- //
	
	//transfers population
    //transfer the particles to chromosomes vice versa
	public void transferSwitch()
	{
		switch(currentAlgorithm)
		{
			case GA: 
				
				particles = initialize(populationSize);

				for(int i = 0; i < particles.length; i++)
				{
					particles[i].setPosition(new Vector(GApopulation[i]));
				}
				
	        	bestPosition = new Vector(highRange, highRange);
	        	bestEval = eval(bestPosition.clone().getDimensions());
	        	currentAlgorithm = OA.PSO;
				
			break;
			
			case PSO: 
				generatePopulation(populationSize, dimension);
				
				for(int i = 0; i < populationSize; i++)
				{
					GApopulation[i] = particles[i].getPosition().getDimensions();
				}
				currentAlgorithm = OA.GA;
			break;
			
		}
		
		
	}
	
	//shrinks the solution space based off of the solution space size,
	//changes the algorithm to run again
	public void transferShrink()
	{
		
		double possibleHighRange1 = ( highRange * 0.5 ) + ( (currentAlgorithm == OA.GA) ? bestChromosome()[0] : bestPosition.clone().getDimensions()[0] );
		double possibleHighRange2 = ( highRange * 0.5 ) + ( (currentAlgorithm == OA.GA) ? bestChromosome()[1] : bestPosition.clone().getDimensions()[1] );
		
		highRange = (possibleHighRange1>possibleHighRange2)? possibleHighRange1 : possibleHighRange2;
		
		double possibleLowRange1 = ( lowRange / 2 ) + ( (currentAlgorithm == OA.GA) ? bestChromosome()[0] : bestPosition.clone().getDimensions()[0] );
		double possibleLowRange2 = ( lowRange / 2 ) + ( (currentAlgorithm == OA.GA) ? bestChromosome()[1] : bestPosition.clone().getDimensions()[1] );
		
		
		lowRange = (possibleLowRange1<possibleLowRange2)? possibleLowRange1 : possibleLowRange2;
		
		
		switch(currentAlgorithm)
		{
			case GA: 
				
				particles = initialize(populationSize);
				
	        	bestPosition = new Vector(highRange, highRange);
	        	bestEval = eval(bestPosition.clone().getDimensions());
	        	currentAlgorithm = OA.PSO;
				
			break;
			
			case PSO: 
				
				generatePopulation(populationSize, dimension);
				
				currentAlgorithm = OA.GA;
			break;
			
		}
		
	}
	
	
	//--------------To String --------------//
	public String toString()
	{
		return (currentAlgorithm == OA.GA) ? "Generation " + generation + ": X: " + Arrays.toString(bestChromosome()) + " Y: " + eval(bestChromosome()) + " " + dimension : "Epoch " + generation + ": inputs: " + Arrays.toString(bestPosition.getDimensions()) + " eval: " + bestEval ;
	}
	
	
	class Particle 
	{

		private double bestEvaluation;
		private Vector Position;
		private Vector Velocity;
		private Vector bp;
		
		public Particle(double beginRange, double endRange)
		{
			Position = new Vector(new double[dimension]);
			Velocity = new Vector(new double[dimension]);
			setRandomPosition(beginRange, endRange);
			setRandomVelocity(beginRange, endRange);
			bp = Velocity.clone();
			bestEvaluation = eval(Position.getDimensions());
		}
		
		public Particle(double beginRange, double endRange, double[] positions)
		{
			Position = new Vector(positions);
			Velocity = new Vector(new double[dimension]);
			setRandomVelocity(beginRange, endRange);
			bp = Velocity.clone();
			bestEvaluation = eval(Position.getDimensions());
		}

		

		
		private void setRandomPosition(double beginningRange, double endingRange)
		{
			double[] dimensions = new double[dimension];
			
			for(int i = 0; i < dimension; i++)
			{
				dimensions[i] = randomBetween(beginningRange, endingRange);
			}
			
			Position.set(dimensions);
		}
		
		private void setRandomVelocity(double beginningRange, double endingRange)
		{
			double[] dimensions = new double[dimension];
			
			for(int i = 0; i < dimension; i++)
			{
				dimensions[i] = randomBetween(beginningRange, endingRange);
			}
			Velocity.set(dimensions);
		}
		
	    void updatePersonalBest () 
	    {
	        double eval = eval(Position.getDimensions());
	        
	        if (eval < bestEvaluation) 
	        {
	        	bp = Position.clone();
	            bestEvaluation = eval;
	        }
	    }

	    void updatePosition () 
	    {
	        this.Position.add(Velocity);
	    }
	    Vector getVelocity () 
	    {
	        return Velocity.clone();
	    }

	    Vector getBestPosition() 
	    {
	        return bp.clone();
	    }

	    double getBestEval () 
	    {
	        return bestEvaluation;
	    }

		public void setVelocity(Vector Velocity) {this.Velocity = Velocity;}
	    
		public Vector getPosition() {return Position.clone();}
		

		public void setPosition(Vector x) {Position = x;}
		public void setBestKnownPosition(Vector x) {bp = x;}
	}
	
	//---- save data to File----------//
	
	public void saveData(String filename) throws Exception
	{
		File RP = new File(filename);
 		BufferedWriter writer = new BufferedWriter(new FileWriter(RP));
		for(int i =0; i < data.size(); i++)
		{
		try {
				writer = new BufferedWriter(new FileWriter(RP, true));
				writer.append(data.get(i));
				writer.flush();
				writer.close();
			}
		catch(IOException f)
			{
				System.out.println("Error");
			}
		}
	}

	//---- Utility ----------//
	
	private double randomBetween(double min, double max)
	{
		double range = max - min;
		double scaled = rand.nextDouble()*range;
		double shifted = scaled  + min;
		return shifted;
	}
	
	//-------------- getters ----------------//
	public double[][] getGAPopulation() {return GApopulation;}
	public double getMutationRate() {return mutationRate;}
	public double getSocialComponent() {return socialComponent;}
	public double getCognitiveComponent() {return cognitiveComponent;}
	public double getInertia() {return inertia;}
	public OA getCurrentAlgorithm() {return currentAlgorithm;}
	public int getPopulationSize() {return populationSize;}
	public double getHighRange() {return highRange;}
	public double getLowRange() {return lowRange;}
	public int getDimension() {return dimension;}

	//-------------- setters ----------------//
	public void setGAPopulation(double[][] population) {this.GApopulation = population;}
	public void setMutationRate(double mutationRate) {this.mutationRate = mutationRate;}
	public void setSocialComponent(double socialComponent) {this.socialComponent = socialComponent;}
	public void setCognitiveComponent(double cognitiveComponent) {this.cognitiveComponent = cognitiveComponent;}
	public void setInertia(double inertia) {this.inertia = inertia;}
	public void setCurrentAlgorithm(OA currentAlgorithm) {this.currentAlgorithm = currentAlgorithm;}
	public void setPopulationSize(int populationSize) {this.populationSize = populationSize;}
	public void setHighRange(double highRange) {this.highRange = highRange;}
	public void setLowRange(double lowRange) {this.lowRange = lowRange;}
	public void setDimension(int dimension) {this.dimension = dimension;}

	public ArrayList<Double> getData() {return MalleableData;}







	
}
