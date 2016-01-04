package mvp.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ConsoleDisplayMassage implements Runnable {

	Display display;
	Shell shell;
	String massage;

	public ConsoleDisplayMassage(Display display, String title,String massage) {
		this.display = display;
		shell = new Shell(display);
		shell.setText(title);
		shell.setSize(500, 500);
		this.massage=massage;
	}

	@Override
	public void run() {
	Text textMassage = new Text(shell, SWT.SINGLE | SWT.BORDER);
	textMassage.setText(massage);

	}

}
