package frs.hotgammon.alphamon;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import frs.hotgammon.Color;
import frs.hotgammon.Game;
import frs.hotgammon.Location;

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
			assertEquals(game.getCount(Location.R1), prevCount - 1);

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
		assertTrue(game.winner() == Color.RED);
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
		assertTrue(rollEquals(new int[] { 1, 2 }));
		game.nextTurn();
		assertTrue(rollEquals(new int[] { 3, 4 }));
		game.nextTurn();
		assertTrue(rollEquals(new int[] { 5, 6 }));
		game.nextTurn();
		assertTrue(rollEquals(new int[] { 1, 2 }));
	}

	@Test
	public void shouldNotBeAbleToMoveIfNotInTurn() {
		game.nextTurn();
		assertFalse(game.move(Location.B1, Location.B2));
		assertEquals(game.getNumberOfMovesLeft(), 2);
	}

	@Test
	public void shouldNotBeAbleToMakeThreeMoves() {
		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R2));
		assertTrue(game.move(Location.R2, Location.R3));
		assertFalse(game.move(Location.R3, Location.R4));
	}
	
	@Test
	public void shouldNotBeAbleToPlaceRedOnBlackOccupiedSquare() {
		game.nextTurn();
		game.nextTurn();
		assertFalse(game.move(Location.B1, Location.R1));

	}

	private boolean rollEquals(int[] roll) {
		return roll[0] == game.diceThrown()[0]
				&& roll[1] == game.diceThrown()[1];
	}

	private boolean occupiedBy(Color color, Location loc) {
		return game.getCount(loc) > 0 && game.getColor(loc) == color;
	}

	// Maria's Tests
	@Test
	public void shouldBeTwoBlackCheckersOnR1() {
		final int CHECKERS_ON_R1_AT_START = 2;
		assertEquals(CHECKERS_ON_R1_AT_START, game.getCount(Location.R1));
	}

	@Test
	public void shouldBeValidToMoveFromR1ToR2AtStartOfGame() {
		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R2));
	}

	@Test
	public void shouldBeInvalidToMoveFromR1ToB1AtStartOfGame() {
		game.nextTurn();
		assertFalse(game.move(Location.R1, Location.B1));
	}

	@Test
	public void shouldBeNoMovesLeftAfterMovingTwoBlackCheckersFromR1toR2() {
		game.nextTurn();
		game.move(Location.R1, Location.R2);
		game.move(Location.R1, Location.R2);

		assertEquals(0, game.getNumberOfMovesLeft());
	}

	@Test
	public void shouldBeRedPlayerTurnAfterSecondNextTurn() {
		game.nextTurn();
		game.nextTurn();
		assertEquals(Color.RED, game.getPlayerInTurn());
	}

	@Test
	public void shouldBe3_4Die() {
		game.nextTurn();
		game.nextTurn();
		int[] expected = { 3, 4 };
		int[] actual = game.diceThrown();
		assertEquals("Should be a 3", expected[0], actual[0]);
		assertEquals("Should be a 4", expected[1], actual[1]);
	}

	@Test
	public void shouldEndGameAfterSixTurns() {
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		assertEquals("Winner should be Red", Color.RED, game.winner());

	}

	@Test
	public void shouldNotEndGameAfterFiveTurns() {
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		assertEquals("Winner should be null", Color.NONE, game.winner());

	}

	@Test
	public void shouldHaveRedIsWinner() {
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		assertEquals("Winner should be Red", Color.RED, game.winner());

	}

	@Test
	public void shouldNotBeAbleToPlaceTwoDifferentColorsOnSameSquare() {
		game.nextTurn();
		game.move(Location.B6, Location.B2);
		game.nextTurn();
		assertFalse(game.move(Location.B1, Location.B2));
	}

	@Test
	public void shouldBeAbleToPlaceTwoSameColorPiecesOnSameSquare() {
		game.nextTurn();
		game.move(Location.R1, Location.R2);
		assertTrue(game.move(Location.R1, Location.R2));
	}

	@Test
	public void shouldReturnProperCountForGivenSquare() {
		game.nextTurn();
		assertEquals("Count should be 2", 2, game.getCount(Location.B1));
	}

	@Test
	public void shouldNotBeAbleToRemovePlayerOfWrongColor() {
		game.nextTurn();
		assertFalse("Should not be able to remove Red pieces.",
				game.move(Location.B1, Location.B2));
	}

	@Test
	public void shouldBeAbleToRemovePlayerOfRightColor() {
		game.nextTurn();
		game.nextTurn();
		assertTrue("Should be able to remove Red pieces.",
				game.move(Location.B1, Location.B2));
	}

	// Dan's Test
	@Test
	public void shouldHaveTwoBlackCheckersOnR1() {
		game.nextTurn();
		assertTrue(game.getCount(Location.R1) == 2);
		assertTrue(game.getColor(Location.R1) == Color.BLACK);
	}

	@Test
	public void shouldBeAbleToMoveBlackR1toR2() {
		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R2));
		assertTrue(game.getCount(Location.R1) == 1);
		assertTrue(game.getCount(Location.R2) == 1);
		assertTrue(game.getNumberOfMovesLeft() == 1);

	}

	@Test
	public void shouldNotBeAbleToMoveBlackR1toB1() {
		game.nextTurn();
		assertFalse(game.move(Location.R1, Location.B1));
	}

	@Test
	public void shouldHaveNoMoveLeft() {
		game.nextTurn();
		game.move(Location.R1, Location.R2);
		game.move(Location.R1, Location.R3);
		assertTrue(game.getNumberOfMovesLeft() == 0);
	}

	@Test
	public void redShouldBeInTurn() {
		game.nextTurn();
		game.move(Location.R1, Location.R2);
		game.move(Location.R1, Location.R3);
		game.nextTurn();
		assertTrue(game.getPlayerInTurn() == Color.RED);
	}

	@Test
	public void diceRollsShouldBeIncremental() {
		game.nextTurn();
		assertArrayEquals(game.diceThrown(), new int[] { 1, 2 });
		game.move(Location.R1, Location.R2);
		game.move(Location.R1, Location.R3);
		game.nextTurn();
		assertArrayEquals(game.diceThrown(), new int[] { 3, 4 });
		game.nextTurn();
		assertArrayEquals(game.diceThrown(), new int[] { 5, 6 });
		game.nextTurn();
		assertArrayEquals(game.diceThrown(), new int[] { 1, 2 });
		game.nextTurn();
		assertArrayEquals(game.diceThrown(), new int[] { 3, 4 });
		game.nextTurn();
		assertArrayEquals(game.diceThrown(), new int[] { 5, 6 });
	}

	@Test
	public void shouldNotBeAbleToMoveWithNoMovesLeft() {
		game.nextTurn();
		game.move(Location.R1, Location.R2);
		game.move(Location.R1, Location.R3);
		assertFalse(game.move(Location.R2, Location.R3));
	}

	/*
	 * @Test public void redShouldBeWinnerAfter6Turns() { game.nextTurn();
	 * assertTrue(game.winner() == Color.NONE); game.nextTurn();
	 * game.nextTurn(); game.nextTurn(); game.nextTurn(); game.nextTurn();
	 * game.nextTurn(); assertTrue(game.winner() == Color.RED); }
	 */

	@Test
	public void redShouldBeAbleToMove() {
		game.nextTurn();
		game.nextTurn();
		assertTrue(game.move(Location.B1, Location.B2));
	}

	@Test
	public void shouldNotBeAbleToMoveFromASpotWithoutCheckers() {
		game.nextTurn();
		assertFalse(game.move(Location.R2, Location.R3));
	}

	// Joe's Test
	@Test
	public void ShouldBeBlackToGoFirst() {

		game.nextTurn();

		assertEquals("Black is first to go", Color.BLACK,
				game.getPlayerInTurn());

	}

	@Test
	public void shouldRunOutOfMoves() {
		// Needed to add nextTurn call
		game.nextTurn();
		game.move(Location.R1, Location.R2);

		game.move(Location.R1, Location.R2);

		assertEquals(" no moves should be left ", 0,
				game.getNumberOfMovesLeft());

	}

	/*
	 * This test is wrong.
	 * 
	 * @Test
	 * 
	 * public void shouldGetNextTurnAndDiceBe34(){
	 * 
	 * game.nextTurn();
	 * 
	 * game.nextTurn();
	 * 
	 * int [] currentDice = game.diceValuesLeft();
	 * 
	 * int firstDie = currentDice[0];
	 * 
	 * int secondDie = currentDice[1];
	 * 
	 * System.out.println(firstDie + " " + secondDie);
	 * 
	 * assertEquals( "dice should be 3-4", 3, secondDie);
	 * 
	 * assertEquals( "dice should be 3-4", 4, firstDie);
	 * 
	 * }
	 */

	/*
	 * Bad Test : Test diceThrown, not diceValuesLeft()
	 * 
	 * @Test
	 * 
	 * public void shouldGetNextTurnThreeTimesAndDiceBe56(){
	 * 
	 * game.nextTurn();
	 * 
	 * game.nextTurn();
	 * 
	 * game.nextTurn();
	 * 
	 * int [] currentDice = game.diceValuesLeft();
	 * 
	 * int firstDie = currentDice[0];
	 * 
	 * int secondDie = currentDice[1];
	 * 
	 * System.out.println(firstDie + " " + secondDie);
	 * 
	 * assertEquals( "dice should be 5-6", 5, secondDie);
	 * 
	 * assertEquals( "dice should be 5-6", 6, firstDie);
	 * 
	 * }
	 */

	/*
	 * @Test
	 * 
	 * public void shouldGetDice1And2OnOpeningTurn(){
	 * 
	 * game.nextTurn();
	 * 
	 * int [] currentDice = game.diceValuesLeft();
	 * 
	 * int firstDie = currentDice[0];
	 * 
	 * int secondDie = currentDice[1];
	 * 
	 * assertEquals( "dice should be 1-2", 1, secondDie);
	 * 
	 * assertEquals( "dice should be 1-2", 2, firstDie);
	 * 
	 * }
	 */

	@Test
	public void shouldEndGameAfter6Rolls() {

		game.nextTurn();

		game.nextTurn();

		assertEquals("should not be a winner ", Color.NONE, game.winner());

		game.nextTurn();

		game.nextTurn();

		game.nextTurn();

		game.nextTurn();

		assertEquals("should not be a winner ", Color.RED, game.winner());

	}

	@Test
	public void shouldEndGameAfter5Rolls() {

		game.nextTurn();

		assertEquals("should not be a winner ", Color.NONE, game.winner());

		game.nextTurn();

		game.nextTurn();

		game.nextTurn();

		assertEquals("should not be a winner ", Color.NONE, game.winner());

	}
	
	// Marta's Tests
	@Test
	public void shouldHaveTwoBlackCheckersOnR1WhenNewGameStarts() {
		game.nextTurn();
		assertTrue(game.getCount(Location.R1) == 2);
	}

	@Test
	public void shouldHave1BlackCheckerOnR1and1BlackCheckerOnR2AfterMoveFromR1toR2() {
		game.nextTurn();
		game.move(Location.R1, Location.R2);
		assertTrue(game.getCount(Location.R1) == 1);
		assertTrue(game.getCount(Location.R2) == 1);
		assertTrue(game.getColor(Location.R1) == Color.BLACK);
		assertTrue(game.getColor(Location.R2) == Color.BLACK);
	}

	@Test
	public void shouldHaveOneMoveLeftForBlackPlayerAfterMoveFromR1ToR2() {

		game.nextTurn();
		game.move(Location.R1, Location.R2);
		assertTrue(game.getPlayerInTurn() == Color.BLACK);
		System.out.println(game.getNumberOfMovesLeft());
		assertTrue(game.getNumberOfMovesLeft() == 1);
	}

	@Test
	public void shouldHave0NumberOfMovesForBlackPlayerAfterTheBlackMovedTwice() {

		game.nextTurn();
		game.move(Location.R1, Location.R2);
		game.move(Location.R1, Location.R3);

		assertTrue(game.getNumberOfMovesLeft() == 0);

	}

	@Test
	public void redPlayerIsInTurnAfterNextTurnIsInvokedTheSecondTime() {

		game.nextTurn();
		game.nextTurn();
		assertTrue(game.getPlayerInTurn() == Color.RED);

	}

	@Test
	public void dieValuesAre34AfterNextTurnIsInvokedTheSecondTime() {

		game.nextTurn();
		game.nextTurn();
		assertTrue(game.diceThrown()[0] == 3);
		assertTrue(game.diceThrown()[1] == 4);
	}

	@Test
	public void moveR1toB1isInvalidAsThereIsAnOpponentThere() {
		game.nextTurn();
		assertFalse(game.move(Location.R1, Location.B1));
		assertTrue(game.getColor(Location.B1) == Color.RED);
	}

	@Test
	public void shouldHaveNoPlayerInTurnBeforeTheGameStarted() {
		assertTrue(game.getPlayerInTurn() == Color.NONE);
	}

	@Test
	public void shouldChangeThePlayerAfterEachTurn() {

		game.nextTurn();
		assertTrue(game.getPlayerInTurn() == Color.BLACK);
		game.nextTurn();
		assertTrue(game.getPlayerInTurn() == Color.RED);
		game.nextTurn();
		assertTrue(game.getPlayerInTurn() == Color.BLACK);
		game.nextTurn();
		assertTrue(game.getPlayerInTurn() == Color.RED);
	}

	@Test
	public void R1ShouldBeNoneAndR3BlackAfterMoveFromR1ToR3() {

		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R2));
		assertTrue(game.move(Location.R1, Location.R3));
		assertTrue(game.getColor(Location.R1) == Color.NONE);
		assertTrue(game.getColor(Location.R3) == Color.BLACK);

	}

	@Test
	public void eachTurnThrowsCorrectNumberOfDice() {

		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		assertTrue(game.diceThrown()[0] == 5);
		assertTrue(game.diceThrown()[1] == 6);
		game.nextTurn();
		assertTrue(game.diceThrown()[0] == 1);
		assertTrue(game.diceThrown()[1] == 2);
		game.nextTurn();
		assertTrue(game.diceThrown()[0] == 3);
		assertTrue(game.diceThrown()[1] == 4);

	}

	@Test
	public void newGameResetsTheBoardToInitial() {
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.newGame();

		assertTrue(game.getCount(Location.R1) == 2);
		assertTrue(game.getCount(Location.B1) == 2);

		assertTrue(game.getColor(Location.R1) == Color.BLACK);
		assertTrue(game.getColor(Location.B1) == Color.RED);
	}

	@Test
	public void newGameResetsPlayerToBlack() {

		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.newGame();
		game.nextTurn();
		assertTrue(game.getPlayerInTurn() == Color.BLACK);
	}

	@Test
	public void redWinsAfter6Turns() {
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		assertTrue(game.winner() == Color.NONE);
		game.nextTurn();
		assertTrue(game.winner() == Color.RED);
	}

	@Test
	public void shouldNotBeAbleToPlaceTwoDifferentCheckersInTheSameContainer() {

		game.nextTurn();
		assertFalse(game.move(Location.R12, Location.B1));
	}

	@Test
	public void shouldBeAbleToPlaceTheSameCheckersInOneContainer() {
		game.nextTurn();
		game.nextTurn();
		assertTrue(game.move(Location.R6, Location.R8));
	}


}
