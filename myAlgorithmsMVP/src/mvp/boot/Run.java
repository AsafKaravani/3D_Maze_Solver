package mvp.boot;

import mvp.model.Model;
import mvp.model.MyModel;
import mvp.presenter.Presenter;
import mvp.view.BasicWindow;
import mvp.view.ConsoleView;
import mvp.view.ControlScreen;
import mvp.view.GameWindow;
import mvp.view.View;
import mvp.view.CanvasWindow;

public class Run {

	public static void main(String[] args) {
		//Checking the drawing on canvas.
		CanvasWindow win = new CanvasWindow("Canvas", 600, 600);
		win.run();
		
		
/*		//This is the MVP connection while using the GUI.
		MyModel model = new MyModel();
		GameWindow view = new GameWindow("3D Labyrinth", 300, 600);
		Presenter pres = new Presenter(view, model);
		model.addObserver(pres);
		view.addObserver(pres);
		
		view.run();*/
		
		
		//This is the MVP connection while using the console.
	/*	MyModel model = new MyModel();
		ConsoleView view = new ConsoleView();
		Presenter pres = new Presenter(view, model);	
		model.addObserver(pres);
		view.addObserver(pres);		
		view.run();*/
		
	}

}
