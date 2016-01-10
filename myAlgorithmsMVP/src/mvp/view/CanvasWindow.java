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
		Wall w1 = new Wall(new Position(0, 1, 1, null), display);
		Wall w2 = new Wall(new Position(0, 2, 1, null), display);
		Wall w3 = new Wall(new Position(0, 1, 3, null), display);
		Wall w4 = new Wall(new Position(0, 2, 3, null), display);
		Path p1 = new Path(new Position(0, 1, 2, null), display);
		Path p2 = new Path(new Position(0, 2, 2, null), display);
		Character c1 = new Character(new Position(0, 2, 2, null), display);
		Hint h1 = new Hint(new Position(0, 1, 2, null), display);
		
		w1.draw(gc);
		w2.draw(gc);
		w3.draw(gc);
		w4.draw(gc);
		p1.draw(gc);
		p2.draw(gc);
		c1.draw(gc);
		h1.draw(gc);
		
	
		
		
	}

}
