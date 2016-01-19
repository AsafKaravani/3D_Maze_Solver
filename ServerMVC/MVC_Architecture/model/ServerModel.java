package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.Position;
import algorithms.search.AStar;
import algorithms.search.BestFirstSearch;
import algorithms.search.MazeAirDistance;
import algorithms.search.Solution;
import controller.Controller;

public class ServerModel implements Model {
	Controller controller;
	int port;
	ServerSocket server;
	int maxClients;
	ExecutorService threadpool;
	volatile boolean stop;
	Thread mainServerThread;
	int clientsHandeling = 0;
	int clientsHandled = 0;
	BufferedReader in;

	public ServerModel(int port, int maxClients) {
		this.port = port;
		this.maxClients = maxClients;
		in = new BufferedReader(new InputStreamReader(System.in));
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public void start() {
		try {
			server = new ServerSocket(port);
			server.setSoTimeout(10 * 1000);
			threadpool = Executors.newFixedThreadPool(maxClients);

			mainServerThread = new Thread(new Runnable() {
				@Override
				public void run() {
					controller.printMessage("0----------------------------------0");
					controller.printMessage("|   THE SERVER IS UP AND RUNNING   |");
					controller.printMessage("0----------------------------------0");
					
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							String command;
							while (true) {
								try {
									command = in.readLine();
									if(command.compareTo("exit") == 0){
										controller.printMessage("Shutting the server down...");
										threadpool.shutdown();
										stop = true;
										break;
										
									} else 
										controller.printMessage( "\"" + command + "\" is an unknown command.");
										
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
							
						}
					}).start();
					
					while (!stop) {
						try {
							final Socket someClient = server.accept();
							if (someClient != null) {
								threadpool.execute(new Runnable() {

									@Override
									public void run() {
										clientsHandeling++;
										clientsHandled++;
										controller.printMessage(
												"New client was entered, now there is " + clientsHandeling + " clients.");
										int clientNumber = clientsHandled;
										try {
											ObjectOutputStream out = new ObjectOutputStream(someClient.getOutputStream());
											out.flush();
											ObjectInputStream in = new ObjectInputStream(someClient.getInputStream());
											String command = (String) in.readObject();											
											if (command.compareTo("solve") == 0) {
												new MazeSolveHandler().handleClient(in, out);
											}
											
											someClient.close();
											controller.printMessage(
													"The session with client number " + clientNumber + " was ended.");
											clientsHandeling--;
											
										} catch (ClassNotFoundException e) {
											e.printStackTrace();
										} catch (IOException e) {
											e.printStackTrace();
										}

									}
								});
							}

						} catch (SocketTimeoutException e) {
							if (clientsHandeling == 0)
								controller.printMessage("No clinet connected...(Total number of clients handeled: " + clientsHandled +")");
							else
								controller.printMessage("Handeling clients... (Current number of clients: " + clientsHandeling +")");
								
						} catch (IOException e) {
							e.printStackTrace();
						}
												
						
					}
					System.out.println("The server is not excepting new clients!");
				}
			});
			mainServerThread.start();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void close() throws Exception {
		stop = true;
		// do not execute jobs in queue, continue to execute running threads
		controller.printMessage("Shutting the server down.");
		threadpool.shutdown();
		// wait 10 seconds over and over again until all running jobs have finished
		boolean allTasksCompleted = false;
		while (!(allTasksCompleted = threadpool.awaitTermination(10, TimeUnit.SECONDS)))
			;
		controller.printMessage("All the sessions have finished.");

		mainServerThread.join();
		controller.printMessage("Main server thread is done.");

		server.close();
		controller.printMessage("Server is safely closed.");
	}

}
