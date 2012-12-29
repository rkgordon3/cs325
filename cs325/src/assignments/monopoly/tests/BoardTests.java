package assignments.monopoly.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import assignments.monopoly.Board;
import assignments.monopoly.Player;

public class BoardTests {

	@Test
	public void test() {
		Player player = new Player();
		
		Board board = new Board();
		board.addPlayer(player, 0);
		assertEquals(board.getPlayer(0), player);
	}

}
