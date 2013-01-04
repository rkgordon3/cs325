package assignments.monopoly;

import java.util.ArrayList;

public class Player {

	private String name;
	private Square square;
	private ArrayList<Property> ownedProperties = new ArrayList<Property>();
	private Monopoly game;
	private int cashOnHand = Monopoly.INITIAL_PLAY_CASH;

	public Player(String name) {
		this.name = name;
	}

	public Square getSquare() {
		return square;
	}

	public String getName() {
		return name;
	}

	public Roll roll() {
		return Monopoly.PairOfDice.roll();
	}

	public void moveTo(Square square) {
		this.square = square;
	}

	public void buy() {
		Property p;
		if (square instanceof Property) {
			p = (Property) square;
			if (game.buy(this,p)) {
				ownedProperties.add(p);
			}
		} else {
			throw new IllegalStateException("Square can not be purchased");
		}
		
	}

	public ArrayList<Property> getOwnedProperties() {
		return ownedProperties;
	}

	public int getCashOnHand() {
		
		return cashOnHand;
	}

	public void setGame(Monopoly game) {
		this.game = game;
		
	}

	public int withdraw(int amt) {
		if (amt > this.cashOnHand) {
			return -1;
		}
		this.cashOnHand  -= amt;
		return this.cashOnHand;
	}


}
