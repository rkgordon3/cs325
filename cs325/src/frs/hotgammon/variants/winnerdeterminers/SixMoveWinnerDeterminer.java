package frs.hotgammon.variants.winnerdeterminers;

import org.junit.Before;

import frs.hotgammon.Color;
import frs.hotgammon.Game;
import frs.hotgammon.WinnerDeterminer;
import frs.hotgammon.common.GameImpl;

public class SixMoveWinnerDeterminer implements WinnerDeterminer {
	
	private static int TURN_MAX = 5;
	private Game game;




	@Override
	public Color winner(int turnCount) {
		if (game.getPlayerInTurn() == Color.NONE) {
			return Color.NONE;
		}
		return turnCount == TURN_MAX ? Color.RED : Color.NONE;
	}

	@Override
	public void setGame(Game game) {
		this.game = game;
		
	}

}
