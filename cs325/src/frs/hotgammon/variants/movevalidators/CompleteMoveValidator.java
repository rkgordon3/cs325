package frs.hotgammon.variants.movevalidators;

import frs.hotgammon.Color;
import frs.hotgammon.Game;
import frs.hotgammon.Location;
import frs.hotgammon.MoveValidator;
import frs.hotgammon.common.GameImpl;

public class CompleteMoveValidator implements MoveValidator {

	private Game game;

	@Override
	public boolean isValid(Location from, Location to) {
		
		// check direction
		int proposedDistance = Location.distance(from, to);
		int sign = proposedDistance/Math.abs(proposedDistance);
		int absProposedDistance = Math.abs(proposedDistance);
		
		if (game.getPlayerInTurn().getSign() != sign) {
			return false;
		}
		 
		// If red attempts move from elsewhere when checker
		// on bar, return false.
		if (game.getPlayerInTurn() == Color.RED
				&& game.getCount(Location.R_BAR) > 0 
				&& from != Location.R_BAR) {
			return false;
		}
		if (game.getPlayerInTurn() == Color.BLACK
				&& game.getCount(Location.B_BAR) > 0 
				&& from != Location.B_BAR) {
			return false;
		}
		boolean lastChecker = false;
		// Bear off attempt
		boolean bear_off = (to == Location.R_BEAR_OFF) || (to == Location.B_BEAR_OFF);	
		if (bear_off) {   
				if ( !isPlayerAllWithinInnerTable(game.getPlayerInTurn())) {
					return false;
				}
			
			    // Are there checkers behind me?
				lastChecker = sumCheckersInRange(game.getPlayerInTurn(), absProposedDistance + 1, 6) == 0;
				
		}
		// If attempting move to a point occupied by opponent, return
		// false if count > 1
		if ( (game.getColor(to) != game.getPlayerInTurn())
				&& game.getCount(to) > 1) {
			return false;
		}
		int[] roll = game.diceValuesLeft();
		int moveDistance = 0; // can't make this move
		// Is there a die that matches proposed distance?

		for (int i = 0; i < roll.length; i++) {
			if (absProposedDistance == roll[i]  
				  || (lastChecker && absProposedDistance < roll[i])) {
				moveDistance = roll[i];
			}
		}
		return moveDistance != 0;
	}

	@Override
	public void setGame(Game game) {
		this.game = game;
	}
	
	private int sumCheckersInRange(Color player, int start, int finish) {
		int sum = 0;
		int len = finish - start + 1;
		String label = GameImpl.playerLabel(player);
		for (int i =start; i <= finish; i++) {
			sum += game.getCount(Location.valueOf(label+i));
		}
		return sum;
	}

	private boolean isPlayerAllWithinInnerTable(Color player) {
		Location b_off = Location.valueOf(GameImpl.playerLabel(player) + "_BEAR_OFF");
	
		int sum = sumCheckersInRange(player, 1, 6) + game.getCount(b_off);
		return sum == Game.NUMBER_OF_PLAYERS;
	}
	


}
