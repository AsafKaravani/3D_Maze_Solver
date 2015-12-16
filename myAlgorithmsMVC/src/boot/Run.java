package boot;

import MVC.controller.MyController;
import MVC.model.Model;
import MVC.model.MyModel;
import MVC.view.ConsoleView;
import MVC.view.View;


public class Run {
	

	Model model= new MyModel();
	ConsoleView view = new ConsoleView();
	MyController ctrl = new MyController(view, model);
}
