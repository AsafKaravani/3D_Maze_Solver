package mvp.boot;

import mvp.model.MyModel;
import mvp.presenter.Presenter;
import mvp.view.BasicWindow;
import mvp.view.ConsoleView;
import mvp.view.ControlScreen;
import mvp.view.View;

public class Run {

	public static void main(String[] args) {
		MyModel model = new MyModel();
		ConsoleView view = new ConsoleView("yaniv",800,400);
		Presenter pres = new Presenter(view, model);	
		model.addObserver(pres);
		view.addObserver(pres);
	}

}
