package assignments.monopoly.tests;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

import assignments.monopoly.Monopoly;
import assignments.monopoly.Player;
import assignments.monopoly.Roll;
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
	public void PlayerShouldBePlaying() {		
		assertTrue(game.isPlaying(p.getName()));	
	}
	
	@Test
	public void PlayerShouldBeOnSquareZero() {
		Player newPlayer = game.getPlayer("dog");
		Square s = newPlayer.getSquare();
		assertEquals(s.index(), 0);	   
	}
	
	@Test 
	public void PlayerShouldBeMovedAfterRoll() {
		Roll roll = p.roll();
		Square currentSquare = p.getSquare();
		int sum = roll.sum();
		Square s = game.getSquare(sum);
		p.moveTo(s);
		assertThat(p.getSquare().index(), not(currentSquare.index()));
		assertEquals(p.getSquare().index(), sum+currentSquare.index());
	}
}
