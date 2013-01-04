package assignments.monopoly;

public class Property extends Square {

	private int value;
	private Group group;
	private Player ownedBy;

	public Property(String name, int value, Group group) {
		super(name);
		this.value = value;
		this.group = group; 
	}

	public boolean purchase(Player player) {
		return false;
	}

	public int getValue() {
		return value;
	}

	public void setOwner(Player player) {
		this.ownedBy = player;
	}

}
