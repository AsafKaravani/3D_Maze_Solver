package algorithms.mazeGenerators;

public abstract class Maze3DGeneratorAbstract implements Maze3DGenerator {

	@Override
	public abstract Maze3D generate(String name,int layers, int rows, int column);

	@Override
	public String measureAlgorithmTime(int layers, int rows, int column) {
		long startTime = System.currentTimeMillis();
		Maze3D maze = generate(null, layers, rows, column);
		long endTime = System.currentTimeMillis();
		
		Double totalTimeinSeconds = (double) ((startTime - endTime)/1000);
		
		return totalTimeinSeconds.toString();
	}
	

}
