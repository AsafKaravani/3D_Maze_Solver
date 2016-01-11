package mvp.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;

import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.myMaze3DGenerator;

public class CanvasWindow extends BasicWindow {

	private double scale;
	public CanvasWindow(String title, int width, int height) {
		super(title, width, height);

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
		shell.setLayout(new GridLayout(1, true));
		//shell.setBackground(new Color(display, new RGB(0, 0, 0)));
		Canvas canvas = new Canvas(shell, SWT.FILL);
		canvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		canvas.setSize(512, 512);
		//canvas.setBackground(new Color(display, new RGB(0, 0, 0)));
		shell.open();
		GC gc = new GC(canvas);
		Game game = new Game(display, canvas, new myMaze3DGenerator().generate(3, 21, 21));
		game.fillEntitiesQueue();
		game.draw();

	}
}
