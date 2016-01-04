package mvp.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ConsoleDisplayMassage implements Runnable {

	Display display;
	Shell shell;
	String massage=null;

	public ConsoleDisplayMassage(Display display, String title, String massage) {
		this.display = display;
		shell = new Shell(display);
		shell.setText(title);
		shell.setSize(500, 500);
		this.massage = massage;
	}

	void initWidgets() {
		shell.setLayout(new GridLayout(1, true));
		Label textMessage = new Label(shell, SWT.NULL);
		textMessage.setText(massage);
	}

	@Override
	public void run() {
		initWidgets();
		shell.open();
		// main event loop
		while (!shell.isDisposed()) { // while window isn't closed

			// 1. read events, put then in a queue.
			// 2. dispatch the assigned listener
			if (!display.readAndDispatch()) { // if the queue is empty
				display.sleep(); // sleep until an event occurs
			}

		} // shell is disposed
	}

}
