package MVC.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import MVC.controller.Command;

public class CLI implements Runnable{
	BufferedReader in;
	PrintWriter out;
	HashMap<String, Command> commandMap;


	public CLI(HashMap<String, Command> commandMap,OutputStreamWriter out, InputStreamReader in){
		commandMap = new HashMap<String, Command>();
		this.in = new BufferedReader(in);
		this.out = new PrintWriter(out);
	}

	@Override
	public void run() {
		start();
	}

	public void start() {
		String commandName;
		while(true){
			try {
				commandName = in.readLine();
				if(commandName == "exit")
					System.exit(0);
				else if(commandName == "help")
					printAllCommands();
				else if(commandMap.containsKey(commandName))
					commandMap.get(commandName).doCommand();
				else{
					System.out.println("Invalid command. Please enter a command: ");
					printAllCommands();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void printAllCommands() {
		for (Map.Entry<String, Command> entry : commandMap.entrySet()) {
			if(entry.getValue() != null)
				entry.getValue().print();
		}
	}
	
	
}









