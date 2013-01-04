package assignments.monopoly.tests;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import assignments.monopoly.Monopoly;
import assignments.monopoly.Player;
import assignments.monopoly.Property;
import assignments.monopoly.Roll;
import assignments.monopoly.Square;

public class MonopolyTests {
	
	private Monopoly game;
	private Player p;
	private Player p2;
	private Player p3;

	@Before
	public void before() {
		game = new Monopoly();
	    p = new Player("dog");
		game.join(p);
	    p2 = new Player("race car");
		p3 = new Player("hat");
		game.join(p2);
		game.join(p3);
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
		System.out.println("roll " + sum);
		Square s = game.getSquare(sum+currentSquare.index());
		p.moveTo(s);
		assertThat(p.getSquare().index(), not(currentSquare.index()));
		assertEquals(p.getSquare().index(), sum+currentSquare.index());
	}
	
	@Test
	public void PlayerShouldPassGo() {
		Square currentSquare = game.getSquare(35);
		p.moveTo(currentSquare);
		int sum = 8;
		Square s = game.getSquare(sum+currentSquare.index());
		p.moveTo(s);
		assertThat(p.getSquare().index(), not(currentSquare.index()));
		assertEquals(p.getSquare().index(), (sum+currentSquare.index()) % Monopoly.N_SQUARES);
	}
	
	@Test
	public void ThreePlayersShouldBePlaying() {
		assertTrue(game.getPlayers().size() == 3);
		assertTrue(game.isPlaying(p.getName()));
		assertTrue(game.isPlaying(p2.getName()));
		assertTrue(game.isPlaying(p3.getName()));
	}
	
	@Test
	public void EachPlayerShouldUniqueTurnValue() {		
		ArrayList<Player> playList = game.assignTurns();
		assertTrue(playList.size() == 3);
		assertTrue(playList.contains(p));
		assertTrue(playList.contains(p2));
		assertTrue(playList.contains(p3));	
		assertThat(playList.indexOf(p), not(playList.indexOf(p2)));
		assertThat(playList.indexOf(p), not(playList.indexOf(p3)));
		assertThat(playList.indexOf(p2), not(playList.indexOf(p3)));
	}
	
	@Test(expected = IllegalStateException.class)
	public void PlayerAttemptsToBuyGo() {
		Square s = game.getSquare("Go");
		p.moveTo(s);
		p.buy();		
	}
	
	@Test(expected = IllegalStateException.class)
	public void PlayerAttemptsToBuyChance() {
		Square s = game.getSquare("Chance");
		p.moveTo(s);
		p.buy();		
	}
	
	@Test
	public void PlayerAttemptsToBuyAvailableProperty() {
		Property s = (Property) game.getSquare("Baltic Ave");
		int initialBal = p.getCashOnHand();
		p.moveTo(s);
		p.buy();
		assertTrue(p.getOwnedProperties().contains(s));
		assertTrue(initialBal-s.getValue() == p.getCashOnHand());
	}

}
