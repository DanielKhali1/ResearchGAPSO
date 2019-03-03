package Main;

import ParticleSwarm.*;

import java.io.BufferedWriter;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

import Function.*;
import GeneticAlgorithm.*;

public class Manager 
{
	//PSO
	final static int PSOPopulation = 50;
	final static double PSOInertia = 0.729844;
	final static double PSOCognitive = 1.496180;
	final static double PSOSocial = 1.496180;
	
	//GeneticAlgorithm
	final static int GAPopulation = 50;
	final static double MutationRate = .01;
	
	static ArrayList<String> dat = new ArrayList<String>();
	
	static DecimalFormat df = new DecimalFormat("#.####");
	
	public static void main(String[] args) throws Exception
	{
		df.setRoundingMode(RoundingMode.CEILING);
		int numberOfIterations = 20;
		
		File RP = new File("data.csv");
 		BufferedWriter writer = new BufferedWriter(new FileWriter(RP));
		writer.write("Test,First,Function,Iterations,GA%,PSO%,GA iterations,PSO iterations,X,Y\n");
		writer.close();
		
		

			for(int j = 0; j < 5; j++)//5
			{
				GAfirstPSO(numberOfIterations, new Functions(2, -5, 5, -1), 1);
 				PSOfirstGA(numberOfIterations, new Functions(2, -5, 5, -1), 1);
				numberOfIterations += 20;
			}
			numberOfIterations = 20;
			System.out.println("NEXT FUNCTION");
		
		
		for(int i =0; i < dat.size(); i++)
		{
			try {
				System.out.println("writing" + (dat.size()-i));
					writer = new BufferedWriter(new FileWriter(RP, true));
					writer.append(dat.get(i));
					writer.flush();
					writer.close();
				}
			catch(IOException f)
				{
					System.out.println("something didn't work");
				}
		}
		
	}
	
