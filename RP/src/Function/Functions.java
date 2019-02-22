package Function;

public class Functions
{
	
	double x;
	int functionNumber;
	
	double lowRange, highRange;
	
	public Functions(int functionNumber, double lowRange, double highRange, int x)
	{
		this.x = x;
		this.lowRange = lowRange;
		this.highRange = highRange;
		this.functionNumber = functionNumber;
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
        if(x < 0 || x < lowRange || x > highRange)
        {
            return 10;
        }
        else
            return Math.pow(x, (x+5)/4)+Math.cos(9*x*x-Math.sin(x));
    }
    
    public double secondFunction(double x)
    {
        if(x < lowRange || x > highRange)
        {
            return 10;
        }
        else
            return (Math.sin(10 * Math.PI * x)/2*x)+Math.pow((x-1), 4);
    }
    
    public double thirdFunction(double x)
    {
        if(x < lowRange || x > highRange)
        {
            return 10;
        }
        else
            return (x*x)+Math.pow(Math.sin(8*x-9), 4);
    }
    
    public double fourthFunction(double x)
    {
        if(x < lowRange || x > highRange)
        {
            return 10;
        }
        else
            return x*x + -1* Math.cos(Math.pow((4*x),2));
    }
}