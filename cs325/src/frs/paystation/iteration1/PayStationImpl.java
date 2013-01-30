package frs.paystation.iteration1;

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

