package mvp.view;

import algorithms.mazeGenerators.Maze3D;
import algorithms.search.Solution;

public interface View {
	
	public void displayMaze(Maze3D maze);
	public void displayMessage(String message);
	public void displaySolution(Solution sol);
	public void getUserCommand();
	public void run();
	public void setConnectedToServer(boolean connectedToServer);

}
