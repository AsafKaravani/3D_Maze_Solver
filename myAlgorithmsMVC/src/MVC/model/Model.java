package MVC.model;

import algorithms.mazeGenerators.Maze3D;

public interface Model {
	void convert();
	public Maze3D generateMaze(int layers, int rows, int columns);
}
