package frs.hotgammon.alphamon;

public class BoardImpl implements Board {

	
	private Square[] board;
	
	public BoardImpl(int size) {
		board = new Square[size];
		for (int i = 0; i < size; i++) {
			board[i] = new Square();
		}
	}

	@Override
	public boolean place(Color player, int sqNumber) {
		Square s = board[sqNumber];
		if (s.player != Color.NONE && s.player != player) {
			return false;
		}
		s.player = player;
		s.occupants++;
		return true;
	}
	@Override
	public boolean remove(Color player, int sqNumber) {
		Square s = board[sqNumber];
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
	public Square getSquare(int sqNumber) {
		return board[sqNumber];
	}
}