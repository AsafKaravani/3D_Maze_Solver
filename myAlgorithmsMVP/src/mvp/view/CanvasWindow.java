package mvp.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;

import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.Position;

public class CanvasWindow extends BasicWindow {

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
		Canvas canvas = new Canvas(shell, SWT.FILL);
		canvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		canvas.setSize(150, 150);
	    shell.open();
		GC gc = new GC(canvas);
		
		Wall w = new Wall(new Position(0, 1, 1, null), display);
		Path w2 = new Path(new Position(0, 1, 2, null), display);
		w.setScale(1);
		w2.setScale(1);
		w.draw(gc);
		w2.draw(gc);
		
	
		
		
	}

}
