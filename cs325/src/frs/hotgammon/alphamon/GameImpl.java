package frs.hotgammon.alphamon;

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
	private Color playerInTurn = Color.NONE;
	private final  int TURN_MAX = 5;
	private Color[] players = { Color.BLACK, Color.RED};
	private int[][] rollSequence = { {1,2}, {3,4}, {5,6} };
	
	private int turnCount = -1;
	final int MOVES_IN_TURN = 2;
	private int remainingMoves = MOVES_IN_TURN;
	
	private Board board;

	public void newGame() {
		board = new BoardImpl(Location.values().length);
		board.place(Color.BLACK, Location.R1.ordinal());
		board.place(Color.BLACK, Location.R1.ordinal());
		board.place(Color.RED, Location.B1.ordinal());
	}

	public void nextTurn() {
		remainingMoves = MOVES_IN_TURN;
		turnCount++;
		playerInTurn = players[turnCount % 2];		
	}


	public boolean move(Location from, Location to) {
		if (remainingMoves == 0) {
			return false;
		}
		if (occupiedByOpponent(to)) {
			return false;
		}
		if (!board.remove(playerInTurn, from.ordinal()))
			return false;
		remainingMoves--;
		return board.place(playerInTurn, to.ordinal());
	}

	public Color getPlayerInTurn() {
		return playerInTurn;
	}

	public int getNumberOfMovesLeft() {
		return remainingMoves;
	}

	public int[] diceThrown() {
		return rollSequence[turnCount % rollSequence.length];
	}

	public int[] diceValuesLeft() {
		return new int[] {};
	}

	public Color winner() {
		return turnCount == TURN_MAX ? Color.RED : Color.NONE;
	}

	public Color getColor(Location location) {
		return board.getSquare(location.ordinal()).player;
	}

	public int getCount(Location location) {
		return board.getSquare(location.ordinal()).occupants;
	}
	
	private boolean occupiedByOpponent(Location loc) {
		return getCount(loc) > 0 &&
			   getColor(loc) != playerInTurn;
	}
}
