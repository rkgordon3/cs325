package frs.hotgammon.variants.movevalidators;


import frs.hotgammon.*;

import frs.hotgammon.framework.Location;

public class SimpleMoveValidator extends BaseDeterminer implements MoveValidator  {
	

	public SimpleMoveValidator() {
	
	}


	@Override
	public boolean isValid(Location from, Location to) {
		if (! isDieAvailable(Math.abs(Location.distance(from, to)), false)) {
			return false;
		}
		if (occupiedByOpponent(to)) {
			return false;
		}
		return true;
	}
	
	private boolean occupiedByOpponent(Location loc) {
		return game.getCount(loc) > 0 &&
			   game.getColor(loc) != game.getPlayerInTurn();
	}
	
	private boolean isDieAvailable(int dieValue, boolean isBearOff) {
		int[] dice = game.diceValuesLeft();
		for (int i = 0; i < dice.length; i++) {
			if (dice[i] == dieValue) {
				return true;
			}
		}
		return false;
	}

}
