package frs.paystation.iteration8;

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
    ps.addPayment(  PayStationImpl.NICKEL );
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

  /** 
   * Verify that illegal coin values are rejected.
  */
  @Test(expected=IllegalCoinException.class)
  public void shouldRejectIllegalCoin() throws IllegalCoinException {
    ps.addPayment(17);
  }
  
  @Test
  public void shouldDisplay14MinFor10And25Cents()
          throws IllegalCoinException {
    ps.addPayment( PayStationImpl.DIME );
    ps.addPayment(  PayStationImpl.QUARTER  );
    assertEquals( "Should display 14 min for 10+25 cents",
                  (PayStationImpl.DIME + PayStationImpl.QUARTER ) / PayStationImpl.NICKEL * PayStationImpl.TIME_PER_5CENT, ps.readDisplay() );
  }
  
  /**
   * Buy should return a valid receipt of the 
   * proper amount of parking time
  */
  @Test 
  public void shouldReturnCorrectReceiptWhenBuy() 
          throws IllegalCoinException {
    ps.addPayment(PayStationImpl.NICKEL);
    ps.addPayment(PayStationImpl.DIME);
    ps.addPayment(PayStationImpl.QUARTER);
    Receipt receipt;
    receipt = ps.buy();
    assertNotNull( "Receipt reference cannot be null",
                   receipt );
    assertEquals( "Receipt value must be 16 min.",
      (PayStationImpl.NICKEL+PayStationImpl.DIME+PayStationImpl.QUARTER) / 
         PayStationImpl.NICKEL * PayStationImpl.TIME_PER_5CENT , receipt.value() );
  }
  
  @Test 
  public void shouldStoreTimeInReceipt() {
    Receipt receipt = new ReceiptImpl(30);
    assertEquals( "Receipt can store 30 minute value",
                  30, receipt.value() );
  }
  
  @Test 
  public void shouldReturnReceiptWhenBuy100c() 
    throws IllegalCoinException {
    ps.addPayment(PayStationImpl.DIME);
    ps.addPayment(PayStationImpl.DIME);
    ps.addPayment(PayStationImpl.DIME);
    ps.addPayment(PayStationImpl.DIME);
    ps.addPayment(PayStationImpl.DIME);
    ps.addPayment(PayStationImpl.QUARTER);
    ps.addPayment(PayStationImpl.QUARTER);

    Receipt receipt;
    receipt = ps.buy();
    assertEquals(100 / PayStationImpl.NICKEL * PayStationImpl.TIME_PER_5CENT , receipt.value() );
  }
  
  @Test 
  public void shouldClearAfterBuy() 
          throws IllegalCoinException {
    ps.addPayment(PayStationImpl.QUARTER);
    ps.buy(); 
    assertEquals( "Display should have been cleared",
                  0 , ps.readDisplay() );
       ps.addPayment(PayStationImpl.DIME); 
    ps.addPayment(PayStationImpl.QUARTER);
    assertEquals( "Next add payment should display correct time",
          (10+25) / PayStationImpl.NICKEL 
             * PayStationImpl.TIME_PER_5CENT, ps.readDisplay() );
    Receipt r = ps.buy();
    assertEquals( "Next buy should return valid receipt",
          (10+25) / PayStationImpl.NICKEL 
             * PayStationImpl.TIME_PER_5CENT, r.value() );
    assertEquals( "Again, display should be cleared",
                  0 , ps.readDisplay() );
  }

}
