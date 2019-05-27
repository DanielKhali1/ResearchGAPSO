package GAPSO;

import java.util.Arrays;

public class Vector 
{
	private double[] dimensions;
	 private double limit = Double.MAX_VALUE;
	
	Vector()
	{
		set(new double[2]);
		this.getDimensions()[0] = 0;
		this.getDimensions()[1] = 0;
	}
	
	Vector(double x)
	{
		set(new double[2]);
		this.getDimensions()[0] = x;
		this.getDimensions()[1] = 0;
	}
	 
	Vector(double x, double y)
	{
		set(new double[2]);
		this.getDimensions()[0] = x;
		this.getDimensions()[1] = y;
	}
	
	Vector(double[] dimensionValues)
	{
		this.set(dimensionValues);
	}
	
	public void add(Vector v)
	{
		for(int i = 0; i < v.dimensions.length; i++)
		{
			dimensions[i] += v.getDimensions()[i];
		}
		limit();
	}
	
	public void sub(Vector v)
	{
		for(int i = 0; i < v.dimensions.length; i++)
		{
			dimensions[i] -= v.getDimensions()[i];
		}
		limit();

	}
	

    void mul (double s) 
    {
		for(int i = 0; i < dimensions.length; i++)
		{
			dimensions[i] *= s;
		}
        limit();
    }

    void div (double s) {
		for(int i = 0; i < dimensions.length; i++)
		{
			dimensions[i] /= s;
		}
        limit();
    }

    void normalize () {
        double m = mag();
        if (m > 0) 
        {	
    		for(int i = 0; i < dimensions.length; i++)
    		{
    			dimensions[i] /= m;
    		}
        }
    }

    private double mag () 
    {
    	double sum = 0;
		for(int i = 0; i < dimensions.length; i++)
		{
			sum += dimensions[i] * dimensions[i];
		}
        return Math.sqrt(sum);
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
    		for(int i = 0; i < dimensions.length; i++)
    		{
    			dimensions[i] /= ratio;
    		}
        }
    }

    public Vector clone () 
    {
        return new Vector(dimensions.clone());
    }

    
    public void set(double[] dimensions)
    {
    	this.dimensions = dimensions;
    }
	
	public String toString()
	{
		String returnString = "";
		for(int i = 0; i < dimensions.length; i++)
		{
			returnString += dimensions[i] + " ";
		}
		
		return returnString;
	}

	public double[] getDimensions() {return dimensions;}

}
