package assignments.monopoly;

public class Board {

	
	private static final int N_SQUARES = 40;
	
	private Square[] boardImpl = new Square[N_SQUARES];
	
	public Board() {
		for (int i = 0; i < N_SQUARES; i++) {
			boardImpl[i] = new Square();
		}
	}

	public void addPlayer(Player player, int i) {
		boardImpl[i].addPlayer(player);
		
	}

	public Object getPlayer(int i) {
		return boardImpl[i].getPlayer();
	}

}