	public static void GAfirstPSO(int iteration, Functions function, int test)
	{
		double GAiterationPercentage = 1.0;
		double PSOiterationPercentage = 0.0;
		
		double [] globalMinumums = {0.6351, 1.5489, 0.1428, 0};
		
		for(int i = 0; i < 5; i++)
		{
			FunctionGA ga = new FunctionGA(GAPopulation, MutationRate, function);
			
			for(int j = 0; j < iteration*GAiterationPercentage; j++)
			{
				System.out.println("Evolving GA");
		        //data += "Test,First,Function,Iterations,GA%,PSO%,GA iterations,PSO iterations,Current iteration,Current Algorithm,Gen/Epoch#,Indv #,X,Y\n";
				for(int k = 0; k < ga.getPopulationSize(); k++)
				{	
					System.out.println("writing to file GAit "+ k +" actual it " + j + " function "  + i);
					//if(ga.getBestChromosome() != ga.getPopulation()[k])
						//dat.add(test+",GA,"+function.getFunctionNumber()+","+ iteration+","+GAiterationPercentage*100+"%,"+PSOiterationPercentage*100+"%,"+iteration*GAiterationPercentage+","+iteration*PSOiterationPercentage+","+j+",GA,"+ga.getGeneration()+","+k+","+ga.ChromosomeToDecimalValue(ga.getPopulation()[k])+","+ga.getFitness(ga.getPopulation()[k])+"\n");
					//else
						//dat.add(test+",GA,"+function.getFunctionNumber()+","+ iteration+","+GAiterationPercentage*100+"%,"+PSOiterationPercentage*100+"%,"+iteration*GAiterationPercentage+","+iteration*PSOiterationPercentage+","+j+",GA,"+ga.getGeneration()+","+k+","+ga.ChromosomeToDecimalValue(ga.getPopulation()[k])+","+ga.getFitness(ga.getPopulation()[k])+",bestInCurrentpop\n");
				}
				ga.Evolve();
			}
			
			//dat.add(test+",GA,"+function.getFunctionNumber()+","+ iteration+","+GAiterationPercentage*100+"%,"+PSOiterationPercentage*100+"%,"+iteration*GAiterationPercentage+","+iteration*PSOiterationPercentage + "," + ga.ChromosomeToDecimalValue(ga.getBestChromosome()) + "," + ga.getFitness(ga.getBestChromosome())+"\n");

			for(int k = 0; k < ga.getPopulationSize(); k++)
			{
				System.out.println("writing to file finishing GA and Starting PSO");
				//if(ga.getBestChromosome() != ga.getPopulation()[k])
					//dat.add(test+",GA,"+function.getFunctionNumber()+","+ iteration+","+GAiterationPercentage*100+"%,"+PSOiterationPercentage*100+"%,"+iteration*GAiterationPercentage+","+iteration*PSOiterationPercentage+","+iteration*GAiterationPercentage+",GA,"+ga.getGeneration()+","+k+","+ga.ChromosomeToDecimalValue(ga.getPopulation()[k])+","+ga.getFitness(ga.getPopulation()[k])+"\n");
				//else
					//dat.add(test+",GA,"+function.getFunctionNumber()+","+ iteration+","+GAiterationPercentage*100+"%,"+PSOiterationPercentage*100+"%,"+iteration*GAiterationPercentage+","+iteration*PSOiterationPercentage+","+iteration*GAiterationPercentage+",GA,"+ga.getGeneration()+","+k+","+ga.ChromosomeToDecimalValue(ga.getPopulation()[k])+","+ga.getFitness(ga.getPopulation()[k])+",bestInCurrentpop\n");

			}
			
			System.out.println("moving to swarm");
			Functions function2 = new Functions(function.getFunctionNumber(), ga.ChromosomeToDecimalValue(ga.getBestChromosome())-smallerSolutionSpace(function), ga.ChromosomeToDecimalValue(ga.getBestChromosome())+smallerSolutionSpace(function), -1);
						
			Swarm swarm = new Swarm(PSOPopulation, function2, PSOInertia, PSOCognitive, PSOSocial);
			//		writer.write("Test,First,Function,Iterations,GA%,PSO%,GA iterations,PSO iterations,X,Y\n");

			
			for(int j = 0; j < iteration*PSOiterationPercentage; j++)
			{
				System.out.println("Updating Velocities");
				for(int k = 0; k < swarm.getParticles().length; k++)
				{
					System.out.println("writing to file GAit "+k +" actual it " + j + " function "  + i);
					//if(swarm.getBestPosition().getX() != swarm.getParticles()[k].getPosition().getX())
						//dat.add(test+",GA,"+function.getFunctionNumber()+","+ iteration+","+GAiterationPercentage*100+"%,"+PSOiterationPercentage*100+"%,"+iteration*GAiterationPercentage+","+iteration*PSOiterationPercentage+","+j+",PSO,"+swarm.getEpoch()+","+k+","+swarm.getParticles()[k].getPosition().getX()+","+swarm.getParticles()[k].eval()+"\n");
					//else
						//dat.add(test+",GA,"+function.getFunctionNumber()+","+ iteration+","+GAiterationPercentage*100+"%,"+PSOiterationPercentage*100+"%,"+iteration*GAiterationPercentage+","+iteration*PSOiterationPercentage+","+j+",PSO,"+swarm.getEpoch()+","+k+","+swarm.getParticles()[k].getPosition().getX()+","+swarm.getParticles()[k].eval()+",bestInCurrentpop\n");

				}
				swarm.updateVelocities();
			}
			for(int k = 0; k < swarm.getParticles().length; k  ++)
			{
				System.out.println("writing to file finishing PSO completely");
				//if(swarm.getBestPosition().getX() != swarm.getParticles()[k].getPosition().getX())
					//dat.add(test+",GA,"+function.getFunctionNumber()+","+ iteration+","+GAiterationPercentage*100+"%,"+PSOiterationPercentage*100+"%,"+iteration*GAiterationPercentage+","+iteration*PSOiterationPercentage+","+iteration*PSOiterationPercentage+",PSO,"+swarm.getEpoch()+","+k+","+swarm.getParticles()[k].getPosition().getX()+","+swarm.getParticles()[k].eval()+"\n");
				//else
					//dat.add(test+",GA,"+function.getFunctionNumber()+","+ iteration+","+GAiterationPercentage*100+"%,"+PSOiterationPercentage*100+"%,"+iteration*GAiterationPercentage+","+iteration*PSOiterationPercentage+","+iteration*PSOiterationPercentage+",PSO,"+swarm.getEpoch()+","+k+","+swarm.getParticles()[k].getPosition().getX()+","+swarm.getParticles()[k].eval()+",bestInCurrentpop\n");
			}
			dat.add(test+",GA,"+function.getFunctionNumber()+","+ iteration+","+GAiterationPercentage*100+"%,"+PSOiterationPercentage*100+"%,"+iteration*GAiterationPercentage+","+iteration*PSOiterationPercentage + "," + df.format(swarm.getBestPosition().getX()) + "," + df.format(swarm.bestPositionsY()) +"\n");

			
			GAiterationPercentage -= .2;
			PSOiterationPercentage += .2;
		}
	}

