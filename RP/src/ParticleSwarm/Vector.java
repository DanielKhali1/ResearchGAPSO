package ParticleSwarm;

public class Vector 
{
	private double x;
	private double y;
	 private double limit = Double.MAX_VALUE;
	
	Vector()
	{
		this.x = 0;
		this.y = 0;
	}
	
	Vector(double x)
	{
		this.x = x;
		this.y = 0;
	}
	 
	Vector(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public double Magnitude()
	{
		return Math.sqrt(x*x + y*y);
	}
	
	public double Direction()
	{
		return Math.atan(y/x);
	}
	
	public void add(Vector v)
	{
		x+=v.x;
		y+=v.y;
		limit();
	}
	
	public void sub(Vector v)
	{
		x-=v.x;
		y-=v.y;
		limit();

	}
	

    void mul (double s) {
        x *= s;
        y *= s;
        limit();
    }

    void div (double s) {
        x /= s;
        y /= s;
        limit();
    }

    void normalize () {
        double m = mag();
        if (m > 0) {
            x /= m;
            y /= m;
        }
    }

    private double mag () 
    {
        return Math.sqrt(x*x + y*y);
    }

    void limit (double l) 
    {
        limit = l;
        limit();
    }

    private void limit () 
    {
        double m = mag();
        if (m > limit) 
        {
            double ratio = m / limit;
            x /= ratio;
            y /= ratio;
        }
    }

    public Vector clone () 
    {
        return new Vector(x, y);
    }

    
    public void set(double x)
    {
    	this.x = x;
    }
	
	public double getX() {return x;}
	public double getY() {return y;}
	
	public String toString() {return getX() + ", " + getY();}

}
