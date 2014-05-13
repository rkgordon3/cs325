package frs.hotgammon.variants.turndeterminers;

import frs.hotgammon.BaseDeterminer;
import frs.hotgammon.framework.Color;
import frs.hotgammon.Game;
import frs.hotgammon.TurnDeterminer;

public class AlternatingTurnDeterminer  extends BaseDeterminer implements TurnDeterminer {


	@Override
	public Color nextTurn() {	
		if (game.getPlayerInTurn() == Color.NONE) {
			return Color.BLACK;
		}
		return game.getPlayerInTurn() == Color.RED ? Color.BLACK : Color.RED;	
	}
	


}
