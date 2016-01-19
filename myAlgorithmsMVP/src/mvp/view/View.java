package mvp.view;

import algorithms.mazeGenerators.Maze3D;
import algorithms.search.Solution;

public interface View {
	/**
	*@author Yaniv and Asaf
	*@return display the maze to the screen
	 */
	public void displayMaze(Maze3D maze);
	/**
	*@author Yaniv and Asaf
	*@return display the message to the screen
	 */
	public void displayMessage(String message);
	/**
	*@author Yaniv and Asaf
	*@return a wigit use for decision witch algo needed to be use
	 */
	public void displaySolution(Solution sol);
	/**
	*@author Yaniv and Asaf
	*@return display the solution to the screen
	 */
	public void getUserCommand();
	public void run();
	

}
