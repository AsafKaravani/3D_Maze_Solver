package boot;

import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.myMaze3DGenerator;
import algorithms.search.AStar;
import algorithms.search.BestFirstSearch;
import algorithms.search.Heuristic;
import algorithms.search.MazeAirDistance;
import algorithms.search.MazeManhattenDistance;
import algorithms.search.Solution;
import algorithms.search.State;

public class run {

	public static void main(String[] args) {
		Maze3D maze = new myMaze3DGenerator().generate(5, 7, 7);
		BestFirstSearch bfs = new BestFirstSearch();
		AStar aStrMan = new AStar(new MazeManhattenDistance(), maze.getGoalState());
		AStar aStrAir =  new AStar(new MazeAirDistance(), maze.getGoalState());

		Solution<Position> sol1 = bfs.search(maze);
		Solution<Position> sol2 = aStrMan.search(maze);
		Solution<Position> sol3 =aStrAir.search(maze);
		
		System.out.println(maze);
		
		System.out.println("------------Results:------------");
		System.out.println("BFS: " + bfs.getNumberOfNodesEvalueted() + " nodes evalueted.");
		System.out.println("A*: " + aStrMan.getNumberOfNodesEvalueted() + " nodes evalueted.");
		System.out.println("A*: " + aStrAir.getNumberOfNodesEvalueted() + " nodes evalueted.");
		
		
	}

}
