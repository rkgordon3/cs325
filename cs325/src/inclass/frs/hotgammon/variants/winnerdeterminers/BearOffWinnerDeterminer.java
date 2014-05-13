 package inclass.frs.hotgammon.variants.winnerdeterminers;

import inclass.frs.hotgammon.common.*;



public class BearOffWinnerDeterminer implements WinnerDeterminer {

	private Game game;

	@Override
	public Color winner(int turnCount) {
		if (game.getCount(Location.B_BEAR_OFF) == Game.NUMBER_OF_PLAYERS) {
			return Color.BLACK;
		} else if (game.getCount(Location.R_BEAR_OFF) == Game.NUMBER_OF_PLAYERS) {
			return Color.RED;
		} else {
			return Color.NONE;
		}
	}
	
	@Override 
	public boolean isGameOver(int turnCount) {
		return game.getCount(Location.B_BEAR_OFF) == Game.NUMBER_OF_PLAYERS ||
			   game.getCount(Location.R_BEAR_OFF) == Game.NUMBER_OF_PLAYERS;
	}

	@Override
	public void setGame(Game game) {
		this.game = game;
	}

}
