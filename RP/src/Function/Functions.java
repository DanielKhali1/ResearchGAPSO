package Function;

public class Functions
{
	
	double x;
	int functionNumber;
	
	double lowRange, highRange;
	
	boolean setSize;
	
	public Functions(int functionNumber, int x)
	{
		this.x = x;
		setSize = false;
		this.functionNumber = functionNumber;
		
		switch(functionNumber)
		{
		case 1 : highRange = 5;
					lowRange = 0;
			break;
		case 2: highRange = 3;
    			lowRange = -1;
			break;
		case 3: highRange = 3;
				lowRange = -3;
			break;
		case 4: highRange = 3;
				lowRange = -3;
			break;
		}
		
	}
	
	public Functions(int functionNumber, double lowRange, double highRange, int x)
	{
		this.x = x;
		
		setSize = true;

		this.lowRange = lowRange;
		this.highRange = highRange;
		this.functionNumber = functionNumber;
	}
	
	public double getLowRange()
	{
		return lowRange;
	}
	public double getHighRange()
	{
		return highRange;
	}
	
	public int getFunctionNumber()
	{
		return functionNumber;
	}
	
	public void setX(double x)
	{
		this.x = x;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		double ret = 0;
		switch(functionNumber)
		{
		case 1 : ret = firstFunction(x);
			break;
		case 2: ret = secondFunction(x);
			break;
		case 3: ret = thirdFunction(x);
			break;
		case 4: ret = fourthFunction(x);
			break;
		}
		return ret;
	}
	
    public double firstFunction(double x)
    {
    	if(!setSize)
    	{
    		highRange = 5;
    		lowRange = 0;
    	}
    	
        return Math.pow(x, (x+5)/4)+Math.cos(9*x*x-Math.sin(x));
    }
    
    public double secondFunction(double x)
    {
    	if(!setSize)
    	{
        	highRange = 3;
        	lowRange = -1;
    	}

    	
        return (Math.sin(10 * Math.PI * x)/2*x)+Math.pow((x-1), 4);
    }
    
    public double thirdFunction(double x)
    {
    	if(!setSize)
    	{
        	highRange = 3;
    		lowRange = -3;
    	}


            return (x*x)+Math.pow(Math.sin(8*x-9), 4);
    }
    
    public double fourthFunction(double x)
    {
    	if(!setSize)
    	{
        	highRange = 3;
    		lowRange = -3;
    	}

            return x*x + -1* Math.cos(Math.pow((4*x),2));
    }
    
    
    public static double Function1(double x)
    {

            return Math.pow(x, (x+5)/4)+Math.cos(9*x*x-Math.sin(x));
    }
    
    public static double Function2(double x)
    {

            return (Math.sin(10 * Math.PI * x)/2*x)+Math.pow((x-1), 4);
    }
    
    public static double Function3(double x)
    {
 
        
            return (x*x)+Math.pow(Math.sin(8*x-9), 4);
    }
    
    public static double Function4(double x)
    {

        
            return x*x + -1* Math.cos(Math.pow((4*x),2));
    }
}