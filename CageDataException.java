/**
 * An exception thrown if the parameters in the cage data object are not legal.  
 * <ul> 
 * <li> The cage designation is a three letter character code that must start with 'A' or 'B', the next two digits must be between 10 and 99 inclusive
 * <li> The month must be one of the 12 months in the Gregorian calendar, no abbreviations allowed! 
 * <li> The data is supplied to CageData as an array of doubles, individual values must lie between 0 to 2000 kg inclusive, accurate to a tenth of a kg
 *      The number of values in the array must match the number of days per month (no leap years!) 
 * </ul>     
 *      
 * @author Vinyas Harish
 *@version 1.0
 */

public class CageDataException extends Exception {
	/**
	 * Supplies a default message.  
	 */
	public CageDataException() {
		super("Illegal parameter value supplied to CageData object.");
	}

	/**
	 * Passes along the message supplied to the exception.
	 * @param message A, which is more telling of the type of error that has occurred. 
	 */
	public CageDataException(String message) {
		super(message);
	}

} // end IllegalCageDataException 


