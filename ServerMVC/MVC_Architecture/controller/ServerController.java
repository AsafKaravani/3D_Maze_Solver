package controller;

import model.Model;
import view.View;

public class ServerController implements Controller{
	Model model;
	View view;
	
	public ServerController(Model model, View view) {
		this.model = model;
		this.view = view;
	}
	
	@Override
	public void printMessage(String message) {
		view.printMessage(message);
		
	}

	@Override
	public void start() {
		model.start();
		
	}
	
}
