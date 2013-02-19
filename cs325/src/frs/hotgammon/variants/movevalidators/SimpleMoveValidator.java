package frs.hotgammon.variants.movevalidators;


import frs.hotgammon.*;

public class SimpleMoveValidator implements MoveValidator  {
	
	private Game game;

	public SimpleMoveValidator() {
	
	}
	
	public void setGame(Game game) {
		this.game = game;
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
