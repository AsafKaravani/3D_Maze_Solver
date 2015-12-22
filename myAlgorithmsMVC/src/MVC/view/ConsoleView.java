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
		System.out.println("---------------Welcome to the 3D maze game---------------");
		cli.start();
	}

	@Override
	public void display(String message) {
		System.out.println(message);
		
	}

	public Controller getCtrl() {
		return ctrl;
	}

	public void setCtrl(Controller ctrl) {
		this.ctrl = ctrl;
	}
	
}
