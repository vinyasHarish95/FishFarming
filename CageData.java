/** A class to store daily food amounts for our fish farmer.  
 * <li> The cage designation, month and array of doubles that is supplied to the object is stored. The food sum is also an attribute.
 * @author Vinyas Harish 
 *@version 1.0
 */

public class CageData {
	// All attributes used must be private. 
	private String cageName;
	private String monthName; 
	private double[] cageFoodArray; 
	private double runningSum;  

	/**  
	 * Full parameter constructor 
	 * @param cageName The designation of the cage, as a string
	 * @param month  The month in which the data is collected, as a string 
	 * @param data   The weight of feed used in kg each day of a month, as an array of doubles
	 * @throws CageDataException  If the arguments supplied are illegal. 
	 */
	//No public mutators!
	public CageData(String cageNm, String month, double[] data) throws CageDataException{
		setCageName(cageNm);  
		setCageData(month,data);
		setMonth(month);
		runningSum = this.getFoodSum();
	} // End CageData constructor 

	/** 
	 * Sets the month that the data was recorded. 
	 * @param month The calendar month
	 * @throws CageDataException if the month is not one of the 12 months in the Gregorian calendar. 
	 */
	private void setMonth (String month) throws CageDataException{
		String[] possibleMonths = {"January","February","March","April","May","June","July","August",
				"September", "October", "November", "December"};
		boolean validMonth = false; 
		int i = 0;
		while (validMonth == false && i < 11){
			if (possibleMonths[i] == month){
				validMonth = true;
			}	
			i++;
		} //End if-else block signifying check each element
		// End while loop that checks the provided month with each of the 12 possible months 

		if (validMonth == false){
			throw new CageDataException("Illegal month provided: " + month); 
		}else if (validMonth == true){
			monthName = month;   
		}//End if-else block checking if the month is valid and setting it if it is. 
	} //End setMonth mutator

	/**
	 * Sets the cage name.
	 * @param cageName A string containing a letter (A or B) and numbers 10 to 99 inclusive
	 * @throws CageDataException if the cage name doesn't not match the conditions above
	 */
	//Note that the first check is the length of the cage name if it is not exactly 3 characters we do not have to check anything else.
	private void setCageName (String cageNm) throws CageDataException{
		if (cageNm.length() !=3 ){
			throw new CageDataException("Illegal cage name: not exactly three characters!");
		}if (cageNm.charAt(0) != 'A' && cageNm.charAt(0) != 'B'){
			throw new CageDataException("Illegal cage letter!");  
		}if (!(cageNm.matches(".{1}([1-9])([0-9])"))){
			throw new CageDataException("Illegal cage number!"); //A hashtag would be recognized here.
		} // Ends the if-block that tests if the cageName string is appropriate 
		cageName = cageNm; 		
	} // Ends the setCageName mutator.

	/**
	 * Sets the values of food eaten each day in the cage data object. To do so, the number of days in the month has to match the number 
	 * of lines in the array created.
	 * Values of food have to be between 0 and 2000 kg. 
	 * @param month Any of the 12 months in the Gregorian calendar.
	 * @param cageFoodArray Read in from the file I/O method
	 * @throws CageDataException if the cage name doesn't not match the conditions above
	 */
	private void setCageData(String month, double[] cageFoodArray) throws CageDataException{
		int i = 0;
		int inputLength = cageFoodArray.length; 

		if (month == "January"||month == "March"||month == "May"||month == "July"||month == "August"
				||month == "October"||month =="December"){
			if (inputLength != 31){
				throw new CageDataException("Food amounts array wrong length!");
			}
		}else if (month == "April"||month == "June"||month == "September"||month == "November"){
			if (inputLength != 30){
				throw new CageDataException("Food amounts array wrong length!");
			}
		}else if (month == "February"){
			if (inputLength != 28){
				throw new CageDataException("Food amounts array wrong length!");
			}
		}else{
			throw new CageDataException("Illegal month name!");
		} // Checks both the month and the length of the array if they are valid 

		double [] tempArray = new double [inputLength];
		for (i=0; i<inputLength; i++)
			if ((cageFoodArray[i]>0) && (cageFoodArray[i]<2000)){ //Checks individual elements in the array 
				tempArray[i] = cageFoodArray[i];
			}else{
				throw new CageDataException("Illegal food amount: " + cageFoodArray[i]);
			} //End if-else block 
		this.cageFoodArray = tempArray.clone();
	} // End setCageData.

	/**
	 * Returns the calendar month that the data was recorded.
	 * @return The month during which the data was recorded, as a string.
	 */
	public String getMonth(){
		return monthName;  
	} // End accessor getMonth


	/**
	 * Returns the cage designation.
	 * @return The cage name as a string.
	 */
	public String getCageName(){
		return cageName;
	} // End accessor getCageName  

	/** A string representation of the cage data object.
	 * @return A String representation of the contents of the cage data object containing 
	 * the values of all the attributes.
	 */
	@Override 
	public String toString(){
		String s = "Data for cage " + cageName + " for " + monthName + ": " + runningSum + " kg total feed.";
		return s;   
	} // End toString method 

	/**
	 * Returns the sum of the fish food consumed over the interval length 
	 * @return The sum as a double, accurate to one decimal place
	 */
	public double getFoodSum(){
		int arrayLength = cageFoodArray.length;
		double runningSum = 0;
		int counter = 0;
		for (counter = 0; counter<arrayLength; counter++)
			runningSum = runningSum + cageFoodArray[counter];
		return Math.round(runningSum*10)/10;
	} //End getFoodSum method

	/**
	 * Returns all the food consumed by the fish in a given cage each day over a month.
	 * @return The food weights in the object, in kg.
	 */
	public double[] getFoodAmounts(){
		return cageFoodArray.clone();
	} // End getFoodAmounds method

	/**
	 * Tests two CageData objects for equality.
	 * @return <code> true <code> if the cage name and month are identical, <code> false <code>
	 * if otherwise.
	 */
	public boolean equals(CageData otherCage){
		if (otherCage instanceof CageData){
			if (cageName == otherCage.cageName){
				if (monthName == otherCage.monthName){
					return true;
				} //End month name check
			} //End cage designation check 
		} //End if block executed if the new object is a cage data object
		return false;
	} // End equals method 

	/**
	 * Compares the food sums for two Cage Data objects.
	 * @param otherCageData The other CageData object.
	 * @return A negative <code> double <code> if the supplied object had a greater sum, zero if they were the same
	 * and a positive number if the current object has the greater food sum. 
	 */
	public int compareTo(CageData otherCageData){
		int comparedFoodValue = (int) Math.round((runningSum-otherCageData.runningSum)*10)/10;
		return comparedFoodValue;
	} // End compareTo method

	/**
	 * Returns a deep copy of the current cage data object.
	 * @return  A clone (deep copy) of the current object
	 */
	// Overrides the clone method in the Object class.  
	@Override
	public CageData clone(){
		CageData deepCopyCageData = null;
		try {
			deepCopyCageData = new CageData(cageName,monthName, cageFoodArray);
		} catch (CageDataException e) {
			return null;
		} 
		return deepCopyCageData; 
	} // End clone method

} //End Class CageData 
