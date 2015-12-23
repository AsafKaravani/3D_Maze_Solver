package mvp.boot;

import mvp.model.MyModel;
import mvp.presenter.Presenter;
import mvp.view.ConsoleView;

public class Run {

	public static void main(String[] args) {
		MyModel model = new MyModel();
		ConsoleView view = new ConsoleView();
		Presenter pres = new Presenter(view, model);	
		model.addObserver(pres);
		view.addObserver(pres);
		view.getCli().setCommandMap(pres.initCommands());	
		view.getUserCommand();
		

	}

}
