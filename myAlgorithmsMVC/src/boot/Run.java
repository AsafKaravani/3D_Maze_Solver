package boot;

import MVC.controller.MyController;
import MVC.view.ConsoleView;
import model.MyModel;

public class Run {
	
	MyModel model= new MyModel();
	MyController ctrl = new MyController();
	ConsoleView view = new ConsoleView(ctrl);

}
