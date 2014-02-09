package inclass.frs.hotgammon.tests;

import static org.junit.Assert.*;
import inclass.frs.hotgammon.common.Color;
import inclass.frs.hotgammon.common.GameImpl;
import inclass.frs.hotgammon.common.WinnerDeterminer;
import inclass.frs.hotgammon.variants.winnerdeterminers.SixMoveWinnerDeterminer;

import org.junit.Before;
import org.junit.Test;

public class SixMoveWinTests {
	
	private GameImpl game;
	private WinnerDeterminer wd;

	@Before
	public void setup() {
		game = new GameImpl();
		wd = new SixMoveWinnerDeterminer();
	}

	@Test
	public void redWinsInSixMoves() {
		assertEquals(Color.RED, wd.winner(6));
	}
	
	@Test
	public void noWinnerBeforeSixMoves() {
		assertEquals(Color.NONE, wd.winner(5));
	}

}
