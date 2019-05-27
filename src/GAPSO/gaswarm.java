package GAPSO;

public class gaswarm extends GAPSO
{
	public gaswarm(OA algorithm, double highRange, double lowRange, int populationSize, double mutationRate, double socialComponent, double cognitiveComponent, double inertia, int dimensions) 
	{
		super(algorithm, highRange, lowRange, populationSize, mutationRate, socialComponent, cognitiveComponent, inertia, dimensions);
	}

	public double eval(double[] x) 
	{
		//ackley's function
		//return ( -20*Math.exp(-0.2*Math.sqrt(0.5*((x[0]*x[0])+(x[1]*x[1])))) ) - (Math.exp(0.5*(Math.cos(2*Math.PI*x[0])+Math.cos(2*Math.PI*x[1])))) + Math.E + 20;
		//rastrigin function
		return 10*2 + ( (x[0]*x[0] - 10*Math.cos(2*Math.PI*x[0])) + (x[1]*x[1] - 10*Math.cos(2*Math.PI*x[1])) );
		//easom function
		//return (-1* Math.cos(x[0]))*Math.cos(x[1])*Math.exp(-1*(Math.pow((x[0]-Math.PI),2) + Math.pow((x[1]-Math.PI),2)));
	}
	
	
}
