package assignments.safe;

public interface Safe {

	public static final String BLANK_DISPLAY = "      ";
	public static final String OPEN_DISPLAY = " OPEN ";

	public String readDisplay();

	public boolean isLocked();

	public void enter(Button b);

}
