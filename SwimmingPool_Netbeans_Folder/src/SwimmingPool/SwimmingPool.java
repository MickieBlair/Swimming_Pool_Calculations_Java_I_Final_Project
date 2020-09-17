//Mickie Blair
//Java I – CIST 2371
//Final Project - Swimming Pool Class

package SwimmingPool;

public class SwimmingPool 
{
    private double length;
    private double width;
    private double depth;
    private double fillRate;
    private double drainRate;
    private final double GAL_PER_FT3 = 7.5;  //gallons of water in a cubic foot
    private double capacity;
    
    /**
     * Constructor
     * @param length Length of Pool
     * @param width Width of Pool
     * @param depth Depth of Pool
     * @param fillRate Fill rate in gpm
     * @param drainRate Drain rate in gpm
     */
    public SwimmingPool(double length, double width, double depth, 
                        double fillRate, double drainRate)      
    {
        this.length = length;
        this.width = width;
        this.depth = depth;
        this.fillRate = fillRate;
        this.drainRate = drainRate;
        this.capacity = length * width * depth * GAL_PER_FT3;
    }
    
    //return pool's water capacity
    public double getPoolCapacity()
        {
            return capacity;
        }
    
    /**
     * 
     * @return Max time to fill
     */
    public double getMaxTimeToFill()
    {
        return (capacity/fillRate)/60;
    }
    
    /**
     * 
     * @return Max Time to Drain
     */
    public double getMaxTimeToDrain()
    {
        return (capacity/drainRate)/60;
    }
    
    /**
     * Calculate the gallons of water needed to adjust fill percentage
     * @param current Current Percentage Full of Pool
     * @param target Target Percentage Full of Pool
     * @return Absolute value of water needed to adjust the fill level
     */
    public double calcGallonsofWater(double current, double target)
        {
            return Math.abs(((target - current)/100) * capacity);
        }

    /**
     * Calculate Time to Fill
     * @param needed Gallons to add to adjust the level
     * @return Hours to Fill
     */
    public double calcTimeToFill(double needed)
        {
            return (needed/fillRate)/60;
        }
        
    /**
     * Calculate Time To Drain
     * @param remove Gallons to drain to adjust the level
     * @return drain time
     */
    public double calcTimeToDrain(double remove)
        {
            return (remove/drainRate)/60;
        } 
    
    /**
     * Calculate Gallons added during filling time
     * @param fillTime hours the user would like to run water
     * @return Gallons added in the time inputted
     */
    public double calcGallonsFill(double fillTime)
    {
        return (fillRate* 60) * fillTime;
    }
    
     /**
     * Calculate Gallons removed during drain time
     * @param drainTime hours the user would like to run water
     * @return Gallons added in the time inputted
     */
    public double calcGallonsDrain(double drainTime)
    {
        return (drainRate* 60) * drainTime;
    }

    /**
     * 
     * @param percentFull Percentage Full
     * @return  Gallons of water in the pool
     */
    public double getGallonsInPool(double percentFull)
        {
            return (percentFull/100) * capacity;
        }
    
    //to String
    public String toString ()
    {
        
        String str = String.format("Pool Information\n\n" 
        + "Pool Length:\t\t%8.1f feet\n" 
        + "Pool Width:\t\t%8.1f feet\n"
        + "Pool Average Depth:\t%8.1f feet\n"
        + "Rate of Fill:\t\t%8.1f gallons per minute\n"
        + "Drain Rate:\t\t%8.1f gallons per minute\n"
        + "Pool Capacity:\t\t%8.1f gallons", length, width, depth, fillRate, 
                                                drainRate, capacity);
       return str;
    }
}
