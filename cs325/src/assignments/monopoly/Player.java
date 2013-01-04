package assignments.monopoly;


public class Player extends FinancialActor {

	private String name;
	Square square;
	
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
	
	public void buy() throws IllegalStateException {
		if (!(square instanceof Property)) {
			throw new IllegalStateException("Can not buy " + square.getName());
		}
		buy((Property) square);
	}

}
