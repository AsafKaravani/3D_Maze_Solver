package MVC.controller;

public interface Command {
	void doCommand(String[] args);
	/**
	*@author Yaniv and Asaf
	*@return Print the command and a brief explanation
	 */
	void print();
}
