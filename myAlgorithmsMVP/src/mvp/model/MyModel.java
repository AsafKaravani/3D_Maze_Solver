package mvp.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.Future;

import algorithms.io.MyCompressorOutputStream;
import algorithms.io.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.myMaze3DGenerator;
import algorithms.search.AStar;
import algorithms.search.BestFirstSearch;
import algorithms.search.MazeAirDistance;
import algorithms.search.Solution;
import mvp.model.notifers.MazeCreationNotifier;
import mvp.model.notifers.MazeSolutionNotifier;
import mvp.presenter.Presenter;

public class MyModel extends Observable implements Model {
	HashMap<String, Solution<Position>> solutionMap = new HashMap<>();
	HashMap<String, Maze3D> mazeMap = new HashMap<>();

	@Override
	public void generateMaze(String name, int layers, int rows, int columns) {
		if (mazeMap.containsKey(name)) {
			setChanged();
			notifyObservers(new MazeCreationNotifier(name, false));
		} else {
			if (layers % 2 == 0)
				layers++;

			if (rows % 2 == 0)
				rows++;

			else if (columns % 2 == 0)
				columns++;

			//NEEDS TO ADD FUTURE!

			Maze3D gameMaze = new myMaze3DGenerator().generate(layers, rows, columns);
			mazeMap.remove(name);
			mazeMap.put(name, gameMaze);
			setChanged();
			notifyObservers(new MazeCreationNotifier(name, true));
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

	@SuppressWarnings("unchecked")
	@Override
	public void solveMaze(String name, String algorithm) {
		setChanged();
		if (mazeMap.containsKey(name)) {
			if (!solutionMap.containsKey(name)) {
				if (algorithm.equals("BFS")) {
					solutionMap.put(name, new BestFirstSearch().search(mazeMap.get(name)));
				} else if (algorithm.equals("AStar")) {					
							solutionMap.put(name, new AStar(new MazeAirDistance(), mazeMap.get(name).getGoalState())
									.search(mazeMap.get(name)));
				}
				notifyObservers(new MazeSolutionNotifier(name, true));
			} else {
				notifyObservers(new MazeSolutionNotifier(name, false));
			}
		} else {
			notifyObservers(new MazeSolutionNotifier(name, false));
		}

	}

	@Override
	public boolean mazeExists(String name) {
		if (mazeMap.containsKey(name)) {
			return true;
		}
		return false;
	}

	@Override
	public Solution<Position> getSolution(String name) {
		if (solutionMap.containsKey(name))
			return solutionMap.get(name);
		else
			return null;
	}

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

	@Override
	public Maze3D getMaze(String name) {
		if (mazeMap.containsKey(name))
			return mazeMap.get(name);
		else
			return null;
	}
}
