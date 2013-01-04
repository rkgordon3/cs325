package assignments.monopoly;

import java.util.ArrayList;

public class FinancialActor {

	private ArrayList<Property> ownedProperties = new ArrayList<Property>();
	private int cashOnHand = Monopoly.INITIAL_PLAY_CASH;
	Monopoly game;

	public void buy(Property p) {
		if (game.buy(this, p)) {
			ownedProperties.add(p);
		}
	}

	public ArrayList<Property> getOwnedProperties() {
		return ownedProperties;
	}

	public int getCashOnHand() {
		return cashOnHand;
	}

	public int withdraw(int amt) {
		if (amt > this.cashOnHand) {
			return -1;
		}
		this.cashOnHand -= amt;
		return this.cashOnHand;
	}

	public void setGame(Monopoly game) {
		this.game = game;

	}

	public void deposit(int value) {
		this.cashOnHand += value;		
	}

}
