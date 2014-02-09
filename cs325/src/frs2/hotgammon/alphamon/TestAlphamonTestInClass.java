package frs2.hotgammon.alphamon;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestAlphamonTestInClass {
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

}
