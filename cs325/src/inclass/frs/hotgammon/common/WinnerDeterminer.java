package inclass.frs.hotgammon.common;

public interface WinnerDeterminer {
	public Color winner(int turnCount);
	public void setGame(Game game);

}
