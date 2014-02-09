package inclass.frs.hotgammon.common;



public class Board {
	
	public static class Position {
		public Color player;
		public int count;
	}
	
    private Position[] board;
    
    public Board(int size) {
    	board = new Position[size];
    	for (int i = 0; i < size; i++) {
    		board[i] = new Position();
    	}
    }
	
	public void place(int index, Color player) {
		board[index].player = player;
		board[index].count++;
	}
	
	public void remove(int index) {
		board[index].count--;	
	}

	
	public Position getPosition(int index) {
		return board[index];
	}
	
	public void clear() {
		int size = board.length;
		board = new Position[size];
		for (int i = 0; i < size; i++) {
			board[i] = new Position();
		}
		
	}
}
