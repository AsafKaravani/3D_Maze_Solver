package MVC.model;

import algorithms.mazeGenerators.Maze3D;
import algorithms.search.Solution;

public interface Model {
	void convert();
	/**
	*@author Yaniv and Asaf
	*@return Generate the maze from the parameters.
	 */
	public Maze3D generateMaze(String name,int layers, int rows, int columns);
	
	/**
	*@author Yaniv and Asaf
	*@return Gets the solution of a maze.
	 */
	public Solution solveMaze(String name,String method);
	
	/**
	*@author Yaniv and Asaf
	*@return A maze by the given name, if there is no such maze it will return null.
	 */
	public Maze3D display(String name);
	
	/**
	*@author Yaniv and Asaf
	*@return A solution that contains the steps to solve the maze.
	 */
	public Solution displaySolution(String name);
	
	/**
	*@author Yaniv and Asaf
	*@return True if a maze exists in the HashMap and false else.
	 */
	public boolean mazeExists(String name);
	
	/**
	*@author Yaniv and Asaf
	*@return Nothing.
	 */
	public void saveToFile(String name, String fileName);
	
	/**
	*@author Yaniv and Asaf
	*@return Nothing.
	 */
	public void loadFromFile(String name, String fileName);
	
	/**
	*@author Yaniv and Asaf
	*@return The size of the maze in the memory.
	 */
	public int sizeInMemory(String name);
	/**
	*@author Yaniv and Asaf
	*@return Give back the size of the maze in a file.
	 */
	public int sizeInFile(String name);
	/**
	*@author Yaniv and Asaf
	*@return The files and folders in a certain path.
	 */
	public String getDir(String path);
	/**
	*@author Yaniv and Asaf
	*@return A slice of the 3D maze.
	 */
	public int[][] displayCrossSection(int index, String name);
	
}
