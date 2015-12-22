package mvp.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import mvp.presenter.Command;

public class CLI implements Runnable {
	BufferedReader in;
	PrintWriter out;
	HashMap<String, Command> commandMap;
	/**
	*@author Yaniv and Asaf
	*@return Constructor that take the HashMap and file and save it
	 */
	public CLI(HashMap<String, Command> commandMap, OutputStreamWriter out, InputStreamReader in) {
		this.commandMap = commandMap;
		this.in = new BufferedReader(in);
		this.out = new PrintWriter(out);
	}

	public CLI(OutputStreamWriter out, InputStreamReader in) {
		this.commandMap = new HashMap<String, Command>();
		this.in = new BufferedReader(in);
		this.out = new PrintWriter(out);
	}
	
	
	@Override
	public void run() {
		start();
	}

	/**
	*@author Yaniv and Asaf
	*@return Gets from the user a command and from the HashMap go to its command and use its method
	 */
	public void start() {
		int n;
		String commandName;
		while(true){
			try {
				System.out.print("Command/> ");
				commandName = in.readLine();
				if(commandName.equals("exit")){
					System.out.println("|----------------------------|");
					System.out.println("|   Thank you for playing!   |");
					System.out.println("|----------------------------| ");
					System.exit(0);
				}
				else if(commandName.equals("help"))
					printAllCommands();
				else {
					String[] splitedCommand = commandName.split(" ");
					for(n = splitedCommand.length - 1; n >= 0; n--){
						StringBuilder connectedCommand = new StringBuilder();
						for (int i = 0; i <= n; i++){
							if(i != n)
								connectedCommand.append(splitedCommand[i] + " ");
							else
								connectedCommand.append(splitedCommand[i]);
						}
						if(commandMap.containsKey(connectedCommand.toString())){
							String[] args = new String[splitedCommand.length - n - 1];
							for (int j = 0; j < args.length; j++) {
								args[j] = splitedCommand[n + 1 + j];
							}
							commandMap.get(connectedCommand.toString()).doCommand(args);
							break;
						}
					}
					if(n < 0){
						System.out.println("Invalid command, you can use the \"help\" command to see the command list.");
					}
						
						
					
				}
					
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	*@author Yaniv and asaf
	*@return Prints all possible commands
	 */
	private void printAllCommands() {
		for (Map.Entry<String, Command> entry : commandMap.entrySet()) {
			if (entry.getValue() != null)
				entry.getValue().print();
		}
	}
	
	public void insertCommand(String name, Command c){
		commandMap.put(name, c);
	}

}
