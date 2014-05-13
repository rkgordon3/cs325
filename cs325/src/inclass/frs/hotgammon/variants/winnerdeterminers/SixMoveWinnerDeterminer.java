package inclass.frs.hotgammon.variants.winnerdeterminers;

import inclass.frs.hotgammon.common.Color;
import inclass.frs.hotgammon.common.Game;
import inclass.frs.hotgammon.common.WinnerDeterminer;


public class SixMoveWinnerDeterminer implements WinnerDeterminer {
	
	private static int TURN_MAX = 6;
	private Game game;

	@Override
	public Color winner(int turnCount) {
		return turnCount == TURN_MAX ? Color.RED : Color.NONE;
	}
	
	@Override
	public boolean isGameOver(int turnCount) {
		return turnCount == TURN_MAX;
	}

	@Override
	public void setGame(Game game) {
		this.game = game;		
	}
}
