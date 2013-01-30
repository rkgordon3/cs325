package assignments.paystation.domain;
/** Factory to configure GammaTown.
 
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
public class GammaTownFactory implements PayStationFactory {
  public RateStrategy createRateStrategy() {
    return new AlternatingRateStrategy( new LinearRateStrategy(),
                                        new ProgressiveRateStrategy(),
                                        new ClockBasedDecisionStrategy() );
  }
  public Receipt createReceipt( int parkingTime ) {
    return new StandardReceipt(parkingTime);
  }
  public DisplayStrategy createDisplayStrategy() {
    return new ValueDisplayStrategy();
  }
}