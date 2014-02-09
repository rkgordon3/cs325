package inclass.frs.hotgammon.tests;

import static org.junit.Assert.*;

import inclass.frs.hotgammon.common.Color;
import inclass.frs.hotgammon.common.GameImpl;
import inclass.frs.hotgammon.common.GameImpl.Placement;
import inclass.frs.hotgammon.common.Location;
import inclass.frs.hotgammon.common.WinnerDeterminer;
import inclass.frs.hotgammon.variants.winnerdeterminers.BearOffWinnerDeterminer;
import inclass.frs.hotgammon.variants.winnerdeterminers.SixMoveWinnerDeterminer;

import org.junit.Before;
import org.junit.Test;

public class BearOffWinnerTests {
	private GameImpl game;
	private WinnerDeterminer wd;

	@Before
	public void setup() {
		game = new GameImpl();
		wd = new BearOffWinnerDeterminer();
		wd.setGame(game);
	}
	@Test
	public void redWinsAfterBearOff() {
		game.configure(null);
		game.configure( new GameImpl.Placement[] {
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
				new Placement(Color.RED, Location.R_BEAR_OFF)
			});
		assertEquals(Color.RED, wd.winner(0));
	}

}
