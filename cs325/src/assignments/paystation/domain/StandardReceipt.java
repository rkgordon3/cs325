package assignments.paystation.domain;

import java.io.*;
import java.util.*;

/** Standard implementation of Receipt.
 
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

public class StandardReceipt implements Receipt {
  private int value;
  private boolean withBarCode;
  /** Make a receipt.
   * @param value the minute value of this receipt
   * @param withBarCode if true then a (fake) bar code is added
   */
  public StandardReceipt(int value, boolean withBarCode) { 
    this.value = value; 
    this.withBarCode = withBarCode;
  }
  public StandardReceipt(int value) {
    this(value, false);
  }
  public int value() { return value;}
  public void print(PrintStream stream) {
    String valuestring = ""+value;
    if ( valuestring.length() == 1 ) { valuestring = "00"+valuestring; }
    if ( valuestring.length() == 2 ) { valuestring = "0"+valuestring; }
    Calendar now = GregorianCalendar.getInstance();
    String hour = ""+now.get(Calendar.HOUR_OF_DAY);
    if ( hour.length() == 1 ) { hour = "0"+hour; }
    String min = ""+now.get(Calendar.MINUTE);
    if ( min.length() == 1 ) { min = "0"+min; }
    String nowstring = hour+":"+min;

    stream.println("-------------------------------------------------");
    stream.println("-------  P A R K I N G   R E C E I P T    -------");
    stream.println("                Value "+valuestring 
                   +" minutes.               ");
    stream.println("              Car parked at "+nowstring);
    if ( withBarCode ) {
      stream.println("||  ||||| | || ||| || ||  ||| | || |||| | || ||||");
    }
    stream.println("-------------------------------------------------");
  }   
}
