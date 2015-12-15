package MVC.model;

import MVC.controller.Controller;
import MVC.controller.MyController;

public class MyModel implements Model {

	Controller ctrl;

	@Override
	public void convert() {
		// TODO Auto-generated method stub
		
	}
	
	public MyModel(){
		
	}

	public Controller getCtrl() {
		return ctrl;
	}

	public void setCtrl(Controller ctrl) {
		this.ctrl = ctrl;
	}
	
}
