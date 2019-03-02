package GeneticAlgorithm;

import java.util.Random;


public abstract class GeneticAlgorithm
{
    int populationSize;
    double mutationRate;
    String population[];
    Random rand = new Random();
    int generation;
    
    public GeneticAlgorithm(int populationSize, double mutationRate)
    {
    	this.populationSize = populationSize;
    	this.mutationRate = mutationRate;
    	this.generation = 0;
    	
    }
    
    public void Evolve()
    {
    	String[] newPopulation = new String[populationSize];
    	
    	//newPopulation[0] = getBestChromosome();
        for(int i = 0; i < populationSize; i++)
        {
        	String parent1 = Selection();
        	String parent2 = Selection();
        	
        	String offSpring = Crossover(parent1, parent2);
        	
        	newPopulation[i] = offSpring;
        	newPopulation[i] = Mutate(newPopulation[i]);
        	//System.out.println(offSpring);
        }
        population = newPopulation;
        generation++;
    }
    
    
    public String Selection()
    {
    	String individual1 = population[rand.nextInt(population.length)];
    	String individual2 = population[rand.nextInt(population.length)];
    	
    	return (getFitness(individual1) < getFitness(individual2)) ? individual1 : individual2;
    }
    
    public abstract double getFitness(String chromosome);
    public abstract String Crossover(String parent1, String parent2);
    public abstract String Mutate(String chromosome);
    public abstract void generatePopulation();
    public abstract String getBestChromosome();

    public abstract String toString();
    
    public int getPopulationSize(){return populationSize;}
    public double getMutationRate(){return mutationRate;}
    public String[] getPopulation(){return population;}
    
    public void setPopulationSize(int populationSize){this.populationSize = populationSize;}
    public void setMutationRate(double mutationRate){this.mutationRate = mutationRate;}
    public void setPopulation(String[] population){this.population = population;}

	public int getGeneration() {return generation;}
    
}