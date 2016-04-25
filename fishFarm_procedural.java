//---Vinyas Harish---      
//The following program is designed to model the growth of fish in a fish farm environment.  The user shall specify certain types of input in the console such as number
//of intervals (or months) in which the fish are farmed as well as the starting number of fish, and the starting weight of the fish.  Various methods have been designed to
//parse user input such that they appropriate for future calculations.  Once all appropriate data has been entered into the console, several methods are implemented in order
//to calculate fish growth, population decline and the total amount of food needed to raise a given population of fish.  Data processing and visualization are done using one 
//2D array. 

import java.util.InputMismatchException; //Both these imports are for use in the I/O helper methods.   
import java.util.Scanner;         

public class fishFarm_procedural {
	//Begin I/O helper methods in order to parse user input.  These methods are drawn from Professor McLeod's IO Helper provided at the end of Exercise 1.  
	private static Scanner screenInput = new Scanner(System.in);
    public static int getInt(int low, String prompt, int high) {
        int numFromUser = 0;
        String dummy;
        boolean numericEntryOK;
        do {
            System.out.print(prompt);
            numericEntryOK = false;
            try {
                numFromUser = screenInput.nextInt();
                numericEntryOK = true; 
            } catch (InputMismatchException e) {
                dummy = screenInput.nextLine();
                System.out.println(dummy + " is not an integer!"); //When running the try/catch, the first test is to see if the type is valid.
                numFromUser = low;
            } // End try-catch block.
            // If the input provided by the user is a valid integer, the next section explains why the user is prompted again.
            if (numFromUser < low || numFromUser > high) {
                System.out.println("The number is not within the legal limits.");
            }
        } while (!numericEntryOK || numFromUser < low || numFromUser > high); //This while loop breaks when the user enters a valid input.
        return numFromUser;
    } //End full parameter getInt method.
    
    //The two methods below are used to call the full parameter getInt method if either a lower or upper bound is not provided. 
    public static int getInt(int low, String prompt) {
        int high = Integer.MAX_VALUE;  //To be used when there is no upper limit, in the case of the number of fish.  
        return getInt(low, prompt, high);
    } //End two parameter getInt method.

    public static int getInt(String prompt, int high) {  
        int low = Integer.MIN_VALUE; 
        return getInt(low, prompt, high);
    } //End two parameter getInt method.  
    
    //The next three methods are extremely similar to the methods above, except they work with doubles as opposed to integers.
    public static double getDouble(double low, String prompt, double high) {
        double numFromUser = 0;
        String dummy;
        boolean numericEntryOK;
        do {
            System.out.print(prompt);
            numericEntryOK = false;
            try {
                numFromUser = screenInput.nextDouble();
                numericEntryOK = true;
            } catch (InputMismatchException e) {
                dummy = screenInput.nextLine();
                System.out.println(dummy + " is not a double!"); 
                numFromUser = low;
            } //End try-catch.
            
            if (numFromUser < low || numFromUser > high) {   
                System.out.println("The number is not within the legal limits.");
            }
        } while (!numericEntryOK || numFromUser < low || numFromUser > high);  
        return numFromUser;
    } //End full parameter getDouble method.
    
    public static double getDouble(double low, String prompt) {
        double high = Double.MAX_VALUE;
        return getDouble(low, prompt, high);
    } //End two parameter getDouble method.

    public static double getDouble(String prompt, double high) {
        double low = -Double.MAX_VALUE;
        return getDouble(low, prompt, high);
    } //End two parameter getDouble method.
    //This marks the end of the methods that are used to help with user input.    
   
    public static double getFishNumber() { 
    	double fishNumber; //Because one big array of doubles shall be used for calculations, we obtain a valid integer and then parse double.
    	fishNumber = (double) getInt(0,"Enter the starting number of fish to be raised: ");//The I/O helper methods are implemented to obtain appropriate inputs.
    	return fishNumber; //In other words, the starting number of fish.
    }//End method getFishNumber.
    
    public static double getFishWeight() { //
    	double fishWeight;
    	fishWeight = getDouble(0, "Enter the starting weight of the fish (in grams):");
    	return fishWeight; //In other words, the starting weight per fish.
    }//End method getFishWeight.
   
    public static int getIntervalNumber() {
    	int intervalNumber; //Because one big array of doubles shall be used for calculations, we obtain a valid integer and then parse double.
    	intervalNumber = getInt(0, "Enter the number of intervals (in months from 0 to 100) "
    			+ "over which you want to model your fish farm:",100); 
    	return intervalNumber; //E.g. the number of rows in the array.
    }//End method getIntervalNumber.
    
