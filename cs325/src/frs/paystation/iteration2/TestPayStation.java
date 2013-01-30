package frs.paystation.iteration2;
import frs.paystation.*;
import org.junit.*;
import static org.junit.Assert.*;

/** Testcases for the Pay Station system.
 
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
public class TestPayStation {
  PayStation ps;
  /** Fixture for pay station testing. */
  @Before
  public void setUp() {
    ps = new PayStationImpl();
  }

  /**
   * Entering 5 cents should make the display report 2 minutes 
   * parking time.
  */
  @Test
  public void shouldDisplay2MinFor5Cents() throws IllegalCoinException {
    ps.addPayment( PayStationImpl.NICKEL );
    assertEquals( "Should display 2 min for 5 cents", 
    		PayStationImpl.TIME_PER_5CENT, ps.readDisplay() ); 
  }

  /**
   * Entering 25 cents should make the display report 10 minutes
   * parking time.
  */
  @Test
  public void shouldDisplay10MinFor25Cents() throws IllegalCoinException {
    ps.addPayment( PayStationImpl.QUARTER );
    assertEquals( "Should display 10 min for 25 cents", 
      PayStationImpl.QUARTER / PayStationImpl.NICKEL * PayStationImpl.TIME_PER_5CENT, 
      ps.readDisplay() );

  }
}
