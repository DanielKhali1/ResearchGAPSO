package Main;

import ParticleSwarm.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
	
	public static void main(String[] args) throws Exception
	{
		int numberOfIterations = 20;
		
		File RP = new File("wow.txt");
 		BufferedWriter writer = new BufferedWriter(new FileWriter(RP));
		writer.write("Test,First,Function,Iterations,GA%,PSO%,GA iterations,PSO iterations,Current iteration,Current Algorithm,Gen/Epoch#,Indv #,X,Y\n");
		writer.close();
		
		

		for(int i = 1; i < 4; i++)
		{
			for(int j = 0; j < 1; j++)//5
			{
				GAfirstPSO(numberOfIterations, new Functions(i,-10.0, 10.0, -1), 1);
				//PSOfirstGA(numberOfIterations, new Functions(i, -10.0, 10.0, -1), 1);
				numberOfIterations += 20;
			}
			numberOfIterations = 20;
			System.out.println("NEXT FUNCTION");
		}
		
		/*
		for(int i =0; i < dat.size(); i++)
		{
			try {
				System.out.println("writing" + (dat.size()-i));
					writer = new FileWriter(new File("data.csv"), true);
					writer.append(dat.get(i));
					writer.flush();
					writer.close();
				}
			catch(IOException f)
				{
					System.out.println("something didn't work");

				}
		}
		*/
	}
	
	public static void GAfirstPSO(int iteration, Functions function, int test)
	{
		double GAiterationPercentage = 1.0;
		double PSOiterationPercentage = 0.0;
		
		double [] globalMinumums = {0.6351, 1.5489, 0.1428, 0};
		
		for(int i = 0; i < 4; i++)
		{
			FunctionGA ga = new FunctionGA(GAPopulation, MutationRate, function);
			
			for(int j = 0; j < iteration*GAiterationPercentage; j++)
			{
				System.out.println("Evolving GA");
		        //data += "Test,First,Function,Iterations,GA%,PSO%,GA iterations,PSO iterations,Current iteration,Current Algorithm,Gen/Epoch#,Indv #,X,Y\n";
				for(int k = 0; k < ga.getPopulationSize(); k++)
				{
					System.out.println("writing to file GAit "+ k +" actual it " + j + " function "  + i);
					dat.add(test+",GA,"+function+","+ iteration+","+GAiterationPercentage*100+"%,"+PSOiterationPercentage*100+"%,"+iteration*GAiterationPercentage+","+iteration*PSOiterationPercentage+","+j+",GA,"+ga.getGeneration()+","+k+","+ga.ChromosomeToDecimalValue(ga.getPopulation()[k])+","+ga.getFitness(ga.getPopulation()[k])+"\n");
				}
				ga.Evolve();
			}
			
			for(int k = 0; k < ga.getPopulationSize(); k++)
			{
				System.out.println("writing to file finishing GA and Starting PSO");
				dat.add(test+",GA,"+function+","+ iteration+","+GAiterationPercentage*100+"%,"+PSOiterationPercentage*100+"%,"+iteration*GAiterationPercentage+","+iteration*PSOiterationPercentage+","+iteration*GAiterationPercentage+",GA,"+ga.getGeneration()+","+k+","+ga.ChromosomeToDecimalValue(ga.getPopulation()[k])+","+ga.getFitness(ga.getPopulation()[k])+"\n");
			}
			
			System.out.println("moving to swarm");
			Functions function2 = new Functions(function.getFunctionNumber(), ga.ChromosomeToDecimalValue(ga.getBestChromosome())-2, ga.ChromosomeToDecimalValue(ga.getBestChromosome())+2, -1);
						
			Swarm swarm = new Swarm(PSOPopulation, function2, PSOInertia, PSOCognitive, PSOSocial);
			
			for(int j = 0; j < iteration*PSOiterationPercentage; j++)
			{
				System.out.println("Updating Velocities");
				for(int k = 0; k < swarm.getParticles().length; k++)
				{
					System.out.println("writing to file GAit "+k +" actual it " + j + " function "  + i);
					dat.add(test+",GA,"+function+","+ iteration+","+GAiterationPercentage*100+"%,"+PSOiterationPercentage*100+"%,"+iteration*GAiterationPercentage+","+iteration*PSOiterationPercentage+","+j+",PSO,"+swarm.getEpoch()+","+k+","+swarm.getParticles()[k].getPosition().getX()+","+swarm.getParticles()[k].getPosition().getY()+"\n");
				}
				swarm.updateVelocities();
			}
			for(int k = 0; k < swarm.getParticles().length; k  ++)
			{
				System.out.println("writing to file finishing PSO completely");
				dat.add(test+",GA,"+function+","+ iteration+","+GAiterationPercentage*100+"%,"+PSOiterationPercentage*100+"%,"+iteration*GAiterationPercentage+","+iteration*PSOiterationPercentage+","+iteration*PSOiterationPercentage+",PSO,"+swarm.getEpoch()+","+k+","+swarm.getParticles()[k].getPosition().getX()+","+swarm.getParticles()[k].getPosition().getY()+"\n");
			}
			
			GAiterationPercentage -= .2;
			PSOiterationPercentage += .2;
		}
	}
	/*
	public static void PSOfirstGA(int iteration, Functions function, int test) throws Exception
	{
		double GAiterationPercentage = 0.0;
		double PSOiterationPercentage = 1.0;
		
		double [] globalMinumums = {0.6351, 1.5489, 0.1428, 0};
		
		for(int i = 0; i < 4; i++)
		{
			
			Swarm swarm = new Swarm(PSOPopulation, function, PSOInertia, PSOCognitive, PSOSocial);
			
			for(int j = 0; j < iteration*PSOiterationPercentage; j++)
			{
				for(int k = 0; k < swarm.getParticles().length; k++)
				{
					System.out.println("writing to file PSOit "+k +" actual it " + j + " function "  + i);
					data+=(test+",PSO,"+function+","+ iteration+","+GAiterationPercentage*100+"%,"+PSOiterationPercentage*100+"%,"+iteration*GAiterationPercentage+","+iteration*PSOiterationPercentage+","+j+",PSO,"+swarm.getEpoch()+","+k+","+swarm.getParticles()[k].getPosition().getX()+","+swarm.getParticles()[k].getPosition().getY()+"\n");
				}
				swarm.updateVelocities();
			}
			
			for(int k = 0; k < swarm.getParticles().length; k  ++)
			{
				System.out.println("writing to file finishing PSO and moving to GA");
				data+=(test+",GA,"+function+","+ iteration+","+GAiterationPercentage*100+"%,"+PSOiterationPercentage*100+"%,"+iteration*GAiterationPercentage+","+iteration*PSOiterationPercentage+","+iteration*PSOiterationPercentage+",PSO,"+swarm.getEpoch()+","+k+","+swarm.getParticles()[k].getPosition().getX()+","+swarm.getParticles()[k].getPosition().getY()+"\n");
			}
			
			Functions function2 = new Functions(function.getFunctionNumber(), swarm.getBestPosition().getX()-2, swarm.getBestPosition().getX()+2, -1);

			FunctionGA ga = new FunctionGA(GAPopulation, MutationRate, function2);
			
			for(int j = 0; j < iteration*GAiterationPercentage; j++)
			{
				for(int k = 0; k < ga.getPopulationSize(); k++)
				{
					System.out.println("writing to file GAit "+k +" actual it " + j + " function "  + i);
					data+=(test+",GA,"+function+","+ iteration+","+GAiterationPercentage*100+"%,"+PSOiterationPercentage*100+"%,"+iteration*GAiterationPercentage+","+iteration*PSOiterationPercentage+","+j+",GA,"+ga.getGeneration()+","+k+","+ga.ChromosomeToDecimalValue(ga.getPopulation()[k])+","+ga.getFitness(ga.getPopulation()[k])+"\n");
				}

				ga.Evolve();
			}
						
			for(int k = 0; k < ga.getPopulationSize(); k++)
			{
				System.out.println("writing to file finishing GA");
				data+=(test+",GA,"+function+","+ iteration+","+GAiterationPercentage*100+"%,"+PSOiterationPercentage*100+"%,"+iteration*GAiterationPercentage+","+iteration*PSOiterationPercentage+","+iteration*GAiterationPercentage+",GA,"+ga.getGeneration()+","+k+","+ga.ChromosomeToDecimalValue(ga.getPopulation()[k])+","+ga.getFitness(ga.getPopulation()[k])+"\n");
			}
			
			GAiterationPercentage += .2;
			PSOiterationPercentage -= .2;
		}
	}
	*/

}
