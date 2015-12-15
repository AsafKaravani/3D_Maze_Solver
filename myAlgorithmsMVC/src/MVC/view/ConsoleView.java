package MVC.view;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import MVC.controller.Command;
import MVC.controller.Controller;
import MVC.controller.MyController;

public class ConsoleView implements View {

	Controller ctrl;
	CLI cli;
	
	public ConsoleView(Controller ctrl){
		this.ctrl = ctrl;
		HashMap<String, Command> commandMap = ctrl.initCommands();
		cli = new CLI(commandMap, new OutputStreamWriter(System.out),new InputStreamReader(System.in));
	}
	
	public void start(){
		cli.start();
	}

	@Override
	public void display() {
		System.out.println("Welcome to the Maze3D game, please enter a command: ");
		start();
		
	}

	public Controller getCtrl() {
		return ctrl;
	}

	public void setCtrl(Controller ctrl) {
		this.ctrl = ctrl;
	}
	
}
