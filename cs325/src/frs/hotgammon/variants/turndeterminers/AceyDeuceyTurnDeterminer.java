package frs.hotgammon.variants.turndeterminers;

import frs.hotgammon.BaseDeterminer;
import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Game;
import frs.hotgammon.TurnDeterminer;

public class AceyDeuceyTurnDeterminer extends BaseDeterminer implements TurnDeterminer {
	

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
	
	private boolean isAceyDeucey(int[] roll) {
		if (roll == null) {
			return false;
		}
		return (roll[0] + roll[1]) == 3;
	}

}
