package frs.hotgammon.alphamon;
import frs.hotgammon.*;

public class AlphaMoveValidator implements MoveValidator  {
	
	private Game game;

	public AlphaMoveValidator(Game game) {
		this.game = game;
	}

	@Override
	public boolean isValid(Location from, Location to) {
		if (occupiedByOpponent(to)) {
			return false;
		}
		return true;
	}
	
	private boolean occupiedByOpponent(Location loc) {
		return game.getCount(loc) > 0 &&
			   game.getColor(loc) != game.getPlayerInTurn();
	}

}
