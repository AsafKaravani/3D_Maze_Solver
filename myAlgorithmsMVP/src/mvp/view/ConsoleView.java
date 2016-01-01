package mvp.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import algorithms.mazeGenerators.Maze3D;

public class ConsoleView extends Observable implements View, Runnable {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	ControlScreen screen;
	
	public ConsoleView(String title,int width,int height) {
	screen=new ControlScreen(title, width, height);
	screen.run();
	screen.setStartLisener(new SelectionListener() {
		
		@Override
		public void widgetSelected(SelectionEvent arg0) {
		setChanged();
		notifyObservers("generate 3d maze yaniv 5 5 5");	
		}
		
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	});
	screen.setEndLisener(new SelectionListener() {
		
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			setChanged();
			notifyObservers("generate 3d maze yaniv 5 5 5");
			
		}
		
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	});
	}
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

	public void run() {
		while (true)
			getUserCommand();
		
	}


	
	

}
