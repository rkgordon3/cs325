package frs2.hotgammon.alphamon;

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
	private Board board = new BoardImpl(28);

	public void newGame() {
		board.place(Color.BLACK, Location.R1);
		board.place(Color.BLACK, Location.R1);
		board.place(Color.RED,  Location.B1);
	}

	public void nextTurn() {
		playerInTurn = Color.BLACK;
	}

	public boolean move(Location from, Location to) {
		board.remove(playerInTurn, from);
		if (getCount(to) == 0 || (getColor(to) == playerInTurn ||
									getColor(to) == Color.NONE)) {
			board.place(playerInTurn, to);
			return true;
		} else {
			return false;
		}
	}

	public Color getPlayerInTurn() {
		return playerInTurn;
	}

	public int getNumberOfMovesLeft() {
		return 0;
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
		return board.getPosition(location).player;
	}

	public int getCount(Location location) {
		return board.getPosition(location).occupants;
	}
}
