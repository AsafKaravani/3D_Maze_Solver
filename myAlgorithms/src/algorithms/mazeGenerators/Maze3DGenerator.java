package algorithms.mazeGenerators;

public interface Maze3DGenerator {
	public Maze3D generate(int layers, int rows, int column);
	public String measureAlgorithmTime(int layers, int rows, int column);
}
