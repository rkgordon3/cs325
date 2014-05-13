package inclass.frs.hotgammon.tests;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;
import inclass.frs.hotgammon.common.Board;
import inclass.frs.hotgammon.common.Board.Placement;
import inclass.frs.hotgammon.common.Color;
import inclass.frs.hotgammon.common.Game;
import inclass.frs.hotgammon.common.GameImpl;
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
		game.nextTurn();
	}
	
	@Test
	public void canMoveToOpenPoint() {
		game.configure(new Board.Placement[] {
				new Board.Placement(Color.BLACK, Location.B1)
		});
		assertTrue(mv.isValid(Location.B1, Location.B3));
		
	}
	
	@Test
	public void cannotMoveToPointOccupiedByOpponent() {
		game.configure(new Board.Placement[] {
				new Board.Placement(Color.BLACK, Location.B1),
				new Board.Placement(Color.RED, Location.B3)
		});
		assertTrue(!mv.isValid(Location.B1, Location.B3));
	}
	
	@Test
	public void canMoveToPointOccupiedBySameColor() {
		game.configure(new Board.Placement[] {
				new Board.Placement(Color.BLACK, Location.B1),
				new Board.Placement(Color.BLACK, Location.B3)
		});
		assertTrue(mv.isValid(Location.B1, Location.B3));
	}
	
	@Test
	public void playerNotInTurnCannotMove() {
		game.configure(new Board.Placement[] {
				new Board.Placement(Color.RED, Location.B1)
		});
		assertTrue(!mv.isValid(Location.B1, Location.B3));
	}
	
	@Test
	public void cannotMoveIfDiceExpired() {
		game.configure(new Board.Placement[] {
				new Board.Placement(Color.BLACK, Location.B1)
		});
		assertTrue(mv.isValid(Location.B1, Location.B3));
		assertTrue(mv.isValid(Location.B3, Location.B5));
		assertTrue(!mv.isValid(Location.B5, Location.B7));			
	}
}
