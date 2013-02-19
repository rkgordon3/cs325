package frs.hotgammon.variants.turndeterminers;

import frs.hotgammon.Color;
import frs.hotgammon.Game;
import frs.hotgammon.TurnDeterminer;

public class AlternatingTurnDeterminer implements TurnDeterminer {

	private Game game;

	@Override
	public Color nextTurn() {	
		if (game.getPlayerInTurn() == Color.NONE) {
			return Color.BLACK;
		}
		return game.getPlayerInTurn() == Color.RED ? Color.BLACK : Color.RED;	
	}
	
	public void setGame(Game game) {
		this.game = game;
	}

}
