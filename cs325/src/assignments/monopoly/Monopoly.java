package assignments.monopoly;

import java.util.HashMap;

public class Monopoly {

	private HashMap<String, Player> players = new HashMap<String,Player>();

	public void join(Player player) {
		players.put(player.getName(), player);
		
	}

	public HashMap<String, Player> getPlayers() {
		// TODO Auto-generated method stub
		return players;
	}

	public Player getPlayer(String name) {
		// TODO Auto-generated method stub
		return players.get(name);
	}
	
	public boolean isPlaying(String name) {
		return players.containsKey(name);
	}

}
