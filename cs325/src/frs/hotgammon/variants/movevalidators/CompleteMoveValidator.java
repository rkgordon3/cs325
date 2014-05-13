package frs.hotgammon.variants.movevalidators;

import frs.hotgammon.BaseDeterminer;
import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.Location;
import frs.hotgammon.MoveValidator;
import frs.hotgammon.common.GameImpl;

public class CompleteMoveValidator extends BaseDeterminer implements MoveValidator  {

   static final int INNER_TABLE_SZ = 6;

	@Override
	public boolean isValid(Location from, Location to) {
		
		//int proposedPtToMove = -1;
		for (int d : game.diceValuesLeft()) {
			Location.findLocation(game.getPlayerInTurn(), from, d);
		}
		// check direction
		int proposedDistance = Location.distance(from, to);
		int direction = proposedDistance/Math.abs(proposedDistance);
		int proposedPtToMove = Math.abs(proposedDistance);
		
		if (game.getPlayerInTurn().getSign() != direction) {
			return false;
		}
		 
		// If player attempts move from elsewhere when checker
		// on bar, return false.
		
		if (isPlayerOnBar(game.getPlayerInTurn()) && !isBar(from)) {
			return false;
		}
		boolean isOuterMostChecker = false;
		// Bear off attempt
		boolean bear_off = (to == Location.R_BEAR_OFF) || (to == Location.B_BEAR_OFF);	
		if (bear_off) {   
				if ( !isPlayerAllWithinInnerTable(game.getPlayerInTurn())) {
					return false;
				}
			
			    // Are there checkers behind me?
				isOuterMostChecker = sumCheckersInRange(game.getPlayerInTurn(), proposedPtToMove + 1, INNER_TABLE_SZ) == 0;
				
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
			if (proposedPtToMove == roll[i]  
				  || (isOuterMostChecker && proposedPtToMove < roll[i])) {
				moveDistance = roll[i];
				break; // found good die value
			}
		}
		return moveDistance != 0;
	}
	
	private int sumCheckersInRange(Color player, int start, int finish) {
		int sum = 0;
		String label = GameImpl.playerLabel(player);
		for (int i =start; i <= finish; i++) {
			sum += game.getCount(Location.valueOf(label+i));
		}
		return sum;
	}

	private boolean isPlayerAllWithinInnerTable(Color player) {
		Location b_off = Location.valueOf(GameImpl.playerLabel(player) + "_BEAR_OFF");
		return (sumCheckersInRange(player, 1, INNER_TABLE_SZ) + game.getCount(b_off)) == Game.NUMBER_OF_PLAYERS;
	}
	
	private boolean isPlayerOnBar(Color player) {
		return game.getCount(Location.valueOf(GameImpl.playerLabel(player) + "_BAR")) > 0;
	}

	private boolean isBar(Location location) {
		return location == Location.R_BAR || location == Location.B_BAR;
	}	
}
