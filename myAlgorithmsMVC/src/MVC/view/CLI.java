package MVC.view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;

import CLI.controller.Command;

public class CLI implements Runnable{
	BufferedReader in;
	PrintWriter out;
	HashMap<String, Command> commandMap;


	public CLI(OutputStreamWriter out, InputStreamReader in){
		commandMap = new HashMap<String, Command>();
		this.in = new BufferedReader(in);
		this.out = new PrintWriter(out);
	}

	@Override
	public void run() {
		start();
	}

	private void start() {
		try {
			do {
				
			} while (in.readLine() != "exit");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}









