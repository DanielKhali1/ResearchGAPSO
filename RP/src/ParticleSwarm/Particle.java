package ParticleSwarm;
import java.util.Random;
import Function.Functions;

public class Particle 
{

	private Vector Position;
	private Vector Velocity;
	private Vector BestPosition;
	private double bestEval;
	private Functions function;
	
	public Particle(double beginRange, double endRange, Functions function)
	{
		Position = new Vector();
		Velocity = new Vector();
		this.function = function;
		setRandomPosition(beginRange, endRange);
		setRandomVelocity(beginRange, endRange);
		BestPosition = Velocity.clone();
		bestEval = eval();
	}
	

	
	public double eval()
	{
		function.setX(Position.getX());
		return function.getY();
	}
	
	private void setRandomPosition(double beginningRange, double endingRange)
	{
		double x = rand(beginningRange, endingRange);
		//double y = rand(beginningRange, endingRange);
		
		Position.set(x);
	}
	
	private void setRandomVelocity(double beginningRange, double endingRange)
	{
		double x = rand(beginningRange, endingRange);
		//double y = rand(beginningRange, endingRange);
		
		Velocity.set(x);
	}
	
    private static double rand (double beginRange, double endRange) {
        Random r = new java.util.Random();
        return r.nextDouble()*(endRange);
}
	
    void updatePersonalBest () {
        double eval = eval();
        if (eval < bestEval) {
            BestPosition = Position.clone();
            bestEval = eval;
        }
}

    void updatePosition () {
        this.Position.add(Velocity);
}
    Vector getVelocity () {
        return Velocity.clone();
    }

    Vector getBestPosition() {
        return BestPosition.clone();
    }

    double getBestEval () {
        return bestEval;
    }

	public void setVelocity(Vector Velocity) {this.Velocity = Velocity;}
    
	public Vector getPosition() {return Position.clone();}
	

	public void setPosition(Vector x) {Position = x;}
	public void setBestKnownPosition(Vector x) {BestPosition = x;}

	
	
}
