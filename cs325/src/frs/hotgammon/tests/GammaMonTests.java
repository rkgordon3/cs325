package frs.hotgammon.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Location;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.variants.movevalidators.SimpleMoveValidator;
import frs.hotgammon.variants.rolldeterminers.PairSequenceRollDeterminer;
import frs.hotgammon.variants.turndeterminers.AlternatingTurnDeterminer;
import frs.hotgammon.variants.winnerdeterminers.BearOffWinnerDeterminer;
import frs.hotgammon.common.GameImpl.Placement;

public class GammaMonTests {

	private GameImpl game;

	@Before
	public void setup() {
		game = new GameImpl(new SimpleMoveValidator(),
				new BearOffWinnerDeterminer(),
				new AlternatingTurnDeterminer(),
				new PairSequenceRollDeterminer());
		game.newGame();
	}

	@Test
	public void shouldBeWinForBlack() {
		game.configure(new Placement[] {

		new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF)

		});
		game.nextTurn();
		assertEquals(Color.BLACK, game.winner());
	}

	@Test
	public void shouldBeNoWinner() {
		game.configure(new Placement[] {

		new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF)

		});
		game.nextTurn();
		assertEquals(Color.NONE, game.winner());
	}

	@Test
	public void shouldBeWinForRed() {
		game.configure(new Placement[] {
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF) });

		game.nextTurn();
		game.nextTurn();
		assertEquals(Color.RED, game.winner());
	}
	
	// dan tests
    @Test
    public void shouldNotBeAWinnerRightAway() {
        assertTrue(game.winner()==Color.NONE);
        game.nextTurn();
        assertTrue(game.winner()==Color.NONE);
    }
    @Test
    public void shouldNotEndAfter6Turns(){
        game.nextTurn();
        game.nextTurn();
        game.nextTurn();
        game.nextTurn();
        game.nextTurn();
        game.nextTurn();
        assertEquals(game.winner(),Color.NONE);
    }
}
