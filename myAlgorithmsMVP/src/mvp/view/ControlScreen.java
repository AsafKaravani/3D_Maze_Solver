package mvp.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import algorithms.mazeGenerators.Maze3D;
import mvp.model.notifers.MazeCreationNotifier;

public class ControlScreen extends BasicWindow {

	SelectionListener startLisener;
	SelectionListener endLisener;

	public SelectionListener getEndLisener() {
		return endLisener;
	}

	public void setEndLisener(SelectionListener endLisener) {
		this.endLisener = endLisener;
	}

	public SelectionListener getStartLisener() {
		return startLisener;
	}

	public void setStartLisener(SelectionListener startLisener) {
		this.startLisener = startLisener;
	}

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
		startGame.addSelectionListener(startLisener);
		endGme.addSelectionListener(endLisener);
	}

}