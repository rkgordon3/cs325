package frs.hotgammon.alphamon;

public interface Board {
	public boolean place(Color player, int sqNumber);
	public boolean remove(Color player, int sqNumber);
	public Square getSquare(int sqNumber );
}
