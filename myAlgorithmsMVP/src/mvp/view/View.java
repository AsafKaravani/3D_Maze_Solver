package mvp.view;

import algorithms.mazeGenerators.Maze3D;

public interface View {
	public void displayMaze(Maze3D maze);
	public int getUserCommand();
	

}
