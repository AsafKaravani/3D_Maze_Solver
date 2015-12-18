package MVC.controller;

import java.util.HashMap;

public interface Controller {
	public void convert();
	public	void display();
	/**
	*@author Yaniv and Asaf
	*@return Inserting commands to a HashMap and returning it.
	 */
	public  HashMap<String, Command> initCommands();
}
