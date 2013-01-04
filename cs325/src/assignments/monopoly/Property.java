package assignments.monopoly;

public class Property extends Square {

	private int value;
	private Group group;
	private FinancialActor ownedBy;

	public Property(String name, int value, Group group) {
		super(name);
		this.value = value;
		this.group = group; 
	}

	public boolean purchase(FinancialActor player) {
		return false;
	}

	public int getValue() {
		return value;
	}

	public void setOwner(FinancialActor player) {
		this.ownedBy = player;
	}

}
