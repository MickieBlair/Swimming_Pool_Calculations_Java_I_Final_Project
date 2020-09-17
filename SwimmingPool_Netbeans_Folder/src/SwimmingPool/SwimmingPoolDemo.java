//Mickie Blair
//Java I – CIST 2371
//Final Project - Swimming Pool Class Test Program

package SwimmingPool;

import javax.swing.JOptionPane;

public class SwimmingPoolDemo 
{
    public static void main(String[] args) 
    {
        String input;
        double lengthOfPool;
        double widthOfPool;
        double averageDepth;
        double poolFillRate;
        double poolDrainRate;
        double poolCapacity;
        int menuChoice;

        //Ask the user for pool dimensions, fill rate, and drain rate
        input = JOptionPane.showInputDialog("Length of Pool in feet:");
        lengthOfPool=Double.parseDouble(input);
        
        input = JOptionPane.showInputDialog("Width of Pool in feet:");
        widthOfPool=Double.parseDouble(input);
        
        input = JOptionPane.showInputDialog("Average Depth of Pool in feet:");
        averageDepth=Double.parseDouble(input);
        
        input = JOptionPane.showInputDialog("Fill Rate in gallons per minute:");
        poolFillRate=Double.parseDouble(input);
        
        input = JOptionPane.showInputDialog("Drain Rate in gallons per minute:");
        poolDrainRate=Double.parseDouble(input);
        
        //create a new pool object
        SwimmingPool test1 = new SwimmingPool(lengthOfPool, widthOfPool,
                                                averageDepth, poolFillRate, 
                                                poolDrainRate);
        
        //menu for determining next steps
        input = JOptionPane.showInputDialog("Program Menu Options\n\n"
            + " 1. Determine the amount of water and time needed "
                    + "adjust the level in the pool.\n"
            + " 2. Add water for a specific amount of time.\n"
            + " 3. Drain water for a specific amount of time.\n\n"  
            + "Enter Menu Number:   ");
        
        menuChoice = Integer.parseInt(input);
        
        //switch for menu
        switch(menuChoice)
        {
            case 1: calcGallonsTime(test1);
                    break;
            case 2: calcUsingTimeFill(test1);
                    break;
            case 3: calcUsingTimeDrain(test1);
                    break;
        }

     System.exit(0);   
    }
    
    /**
     * Determine the amount of water and time needed for pool filling
     * and draining
     * @param test Swimming Pool test object
     * 
     */
    public static void calcGallonsTime(SwimmingPool test)
    {
        String input;               //variable for JOptionPane Input
        double currentPercent;      //percentage of water currently in 
        double targetPercent;       //target percantage of water in the pool
        double gallonsForAdjust;    //gallons needed to adjust the level
        
        //Ask the user the current percentage of water in pool
        input = JOptionPane.showInputDialog("How much water is currently in the pool?\n\n"
                                            + "Examples:\n0 for an empty pool\n"
                                            + "50 if the pool is 50% full\n"
                                            + "100 if the pool is 100% full\n\n"
                                            + "Enter current percentage full:  ");
        
        currentPercent=Double.parseDouble(input);
         
        
        //Ask the user how full they would like the pool
        input = JOptionPane.showInputDialog("How much water do you want in the pool?\n\n"
                                            + "Example:\n0 for an empty pool\n"
                                            + "50 for 50% full\n"
                                            + "100 for 100% full\n\n"
                                            + "Enter target percentage full:  ");
        
        targetPercent=Double.parseDouble(input);
        
        //calculate amount to fill
        gallonsForAdjust = test.calcGallonsofWater(currentPercent, targetPercent);

        //display pool info and results
        System.out.println(test);
        System.out.printf("\nTo adjust the pool from %.1f%% to %.1f%% "
                            + " full:\n" ,currentPercent, targetPercent);
        
        //if statements for fill or drain
        if (currentPercent<targetPercent)
        {
            System.out.printf("\nWater To Add:\t\t%8.1f gallons\n", gallonsForAdjust);
            System.out.printf("Time to Fill:\t\t%8.1f hours \n\n", 
                                    test.calcTimeToFill(gallonsForAdjust));
        }
        else if (currentPercent>targetPercent)
        {
            System.out.printf("\nWater to Drain:\t\t%8.1f gallons\n", gallonsForAdjust);
            System.out.printf("Time to Drain:\t\t%8.1f hours \n\n", 
                                    test.calcTimeToDrain(gallonsForAdjust));
        }
    }
   