	public static void PSOfirstGA(int iteration, Functions function, int test) throws Exception
	{
		double GAiterationPercentage = 0.0;
		double PSOiterationPercentage = 1.0;
		
		double [] globalMinumums = {0.6351, 1.5489, 0.1428, 0};
		
		for(int i = 0; i < 5; i++)
		{
			
			Swarm swarm = new Swarm(PSOPopulation, function, PSOInertia, PSOCognitive, PSOSocial);
			
			for(int j = 0; j < iteration*PSOiterationPercentage; j++)
			{
				/*
				for(int k = 0; k < swarm.getParticles().length; k++)
				{
					data+=(test+",PSO,"+function+","+ iteration+","+GAiterationPercentage*100+"%,"+PSOiterationPercentage*100+"%,"+iteration*GAiterationPercentage+","+iteration*PSOiterationPercentage+","+j+",PSO,"+swarm.getEpoch()+","+k+","+swarm.getParticles()[k].getPosition().getX()+","+swarm.getParticles()[k].getPosition().getY()+"\n");
				}*/
				swarm.updateVelocities();
				System.out.println("updating velocities for PSO first");
			}
			
			/*for(int k = 0; k < swarm.getParticles().length; k  ++)
			{
				System.out.println("writing to file finishing PSO and moving to GA");
				data+=(test+",GA,"+function+","+ iteration+","+GAiterationPercentage*100+"%,"+PSOiterationPercentage*100+"%,"+iteration*GAiterationPercentage+","+iteration*PSOiterationPercentage+","+iteration*PSOiterationPercentage+",PSO,"+swarm.getEpoch()+","+k+","+swarm.getParticles()[k].getPosition().getX()+","+swarm.getParticles()[k].getPosition().getY()+"\n");
			}*/
			//dat.add(test+",PSO,"+function.getFunctionNumber()+","+ iteration+","+GAiterationPercentage*100+"%,"+PSOiterationPercentage*100+"%,"+iteration*GAiterationPercentage+","+iteration*PSOiterationPercentage + "," + swarm.getBestPosition().getX() + "," + swarm.bestPositionsY() +"\n");
			Functions function2 = new Functions(function.getFunctionNumber(), swarm.getBestPosition().getX()-smallerSolutionSpace(function), swarm.getBestPosition().getX()+smallerSolutionSpace(function), -1);

			FunctionGA ga = new FunctionGA(GAPopulation, MutationRate, function2);
			
			for(int j = 0; j < iteration*GAiterationPercentage; j++)
			{
				/*for(int k = 0; k < ga.getPopulationSize(); k++)
				{
					System.out.println("writing to file GAit "+k +" actual it " + j + " function "  + i);
					data+=(test+",GA,"+function+","+ iteration+","+GAiterationPercentage*100+"%,"+PSOiterationPercentage*100+"%,"+iteration*GAiterationPercentage+","+iteration*PSOiterationPercentage+","+j+",GA,"+ga.getGeneration()+","+k+","+ga.ChromosomeToDecimalValue(ga.getPopulation()[k])+","+ga.getFitness(ga.getPopulation()[k])+"\n");
				}*/

				ga.Evolve();
				System.out.println("Evolving GA PSO first");
			}
			dat.add(test+",PSO,"+function.getFunctionNumber()+","+ iteration+","+GAiterationPercentage*100+"%,"+PSOiterationPercentage*100+"%,"+iteration*GAiterationPercentage+","+iteration*PSOiterationPercentage + "," + df.format(ga.ChromosomeToDecimalValue(ga.getBestChromosome())) + "," + df.format(ga.getFitness(ga.getBestChromosome()))  +"\n");
						
			/*for(int k = 0; k < ga.getPopulationSize(); k++)
			{
				System.out.println("writing to file finishing GA");
				data+=(test+",GA,"+function+","+ iteration+","+GAiterationPercentage*100+"%,"+PSOiterationPercentage*100+"%,"+iteration*GAiterationPercentage+","+iteration*PSOiterationPercentage+","+iteration*GAiterationPercentage+",GA,"+ga.getGeneration()+","+k+","+ga.ChromosomeToDecimalValue(ga.getPopulation()[k])+","+ga.getFitness(ga.getPopulation()[k])+"\n");
			}*/
			
			GAiterationPercentage += .2;
			PSOiterationPercentage -= .2;
		}
		
	}
	
	public static double smallerSolutionSpace(Functions function)
	{
		double ss = function.getHighRange()-function.getLowRange();
		
		double R = (ss/2) * .05;
		
		return R;
	}

}
