package mvp.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TextScreen implements Runnable {
	Display display;
	Shell shell;
	String textCommand=null;
	String name;

	public String getName() {
		return name;
	}

	public TextScreen(Display display, String title, int width, int height) {
		this.display = display;
		shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN);
		shell.setText(title);
		shell.setSize(width, height);
	}

	void initWidgets() {
		shell.setLayout(new GridLayout(2, true));
		Label textNameLabel = new Label(shell, SWT.NULL);
		textNameLabel.setText("Name of the maze");
		Text textName = new Text(shell, SWT.SINGLE | SWT.BORDER);
		Label textLayerLabel = new Label(shell, SWT.NULL);
		textLayerLabel.setText("number of layers");
		Text textLayer = new Text(shell, SWT.SINGLE | SWT.BORDER);
		Label textColumnLabel = new Label(shell, SWT.NULL);
		textColumnLabel.setText("number of columns");
		Text textColumn = new Text(shell, SWT.SINGLE | SWT.BORDER);
		Label textRowsabel = new Label(shell, SWT.NULL);
		textRowsabel.setText("number of rows");
		Text textRows = new Text(shell, SWT.SINGLE | SWT.BORDER);
		Button okButton = new Button(shell, SWT.PUSH);
		okButton.setText("OK");
		okButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		Button cancelButton = new Button(shell, SWT.PUSH);
		cancelButton.setText("cancel");
		cancelButton.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event args) {
				switch (args.type) {
				case SWT.Selection:
					shell.dispose();
				}
			}
		});
		textName.addListener(SWT.Modify, new Listener() {
			@Override
			public void handleEvent(Event args) {
				try {
					okButton.setEnabled(true);
				} catch (Exception e) {
					okButton.setEnabled(false);
				}
			}
		});
		textLayer.addListener(SWT.Modify, new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				try {
					okButton.setEnabled(true);
				} catch (Exception e) {
					okButton.setEnabled(false);
				}
			}
		});
		textColumn.addListener(SWT.Modify, new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				try {
					okButton.setEnabled(true);
				} catch (Exception e) {
					okButton.setEnabled(false);
				}
			}
		});
		textLayer.addListener(SWT.Modify, new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {
					try {
						okButton.setEnabled(true);
					} catch (Exception e) {
						okButton.setEnabled(false);
					}
			}
		});
	okButton.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {
				if (okButton.getEnabled()) {
					textCommand=textName.getText()+" "+textLayer.getText()+" "+textColumn.getText()+" "+textRows.getText();
					name = textName.getText();
					shell.dispose();
				}
			}
		});

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

	public String returnMessage(String messege) {
		return messege;
	}

	public String getTextCommand() {
		return textCommand;
	}
}
