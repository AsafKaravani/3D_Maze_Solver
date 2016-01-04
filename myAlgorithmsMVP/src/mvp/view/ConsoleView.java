package mvp.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;

import algorithms.mazeGenerators.Maze3D;

public class ConsoleView extends Observable implements View, Runnable {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	/**
	 * Printing a Maze3D with its toString method.
	 * 
	 * @author Asaf & Yaniv
	 */
	@Override
	public void displayMaze(Maze3D maze) {
		System.out.println(maze);
	}
	
	/**
	 * Gets a string from the client and sends it to its observers.
	 * 
	 * @author Asaf & Yaniv
	 */
	@Override
	public void getUserCommand() {
		System.out.print("Command/> ");
		try {
			setChanged();
			notifyObservers(in.readLine());
		} catch (IOException e) {
			System.out.println(this.getClass().getName() + " : Reading ERROR");
			e.printStackTrace();
		}
	}

	/**
	 * Prints the given {@link String}.
	 * @author Asaf & Yaniv
	 */
	@Override
	public void displayMessage(String message) {
		System.out.println(message);
	}

	@Override
	public void run() {
		while (true)
			getUserCommand();
		
	}


	
	

}