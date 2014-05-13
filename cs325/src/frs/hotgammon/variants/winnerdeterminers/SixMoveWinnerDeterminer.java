package frs.hotgammon.variants.winnerdeterminers;

import org.junit.Before;

import frs.hotgammon.BaseDeterminer;
import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Game;
import frs.hotgammon.WinnerDeterminer;
import frs.hotgammon.common.GameImpl;

public class SixMoveWinnerDeterminer extends BaseDeterminer implements WinnerDeterminer {
	
	private static int TURN_MAX = 6;
	private Game game;

	@Override
	public Color winner(int turnCount) {
		if (game.getPlayerInTurn() == Color.NONE) {
			return Color.NONE;
		}
		return turnCount == TURN_MAX ? Color.RED : Color.NONE;
	}

	@Override
	public boolean isGameOver(int turnCount) {
		return turnCount == TURN_MAX;
	}

}
