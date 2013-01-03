package assignments.monopoly;

import java.util.Random;


public class Die {
	private static final int N_FACES = 6;
	private Random random = new Random();
	
	public int roll() {
		return random.nextInt(N_FACES) + 1;
	}

}
