package MVC.controller;

import java.util.HashMap;

import javax.activation.CommandMap;

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
				System.out.println("generate 3d maze <name> <layers> <rows> <colums>(genetaring a new 3d maze)");		
			}
			
			@Override
			public void doCommand(String[] args) {
				model.generateMaze(args[0],Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
			}
		});
		commandMap.put("maze size", new Command() {
			
			@Override
			public void print() {
			System.out.println("maze size <name>");
				
			}
			
			@Override
			public void doCommand(String[] args) {
			 model.mazeSize(args[0]);
			}
		});
		commandMap.put("solve",new Command() {
			
			@Override
			public void print() {
				System.out.println("solve<name> <algorithm>");
				
			}
			@Override
			public void doCommand(String[] args) {
	    
				
			}
		});
		commandMap.put("display",new Command() {
			
			@Override
			public void print() {
				System.out.println("display <name>");
				
			}
			
			@Override
			public void doCommand(String[] args) {
				model.display(args[0]);
			}
		});
		return commandMap;
	}
}