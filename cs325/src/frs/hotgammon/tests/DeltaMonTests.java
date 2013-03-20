package frs.hotgammon.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import frs.hotgammon.Color;
import frs.hotgammon.common.GameImpl;

import frs.hotgammon.variants.movevalidators.SimpleMoveValidator;
import frs.hotgammon.variants.rolldeterminers.PairSequenceRollDeterminer;
import frs.hotgammon.variants.turndeterminers.AceyDeuceyTurnDeterminer;
import frs.hotgammon.variants.winnerdeterminers.SixMoveWinnerDeterminer;

public class DeltaMonTests {

	private GameImpl game;

	@Before
	public void setup() { 
		game = new GameImpl(new SimpleMoveValidator(), new SixMoveWinnerDeterminer(), new AceyDeuceyTurnDeterminer(), new PairSequenceRollDeterminer());
		game.newGame();
	}
	
	@Test
	public void shouldGiveBlackConsectiveTurnsAfter12() {
		game.nextTurn();
		assertEquals(Color.BLACK, game.getPlayerInTurn());
		game.nextTurn();
		assertEquals(Color.BLACK,  game.getPlayerInTurn());
		game.nextTurn();
		assertEquals(Color.RED,  game.getPlayerInTurn());
	}
	
	// Dan Tests
	
	@Test
    public void shouldBeExtraTurn() {
        game.nextTurn();
        assertTrue(game.getPlayerInTurn()==Color.BLACK);
        assertArrayEquals(new int[]{1,2},game.diceThrown());
        game.nextTurn();
        assertTrue(game.getPlayerInTurn()==Color.BLACK);
    }
    @Test
    public void shouldNotBeExtraTurn(){
        game.nextTurn();
        game.nextTurn();
        game.nextTurn();
        assertTrue(game.getPlayerInTurn()==Color.RED);
    }

}
