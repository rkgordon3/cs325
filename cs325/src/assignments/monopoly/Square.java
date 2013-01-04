package assignments.monopoly;

public class Square {

	private String name;
	
	public Square( String name) {
		this.name = name;
	}

	public int index() {
		return Squares.indexOf(name);
	}

	public String getName() {
		return name;
	}
	
}
