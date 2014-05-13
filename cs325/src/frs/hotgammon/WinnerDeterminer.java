package frs.hotgammon;

import frs.hotgammon.framework.Color;

public interface WinnerDeterminer {
	public Color winner(int turnCount);
	public boolean isGameOver(int turnCount);
}
