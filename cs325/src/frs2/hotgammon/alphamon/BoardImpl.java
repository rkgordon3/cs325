package frs2.hotgammon.alphamon;

import frs2.hotgammon.alphamon.Board;
import frs2.hotgammon.alphamon.Color;

public class BoardImpl implements Board {
	
	private Position board[];
	
	public BoardImpl(int size) {
		board = new Position[size];
		for (int i = 0; i < size; i++) {
			board[i] = new Position();
		}
		
	}

	@Override
	public boolean place(Color player, Location loc) {
		int index = loc.ordinal();
		Position s = board[index];

		s.player = player;
		s.occupants++;
		return true;
	}
	@Override
	public boolean remove(Color player, Location loc) {
		Position s = board[loc.ordinal()];
		if (player != s.player) {
			return false;
		}
		s.occupants--;
		if (s.occupants == 0) {
			player = Color.NONE;;
		}
		return true;
	}

	@Override
	public Position getPosition(Location loc) {
		return board[loc.ordinal()];
	}
	
	@Override
	public void clear() {
		int size = board.length;
		board = new Position[size];
		for (int i = 0; i < size; i++) {
			board[i] = new Position();
		}
		
	}

}
