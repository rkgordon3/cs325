package assignments.monopoly;

public class Player {

	private String name;
	private Square square;

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

	public void move(int sum) {
		// TODO Auto-generated method stub
	}

	public void moveTo(Square square) {
		this.square = square;
	}

}
