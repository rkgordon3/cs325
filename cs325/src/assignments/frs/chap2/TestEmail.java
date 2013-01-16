package assignments.frs.chap2;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestEmail {

	@Test
	public void test() {
		EmailAddress e = new EmailAddress("rgrodon9@smumn.csdept.edu");
		assertTrue(e.isValid());
	}

}
