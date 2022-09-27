package View;
import java.io.IOException;

import engine.*;
public interface FirstFrameListener {
	public void onStartGame(String firstPLayer, String secondPlayer);
	public void onChampSelect(Game game, FirstFrame view) throws IOException;}
