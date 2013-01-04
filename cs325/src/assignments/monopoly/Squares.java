package assignments.monopoly;

public class Squares {
	
	private static Square[] squares = new Square[] {
		new Square("Go"),
		new Property("Mediterranean Ave", 60, Group.NAVY),
		new Square("Community Chest"),
		new Property( "Baltic Ave", 60, Group.NAVY),
		new Square( "Income Tax"),
		new Property( "Oriental Ave", 100, Group.TEAL),
		new Property("Reading RR", 200, Group.RAILROAD),
		new Square( "Chance"),
		new Property( "Vermont Ave", 100, Group.TEAL),
		new Property( "Connecticut Ave", 120, Group.TEAL),	

		new Square("Jail"),	
		new Property("St. Charles Place", 140, Group.PURPLE),
		new Property("Electric Company", 150, Group.UTILITY),
		new Property("States Ave", 140, Group.PURPLE),
		new Property("Virginia Ave", 160, Group.PURPLE),
		new Property("Pennsylvania RR", 200, Group.RAILROAD),
		new Property("St. James Place", 180, Group.ORANGE),
		new Square("Community Chest"),
		new Property("Tennessee Ave", 180, Group.ORANGE),
		new Property("New York Ave", 200, Group.ORANGE),
		
		new Square("Free Parking"),	
		new Property("Kentucky Ave", 220, Group.RED),
		new Square("Chance"),
		new Property("Indiana Ave", 220, Group.RED),
		new Property("Illinois Ave", 240, Group.RED),
		new Property("B&O RR", 200, Group.RAILROAD),
		new Property("Atlantic Ave", 260, Group.YELLOW),
		new Property("Ventnor Ave", 260, Group.YELLOW),
		new Property("Water Works", 150, Group.UTILITY),		
		new Property("Marvin Gardens", 280, Group.ORANGE),
		
		new Square("Go To Jail"),
		new Property("Pacific Ave", 300, Group.GREEN),
		new Property("North Carolina Ave", 300, Group.GREEN),
		new Square("Community Chest"),
		new Property("Pennsylvania Ave", 320, Group.GREEN),
		new Property("Short Line", 200, Group.RAILROAD),
		new Square("Chance"),
		new Property("Park Place", 350, Group.BLUE),
		new Square("Luxury Tax"),		
		new Property("Boardwalk", 400, Group.BLUE),
		
	};

	public static Square get(int index) {
		return squares[index];
	}

	public static int indexOf(String name) {
		for (int i = 0; i < squares.length; i++) {
			if (name.equalsIgnoreCase(squares[i].getName())) {
				return i;
			}
		}
		return -1;
	}

}
