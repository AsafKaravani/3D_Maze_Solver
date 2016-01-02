package mvp.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import algorithms.mazeGenerators.Maze3D;
import mvp.model.notifers.MazeCreationNotifier;

public class ControlScreen extends BasicWindow {

	public ControlScreen(String title, int width, int height) {
		super(title, width, height);
	}

	@Override
	void initWidgets() {
		shell.setSize(500, 400);
		shell.setLayout(new GridLayout());
		//creat the bar
		Menu menuBar=new Menu(shell,SWT.BAR);
		//creat the file items
		Menu fileMenu=new Menu(menuBar);
		MenuItem fileItems=new MenuItem(menuBar, SWT.CASCADE);
		fileItems.setText("file");
		//set menu
		fileItems.setMenu(fileMenu);
		//new item in the bar
		 MenuItem startGame = new MenuItem(fileMenu, SWT.NONE);
		 startGame.setText("New game");
		startGame.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				switch (e.type) {
				case SWT.Selection:
					setChanged();
					notifyObservers("generate 3d maze yaniv 5 5 5");

					break;
				}
			}
		});
		MenuItem displayMaze = new MenuItem(fileMenu, SWT.NONE);
		displayMaze.setText("display maze");
		displayMaze.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event args) {
				switch (args.type) {
				case SWT.Selection:
					setChanged();
					notifyObservers("display yaniv");
					break;

				}

			}
		});
		MenuItem solveMaze = new MenuItem(fileMenu, SWT.NONE);
		solveMaze.setText("solve maze");
		solveMaze.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event args) {
				switch (args.type) {
				case SWT.Selection:
					setChanged();
					notifyObservers("solve yaniv BFS");
					break;
				}

			}
		});
		MenuItem displaySolve = new MenuItem(fileMenu, SWT.NONE);
		displaySolve.setText("display solution");
		displaySolve.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event args) {
				switch (args.type) {
				case SWT.Selection:
					setChanged();
					notifyObservers("display solution yaniv");
					break;

				}

			}
		});
		 shell.setMenuBar(menuBar);
	}

	@Override
	public void displayMaze(Maze3D maze) {
		System.out.println(maze);

	}

	@Override
	public void displayMessage(String message) {
		System.out.println(message);

	}

	@Override
	public void getUserCommand() {
		// TODO Auto-generated method stub

	}

}