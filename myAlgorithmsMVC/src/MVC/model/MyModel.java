package MVC.model;

import MVC.controller.Controller;
import MVC.controller.MyController;

public class MyModel implements Model {

	Controller ctrl;

	@Override
	public void convert() {
		// TODO Auto-generated method stub
		
	}
	
	public MyModel(	Controller ctrl){
		this.ctrl = ctrl;
	}
	
}
