package inclass.frs.hotgammon.common;



/**
 * Skeleton implementation of HotGammon.
 * 
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 * 
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */

public class GameImpl implements Game {
	public static final int N_POSITIONS = 28;
	private MoveValidator moveValidator;
	private RollDeterminer rollDeterminer;
	private TurnDeterminer turnDeterminer;

	private int[] diceThown;
	
	public GameImpl() {
	}

	public GameImpl(GammonFactory factory) {
		this.moveValidator = factory.createMoveValidator();
		this.rollDeterminer = factory.createRollDeterminer();
		this.turnDeterminer = factory.createTurnDeterminer();
		this.moveValidator.setGame(this);
	}

	private Color playerInTurn = Color.NONE;
	private int remainingMoves = 2;
	private int turnCount = 0;

	Board board = new Board(N_POSITIONS);
	private int[] diceThrown;

	public void newGame() {
	}

	public void nextTurn() {
		playerInTurn = turnDeterminer.nextTurn(playerInTurn, diceThrown);	
		diceThrown = rollDeterminer.roll();
		turnCount++;
	}

	public boolean move(Location from, Location to) {
		if (!moveValidator.isValid(from, to)) {
			return false;
		}
		Color player = getColor(from);
		board.remove(from.ordinal());
		board.place(to.ordinal(), player);
		remainingMoves--;
		return true;
	}

	public Color getPlayerInTurn() {
		return playerInTurn;
	}

	public int getNumberOfMovesLeft() {
		return remainingMoves;
	}

	public int[] diceThrown() {
		return diceThrown;
	}

	public int[] diceValuesLeft() {
		return new int[] {};
	}

	public Color winner() {
		return gameOver() ? Color.RED : Color.NONE;
	}

	private boolean gameOver() {
		final int MAX_MOVES = 6;
		return turnCount == MAX_MOVES;
	}

	public Color getColor(Location location) {
		return board.getPosition(location.ordinal()).player;
	}

	public int getCount(Location location) {
		return board.getPosition(location.ordinal()).count;
	}

	public void configure(Board.Placement[] placements) {
		board.clear();
		if (placements == null || placements.length == 0) {
			return;
		}
		for (int i = 0; i < placements.length; i++) {
			board.place(placements[i].location.ordinal(), placements[i].player);
		}
	}
	public static String playerLabel(Color player) {
		return String.valueOf(player.toString().charAt(0));
	}
}
