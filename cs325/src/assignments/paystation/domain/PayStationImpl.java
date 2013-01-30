package assignments.paystation.domain;

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
  private int totalInserted;
  private int timeBought;

  /** the strategy for rate calculations */
  private RateStrategy rateStrategy;
  /** the strategy for display output */
  private DisplayStrategy displayStrategy;
  /** the factory that defines strategies */
  private PayStationFactory factory;

  
  /** Construct a pay station.
      @param factory the factory to produce strategies and receipts
  */
  public PayStationImpl( PayStationFactory factory ) {
    this.factory = factory;
    this.rateStrategy = factory.createRateStrategy();
    this.displayStrategy = factory.createDisplayStrategy();
    reset();
  }

  public void addPayment( int coinValue ) 
    throws IllegalCoinException {
    switch ( coinValue ) {
    case 5: break;
    case 10: break;  
    case 25: break;  
    default: 
      throw new IllegalCoinException("Invalid coin: "+coinValue);
    }
    totalInserted += coinValue;
    timeBought = rateStrategy.calculateTime(totalInserted);
  }
  public int readDisplay() {
    return displayStrategy.calculateOutput(timeBought);
  }
  public Receipt buy() {
    Receipt r = factory.createReceipt(timeBought);
    reset();
    return r;
  }
  public void cancel() {
    reset();
  }
  private void reset() {
    timeBought = totalInserted = 0;
  }
}

