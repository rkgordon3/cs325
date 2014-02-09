package frs2.hotgammon.alphamon;


public interface Board {
	public boolean place(Color player, Location loc);
	public boolean remove(Color player, Location loc);
	public void clear();
	public Position getPosition(Location loc);
}
