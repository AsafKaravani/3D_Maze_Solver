package mvp.view;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Shell;

import algorithms.mazeGenerators.Maze3D;

public class TextScreen extends BasicWindow {

	public TextScreen(String title, int width, int height) {
		super(title, width, height);
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

	@Override
	void initWidgets() {
		Shell newShell = new Shell(display);
		newShell.setSize(800, 400);
		newShell.setLayout(new GridLayout(1, true));

	}

}
