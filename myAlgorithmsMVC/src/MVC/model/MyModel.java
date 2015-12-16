package MVC.model;

import java.util.ArrayList;
import java.util.HashMap;

import MVC.controller.Controller;
import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.myMaze3DGenerator;
import algorithms.search.AStar;
import algorithms.search.MazeAirDistance;
import algorithms.search.Solution;

public class MyModel implements Model {

	Controller ctrl;
	HashMap<String,Maze3D> mazeMap;
	HashMap<String,Solution> solutionMap;

	@Override
	public void convert() {
		
	}
	
	public MyModel(Controller ctrl){
		this.ctrl = ctrl;
	}

	public Controller getCtrl() {
		return ctrl;
	}

	public void setCtrl(Controller ctrl) {
		this.ctrl = ctrl;
	}

	
	public Maze3D generateMaze(String name,int layers, int rows, int columns){
 	Maze3D gameMaze= new myMaze3DGenerator().generate(layers, rows, columns);
	mazeMap.put(name, gameMaze);
 	return gameMaze;
	}
	public int[] mazeSize(String name){
			if (mazeMap.containsKey(name)) {
				System.out.println("the size of the maze is:");
			int[] gameMazeSize=new int[3];
			gameMazeSize[0]=mazeMap.get(name).getMaze().length;
			gameMazeSize[1]=mazeMap.get(name).getMaze()[0].length;
			gameMazeSize[2]=mazeMap.get(name).getMaze()[0][0].length;
			return gameMazeSize;
			}
			else{
				System.out.println("There are no maze of that name");
				return null;
			}
	}

	@Override
	public Solution solveMaze(String name, String algorithm) {
		
			if (mazeMap.containsKey(name)) {
				if (algorithm=="bfs") {
					
				}
			}
		return null;
	}
	public Maze3D display(String name){
			if (mazeMap.containsKey(name)) {
				Maze3D theMaze=mazeMap.get(name);
				return theMaze;
			}
		System.out.println("There is no maze in that name");
		return null;
	}
	
}
