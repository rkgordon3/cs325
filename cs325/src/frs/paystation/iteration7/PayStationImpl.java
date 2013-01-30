package frs.paystation.iteration7;

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
	public static final int DIME = 10;
	public static final int TIME_PER_5CENT = 2;
	
  private int totalInserted;
private int timeBought;
  public void addPayment( int coinValue ) 
          throws IllegalCoinException {
    switch ( coinValue ) {
    case NICKEL: break;
    case QUARTER: break; 
    case DIME: break;
    default: 
      throw new IllegalCoinException("Invalid coin: "+coinValue);
    }
   
    totalInserted += coinValue;
    timeBought = totalInserted / NICKEL * TIME_PER_5CENT;
  }
  public int readDisplay() {
	    return timeBought;
  }
  public Receipt buy() {
	 return new ReceiptImpl(timeBought);
  }
  public void cancel() {
  }
}

