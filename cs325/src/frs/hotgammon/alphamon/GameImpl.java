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
	private Board board;

	public void newGame() {
		board = new BoardImpl(Location.values().length);
		board.place(Color.BLACK, Location.R1.ordinal());
		board.place(Color.BLACK, Location.R1.ordinal());
	}

	public void nextTurn() {
		playerInTurn = Color.BLACK;
	}

	public boolean move(Location from, Location to) {
		if (!board.remove(playerInTurn, from.ordinal()))
			return false;
		board.place(playerInTurn, to.ordinal());
		return true;
	}

	public Color getPlayerInTurn() {
		return playerInTurn;
	}

	public int getNumberOfMovesLeft() {
		return 1;
	}

	public int[] diceThrown() {
		return new int[] { 1, 1 };
	}

	public int[] diceValuesLeft() {
		return new int[] {};
	}

	public Color winner() {
		return Color.NONE;
	}

	public Color getColor(Location location) {
		return Color.BLACK;
	}

	public int getCount(Location location) {
		return board.getSquare(location.ordinal()).occupants;
	}
}
