package inclass.frs.hotgammon.tests;

import static org.junit.Assert.*;
import inclass.frs.hotgammon.common.Board;
import inclass.frs.hotgammon.common.Color;

import org.junit.Before;
import org.junit.Test;

public class TestBoard {
	
	Board board;
	
	@Before
	public void setup() {
		board = new Board(10);
	}

	@Test
	public void placeBlackCheckerOnBoard() {
		board.place(0, Color.BLACK);
		assertEquals(board.getPosition(0).player, Color.BLACK);
		assertEquals(1, board.getPosition(0).count);	
	}
	
	@Test
	public void removeBlackCheckerFromBoard() {
		board.place(0, Color.BLACK);
		board.remove(0);
		assertEquals(0, board.getPosition(0).count);		
	}

}
