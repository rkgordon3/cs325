package frs.paystation.iteration3;

import frs.paystation.*;

/** Implementation of the pay station.

   Responsibilities:
			
   1) Accept payment;
   2) Calculate parking time based on payment;
   3) Know earning, parking time bought;
   4) Issue receipts;
   5) Handle buy and cancel events.
 
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Computer Science Department
     Aarhus University
   
   This source code is provided WITHOUT ANY WARRANTY either 
   expressed or implied. You may study, use, modify, and 
   distribute it for non-commercial purposes. For any 
   commercial use, see http://www.baerbak.com/
*/

public class PayStationImpl implements PayStation {
	public static final int QUARTER = 25;
	public static final int NICKEL = 5;
	public static final int TIME_PER_5CENT = 2;
	
  private int totalInserted;
  public void addPayment( int coinValue ) 
          throws IllegalCoinException {
	  // Iteration 3 modification
    switch ( coinValue ) {
    case NICKEL: break;
    case QUARTER: break;  
    default: 
      throw new IllegalCoinException("Invalid coin: "+coinValue);
    }
    totalInserted = coinValue;
  }
  public int readDisplay() {
    return totalInserted / NICKEL * 2;
  }
  public Receipt buy() {
    return null;
  }
  public void cancel() {
  }
}

