package assignments.safe;

public class SafeImpl implements Safe {

	private int displaySize = 6;
	private char[] display = new char[displaySize];
	private int displayIndex = 0;
	private boolean locked = true;
	
	public SafeImpl() {
		System.arraycopy(Safe.BLANK_DISPLAY.toCharArray(), 0, display, 0, display.length);
	}
	
	@Override
	public String readDisplay() {
		return String.valueOf(display);
	}

	@Override
	public boolean isLocked() {
		return locked;
	}

	@Override
	public void enter(char c) {
		if (Character.isDigit(c)) {
		  display[displayIndex++] = c;
		}
		if (String.valueOf(display).equals("123456")) {
			System.arraycopy(Safe.OPEN_DISPLAY.toCharArray(), 0, display, 0, display.length);
			locked  = false;
		}
	}

}
