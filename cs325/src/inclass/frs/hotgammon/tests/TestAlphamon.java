package inclass.frs.hotgammon.tests;

import frs.hotgammon.factory.AlphaMonFactory;
import inclass.frs.hotgammon.common.Board;
import inclass.frs.hotgammon.common.Color;
import inclass.frs.hotgammon.common.GameImpl;
import inclass.frs.hotgammon.common.Location;
import inclass.frs.hotgammon.variants.movevalidators.SimpleMoveValidator;
import inclass.frs.hotgammon.variants.rolldeterminers.*;
import inclass.frs.hotgammon.variants.turndeterminer.AlternatingTurnDeterminer;

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
  private GameImpl game;
  
  @Before public void setup() {
    game = new GameImpl(new AlphaMonFactory());
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
  public void shouldHave2BlacksOnR1() {
	  game.configure(new Board.Placement[] {
			  new Board.Placement(Color.BLACK, Location.R1),
			  new Board.Placement(Color.BLACK, Location.R1)
	  });
	  assertEquals("Two checkers on R1", 2, game.getCount(Location.R1));
	  assertEquals("R1 contains Black", Color.BLACK, game.getColor(Location.R1));
  }
  
  
  public void r1ToR2isValidAtStartOfGame() {
	  assertTrue(game.move(Location.R1, Location.R2));
	  assertEquals("One black on R1", 1, game.getCount(Location.R1));
	  assertEquals("One black on R2", 1, game.getCount(Location.R2));
	  assertEquals("One move left", 1, game.getNumberOfMovesLeft());
  }
  
  @Test 
  public void canMoveBothR1ChipsToR2() {
	  
  }
  
  @Test
  public void afterTwoBlackMovesMoveCountIsZero() {
	  game.configure(new Board.Placement[] {
			  new Board.Placement(Color.BLACK, Location.R1),
			  new Board.Placement(Color.BLACK, Location.R1)
	  });
	  game.nextTurn();
	  assertTrue(game.move(Location.R1,  Location.R2));
	  assertTrue(game.move(Location.R1,  Location.R2));
	  assertEquals("Move count should be zero", 0, game.getNumberOfMovesLeft());
  }
  
  @Test
  public void redInTurnAfter2ndNextTurn() {
	  game.nextTurn();
	  game.nextTurn();
	  assertEquals("Red should be in turn after 2nd roll", Color.RED, game.getPlayerInTurn());
	  
  }
  
  @Test
  public void dieShouldShow1and2After1stNextTurn() {
	  game.nextTurn();
	  assertArrayEquals("Die should show [1,2]", new int[] { 1, 2}, game.diceThrown());
  }
  
  @Test
  public void dieShouldShow3and4After2ndNextTurn() {
	  game.nextTurn();
	  game.nextTurn();
	  assertArrayEquals("Die should show [3,4]", new int[] { 3, 4}, game.diceThrown());
  }
  
  @Test
  public void dieShouldShow5and6After3rdNextTurn() {
	  game.nextTurn();
	  game.nextTurn();
	  game.nextTurn();
	  assertArrayEquals("Die should show [5,6]", new int[] { 5, 6}, game.diceThrown());
  }
  
  @Test
  public void dieShouldShow1and2After4thNextTurn() {
	  game.nextTurn();
	  game.nextTurn();
	  game.nextTurn();
	  game.nextTurn();
	  assertArrayEquals("Die should show [1,2]", new int[] { 1, 2 }, game.diceThrown());
  }
  
  @Test
  public void redWinsAfterSixMoves() {
	  game.nextTurn();
	  game.nextTurn();
	  game.nextTurn();
	  game.nextTurn();
	  game.nextTurn();
	  game.nextTurn();
	  assertEquals("Red wins after six moves", Color.RED, game.winner());
  }
  
  @Test 
  public void noWinnerAfterFiveMoves() {
	  game.nextTurn();
	  game.nextTurn();
	  game.nextTurn();
	  game.nextTurn();
	  game.nextTurn();
	  assertEquals("No winner after five moves", Color.NONE, game.winner());
  }
}
