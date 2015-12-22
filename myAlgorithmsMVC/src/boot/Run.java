package boot;

import MVC.controller.MyController;
import MVC.model.MyModel;
import MVC.view.ConsoleView;



public class Run {
	
	public static void main(String[] args){
		MyController ctrl = new MyController();
		MyModel model= new MyModel(ctrl);
		ConsoleView view = new ConsoleView(ctrl);
		ctrl.setModel(model);
		ctrl.setView(view);
		
		view.start();
	}
	

}
