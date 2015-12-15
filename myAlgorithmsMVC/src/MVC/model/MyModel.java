package MVC.model;

import MVC.controller.Controller;
import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.myMaze3DGenerator;
import algorithms.search.AStar;
import algorithms.search.MazeAirDistance;
import algorithms.search.Solution;

public class MyModel implements Model {

	Controller ctrl;

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

	
	public Maze3D generateMaze(int layers, int rows, int columns){
		
		return new myMaze3DGenerator().generate(layers, rows, columns);
		
	}
	
	public Solution solveMaze(Maze3D maze){
		
		return  new AStar(new MazeAirDistance(), maze.getGoalState()).search(maze);
	}
}
