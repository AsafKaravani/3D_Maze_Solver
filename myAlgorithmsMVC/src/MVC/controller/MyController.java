package MVC.controller;

import java.util.HashMap;

import MVC.model.Model;
import MVC.view.View;
import algorithms.mazeGenerators.Maze3D;

public class MyController implements Controller {

	View view;
	Model model;
	
	public MyController(){

	}
	public void start(){
		
	}

	public View getView() {
		return view;
	}
	public void setView(View view) {
		this.view = view;
	}
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	@Override
	public void convert() {
		model.convert();
		
	}

	@Override
	public void display() {
		view.display();
		
	}
	
	public HashMap<String, Command> initCommands(){
		HashMap<String, Command> commandMap = new  HashMap<String, Command>();
		
		commandMap.put("generate 3d maze", new Command() {
			
			@Override
			public void print() {
				System.out.println("generate 3d maze <name> <other params> (genetaring a new 3d maze)");		
			}
			
			@Override
			public void doCommand(String[] args) {
				model.generateMaze(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
				
			}
		});
	
		return commandMap;
	}
}
