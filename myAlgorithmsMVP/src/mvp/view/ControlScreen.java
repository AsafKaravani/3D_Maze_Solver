package mvp.view;

import java.io.Console;

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

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import algorithms.mazeGenerators.Maze3D;
import mvp.model.notifers.MazeCreationNotifier;

public class ControlScreen extends BasicWindow implements View{

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
			TextScreen t=new TextScreen(display, "new maze 3D game", 400, 400);
			t.run();
					
					setChanged();
					notifyObservers("generate 3d maze"+" "+t.getTextCommand());

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
					notifyObservers("display ");
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
		Text messageText = new Text(shell, SWT.BORDER | SWT.FILL);
		messageText.setBounds(10,10,400,400);
		messageText.setText(maze.toString());

	}

	@Override
	public void displayMessage(String message) {
		Text messageText = new Text(shell, SWT.BORDER | SWT.FILL);
		messageText.setBounds(10,10,400,400);
		messageText.setText(message);
	}

	@Override
	public void getUserCommand() {
		// TODO Auto-generated method stub

	}
}