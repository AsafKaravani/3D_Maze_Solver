package mvp.view;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Observable;

import algorithms.mazeGenerators.Maze3D;
import mvp.presenter.Presenter;

public class myView extends Observable implements View {
	CLI cli = new CLI(new OutputStreamWriter(System.out), new InputStreamReader(System.in));
	
	@Override
	public void displayMaze(Maze3D maze) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getUserCommand() {
		return 0;
	
		
	}
	

}