    public static double[][] buildFishArray(int numRows) {
    	int numCols = 6; //This array shall be used to store all inputs as well as calculated values. 
    	int row;
    	double[][] fishArray = new double[(int) numRows][(int)numCols]; //Calculations are done by iterating through certain sections of the array.
    		for (row = 0; row < numRows; row = row + 1 )
    			fishArray[row][0] = (double) row + 1; //We use the first column to label the interval which this row represents.
    		for (row = 0; row < numRows; row = row + 1 ) {  
    			fishArray[row][1] = (double) getInt(0,"Enter the number of days in interval " + (row+1) + 
    					". This number must be between 0 and a 100 ", 100);  
    			fishArray[row][2] = getDouble(0.5,"Enter the average temperature (in degrees Celcius) during interval " + (row+1) +
    					". This number must be between 0.5 and 30 ", 30);
    		}//End collecting number of days in the interval and average temperature per interval from user. 
    	return fishArray;  
    }//End method buildFishArray. 
    
    public static void displayArrayAsTable(double[][] anArray, int numRows) { 
		int row = 0;
    	int col = 0;
    	System.out.printf("%22s","Interval Number");  
    	System.out.printf("%22s","Number of Days");
    	System.out.printf("%22s","Temperature (C)");
    	System.out.printf("%22s","Fish Weight (g)");
    	System.out.printf("%22s","Amount of Feed (kg)"); 
    	System.out.printf("%22s","Number of Fish");
    	System.out.println(); //Ensures that the rest of the array gets printed on the next line.
    	
    	for (row = 0; row < numRows; row = row + 1) { 
			for (col = 0; col < 6; col = col + 1)
				if (col == 0||col == 1||col == 5){
					System.out.printf("%22.0f", anArray[row][col]); //Certain values, e.g. interval number are shown without decimals. 
				}else{
				System.out.printf("%22.2f", anArray[row][col]); //Others, e.g. fish weight, are shown up to 2 decimal places. 
				}//End if-else block.
			System.out.println(); //Each interval (row) is printed on a different line. 
		}//End outer for-loop. 
	}//End displayArrayAsTable method.
    
    public static double[][] calcFishWeight(double startingWeight,double[][] fishArray) {
    	int row;
    	double sum = 0; 
    	double fishWeight = 0; 
    	for (row = 0; row < fishArray.length; row = row + 1) { //The summation is done through a for loop.
    		double rowSum = 0;
    		rowSum = (0.191*fishArray[row][1]*fishArray[row][2])/100.0; //The Thermal Growth Coefficient is 0.191.
    		sum = sum + rowSum;
    		fishWeight = Math.pow((Math.pow(startingWeight, (1.0/3.0)) + sum),3.0);
    		fishArray[row][3] = fishWeight; //Once the fish weight is calculated, it is stored in column 4 of the array. 
    	}//End for-loop to iterate through rows. 
    	return fishArray;
    }//End method calcFishWeight.
   
    public static double[][] calcFishNumber(double startingNumber, double[][] fishArray, int row) {
    	double fishNumber = 0;  //This method takes into account that some fish die every interval according to the mortality rate 0.022% per day.
    	if (row == 0){
    	fishNumber = startingNumber-0.022*startingNumber*fishArray[row][1]/100.0; //The if-statement is used to avoid an array out of range error. 
    	startingNumber = fishNumber;
    	fishArray[row][5] = Math.floor(fishNumber); //The calculation is floored in order to have a whole number of fish. 
    	}else{
    		fishNumber = fishArray[row-1][5]-0.022*fishArray[row-1][5]*fishArray[row][1]/100.0; //For other rows, we can simply look to the row above. 
    		fishArray[row][5] = Math.floor(fishNumber);//Once calculated, the number of fish at the end of each interval is stored in column six of the array. 
    	}//End if-else block.
    	return fishArray;
    }//End calcFishNumber method.   
    
    //The next several methods break down the calculations needed to determine the amount of food needed to raise the fish. 
    
    public static double calcMaintEnergy(int row ,double[][]fishArray) {
    	double quad = 0; //To simplify the entry of the calculation, quad is a variable to designate the quadratic portion of the equation.
        quad = 3.26*(fishArray[row][2]) - 0.05*(Math.pow((fishArray[row][2]), 2)) - 0.0104 ;
        double ME = quad*(Math.pow((fishArray[row][3]/1000.0), 0.824)) * (fishArray[row][1]);
        return ME; //The maintenance energy is now called ME for ease of use. 
    }//End method calcMaintEnergy.
    
    public static double calcRetainEnergy(int row, double[][] fishArray, double startingWeight){
    	if (row == 0){ //The if-statement is used in order to avoid an array out of range error.
    		double RE = (5.0+0.005*(fishArray[0][3]))*((fishArray[0][3])-(startingWeight));
    		return RE; //The retained energy is now called RE for ease of use.  
    	}else{
    		double RE = (5.0+0.005*(fishArray[row][3]))*((fishArray[row][3])-(fishArray[row-1][3])); //For all other rows, we can look to the row above.
    		return RE;
    	}//End else block.	
    }//End method calcRetainEnergy.
   
