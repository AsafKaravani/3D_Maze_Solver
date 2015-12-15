package MVC.controller;

import java.util.HashMap;

import MVC.model.Model;
import MVC.view.View;

public class MyController implements Controller {

	View view;
	Model model;
	
	public void start(){
		
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
		return commandMap;
	}
}
