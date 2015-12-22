package mvp.view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public abstract class BasicWindow {
	
	Display display=new Display();
	Shell shell=new Shell(display);
	
	public abstract void initWigits();
	
}
