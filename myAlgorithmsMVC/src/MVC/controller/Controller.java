package MVC.controller;

import java.util.HashMap;

public interface Controller {
	public void convert();
	public	void display();
	public  HashMap<String, Command> initCommands();
}
