package model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface ClinetHandler {
	void handleClient(ObjectInputStream inFromClient, ObjectOutputStream outToClient);
}
