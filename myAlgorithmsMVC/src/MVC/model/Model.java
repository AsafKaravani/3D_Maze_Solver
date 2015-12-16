package MVC.model;

import algorithms.mazeGenerators.Maze3D;
import algorithms.search.Solution;

public interface Model {
	void convert();
	public Maze3D generateMaze(String name,int layers, int rows, int columns);
	public int[] mazeSize(String name);
	public Solution solveMaze(String name,String method);
	public Maze3D display(String name);
}
