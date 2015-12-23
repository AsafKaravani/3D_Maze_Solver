package mvp.view;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Observable;

import algorithms.mazeGenerators.Maze3D;
import mvp.presenter.Command;
import mvp.presenter.Presenter;

public class ConsoleView extends Observable implements View {
	CLI cli = new CLI(new OutputStreamWriter(System.out), new InputStreamReader(System.in));
	
	
	@Override
	public void displayMaze(Maze3D maze) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void getUserCommand() {
		while (true) {
			this.setChanged();
			notifyObservers(cli.getCommand());
		}	

	}

	@Override
	public void displayMessage(String message) {
		System.out.println(message);
	}

	public CLI getCli() {
		return cli;
	}

	public void setCli(CLI cli) {
		this.cli = cli;
	}
	
	

}
