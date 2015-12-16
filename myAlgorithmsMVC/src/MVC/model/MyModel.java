package MVC.model;

import java.util.ArrayList;

import MVC.controller.Controller;
import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.myMaze3DGenerator;
import algorithms.search.AStar;
import algorithms.search.MazeAirDistance;
import algorithms.search.Solution;

public class MyModel implements Model {

	Controller ctrl;
	ArrayList<Maze3D> mazeArchive;

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
		gameMaze.setMazeName(name);
		mazeArchive.add(gameMaze);
 	return gameMaze;
	}
	public int[] mazeSize(String name){
		for (Maze3D maze3d : mazeArchive) {
			if (maze3d.getMazeName().contains(name)) {
				System.out.println("the size of the maze is:");
			int[] gameMazeSize=new int[maze3d.getMaze().length];
			gameMazeSize[0]=maze3d.getMaze().length;
			gameMazeSize[1]=maze3d.getMaze()[0].length; 
			gameMazeSize[2]=maze3d.getMaze()[0][0].length;
			return gameMazeSize;
			}
			else{
				System.out.println("There are no maze of that name");
				return null;
			}
		}
		return null;
	}

	@Override
	public Solution solveMaze(String name, String method) {
		for (Maze3D maze3d : mazeArchive) {
			if (maze3d.getMazeName().contains(name)) {
			}
		}
		return null;
	}
	public Maze3D display(String name){
		for (Maze3D maze3d : mazeArchive) {
			if (maze3d.getMazeName().contains(name)) {
				return maze3d;
			}
		}
		System.out.println("There is no maze in that name");
		return null;
	}
	
}
