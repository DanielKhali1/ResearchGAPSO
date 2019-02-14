package Function;

public class Functions
{    
    public static double firstFunction(double x)
    {
        if(x < 0)
        {
            return Integer.MAX_VALUE;
        }
        else
            return Math.pow(x, (x+5)/4)+Math.cos(9*x*x-Math.sin(x));
    }
    
    public static double secondFunction(double x)
    {
        /*if(x < -3 || x > 3)
        {
            System.out.println("ERROR OUT OF BOUNDS");
            return Integer.MAX_VALUE;
        }
        else*/
            return (Math.sin(10 * Math.PI * x)/2*x)+Math.pow((x-1), 4);
    }
    
    public static double thirdFunction(double x)
    {
        /*if(x < -3 || x > 3)
        {
            System.out.println("ERROR OUT OF BOUNDS");
            return Integer.MAX_VALUE;
        }
        else*/
            return (x*x)+Math.pow(Math.sin(8*x-9), 4);
    }
    
    public static double fourthFunction(double x)
    {
        /*if(x < -3 || x > 3)
        {
            System.out.println("ERROR OUT OF BOUNDS");
            return Integer.MAX_VALUE;
        }
        else*/
            return x*x + -1* Math.cos(Math.pow((4*x),2));
    }
}