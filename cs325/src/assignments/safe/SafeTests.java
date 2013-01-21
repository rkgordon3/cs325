package assignments.safe;

import static org.junit.Assert.*;

import org.junit.Test;

public class SafeTests {
	@Test
	public void shouldBeLockedWithBlankDisplay() {
		Safe s = new SafeImpl();
		assertEquals("Display should be blank",
				Safe.BLANK_DISPLAY, s.readDisplay());
		assertTrue("Safe should be locked", s.isLocked());			  
	}
	
	@Test
	public void shouldBeLockedAndDisplayPartialCodeAsEntered() {
		Safe s = new SafeImpl();
		s.enter('K');
		s.enter('1');
		s.enter('2');
		s.enter('3');
		assertEquals ("Display should read '123   '",
				"123   ", s.readDisplay());
		assertTrue("Safe should be locked", s.isLocked());
	}
	
	@Test
	public void shouldBeUnlockedAndDisplayOpen() {
		Safe s = new SafeImpl();
		s.enter('K');
		s.enter('1');
		s.enter('2');
		s.enter('3');
		s.enter('4');
		s.enter('5');
		s.enter('6');
		assertEquals ("Display should read ' OPEN '",
				Safe.OPEN_DISPLAY, s.readDisplay());
		assertFalse("Safe should be unlocked", s.isLocked());
	}
	
}
