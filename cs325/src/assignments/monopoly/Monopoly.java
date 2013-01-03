package assignments.monopoly;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Monopoly {

	public static final int N_SQUARES = 40;
	private HashMap<String, Player> players = new HashMap<String,Player>();
	private Square[] board = new Square[N_SQUARES];

	public Monopoly() {
		for (int i = 0; i < N_SQUARES; i++) {
			board[i] = new Square(i);
		}
	}
	public void join(Player player) {
		players.put(player.getName(), player);
		// Place player at "GO"
		player.moveTo(board[0]);
	}
	

	public HashMap<String, Player> getPlayers() {
		return players;
	}

	public Player getPlayer(String name) {
		return players.get(name);
	}
	
	public boolean isPlaying(String name) {
		return players.containsKey(name);
	}
	
	public static class PairOfDice {
		private static Die d1 = new Die();
		private static Die d2 = new Die();
		public static Roll roll() {
			int d1value = d1.roll();
			int d2value = d2.roll();
			return new Roll(d1value, d2value);
		}
	}

	public Square getSquare(int index) {
		return board[index % N_SQUARES];
	}
	
	public ArrayList<Player> assignTurns() {
		Collection<Player>  c = players.values();
		ArrayList<Player> turnList = Collections.list(Collections.enumeration(c));
		Collections.shuffle(turnList);
		return turnList;
	}

}
