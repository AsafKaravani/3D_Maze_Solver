package mvp.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;

import algorithms.mazeGenerators.Maze3D;

public class ControlScreen extends BasicWindow {

//	SelectionListener startLisener;
//	SelectionListener endLisener;
//
//	public SelectionListener getEndLisener() {
//		return endLisener;
//	}
//
//	public void setEndLisener(SelectionListener endLisener) {
//		this.endLisener = endLisener;
//	}
//
//	public SelectionListener getStartLisener() {
//		return startLisener;
//	}
//
//	public void setStartLisener(SelectionListener startLisener) {
//		this.startLisener = startLisener;
//	}
//
	public ControlScreen(String title, int width, int height) {
		super(title, width, height);
	}

	@Override
	void initWidgets() {
		shell.setSize(800, 400);
		shell.setLayout(new GridLayout(2, true));
		Button startGame = new Button(shell, SWT.PUSH);
		startGame.setText("new game");
		Button endGme = new Button(shell, SWT.PUSH);
		endGme.setText("end game");
//		startGame.addSelectionListener(startLisener);
//		endGme.addSelectionListener(endLisener);
		
	}

	@Override
	public void displayMaze(Maze3D maze) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMessage(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getUserCommand() {
		// TODO Auto-generated method stub
		
	}





}