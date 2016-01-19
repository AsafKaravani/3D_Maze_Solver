package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import algorithms.io.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.myMaze3DGenerator;
import algorithms.search.AStar;
import algorithms.search.BestFirstSearch;
import algorithms.search.MazeAirDistance;
import algorithms.search.Solution;

public class MazeSolveHandler implements ClinetHandler {

	@Override
	public void handleClient(ObjectInputStream inFromClient, ObjectOutputStream outToClient) {
		try {

			outToClient.flush();
			byte[] compressedMaze = (byte[]) inFromClient.readObject();
			Maze3D mazeToSolve = new Maze3D(new Maze3D(3, 3, 3).deCompress(compressedMaze));
			String huristic = (String) inFromClient.readObject();
			Solution<Position> sol;
			if (huristic.compareTo("BFS") == 0) {
				sol = new BestFirstSearch().search(mazeToSolve);
			} else /* (huristic.compareTo("AStar")== 0) */ {
				sol = new AStar(new MazeAirDistance(), mazeToSolve.getGoalState()).search(mazeToSolve);
			}

			outToClient.writeObject(sol);

			inFromClient.close();
			outToClient.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
