package mvp.view;



import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import algorithms.mazeGenerators.Maze3D;

public class GameWindow extends BasicWindow  {

	public GameWindow(String title, int width, int height) {
		super(title, width, height);
		shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN );
		shell.setText(title);
		shell.setSize(width, height);
	
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

	@Override
	void initWidgets() {
		shell.setImage(new Image(display, "labyrinth3.png"));
		shell.setLayout(new GridLayout(2, false));
		Button b1 = new Button(shell, SWT.BORDER | SWT.PUSH);
		Button b2 = new Button(shell, SWT.BORDER | SWT.PUSH);
		Button b3 = new Button(shell, SWT.BORDER | SWT.PUSH);
		Button b4 = new Button(shell, SWT.BORDER | SWT.PUSH);
		
		

	}

}