   /**
    * Calculate how much water is filled in a specific amount of time
    * @param test Swimming Pool Object
    */ 
    public static void calcUsingTimeFill(SwimmingPool test)
    {
        String input;               //variable for JOptionPane Input
        double initialPercent;      //percentage of water currently in pool
        double hours;               //hours to fill or drain
        double gallonsAdded;        //gallons added
        double initialGallons;      //initial gallons in pool
        double endPercent;          //ending percent full
        
        
        //Ask the user the current percentage of water in pool
        input = JOptionPane.showInputDialog("How much water is currently in the pool?\n\n"
                                            + "Examples:\n0 for an empty pool\n"
                                            + "50 if the pool is 50% full\n"
                                            + "100 if the pool is 100% full\n\n"
                                            + "Enter current percentage full:  ");
        
        initialPercent=Double.parseDouble(input);
        
        //calculate initial gallons in pool
        initialGallons=test.getGallonsInPool(initialPercent);
        
        //Ask the user how long they would like to fill
        input = JOptionPane.showInputDialog("Enter the hours you plan on leaving\n"
                + "the water on to fill the pool:  ");
        
        hours=Double.parseDouble(input);
        
        //display results
        if (hours > test.getMaxTimeToFill())
        {
            System.out.println(test);

            System.out.printf("\nInitially (%.1f%% full), the pool has %.1f gallons of "
                              + "water. \n",initialPercent, initialGallons);
            
            System.out.println("\nThe time entered is greater than needed.");
            
            //calculate amount added 
            gallonsAdded = test.getPoolCapacity()- initialGallons ; 
            
            System.out.printf("\nThe Pool will be 100%% full in %.1f hours\n",
                                test.getMaxTimeToFill());            
            
            System.out.printf("\nThe amount added was %.1f gallons.\n", gallonsAdded);
          
        }
        
        else
        {
            System.out.println(test);
            
            System.out.printf("\nInitially (%.1f%% full), the pool has %.1f gallons of "
                              + "water. \n",initialPercent, initialGallons);
            
            //calculate amount added in time period
            gallonsAdded = test.calcGallonsFill(hours); 
            
            //calculate percent full after time period
            endPercent = ((initialGallons + gallonsAdded)/test.getPoolCapacity())*100;
            
            System.out.printf("\nDuring %.1f hours of filling, %.1f gallons "
                    + "will be added.\n", hours, gallonsAdded);
       
            System.out.printf("\nThe Pool will then be %.1f %% full.\n", endPercent);
            
        }
    }
    
    
    /**
     * Calculate how much water is removed in a specific amount of time
     * @param test Swimming Pool Object
     */
    public static void calcUsingTimeDrain(SwimmingPool test)
    {
        String input;               //variable for JOptionPane Input
        double initialPercent;      //percentage of water currently in pool
        double hours;               //hours to fill or drain
        double gallonsRemoved;      //gallons added
        double initialGallons;      //initial gallons in pool
        double endPercent;          //ending percent full
        
        
        //Ask the user the current percentage of water in pool
        input = JOptionPane.showInputDialog("How much water is currently in the pool?\n\n"
                                            + "Examples:\n0 for an empty pool\n"
                                            + "50 if the pool is 50% full\n"
                                            + "100 if the pool is 100% full\n\n"
                                            + "Enter current percentage full:  ");
        
        initialPercent=Double.parseDouble(input);
        
        //calculate initial gallons in pool
        initialGallons=test.getGallonsInPool(initialPercent);
        
        //Ask the user how long they would like to drain
        input = JOptionPane.showInputDialog("Enter the hours you plan on draining\n"
                + "the water from the pool:  ");
        
        hours=Double.parseDouble(input);
        
        //using max time to fill let user know
        if (hours > test.getMaxTimeToDrain())
        {
            System.out.println(test);
     
            System.out.printf("\nInitially (%.1f%% full), the pool has %.1f gallons of "
                              + "water. \n",initialPercent, initialGallons);
            
            System.out.println("\nThe time entered is greater than needed.");
            
            //calculate amount added 
            gallonsRemoved = initialGallons ; 
            
            System.out.printf("\nThe Pool will be empty in %.1f hours\n",
                                test.getMaxTimeToDrain());            
            
            System.out.printf("\nThe amount drained was %.1f gallons.\n", gallonsRemoved);
          
        }
        
        else
        {
            System.out.println(test);
            
            System.out.printf("\nInitially (%.1f%% full), the pool has %.1f gallons of "
                              + "water. \n",initialPercent, initialGallons);
            
            //calculate amount added in time period
            gallonsRemoved = test.calcGallonsDrain(hours); 
            
            //calculate percent full after time period
            endPercent = ((initialGallons - gallonsRemoved)/test.getPoolCapacity())*100;
            
            System.out.printf("\nDuring %.1f hours of draining, %.1f gallons "
                    + "will be removed.\n", hours, gallonsRemoved);
       
            System.out.printf("\nThe Pool will then be only %.1f %% full.\n", endPercent);
            
        }
    }
}
