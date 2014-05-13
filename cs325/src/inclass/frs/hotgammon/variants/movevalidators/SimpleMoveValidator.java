package inclass.frs.hotgammon.variants.movevalidators;


import inclass.frs.hotgammon.*;
import inclass.frs.hotgammon.common.Game;
import inclass.frs.hotgammon.common.Location;
import inclass.frs.hotgammon.common.MoveValidator;

public class SimpleMoveValidator implements MoveValidator  {
	
	private Game game;

	public SimpleMoveValidator() {
	
	}
	
	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public boolean isValid(Location from, Location to) {
		if (game.getColor(from) != game.getPlayerInTurn()) {
			return false;
		}
		if (occupiedByOpponent(from, to)) {
			return false;
		}
		return true;
	}
	
	private boolean occupiedByOpponent(Location from, Location loc) {
		return game.getCount(loc) > 0 &&
			   game.getColor(loc) != game.getColor(from);
	}
	


}
