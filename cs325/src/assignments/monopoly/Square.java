package assignments.monopoly;

public class Square {

	private Player player;
	private int index;
	
	public Square(int index) {
		this.index = index;
	}

	public void addPlayer(Player player) {
		this.player = player;
	}

	public Object getPlayer() {
		return player;
	}

	public int index() {
		return index;
	}

}
