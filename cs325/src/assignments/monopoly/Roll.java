package assignments.monopoly;

public class Roll {
	private int d1;
	private int d2;
	
	public Roll(int d1, int d2) {
		this.d1 = d1;
		this.d2 = d2;
	}
	public int sum() {
		return d1 + d2;
	}
	
}
