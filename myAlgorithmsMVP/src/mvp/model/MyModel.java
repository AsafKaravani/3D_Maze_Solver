package mvp.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.Future;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import algorithms.io.MyCompressorOutputStream;
import algorithms.io.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.myMaze3DGenerator;
import algorithms.search.AStar;
import algorithms.search.BestFirstSearch;
import algorithms.search.MazeAirDistance;
import algorithms.search.Solution;
import javafx.geometry.Pos;
import mvp.model.notifers.MazeCreationNotifier;
import mvp.model.notifers.MazeSolutionNotifier;
import mvp.presenter.Presenter;

public class MyModel extends Observable implements Model {
	HashMap<String, Solution<Position>> solutionMap = new HashMap<>();
	HashMap<String, Maze3D> mazeMap = new HashMap<>();
	/**
	*@author Yaniv and Asaf
	*@return generate the maze using the name layer rows and columns
	 */
	@Override
	public Maze3D generateMaze(String name, int layers, int rows, int columns) {
		
		if (mazeMap.containsKey(name)) {
			setChanged();
			notifyObservers(new MazeCreationNotifier(name, false));
			return null;
		} else {
			if (layers % 2 == 0)
				layers++;

			if (rows % 2 == 0)
				rows++;

			if (columns % 2 == 0)
				columns++;

			
			Maze3D gameMaze = new myMaze3DGenerator().generate(layers, rows, columns);
			mazeMap.remove(name);
			mazeMap.put(name, gameMaze);
			setChanged();
			notifyObservers(new MazeCreationNotifier(name, true));
			return gameMaze;
			
		}
	}

	public HashMap<String, Solution<Position>> getSolutionMap() {
		return solutionMap;
	}

	public void setSolutionMap(HashMap<String, Solution<Position>> solutionMap) {
		this.solutionMap = solutionMap;
	}

	public HashMap<String, Maze3D> getMazeMap() {
		return mazeMap;
	}

	public void setMazeMap(HashMap<String, Maze3D> mazeMap) {
		this.mazeMap = mazeMap;
	}
	/**
	*@author Yaniv and Asaf
	*@return gets from the presenter the name of the maze and the algo to solve it and from that its create a solution
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Solution<Position> solveMaze(String name, String algorithm) {
		setChanged();
		if (mazeMap.containsKey(name)) {
			if (!solutionMap.containsKey(name)) {
				if (algorithm.equals("BFS")) {
					try {
						Socket server = new Socket("localhost", 5400);
						if (server.isConnected()) {		
							ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
							out.flush();							
							out.writeObject("solve");
							out.writeObject(mazeMap.get(name).compress());
							out.writeObject("BFS");					
							ObjectInputStream in = new ObjectInputStream(server.getInputStream());
							Solution<Position> sol = (Solution<Position>) in.readObject();
							in.close();
							out.close();
							return sol;
							
							
						} else {
							notifyObservers(new MazeSolutionNotifier(name, false));
							return null;
						}
						
					} catch (UnknownHostException e) {
						e.printStackTrace();
					} catch (ConnectException e) {
						System.out.println("Could not connect to the server.");
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} 
					

				//	return new BestFirstSearch().search(mazeMap.get(name));
					
				} else if (algorithm.equals("AStar")) {		
					return new AStar(new MazeAirDistance(), mazeMap.get(name).getGoalState())
							.search(mazeMap.get(name));

				}
				notifyObservers(new MazeSolutionNotifier(name, true));
				return null;
			} else {
				notifyObservers(new MazeSolutionNotifier(name, false));
				return null;
			}
		} else {
			notifyObservers(new MazeSolutionNotifier(name, false));
			return null;
		}

	}
	/**
	*@author Yaniv and Asaf
	*@return checks if there is a maze in the hashmap
	 */ 
	@Override
	public boolean mazeExists(String name) {
		if (mazeMap.containsKey(name)) {
			return true;
		}
		return false;
	}
	/**
	*@author Yaniv and Asaf
	*@return return the solution
	 */
	@Override
	public Solution<Position> getSolution(String name) {
		if (solutionMap.containsKey(name))
			return solutionMap.get(name);
		else
			return null;
	}
	/**
	*@author Yaniv and Asaf
	*@return saving the file to the ordered path
	 */
	@Override
	public void saveToFile(String name, String fileName) {
		try {
			MyCompressorOutputStream<Maze3D> out = new MyCompressorOutputStream<>(
					new FileOutputStream(new File(fileName)));
			// MyCompressorOutputStream<Maze3D> out = new
			// MyCompressorOutputStream<>(new FileOutputStream(new
			// File(MyModel.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()
			// + "\\" + fileName)));
			out.writeObject(mazeMap.get(name));
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	*@author Yaniv and Asaf
	*@return load the data from the file
	 */
	@Override
	public void loadFromFile(String name, String fileName) {
		try {
			MyDecompressorInputStream<Maze3D> in = new MyDecompressorInputStream<>(
					new FileInputStream(new File(fileName)));
			// MyDecompressorInputStream<Maze3D> in = new
			// MyDecompressorInputStream<>(new FileInputStream(new
			// File(MyModel.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()
			// + "\\" + fileName)));
			Maze3D maze = new Maze3D(in.readObject(new Maze3D(3, 3, 3)));
			mazeMap.put(name, maze);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	*@author Yaniv and Asaf
	*@return checks the size of the file
	 */
	@Override
	public int sizeInMemory(String name) {
		if (mazeMap.containsKey(name))
			return (((mazeMap.get(name).getMaze().length * mazeMap.get(name).getMaze()[0].length
					* mazeMap.get(name).getMaze()[0][0].length) + 6) * 4);
		else
			return -1;
	}

	@Override
	public int sizeInFile(String filename) {
		File f;
		f = new File(filename);
		return (int) f.length();
	}

	@Override
	public void getDir(String path) {
		// TODO Auto-generated method stub
	}

	@Override
	public int[][] getCrossSection(int index, String name) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	*@author Yaniv and Asaf
	*@return the maze that have that name
	 */
	@Override
	public Maze3D getMaze(String name) {
		if (mazeMap.containsKey(name))
			return mazeMap.get(name);
		else
			return null;
	}
	/**
	*@author Yaniv and Asaf
	*@return save the hashmap
	 */
	@Override
	public void saveMaps(){
		try {
			ObjectOutput out = new ObjectOutputStream(new FileOutputStream("maps"));
			out.writeObject(mazeMap);
			out.writeObject(solutionMap);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	*@author Yaniv and Asaf
	*@return load the saved mazes
	 */
	public void loadMaps(){
		File f = new File("maps");
		if(!(f.exists()))
			return;
		
			try {
				ObjectInput in = new ObjectInputStream(new FileInputStream("maps"));
				mazeMap = (HashMap<String, Maze3D>)in.readObject();
				solutionMap = (HashMap<String, Solution<Position>>) in.readObject();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		
		
	}
}
