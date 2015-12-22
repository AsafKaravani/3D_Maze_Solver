package boot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import algorithms.io.MyCompressorOutputStream;
import algorithms.io.MyDecompressorInputStream;
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
		
		System.out.println();
	
		try {
			MyCompressorOutputStream<Maze3D> myComp = new MyCompressorOutputStream<Maze3D>(new FileOutputStream("C:\\Users\\Asaf\\git\\my-algorithms\\myAlgorithms\\assets\\mazeSream"));
			myComp.writeObject(maze);
			myComp.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			MyDecompressorInputStream<Maze3D> myDecomp = new MyDecompressorInputStream<Maze3D>(new FileInputStream("C:\\Users\\Asaf\\git\\my-algorithms\\myAlgorithms\\assets\\mazeSream"));
			Maze3D readMaze = new Maze3D(myDecomp.readObject(maze));
			System.out.println("The maze that was read:");
			System.out.println(readMaze);
			myDecomp.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
