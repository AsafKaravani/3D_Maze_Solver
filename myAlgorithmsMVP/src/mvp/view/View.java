package mvp.view;

import algorithms.mazeGenerators.Maze3D;

public interface View {
	
	public void displayMaze(Maze3D maze);
	public void displayMessage(String message);
	public void getUserCommand();
	

}
