package frs.hotgammon.common;

import java.util.ArrayList;

import frs.hotgammon.Board;
import frs.hotgammon.Color;
import frs.hotgammon.Game;
import frs.hotgammon.Location;
import frs.hotgammon.MoveValidator;
import frs.hotgammon.RollDeterminer;
import frs.hotgammon.TurnDeterminer;
import frs.hotgammon.Square;
import frs.hotgammon.WinnerDeterminer;

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
	private Color[] players = { Color.BLACK, Color.RED};

	private MoveValidator validator;
	private ArrayList<Integer> dieLeft;
	 
    
	final int MOVES_IN_TURN = 2;
	private int remainingMoves = MOVES_IN_TURN;
	private int turnCount = -1;
	private Board board;
	private int[] thrown;
	private WinnerDeterminer winnerDeterminer;
	private TurnDeterminer nextTurnDeterminer;
	private RollDeterminer rollDeterminer;
	
	public GameImpl(MoveValidator mv, WinnerDeterminer wd, TurnDeterminer ntd, RollDeterminer rd) {
		validator = mv;
		winnerDeterminer  = wd;
		nextTurnDeterminer = ntd;
		rollDeterminer = rd;
		mv.setGame(this);
		wd.setGame(this);
		ntd.setGame(this);
	}

	public void newGame() {
		board = new BoardImpl(Location.values().length);
		
		configure( new Placement[] { 
				new Placement(Color.BLACK, Location.R1),
				new Placement(Color.BLACK, Location.R1),
				new Placement(Color.BLACK, Location.R2),
				new Placement(Color.BLACK, Location.B6),
				new Placement(Color.RED, Location.B1),
				new Placement(Color.RED, Location.B1),
				new Placement(Color.RED, Location.R6)
			});


		playerInTurn = Color.NONE;	
		turnCount  = -1;
		remainingMoves = MOVES_IN_TURN;
		rollDeterminer.reset();
	}
	

	public void nextTurn() {
		turnCount++;
		// Call before throw so previous throw can be read
		Color nextPlayer = nextTurnDeterminer.nextTurn();
		remainingMoves = MOVES_IN_TURN;
		thrown = rollDeterminer.getRoll();
	
		dieLeft = new ArrayList<Integer>(remainingMoves);
		for (int i = 0; i <  remainingMoves;  i++) {
			dieLeft.add(thrown[i]);
		}
		playerInTurn = nextPlayer;		
	}


	public boolean move(Location from, Location to) {

		if (remainingMoves == 0) {
			return false;
		}
		
		if (! validator.isValid(from, to)) {
			return false;
		}
		if (!board.remove(playerInTurn, from.ordinal())) {
			return false;
		}
		
		if (getCount(to) > 0 && getColor(to) != playerInTurn) {
			// one player on  'to' spot, send to bar
			board.remove(otherPlayer(), to.ordinal());
			board.place(otherPlayer(), playerInTurn == Color.RED ? Location.B_BAR.ordinal() 
                                                                 : Location.R_BAR.ordinal());
		}
		
		consumeDie(Math.abs(Location.distance(from,  to)), isBearOff(to));
		return board.place(playerInTurn, to.ordinal());
	}

	public Color getPlayerInTurn() {
		return playerInTurn;
	}

	public int getNumberOfMovesLeft() {
		return remainingMoves;
	}

	public int[] diceThrown() {
		return thrown;
	}

	public int[] diceValuesLeft() {	
		int[] left = new int[dieLeft.size()];
		for (int i = 0; i < dieLeft.size(); i++) {
			left[i] = dieLeft.get(i);
		}
		return left;
	}

	public Color winner() {
		return winnerDeterminer.winner(turnCount);
	}

	public Color getColor(Location location) {
		Square sq = board.getSquare(location.ordinal());
		return sq.occupants == 0 ? Color.NONE : sq.player;
	}

	public int getCount(Location location) {
		return board.getSquare(location.ordinal()).occupants;
	}
	/**
	 * Precondition: Move already validated.
	 * @param die die value used with valid move
	 * @param wasBearOff true if bear off made
	 * @throws IllegalStateException if attempt consume die not available
	 */
	private void consumeDie(int die, boolean wasBearOff)  throws IllegalStateException {
		
		if (! dieLeft.remove(new Integer(die)) ) {
			if (!wasBearOff) {
				throw new IllegalStateException("Die value not available");
			}
			// pick one. We have a valid bear off move.
			dieLeft.remove(0);
		}
		remainingMoves--;	
	}
	/*
	 * Report 'other player', NONE if there is no current player.
	 */
	private Color otherPlayer() {
		if (playerInTurn == Color.NONE) {
			return Color.NONE;
		}
		return playerInTurn == Color.RED ? Color.BLACK : Color.RED;
	}
	
	static public class Placement {
		public Location location;
		public Color	player;
		public Placement(Color player, Location location) {
			this.player = player;
			this.location = location;
		}
	}
	public void configure(Placement[] placements) {
		board.clear();
		if (placements == null || placements.length == 0) {			
			return;
		}
		for (int i = 0; i < placements.length; i++) {
			board.place(placements[i].player, placements[i].location.ordinal());	
		}
	}
	
	public static String playerLabel(Color player) {
		return String.valueOf(player.toString().charAt(0));
	}
	
	private boolean isBearOff(Location to) {
		return to == Location.R_BEAR_OFF || to == Location.B_BEAR_OFF;
	}
	
}
