package mvp.presenter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;
import mvp.model.Model;
import mvp.model.notifers.MazeCreationNotifier;
import mvp.model.notifers.MazeSolutionNotifier;
import mvp.model.notifers.ServerStateNotifier;
import mvp.view.View;

public class Presenter implements Observer {
	View view;
	Model model;
	ExecutorService executor;
	HashMap<String, Command> commandMap;
	/**
	 * Constructor that gets a Model and a View.
	 * 
	 * @author Asaf and Yaniv
	 */
	public Presenter(View view, Model model) {
		initCommands();
		this.view = view;
		this.model = model;
		model.loadMaps();
		executor = Executors.newFixedThreadPool(3);

	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Updating Presenter");
		if (o == view) {
			if (arg.getClass() == String.class) {
				int n;
				String tmpStr = (String) arg;
				String[] splitedCommand = tmpStr.split(" ");
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
					view.displayMessage("Invalid command, you can use the \"help\" command to see the command list.");
				}
				
				
				
				
				
			} else
				view.displayMessage("The object that have been recived was from the type " 
										+ arg.getClass().getName() + " and not String!");

		} else if (o == model) {
			if (arg instanceof MazeCreationNotifier) {
				if(((MazeCreationNotifier) arg).isSucceed()){
					view.displayMessage("The maze \"" + ((MazeCreationNotifier) arg).getMazeName() + "\" was create." );
				}
				else
					view.displayMessage("The maze \"" + ((MazeCreationNotifier) arg).getMazeName() + "\" is already exists! Please choose another name." );
				
			} else if (arg instanceof MazeSolutionNotifier){
				if(((MazeSolutionNotifier) arg).isSucceed()) {
					view.displayMessage("The solution for the maze \"" + ((MazeSolutionNotifier) arg).getMazeName() + "\" is ready.");					
				} else {
					view.displayMessage("The solution for the maze \"" + ((MazeSolutionNotifier) arg).getMazeName() + "\" coudn't be created!");
				}
				
			} else if (arg instanceof ServerStateNotifier){
				if(!((ServerStateNotifier)arg).isConnectionSucceed())
					view.displayMessage("Can't connect to the server.");
				else
					view.setConnectedToServer(true);
			}
		}
	}

	/**
	 * 
	 * @return
	 * Initiated HashMap that maps Strings to Commands.
	 */
	public HashMap<String, Command> initCommands() {
		HashMap<String, Command> commandMap = new HashMap<String, Command>();

		commandMap.put("generate 3d maze", new Command() {

			@Override
			public void doCommand(String[] args) {
				if (model.mazeExists(args[0])==true){
					view.displayMessage("This maze is already exists!");
				}
				else {
					
					Future<Maze3D> futureMaze = executor.submit(new Callable<Maze3D>() {

						@Override
						public Maze3D call() throws Exception {						
							return 	model.generateMaze(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]),Integer.parseInt(args[3]));
						}
					});
					
					
					try {
						if(futureMaze.get() != null)
							model.getMazeMap().put(args[0], futureMaze.get());
						else
							view.displayMessage("Maze creation failed.");
					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
					}

				}
			}
		});
		commandMap.put("solve", new Command() {

			@Override
			public void doCommand(String[] args) {
				Future<Solution<Position>> futureSol = executor.submit(new Callable<Solution<Position>>() {

					@Override
					public Solution<Position> call() throws Exception {
						
						return model.solveMaze(args[0], args[1]);
					}
				});
				
				try {
					if(futureSol.get() == null){
						view.displayMessage("Solution creation failed.");
					} else {
						model.getSolutionMap().put(args[0], futureSol.get());
						view.displayMessage("Solution creation succeed.");
					}
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
					
				
				
			}
		});
		commandMap.put("display", new Command() {

			@Override
			public void doCommand(String[] args) {
				if (model.getMaze(args[0]) == null) {
					view.displayMessage("There is no maze in that name.");
				} else {
					view.displayMaze(model.getMaze(args[0]));
				}
			}
		});
		commandMap.put("display solution", new Command() {

			@Override
			public void doCommand(String[] args) {
				if (model.getSolution(args[0]) == null) {
					view.displayMessage("There is no maze in that name.");
				} else {
					Solution sol = model.getSolution(args[0]);
							view.displaySolution(sol);
					}
				
			}
		});
		commandMap.put("save maze", new Command() {

			@Override
			public void doCommand(String[] args) {
				model.saveToFile(args[0], args[1]);

			}
		});
		commandMap.put("load maze", new Command() {

			@Override
			public void doCommand(String[] args) {
				model.loadFromFile(args[0], args[1]);

			}
		});
		commandMap.put("maze size", new Command() {

			@Override
			public void doCommand(String[] args) {
				int size = model.sizeInMemory(args[0]);
				if (size == -1)
					view.displayMessage("There is no such maze!");
				else
					view.displayMessage("The size of the maze in the memory is " + size + " bytes.");

			}
		});
		commandMap.put("file size", new Command() {

			@Override
			public void doCommand(String[] args) {
				int size = model.sizeInFile(args[0]);
				if (size == -1)
					view.displayMessage("There is no such file!");
				else
					view.displayMessage("The size of the maze in the file is " + size + " bytes.");
			}
		});
		
		commandMap.put("exit", new Command() {
			
			@Override
			public void doCommand(String[] args) {
				view.displayMessage("Thanks for playing!");
				model.saveMaps();
				executor.shutdown();
				System.exit(0);
				
			}
		});

		 this.commandMap = commandMap;
		 return commandMap;
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public HashMap<String, Command> getCommandMap() {
		return commandMap;
	}

	public void setCommandMap(HashMap<String, Command> commandMap) {
		this.commandMap = commandMap;
	}

}
