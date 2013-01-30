package frs.paystation.iteration2.initial;

import frs.paystation.IllegalCoinException;
import frs.paystation.PayStation;
import frs.paystation.Receipt;

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

  public void addPayment( int coinValue ) 
    throws IllegalCoinException {
  }
  
  public int readDisplay() {
    return 2;
  }
  
  public Receipt buy() {
    return null;
  }

  public void cancel() {
  }
}

