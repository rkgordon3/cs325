package assignments.monopoly.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import assignments.monopoly.Monopoly;
import assignments.monopoly.Player;
import assignments.monopoly.Square;

public class MonopolyTests {
	
	private Monopoly game;
	private Player p;

	@Before
	public void before() {
		game = new Monopoly();
	    p = new Player("dog");
		game.join(p);
	}

	@Test
	public void AddPlayerToGame() {		
		assertTrue(game.isPlaying(p.getName()));	
	}
	
	@Test
	public void IsNewPlayerOnStartSquare() {
		Player newPlayer = game.getPlayer("dog");
		Square s = newPlayer.getSquare();
		assertEquals(s.index(), 0);	   
	}

}
