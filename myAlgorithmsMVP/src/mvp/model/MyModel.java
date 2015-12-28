package mvp.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Observable;

import algorithms.io.MyCompressorOutputStream;
import algorithms.io.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.myMaze3DGenerator;
import algorithms.search.AStar;
import algorithms.search.BestFirstSearch;
import algorithms.search.MazeAirDistance;
import algorithms.search.Solution;

import mvp.presenter.Presenter;

public class MyModel extends Observable implements Model {
	HashMap<String, Solution<Position>> solutionMap = new HashMap<>();
	HashMap<String, Maze3D> mazeMap = new HashMap<>();

	@Override
	public void generateMaze(String name, int layers, int rows, int columns) {
		if (mazeMap.containsKey(name)) {
			notifyObservers(null);
		} else {
			if (layers % 2 == 0) {
				layers++;
			}
			if (rows % 2 == 0) {
				rows++;
			}

			else if (columns % 2 == 0) {
				columns++;
			}
			Maze3D gameMaze = new myMaze3DGenerator().generate(layers, rows, columns);
			notifyObservers(gameMaze);
		}
	}

	@Override
	public void solveMaze(String name, String algorithm) {

		if (solutionMap.containsKey(name)) {
			if (algorithm.equals("BFS")) {
				notifyObservers(solutionMap.put(name, new BestFirstSearch().search(mazeMap.get(name))));
			} else if (algorithm.equals("AStar")) {
				notifyObservers(solutionMap.put(name,
						new AStar(new MazeAirDistance(), mazeMap.get(name).getGoalState()).search(mazeMap.get(name))));
			}
		} else {
			notifyObservers(null);
		}
	}

	@Override
	public Boolean mazeExists(String name) {
		if (mazeMap.containsKey(name)) {
			return true;
		}
	return false;
	}

	@Override
	public void displaySolution(String name) {
		if (solutionMap.containsKey(name))
			notifyObservers(solutionMap.get(name));
		else
			notifyObservers(null);
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
	public void sizeInMemory(String name) {
		if (mazeMap.containsKey(name))
			notifyObservers(((mazeMap.get(name).getMaze().length * mazeMap.get(name).getMaze()[0].length
					* mazeMap.get(name).getMaze()[0][0].length) + 6) * 4);
		else
			notifyObservers(-1);
	}

	@Override
	public void sizeInFile(String filename) {
		File f;
		f = new File(filename);
		notifyObservers((int) f.length());
	}

	@Override
	public void getDir(String path) {
		// TODO Auto-generated method stub
	}

	@Override
	public int[][] displayCrossSection(int index, String name) {
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
