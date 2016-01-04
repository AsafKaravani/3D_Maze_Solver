package mvp.model;


import java.util.HashMap;

import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public interface Model  {
	/**
	*@author Yaniv and Asaf
	*@return Generate the maze from the parameters.
	 */
	public Maze3D generateMaze(String name,int layers, int rows, int columns);
	
	/**
	*@author Yaniv and Asaf
	*@return Gets the solution of a maze.
	 */
	public Solution<Position> solveMaze(String name,String method);
	
	/**
	*@author Yaniv and Asaf
	*@return A maze by the given name, if there is no such maze it will return null.
	 */
	public Maze3D getMaze(String name);
	
	/**
	*@author Yaniv and Asaf
	*@return A solution that contains the steps to solve the maze.
	 */
	public Solution<Position> getSolution(String name);
	
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
	public void getDir(String path);
	
	/**
	*@author Yaniv and Asaf
	*@return A slice of the 3D maze.
	 */
	public int[][] getCrossSection(int index, String name);
	
	
	public HashMap<String, Solution<Position>> getSolutionMap();
	
	public HashMap<String, Maze3D> getMazeMap();
	
}
