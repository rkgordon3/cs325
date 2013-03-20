 package frs.hotgammon.variants.winnerdeterminers;

import frs.hotgammon.Color;
import frs.hotgammon.Game;
import frs.hotgammon.Location;
import frs.hotgammon.WinnerDeterminer;
import frs.hotgammon.common.GameImpl;

public class BearOffWinnerDeterminer implements WinnerDeterminer {

	private Game game;

	@Override
	public Color winner(int turnCount) {
		if (game.getPlayerInTurn() == Color.NONE) {
			return Color.NONE;
		}
		String label = GameImpl.playerLabel(game.getPlayerInTurn());
		return game.getCount(Location.valueOf(label+"_BEAR_OFF")) == Game.NUMBER_OF_PLAYERS ? game.getPlayerInTurn() : Color.NONE;
	}

	@Override
	public void setGame(Game game) {
		this.game = game;
	}

}