    public static double calcDigestEnergy(double ME, double RE){
    	double DE = (ME + RE)*0.2; 
    	return DE; //Digestion energy is now called DE for ease of use. 
    }//End method calcDigestEnergy.

    public static double calcEnergyLoss(double ME, double RE, double DE){
    	double EL = (ME + RE + DE)*0.1;
    	return EL;//Energy loss is now called EL for ease of use. 
    }//End method calcEnergyLoss.
    
    public static double calcTotalEnergy(double ME, double RE, double DE, double EL){
    	double TE = ME + RE + DE + EL;
    	return TE; //Total energy is now called TE for ease of use. 
    }//End method calcTotalEnergy.

    public static double calcFeedWeight(int row, double TE, double startingNumber, double[][] fishArray, double runningSumFeedWeight){
    	if (row == 0){
    		double feedWeight = startingNumber*(TE/18.5)/1000.0; //The first row is calculated relative to the starting number of fish.
    		fishArray[row][4] = feedWeight; //Once calculated the feed weight is stored in column five of the array. 
    		runningSumFeedWeight = 0 + feedWeight; //The running sum of the feed weight is updated with each iteration. 
    		return runningSumFeedWeight;
    	}else{
    		double feedWeight = fishArray[row-1][5]*(TE/18.5)/1000.0;  //All other rows are calculated relative to the row previous. 
    		fishArray[row][4] = feedWeight;
    		runningSumFeedWeight = runningSumFeedWeight + feedWeight; 
    		return runningSumFeedWeight; 
    	}//End if/else block.  
    }//End method calcFeedWeight.
   
    public static double[][] calcArray(double [][] fishArray, double startingWeight, double startingNumber){
    	int row;//This method does a great deal of the "heavy lifting" as it strings together all the previous methods needed to calculate feed weight.
    	double ME;//For each row in the array, the various sub-energies are calculated culminating the storage of the feed weight in the array and as a running sum.
    	double RE;
    	double DE;
    	double EL;
    	double TE;  
    	double runningSumFeedWeight = 0;
    	int lastRow = (fishArray.length)-1; //The last row of the array will be used to calculate the ratio.
    	double ratio = 0;
    	for (row = 0; row < fishArray.length; row = row + 1){ //A for loop is used to iterate through each row/interval.
    		ME = calcMaintEnergy(row,fishArray);
    		RE = calcRetainEnergy(row,fishArray, startingWeight);
    		DE = calcDigestEnergy(ME, RE);
    		EL = calcEnergyLoss(ME, RE, DE);  
    		TE = calcTotalEnergy(ME, RE, DE, EL); 
    		runningSumFeedWeight = calcFeedWeight(row,TE, startingNumber, fishArray, runningSumFeedWeight);
    		calcFishNumber(startingNumber, fishArray, row); //Finally once the amount of feed is calculated, the method accounting for fish death is invoked. 
    	}//End populating array.
    	ratio = ((fishArray[lastRow][3]-(startingWeight))/1000.0)*(fishArray[lastRow][5])/(runningSumFeedWeight); //The gain-to-feed ratio is also calculated.
    	System.out.println("\n");
    	System.out.printf("The total amount of feed needed in kg is: %.2f", runningSumFeedWeight);//The running sum of feed used and gain-to-feed ratio are printed.  
    	System.out.printf("\n The gain to feed ratio is: %3.2f", ratio);
    	System.out.println("\n");
    	return fishArray; //The array is now ready for printing. 
    }//End method calcArray. 
    
    public static void main(String[] args) {
    	System.out.println("Welcome to the fish farming modelling system! \nFollow the input instructions to "
    			+ "obtain information regarding the fish at each time interval as well as the total amount"
    			+ " of feed used \n and the gain-to-feed ratio.\n");   
    	double startingNumber = getFishNumber();  //Inputs are obtained.  
    	double startingWeight = getFishWeight();
    	System.out.println("\n");
    	int intervalNumber = getIntervalNumber();  
    	double [][]fishArray = buildFishArray(intervalNumber); //Once inputs are obtained, the array can start being built.
    	calcFishWeight(startingWeight, fishArray); //Fish weight is the first calculation done.
    	calcArray(fishArray,startingWeight,startingNumber); //All other columns in the array are populated. The gain to feed ratio and total amount of feed are printed.
    	displayArrayAsTable(fishArray, intervalNumber);    //The final array is printed.  
    }//End main method.  
} //Ends the entire class. 