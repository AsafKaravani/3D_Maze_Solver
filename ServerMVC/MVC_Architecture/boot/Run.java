package boot;

import controller.Controller;
import controller.ServerController;
import model.Model;
import model.ServerModel;
import view.ServerView;
import view.View;

public class Run {

	public static void main(String[] args) {
		Model model = new ServerModel(5400, 5);
		View view = new ServerView();
		Controller controller = new ServerController(model, view);
		view.setController(controller);
		model.setController(controller);
		
		view.start();
		
	}

}
