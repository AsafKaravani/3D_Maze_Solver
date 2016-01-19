package view;

import javax.xml.stream.events.StartDocument;

import controller.Controller;

public interface View {
	public void printMessage(String message);
	public void start();
	public void setController(Controller controller);
}
