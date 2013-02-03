package frs.hotgammon.alphamon;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameTests {
	Game game;

	@Before
	public void setup() {
		game = new GameImpl();
		game.newGame();
	}

	@Test
	public void shouldHaveNoPlayerInTurnAfterNewGame() {
		assertEquals(Color.NONE, game.getPlayerInTurn());
	}

	@Test
	public void shouldHaveBlackPlayerInTurnAfterFirstRoll() {
		game.nextTurn(); // will throw [1,2] and thus black starts
		assertEquals(Color.BLACK, game.getPlayerInTurn());
	}

	@Test
	public void shoudlBeTwoBlackCheckersOnR1() {
		assertEquals(2, game.getCount(Location.R1));
		assertEquals(Color.BLACK, game.getColor(Location.R1));
	}

	@Test
	public void shouldHaveBlackOnR1andBlackOnB2AndOneMoreLeft() {
		assertEquals(2, game.getCount(Location.R1));
		assertEquals(Color.BLACK, game.getColor(Location.R1));
		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R2));
		assertEquals(1, game.getCount(Location.R2));
		assertEquals(Color.BLACK, game.getColor(Location.R2));
		assertEquals(1, game.getCount(Location.R1));
		assertEquals(Color.BLACK, game.getColor(Location.R1));

		assertEquals(1, game.getNumberOfMovesLeft());
	}


	@Test
	public void shouldNotBeAbleToPlaceBlackOnRedOccupiedSquare() {
		game.nextTurn();
		int prevCount = game.getCount(Location.R1);
		if (occupiedBy(Color.RED, Location.B1)) {
		        assertFalse(game.move(Location.R1, Location.B1));
		        assertEquals(game.getCount(Location.R1), prevCount);
		} else {
			assertTrue(game.move(Location.R1, Location.B1));
			assertEquals(game.getCount(Location.R1), prevCount-1);
		
		}
	}
	@Test
	public void shouldBeZeroMovesAfterTwoConsecutive() {
		game.nextTurn();
		game.move(Location.R1, Location.R2);
		game.move(Location.R1, Location.R2);
		assertEquals(0, game.getNumberOfMovesLeft());
	}
	
	@Test
	public void shouldBeRedTurnAfter2NextTurns() {
		game.nextTurn();
		game.nextTurn();
		assertEquals(game.getPlayerInTurn(), Color.RED);
		assertEquals(game.diceThrown()[0], 3);
		assertEquals(game.diceThrown()[1], 4);
	}
	
	@Test
	public void shouldBeRedWinnerAfterSixTurns() {
		for (int i = 0; i < 6; i++) {
			game.nextTurn();
		}
		assertTrue(game.winner()== Color.RED);
	}
	@Test
	public void shouldBeNoWinnerAfterFourTurns() {
		for (int i = 0; i < 4; i++) {
			game.nextTurn();
		}
		assertTrue(game.winner() == Color.NONE);
	}
	
	@Test
	public void shouldRoll12Then34Then56Then12() {
		game.nextTurn();
		assertTrue(rollEquals(new int[] {1,2}));
		game.nextTurn();
		assertTrue(rollEquals(new int[] {3,4}));
		game.nextTurn();
		assertTrue(rollEquals(new int[] {5,6}));
		game.nextTurn();
		assertTrue(rollEquals(new int[] { 1, 2}));			
	}
	
	@Test
	public void shouldNotBeAbleToMoveIfNotInTurn() {
		game.nextTurn();
		assertFalse(game.move(Location.B1,  Location.B2));	
		assertEquals(game.getNumberOfMovesLeft(), 2);
	}
	@Test 
	public void shouldNotBeAbleToMakeThreeMoves() {
		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R2));
		assertTrue(game.move(Location.R2, Location.R3));
		assertFalse(game.move(Location.R3, Location.R4));
	}
	
	private boolean rollEquals(int[] roll) {
		return roll[0] == game.diceThrown()[0] && roll[1] == game.diceThrown()[1];
	}
	
	private boolean occupiedBy(Color color, Location loc) {
		return game.getCount(loc) > 0 && game.getColor(loc) == color;
	}

}
