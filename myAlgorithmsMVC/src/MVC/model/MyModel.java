package MVC.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import MVC.controller.Controller;
import algorithms.io.MyCompressorOutputStream;
import algorithms.io.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.myMaze3DGenerator;
import algorithms.search.AStar;
import algorithms.search.BestFirstSearch;
import algorithms.search.Heuristic;
import algorithms.search.MazeAirDistance;
import algorithms.search.Solution;
import algorithms.search.State;

public class MyModel implements Model {

	Controller ctrl;
	HashMap<String,Maze3D> mazeMap;
	HashMap<String,Solution> solutionMap;

	@Override
	public void convert() {
		
	}
	
	public MyModel(Controller ctrl){
		this.ctrl = ctrl;
		mazeMap = new HashMap<String,Maze3D>();
		solutionMap = new HashMap<String,Solution>();
	}

	public Controller getCtrl() {
		return ctrl;
	}
	
	public void setCtrl(Controller ctrl) {
		this.ctrl = ctrl;
	}
	
	@Override
	public Maze3D generateMaze(String name,int layers, int rows, int columns){
	 	Maze3D gameMaze= new myMaze3DGenerator().generate(layers, rows, columns);
		mazeMap.put(name, gameMaze);
	 	return gameMaze;
		}

	@Override
	public Solution solveMaze(String name, String algorithm) {
		
			if (mazeMap.containsKey(name)) {
				if (algorithm.equals("BFS")) {
					solutionMap.put(name, new BestFirstSearch().search(mazeMap.get(name)));
					return solutionMap.get(name);
				}
				else if (algorithm.equals("Astar")) {
					solutionMap.put(name, new AStar(new MazeAirDistance(), mazeMap.get(name).getGoalState()).search(mazeMap.get(name)));
					return solutionMap.get(name);
				}
			
				else{
					return null;
				}			
			}
			else{
				return null;
			}
	
	}
	
	@Override
	public Maze3D display(String name){
			if (mazeMap.containsKey(name)) {
				Maze3D theMaze=mazeMap.get(name);
				return theMaze;
			}
		return null;
	}
	
	@Override
	public boolean mazeExists(String name){
		if(mazeMap.containsKey(name))
			return true;
		return false;
	}
	
	@Override
	public Solution displaySolution(String name){
		if (solutionMap.containsKey(name))
			return solutionMap.get(name);
		else
			return null;			
	}

	

	
	@Override
	public void saveToFile(String name, String fileName) {
		try {
			MyCompressorOutputStream<Maze3D> out = new MyCompressorOutputStream<>(new FileOutputStream(new File(MyModel.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath() + "\\" + fileName)));
			out.writeObject(mazeMap.get(name));
			out.close();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
	

	@Override
	public void loadFromFile(String name, String fileName) {
		try {
			MyDecompressorInputStream<Maze3D> in = new MyDecompressorInputStream<>(new FileInputStream(new File(MyModel.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath() + "\\" + fileName)));
			Maze3D maze = new Maze3D(in.readObject(new Maze3D(3, 3, 3)));
			mazeMap.put(name, maze);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	@Override
	public int sizeInMemory(String name) {
		if(mazeMap.containsKey(name))
			return ((mazeMap.get(name).getMaze().length * mazeMap.get(name).getMaze()[0].length * mazeMap.get(name).getMaze()[0][0].length) + 6) * 4;
		else
			return -1;
	}
	

	@Override
	public int sizeInFile(String filename) {
		File f;
		try {
			f = new File(MyModel.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath() + "\\" + filename);
			return (int) f.length();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return -1;
		
	}
	

	@Override
	public String getDir(String path) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public int[][] displayCrossSection(int index, String name) {
		// TODO Auto-generated method stub
		return null;
	}


	


}