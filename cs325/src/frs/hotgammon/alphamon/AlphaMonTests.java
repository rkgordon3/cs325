package frs.hotgammon.alphamon;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AlphaMonTests {
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
	public void shouldHaveBlackPlayerInTurnAfterNewGame() {
		game.nextTurn(); // will throw [1,2] and thus black starts
		assertEquals(Color.BLACK, game.getPlayerInTurn());
	}

	@Test
	public void r1shouldContainTwoBlackCheckers() {
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
		assertEquals(1, game.getCount(Location.R1));

		assertEquals(1, game.getNumberOfMovesLeft());
	}

	@Test
	public void shouldNotBeAbleToPlaceTwoDifferentColorsOnSameSquare() {
		BoardImpl b = new BoardImpl(25);
		assertTrue(b.place(Color.BLACK, Location.R1.ordinal()));
		assertFalse(b.place(Color.RED, Location.R1.ordinal()));
	}

	@Test
	public void shouldBeAbleToPlaceSameColorOnGivenSquare() {
		BoardImpl b = new BoardImpl(25);
		assertTrue(b.place(Color.BLACK, Location.R1.ordinal()));
		assertTrue(b.place(Color.BLACK, Location.R1.ordinal()));
	}

	@Test
	public void shouldReturnProperCountForGivenSquare() {
		BoardImpl b = new BoardImpl(25);
		b.place(Color.BLACK, Location.R1.ordinal());
		b.place(Color.BLACK, Location.R1.ordinal());
		assertEquals(2, b.getSquare(Location.R1.ordinal()).occupants);
	}

	@Test
	public void shouldNotBeAbleToRemovePlayerOfWrongColor() {
		Board b = new BoardImpl(25);
		b.place(Color.BLACK, Location.R1.ordinal());
		assertFalse(b.remove(Color.RED, Location.R1.ordinal()));
		assertEquals(1, b.getSquare(Location.R1.ordinal()).occupants);
	}

	@Test
	public void shouldBeAbleToRemovePlayerOfCorrectColor() {
		Board b = new BoardImpl(25);
		b.place(Color.BLACK, Location.R1.ordinal());
		assertTrue(b.remove(Color.BLACK, Location.R1.ordinal()));
		assertEquals(0, b.getSquare(Location.R1.ordinal()).occupants);
	}

}
