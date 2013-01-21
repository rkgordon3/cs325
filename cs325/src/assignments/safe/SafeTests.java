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
		s.enter(Button.KEY);
		s.enter(Button.B1);
		s.enter(Button.B2);
		s.enter(Button.B3);
		assertEquals ("Display should read '123   '",
				"123   ", s.readDisplay());
		assertTrue("Safe should be locked", s.isLocked());
	}
	
	@Test
	public void shouldBeUnlockedAndDisplayOpen() {
		Safe s = new SafeImpl();
		s.enter(Button.KEY);
		s.enter(Button.B1);
		s.enter(Button.B2);
		s.enter(Button.B3);
		s.enter(Button.B4);
		s.enter(Button.B5);
		s.enter(Button.B6);
		assertEquals ("Display should read ' OPEN '",
				Safe.OPEN_DISPLAY, s.readDisplay());
		assertFalse("Safe should be unlocked", s.isLocked());
	}
	
}
