package MVC.model;

import algorithms.mazeGenerators.Maze3D;
import algorithms.search.Solution;

public interface Model {
	void convert();
	public Maze3D generateMaze(String name,int layers, int rows, int columns);
	public Solution solveMaze(String name,String method);
	public Maze3D display(String name);
	public Solution displaySolution(String name);
	public boolean mazeExists(String name);
	public void saveToFile(String name, String fileName);
	public void loadFromFile(String name, String fileName);
	public int sizeInMemory(String name);
	public int sizeInFile(String name);
	public String getDir(String path);
	public int[][] displayCrossSection(int index, String name);
	
}
