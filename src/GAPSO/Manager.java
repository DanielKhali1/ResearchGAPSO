package GAPSO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Manager 
{
	
	final static double PSOInertia = 0.729844;
	final static double PSOCognitive = 1.496180;
	final static double PSOSocial = 1.496180;
	final static int population = 20;
	final static double mutationRate = 0.1;
	final static double highRange = 5.12;
	final static double lowRange = -5.12;
	final static int inputs = 2;
	final static int totalIterations = 100;
	final static int repititions = 100;
	
	public static ArrayList<String> data = new ArrayList<String>();
	public static double[][] rawData = new double[4][repititions];
	
	public static void main(String[] args)
	{
		//public gaswarm(OA algorithm, double highRange, double lowRange, int populationSize, double mutationRate, double socialComponent, double cognitiveComponent, double inertia) {
		data.add("Iteration,GA,PSO,PSO-GA,GA-PSO\n");
		
		for(int k = 0; k < repititions; k++)
		{
			
			
			gaswarm psoga = new gaswarm(OA.PSO, highRange, lowRange, population, mutationRate, PSOSocial,PSOCognitive,PSOInertia, inputs);
			for(int i = 0; i < totalIterations*.5; i++)
			{
				psoga.step();
			}
			psoga.transferSwitch();		
			
			for(int i = 0; i < totalIterations*.5; i++)
				psoga.step();
	
			gaswarm gapso = new gaswarm(OA.GA, highRange, lowRange, population, mutationRate, PSOSocial,PSOCognitive,PSOInertia, inputs);
			for(int i = 0; i < totalIterations*.5; i++)
				gapso.step();
			
			psoga.transferSwitch();		
			
			for(int i = 0; i < totalIterations*.5; i++)
				gapso.step();
	
			gaswarm ga = new gaswarm(OA.GA,  highRange, lowRange, population, mutationRate, PSOSocial,PSOCognitive,PSOInertia, inputs);
			for(int i = 0; i < totalIterations; i++)
				ga.step();
			gaswarm pso = new gaswarm(OA.PSO,  highRange, lowRange, population, mutationRate, PSOSocial,PSOCognitive,PSOInertia, inputs);
			for(int i = 0; i < totalIterations; i++)
				pso.step();
			
			for(int i = 0; i < repititions; i++)
			{
						rawData[0][i] += ga.getData().get(i);
						rawData[1][i] += pso.getData().get(i);
						rawData[2][i] += psoga.getData().get(i);
						rawData[3][i] += gapso.getData().get(i);
			}
		}
		
		
		for(int i = 0 ; i < rawData[0].length; i++)
		{
			data.add(i+1 + "," +rawData[0][i]/repititions + "," + rawData[1][i]/repititions+ "," + rawData[2][i]/repititions + "," + rawData[3][i]/repititions+"\n");
		}
		
		try {
			saveData("data.csv");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void saveData(String filename) throws Exception
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
}
