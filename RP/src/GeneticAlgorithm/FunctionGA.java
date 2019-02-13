package GeneticAlgorithm;
import Function.Functions;

public class FunctionGA extends GeneticAlgorithm 
{
	int function;
	
	public FunctionGA(int populationSize, double mutationRate, int function)
	{
		super(populationSize, mutationRate);
		this.function = function;
	}

	@Override
	public double getFitness(String chromosome) 
	{
		double fitness = 0;
		switch(function)
		{
			case 1: fitness = Functions.firstFunction(ChromosomeToDecimalValue(chromosome));
				break;
			case 2: fitness = Functions.secondFunction(ChromosomeToDecimalValue(chromosome));
				break;
			case 3: fitness = Functions.thirdFunction(ChromosomeToDecimalValue(chromosome));
				break;
			case 4: fitness = Functions.fourthFunction(ChromosomeToDecimalValue(chromosome));
				break;
		}
		return fitness;
	}

	@Override
	public String Crossover(String parent1, String parent2) 
	{
    	int crossOverPoint = rand.nextInt(parent1.length()-1);
    	
    	String tempParent1 = parent1.substring(0, parent1.indexOf(".")) + parent1.substring(parent1.indexOf(".")+1);
    	String tempParent2 = parent2.substring(0,  parent2.indexOf(".")) + parent2.substring(parent2.indexOf(".")+1);
    	
    	String finishedCrossOver = tempParent1.substring(0, crossOverPoint) + tempParent2.substring(crossOverPoint);
    	
    	return finishedCrossOver.substring(0, 4) + "." + finishedCrossOver.substring(4);
	}

	@Override
	public String Mutate(String chromosome) 
	{
		String offspring = chromosome;
		for(int i = 0; i < chromosome.length(); i++)
		{
			if(i == 4)
			{
				continue;
			}
			else if(rand.nextDouble() <= mutationRate)
			{
				int randomNumber = (int)Math.round(Math.random());
				
				if(randomNumber == 0) 
				{
					offspring = offspring.substring(0, i) + randomNumber + offspring.substring(i+1);
					i++;
				}
				else if(randomNumber == 1)
				{
					offspring = offspring.substring(0, i) + randomNumber + offspring.substring(i+1);
					i++;
				}
			}
		}
		return offspring;
	}

	@Override
	public void generatePopulation() 
	{
		population = new String[populationSize];
		for(int i = 0; i < population.length; i++)
		{
			String newIndividual = "";
			for(int j = 0; j < 16; j++)//fix this line
			{
				if(j == 3)
					newIndividual += ".";
				newIndividual += Math.round(Math.random());
			}
			population[i] = newIndividual;
		}
	}
	
	@Override
	public String toString()
	{
		return "Generation: " + generation + " x = " + ChromosomeToDecimalValue(getBestChromosome()) + " y = " + getFitness(getBestChromosome());
	}
	
	public double ChromosomeToDecimalValue(String chromosome)
	{
    	double answer = 0;
    	
    	answer += Integer.parseInt(chromosome.substring(0, chromosome.indexOf('.')),2);
    	answer += .001 * Integer.parseInt(chromosome.substring(chromosome.indexOf('.')+1),2);
    	answer -= 3;
    	if(answer > 3)
    		return 3;
    	if(answer < -3)
    		return -3;
    	return answer;
	}
	
	public String getBestChromosome()
	{
    	String theBestOne = population[0];
    	for(int i = 1; i < population.length; i++)
    	{
    		if(getFitness(population[i]) < getFitness(theBestOne))
    			theBestOne = population[i];
    	}
    	
    	return theBestOne;
	}
}
