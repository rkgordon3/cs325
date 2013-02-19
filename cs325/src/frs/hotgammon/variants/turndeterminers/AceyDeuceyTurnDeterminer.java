package frs.hotgammon.variants.turndeterminers;

import frs.hotgammon.Color;
import frs.hotgammon.Game;
import frs.hotgammon.TurnDeterminer;

public class AceyDeuceyTurnDeterminer implements TurnDeterminer {
	private Game game;

	@Override
	public Color nextTurn() {
		if (game.getPlayerInTurn() == Color.NONE) {
			return Color.BLACK;
		}
		int[] previousRoll = game.diceThrown();
		boolean isAD = isAceyDeucey(previousRoll);
		Color pit = game.getPlayerInTurn();
		return isAD ? pit : (pit == Color.RED ? Color.BLACK : Color.RED);
	}

	@Override
	public void setGame(Game game) {
		this.game = game;
	}
	
	private boolean isAceyDeucey(int[] roll) {
		if (roll == null) {
			return false;
		}
		return (roll[0] + roll[1]) == 3;
	}

}
