package ParticleSwarm;
import java.util.Random;

import Function.Functions;

public class Swarm 
{
	
    private int numOfParticles, epochs;
    private double inertia, cognitiveComponent, socialComponent;
    Vector bestPosition;
    private double bestEval;
    
    private double beginRange, endRange;
	private Functions function;
    Particle[] particles;

    public Swarm (int particles1, Functions function, double inertia, double cognitiveComponent, double socialComponent) {
        this.numOfParticles = particles1;
		this.function = function;
        double infinity = Double.POSITIVE_INFINITY;
        bestPosition = new Vector(infinity, infinity);
        bestEval = Double.POSITIVE_INFINITY;
        this.beginRange = function.getLowRange();
        this.endRange = function.getHighRange();
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
    
    public double bestPositionsY()
    {    	
		function.setX(bestPosition.getX());
		return function.getY();
    }
    
    public Particle[] getParticles() {return particles;}
    public Vector getBestPosition() {return bestPosition;}
    public int getEpoch() {return epochs;}
	

}
