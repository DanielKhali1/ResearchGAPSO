package Main;

import ParticleSwarm.*;

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
	
	public static void main(String[] args)
	{
		int numberOfIterations = 20;
		int function = 1;
		
		
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				GAfirstPSO(numberOfIterations, new Functions(i,-10, 10, -1));
				numberOfIterations += 20;
			}
			function += 1;
			numberOfIterations = 20;
			System.out.println("NEXT FUNCTION");
		}
	}
	
	public static void GAfirstPSO(int iteration, Functions function)
	{
		double GAiterationPercentage = 1.0;
		double PSOiterationPercentage = 0.0;
		
		double [] globalMinumums = {0.6351, 1.5489, 0.1428, 0};
		
		for(int i = 0; i < 4; i++)
		{
			FunctionGA geneticAlgorithm = new FunctionGA(GAPopulation, MutationRate, function);
			
			for(int j = 0; j < iteration*GAiterationPercentage; j++)
			{
				System.out.println(Arrays.toString(geneticAlgorithm.getPopulation()));
				geneticAlgorithm.Evolve();
			}
			
			System.out.println("moving to swarm");
			Functions function2 = new Functions(function.getFunctionNumber(), geneticAlgorithm.ChromosomeToDecimalValue(geneticAlgorithm.getBestChromosome())-1, geneticAlgorithm.ChromosomeToDecimalValue(geneticAlgorithm.getBestChromosome())+1, -1);
						
			Swarm swarm = new Swarm(PSOPopulation, function2, PSOInertia, PSOCognitive, PSOSocial);
			
			for(int j = 0; j < iteration*PSOiterationPercentage; j++)
			{
				System.out.println(Arrays.toString(swarm.getParticles()));
				swarm.updateVelocities();
			}
			GAiterationPercentage -= .2;
			PSOiterationPercentage += .2;
		}
	}
	
	public static void PSOfirstGA(int iteration, int function)
	{
		
	}

}
