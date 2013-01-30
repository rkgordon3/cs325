package assignments.paystation.domain;
/** The factory for creating the objects that configure
    a pay station for the particular town to operate in.
 
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

public interface PayStationFactory {
  /** Create an instance of the rate strategy to use. */
  public RateStrategy createRateStrategy();

  /** Create an instance of the receipt.
   * @param the number of minutes parking time the receipt is valid for. */
  public Receipt createReceipt( int parkingTime );

  /** Create instance of DisplayStrategy */
  public DisplayStrategy createDisplayStrategy();
}
