package MVC.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import MVC.controller.Command;

public class CLI implements Runnable {
	BufferedReader in;
	PrintWriter out;
	HashMap<String, Command> commandMap;

	public CLI(HashMap<String, Command> commandMap, OutputStreamWriter out, InputStreamReader in) {
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
				if(commandName.equals("exit")){
					System.out.println("Thank you for playing)");
					System.exit(0);
				}
				else if(commandName.equals("help"))
					printAllCommands();
				else {
					String[] splitedCommand = commandName.split(" ");
					for(int n = splitedCommand.length - 1; n >= 0; n--){
						StringBuilder connectedCommand = null;
						for (int i = 0; i <= n; i++)
							connectedCommand.append(splitedCommand[i]);					
						if(commandMap.containsKey(connectedCommand)){
							String[] args = new String[splitedCommand.length - n - 1];
							for (int j = 0; j < args.length; j++) {
								args[j] = splitedCommand[n + 1 + j].substring(1);
							}
							commandMap.get(connectedCommand).doCommand(args);
							break;
						}
					}
					
				}
					
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	private void printAllCommands() {
		for (Map.Entry<String, Command> entry : commandMap.entrySet()) {
			if (entry.getValue() != null)
				entry.getValue().print();
		}
	}

}
