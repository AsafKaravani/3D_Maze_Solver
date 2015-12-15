package boot;

import MVC.controller.MyController;
import MVC.model.Model;
import MVC.view.ConsoleView;
import MVC.view.View;
import model.MyModel;

public class Run {
	


	MyModel model= new MyModel();
	ConsoleView view = new ConsoleView();
	MyController ctrl = new MyController(view, model);
}
