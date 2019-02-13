package ParticleSwarm;
import java.util.Random;

import Function.Functions;

public class Swarm 
{
	
    private int numOfParticles, epochs;
    private double inertia, cognitiveComponent, socialComponent;
    Vector bestPosition;
    private double bestEval;
    public static final double DEFAULT_INERTIA = 0.729844;
    public static final double DEFAULT_COGNITIVE = 1.496180; // Cognitive component.
    public static final double DEFAULT_SOCIAL = 1.496180; // Social component.
    
    private double beginRange, endRange;
    private static final double DEFAULT_BEGIN_RANGE = -10;
    private static final double DEFAULT_END_RANGE = 10;
    Particle[] particles;

    int function;
	
    public Swarm (int particles1, int function, double inertia, double cognitiveComponent, double socialComponent) {
        this.numOfParticles = particles1;
        this.function = function;
        double infinity = Double.POSITIVE_INFINITY;
        bestPosition = new Vector(infinity, infinity);
        bestEval = Double.POSITIVE_INFINITY;
        beginRange = DEFAULT_BEGIN_RANGE;
        endRange = DEFAULT_END_RANGE;
        particles = initialize();
        epochs = 0;
        
        this.inertia =  inertia;
        this.cognitiveComponent = cognitiveComponent;
        this.socialComponent = socialComponent;
    }
    
    private Particle[] initialize () {
        Particle[] particles = new Particle[numOfParticles];
        for (int i = 0; i < numOfParticles; i++) {
            Particle particle = new Particle(beginRange, endRange, function);
            particles[i] = particle;
            updateGlobalBest(particle);
        }
        return particles;
    }
    private void updateGlobalBest (Particle particle) {
        if (particle.getBestEval() < bestEval) {
            bestPosition = particle.getBestPosition();
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
		epochs ++;

    }

	
    private void updateVelocity (Particle particle) {
        Vector oldVelocity = particle.getVelocity();
        Vector pBest = particle.getBestPosition();
        Vector gBest = bestPosition.clone();
        Vector pos = particle.getPosition();

        Random random = new Random();
        double r1 = random.nextDouble();
        double r2 = random.nextDouble();

        // The first product of the formula.
        Vector newVelocity = oldVelocity.clone();
        newVelocity.mul(inertia);

        // The second product of the formula.
        pBest.sub(pos);
        pBest.mul(cognitiveComponent);
        pBest.mul(r1);
        newVelocity.add(pBest);

        // The third product of the formula.
        gBest.sub(pos);
        gBest.mul(socialComponent);
        gBest.mul(r2);
        newVelocity.add(gBest);


       particle.setVelocity(newVelocity);
}
    
    public double bestPositionsY()
    {    	
    	double bestY = -1;
    	
		switch(function)
		{
			case 1: bestY = Functions.firstFunction(bestPosition.getX());
				break;
			case 2: bestY = Functions.secondFunction(bestPosition.getX());
				break;
			case 3: bestY = Functions.thirdFunction(bestPosition.getX());
				break;
			case 4: bestY = Functions.fourthFunction(bestPosition.getX());
				break;
		}
		
		return bestY;
    }
    
    public Particle[] getParticles() {return particles;}
    public Vector getBestPosition() {return bestPosition;}
    public int getEpoch() {return epochs;}
	

}
