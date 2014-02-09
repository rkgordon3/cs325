package frs2.hotgammon.alphamon;

import org.junit.*;
import static org.junit.Assert.*;

/** Testing skeleton for AlphaMon.
 
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
public class TestAlphamon {
  private Game game;
  
  @Before public void setup() {
    game = new GameImpl();
    game.newGame();
  }
  
  @Test public void shouldHaveNoPlayerInTurnAfterNewGame() {
    
    assertEquals( Color.NONE, game.getPlayerInTurn() );
  }
  @Test public void shouldHaveBlackPlayerInTurnAfterNextTurn() {
    
    game.nextTurn(); // will throw [1,2] and thus black starts
    assertEquals( Color.BLACK, game.getPlayerInTurn() );
  }
  
  @Test
  public void afterTwoConsecutiveBlackMovesNumberOfMovesIsZero() {
	  assertTrue(game.move(Location.R1,  Location.R2));
	  assertTrue(game.move(Location.R1,  Location.R2));
	  assertEquals("Number of remaining moves should be zero", 0, game.getNumberOfMovesLeft());
  }
  

  
  @Test 
  public void r1ShouldInitiallyContainTwoCheckers() {
	  assertEquals("R1 should contain 2 checkers", 2, game.getCount(Location.R1));
  }
  @Test
  public void r1ShouldInitiallyContainBlackCheckers() {
	  assertEquals("R1 should contain black checkers", Color.BLACK, game.getColor(Location.R1));
  }
  
  @Test
  public void r1Tor2IsLegalAtGameStart() {
	  assertTrue(game.move(Location.R1,  Location.R2));
  }
  
  @Test
  public void r1Tob1IsIllegalAtGameStart() {
	  assertTrue(!game.move(Location.R1,  Location.B1));
	/*  assertEquals("One checker on R2", 1, game.getCount(Location.R2));
	  assertEquals("One checker on R1", 1, game.getCount(Location.R1));
	  assertEquals("Black on R1", Color.BLACK, game.getColor(Location.R1));
	  assertEquals("Black on R2", Color.BLACK, game.getColor(Location.R2));
	  
	  assertEquals("One move remains", 1, game.getNumberOfMovesLeft());*/
  }
  
  // Board Tests
  @Test
  public void testBoardPlace() {
	  BoardImpl b = new BoardImpl(28);
	  
	  b.place(Color.BLACK, Location.R1);
	  assertEquals(1, b.getPosition(Location.R1).occupants);
	  assertEquals(Color.BLACK, b.getPosition(Location.R1).player);
  }
  
  @Test
  public void testBoardRemove() {
	  BoardImpl b = new BoardImpl(28);
	  
	  b.place(Color.BLACK, Location.R1);
	  b.remove(Color.BLACK,  Location.R1);
	  assertEquals(0, b.getPosition(Location.R1).occupants);
  }
}
