package mvp.presenter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import mvp.presenter.Command;
import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;
import mvp.model.Model;
import mvp.view.View;

public class Presenter implements Observer {
	View view;
	Model model;
	ExecutorService executor;
	HashMap<String, Command> commandMap;
	/**
	 * Constructor that gets a Model and a View.
	 * 
	 * @author Asaf and Yaniv(A.K.A. looser)
	 */
	public Presenter(View view, Model model) {
		initCommands();
		this.view = view;
		this.model = model;
		executor = Executors.newCachedThreadPool();

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
				System.out.println("The object that have been recived was from the type " 
										+ arg.getClass().getName() + " and not String!");

		} else if (o == model) {

		}
	}

	/**
	 * 
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
				
					executor.execute(new Runnable() {						
						@Override
						public void run() {
							//TODO  Return something immediately (Future) and then update when the maze is ready. 
							//(mazeMap.put(args[0], Future<Maze3D>)
							model.generateMaze(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]),Integer.parseInt(args[3]));
							
						}
					});
				
					view.displayMessage("The maze \"" + args[0] + "\" has been created.");
				}
			}
		});
		commandMap.put("solve", new Command() {

			@Override
			public void doCommand(String[] args) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						Solution sol = model.solveMaze(args[0], args[1]);
						if (sol == null)
							view.displayMessage("The solving proccess could not have been complete.");
						else
							view.displayMessage("The maze " + args[0] + "'s solution is ready");
					}
				}).start();
			}
		});
		commandMap.put("display", new Command() {

			@Override
			public void doCommand(String[] args) {
				if (model.getMaze(args[0]) == null) {
					view.displayMessage("There is no maze in that name");
				} else {
					view.displayMessage("The requested is:");
					view.displayMaze(model.getMaze(args[0]));
				}
			}
		});
		commandMap.put("display solution", new Command() {

			@Override
			public void doCommand(String[] args) {
				if (model.displaySolution(args[0]) == null) {
					view.displayMessage("There is no maze in that name.");
				} else {
					Solution sol = model.displaySolution(args[0]);
					Iterator<State> i = sol.getPathToSolution().iterator();
					while (i.hasNext()) {
						State s = i.next();
						if (s == null)
							break;
						else
							view.displayMessage(s.getState().toString());
					}
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
