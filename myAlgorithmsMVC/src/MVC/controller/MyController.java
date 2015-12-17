package MVC.controller;

import java.util.HashMap;
import java.util.Iterator;

import MVC.model.Model;
import MVC.view.View;
import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;

public class MyController implements Controller {

	View view;
	Model model;

	public MyController() {

	}

	public void start() {

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

	@Override
	public void convert() {
		model.convert();

	}

	@Override
	public void display() {
		view.display();

	}

	public HashMap<String, Command> initCommands() {
		HashMap<String, Command> commandMap = new HashMap<String, Command>();

		commandMap.put("generate 3d maze", new Command() {

			@Override
			public void print() {
				System.out.println("generate 3d maze <name> <layers> <rows> <colums>(genetaring a new 3d maze)");
			}

			@Override
			public void doCommand(String[] args) {
				if(model.mazeExists(args[0]))
					System.out.println("This maze is already exists!");
				else {
					model.generateMaze(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]),Integer.parseInt(args[3]));
					System.out.println("The maze has been created");
				}
			}
		});
		commandMap.put("solve", new Command() {

			@Override
			public void print() {
				System.out.println("solve<name> <algorithm>");

			}

			@Override
			public void doCommand(String[] args) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						Solution sol = model.solveMaze(args[0], args[1]);
						if (sol == null)
							System.out.println("The solving proccess could not have been complete.");
						else
							System.out.println("The maze " + args[0] + "'s solution is ready");
					}
				}).start();
			}
		});
		commandMap.put("display", new Command() {

			@Override
			public void print() {
				System.out.println("display <name>");

			}

			@Override
			public void doCommand(String[] args) {
				if (model.display(args[0]) == null) {
					System.out.println("There is no maze in that name");
				} else {
					System.out.println("The requested is:");
					System.out.println(model.display(args[0]));
				}
			}
		});
		commandMap.put("display solution", new Command() {

			@Override
			public void print() {
				System.out.println("display solution <name> (Displaying the whole maze)");

			}

			@Override
			public void doCommand(String[] args) {
				if (model.displaySolution(args[0]) == null) {
					System.out.println("There is no maze in that name");
				} else {
					Solution sol = model.displaySolution(args[0]);
					Iterator<State> i = sol.getPathToSolution().iterator();
					while(i.hasNext()){
						State s = i.next();
						if(s == null)
							break;	
						else
							System.out.println(s.getState());
					}
				}
			}
		});
		commandMap.put("save maze", new Command() {

			@Override
			public void print() {
				System.out.println("save maze <name> <file name> (Saving a maze to this program's dir)");

			}

			@Override
			public void doCommand(String[] args) {
				model.saveToFile(args[0], args[1]);

			}
		});
		commandMap.put("load maze", new Command() {

			@Override
			public void print() {
				System.out.println("load maze <file name> <name> (Loading a maze from this program's dir)");

			}

			@Override
			public void doCommand(String[] args) {
				model.loadFromFile(args[0], args[1]);

			}
		});
		commandMap.put("maze size", new Command() {

			@Override
			public void print() {
				System.out.println("maze size <name> (Display the size of the maze in the memory)");

			}

			@Override
			public void doCommand(String[] args) {
				int size = model.sizeInMemory(args[0]);
				if (size == -1)
					System.out.println("There is no such maze!");
				else
					System.out.println("The size of the maze in the memory is " + size + " bytes.");

			}
		});
		commandMap.put("file size", new Command() {

			@Override
			public void print() {
				System.out.println("file size <name> (Display the size of the saved file)");

			}

			@Override
			public void doCommand(String[] args) {
				int size = model.sizeInFile(args[0]);
				if (size == -1)
					System.out.println("There is no such file!");
				else
					System.out.println("The size of the maze in the file is " + size + " bytes.");
			}
		});

		return commandMap;
	}

}
