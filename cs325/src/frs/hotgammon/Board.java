package frs.hotgammon;

import frs.hotgammon.framework.Color;

public interface Board {
	public boolean place(Color player, int sqNumber);
	public boolean remove(Color player, int sqNumber);
	public Square getSquare(int sqNumber );
	public void clear();
}
