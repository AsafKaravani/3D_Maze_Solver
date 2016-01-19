package view;

import controller.Controller;

public class ServerView implements View {

	Controller controller;
	
	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void printMessage(String message) {
		System.out.println("Message: " + message);		
	}

	@Override
	public void start() {
		controller.start();
		
	}
	
}
