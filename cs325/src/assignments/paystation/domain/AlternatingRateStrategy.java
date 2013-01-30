package assignments.paystation.domain;

import java.util.*;

/** A rate strategy that uses the State pattern to vary behavior
    according to the state of the system clock: a linear rate
    during weekdays and a progressive rate during weekdends.

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
public class AlternatingRateStrategy implements RateStrategy {
  private RateStrategy weekendStrategy, weekdayStrategy, currentState;
  private WeekendDecisionStrategy decisionStrategy;

  public AlternatingRateStrategy( RateStrategy weekdayStrategy,
                                  RateStrategy weekendStrategy,
                                  WeekendDecisionStrategy decisionStrategy) {
    this.weekdayStrategy = weekdayStrategy;
    this.weekendStrategy = weekendStrategy;
    this.currentState = null;
    this.decisionStrategy = decisionStrategy;
  }
  public int calculateTime( int amount ) {
    if ( decisionStrategy.isWeekend() ) {
      currentState = weekendStrategy; 
    } else {
      currentState = weekdayStrategy;
    }
    return currentState.calculateTime( amount );
  }
}

