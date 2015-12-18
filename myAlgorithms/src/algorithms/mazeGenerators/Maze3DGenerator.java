package algorithms.mazeGenerators;


public interface Maze3DGenerator {
	/**
	*@author Yaniv and asaf
	*@return generate the maze using the 3 parameters and set them as cell so that it will be better to the DFS algo on them.
	 */
	public Maze3D generate(int layers, int rows, int column);
	
	public String measureAlgorithmTime(int layers, int rows, int column);
}
