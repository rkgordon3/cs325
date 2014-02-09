package inclass.frs.hotgammon.tests;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;
import inclass.frs.hotgammon.common.Color;
import inclass.frs.hotgammon.common.Game;
import inclass.frs.hotgammon.common.GameImpl;
import inclass.frs.hotgammon.common.GameImpl.Placement;
import inclass.frs.hotgammon.common.Location;
import inclass.frs.hotgammon.common.MoveValidator;
import inclass.frs.hotgammon.variants.movevalidators.SimpleMoveValidator;

import org.junit.Before;
import org.junit.Test;

public class SimpleMoveValidatorTests {
	
	GameImpl game;
	MoveValidator mv;
	
	@Before
	public void setup() {
		game = new GameImpl();
		mv = new SimpleMoveValidator();
		mv.setGame(game);
	}
	
	@Test
	public void canMoveToOpenPoint() {
		game.configure(null);
		game.configure(new GameImpl.Placement[] {
				new Placement(Color.RED, Location.B1)
		});
		assertTrue(mv.isValid(Location.B1, Location.B3));
		
	}
	
	@Test
	public void cannotMoveToPointOccupiedByOpponent() {
		game.configure(null);
		game.configure(new GameImpl.Placement[] {
				new Placement(Color.RED, Location.B1),
				new Placement(Color.BLACK, Location.B3)
		});
		assertTrue(!mv.isValid(Location.B1, Location.B3));
	}
	
	@Test
	public void canMoveToPointOccupiedBySameColor() {
		game.configure(null);
		game.configure(new GameImpl.Placement[] {
				new Placement(Color.RED, Location.B1),
				new Placement(Color.RED, Location.B3)
		});
		assertTrue(mv.isValid(Location.B1, Location.B3));
	}
	

}
