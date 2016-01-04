package mvp.boot;

import org.eclipse.swt.widgets.Display;

import mvp.model.MyModel;
import mvp.presenter.Presenter;
import mvp.view.BasicWindow;
import mvp.view.ConsoleView;
import mvp.view.ControlScreen;
import mvp.view.View;

public class Run {

	public static void main(String[] args) {
		MyModel model = new MyModel();
		ControlScreen view = new ControlScreen("The 3D maze game",800,400);
		Presenter pres = new Presenter(view, model);
		model.addObserver(pres);
		view.addObserver(pres);
		view.run();
	
		
	}
}
