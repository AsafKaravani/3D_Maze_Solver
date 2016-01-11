package mvp.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class MaxTextScreen implements Runnable {
	Display display;
	Shell shell;
	String textCommand = null;
	String nameCommand=null;
	String solveName=null;

	public MaxTextScreen(Display display, String title, int width, int height) {
		this.display = display;
		shell = new Shell(display);
		shell.setText(title);
		shell.setSize(width, height);
	}

	void initWidgets() {
		shell.setLayout(new GridLayout(2, true));
		Label textLabel = new Label(shell, SWT.NULL);
		textLabel.setText("name:");
		Text singleText = new Text(shell, SWT.SINGLE | SWT.BORDER);
		singleText.addListener(SWT.Modify, new Listener() {
		
			@Override
			public void handleEvent(Event args) {
			}
		});
		Label Slabel = new Label(shell, SWT.NULL);
		Slabel.setText("solution:");
		Slabel.addListener(SWT.Modify, new Listener() {

			@Override
			public void handleEvent(Event arg0) {
			}
		});
		Button OKbutton = new Button(shell, SWT.PUSH);
		OKbutton.setText("OK");
		OKbutton.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event args) {
				switch (args.type) {
				case SWT.Selection:
					textCommand = textLabel.getText() + " " + Slabel.getText();
					solveName=singleText.getText();
					nameCommand=singleText.getText();
					shell.dispose();
				}
			}
		});
		shell.pack();
	}

	public String returnMessage(String messege) {
		return messege;
	}

	public String getTextCommand() {
		return textCommand;
	}
	public String getSolveName() {
		return solveName;
	}

	public void setSolveName(String solveName) {
		this.solveName = solveName;
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
