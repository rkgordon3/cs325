package frs.paystation.iteration1;
import org.junit.*;

import frs.paystation.IllegalCoinException;
import frs.paystation.PayStation;
import static org.junit.Assert.*;

/** Testcases for the Pay Station system.
 

*/
public class TestPayStation {

  /**
   * Entering 5 cents should make the display report 2 minutes 
   * parking time.
  */
  @Test
  public void shouldDisplay2MinFor5Cents() 
          throws IllegalCoinException {
    PayStation ps = new PayStationImpl();
    ps.addPayment( 5 );
    assertEquals( "Should display 2 min for 5 cents", 
                  2, ps.readDisplay() ); 
  }
}
