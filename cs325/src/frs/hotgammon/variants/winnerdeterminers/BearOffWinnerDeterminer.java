 package frs.hotgammon.variants.winnerdeterminers;

import frs.hotgammon.BaseDeterminer;
import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.Location;
import frs.hotgammon.WinnerDeterminer;
import frs.hotgammon.common.GameImpl;

public class BearOffWinnerDeterminer extends BaseDeterminer implements WinnerDeterminer {


	@Override
	public Color winner(int turnCount) {
		if (game.getPlayerInTurn() == Color.NONE) {
			return Color.NONE;
		}
		
		return isGameOver(turnCount) ? game.getPlayerInTurn() : Color.NONE;
	}

	@Override
	public boolean isGameOver(int turnCount) {
		String label = GameImpl.playerLabel(game.getPlayerInTurn());
		return game.getCount(Location.valueOf(label+"_BEAR_OFF")) == Game.NUMBER_OF_PLAYERS;
	}

}
