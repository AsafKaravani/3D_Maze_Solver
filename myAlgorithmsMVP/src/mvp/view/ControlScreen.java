package mvp.view;

import java.awt.Desktop;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.graphics.DeviceData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import algorithms.io.MyCompressorOutputStream;
import algorithms.io.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.myMaze3DGenerator;
import algorithms.search.Solution;
import mvp.model.notifers.MazeCreationNotifier;
import sun.dc.pr.PathStroker;

public class ControlScreen extends BasicWindow implements View {

	private Canvas canvas;
	private Game game;
	private boolean gameOn = false;
	private String gameName = null;
	private boolean hasListeners = false;

	public void setConnectedToServer(boolean connectedToServer) {
		this.connectedToServer = connectedToServer;
	}

	private boolean hasSolution;
	private boolean connectedToServer;

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public ControlScreen(String title, int width, int height) {
		super(title, width, height);
	}

	@Override
	void initWidgets() {
		shell.setSize(500, 500);
		shell.setLayout(new GridLayout(1, false));
		canvas = new Canvas(shell, SWT.FILL);
		canvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		shell.open();
		

		canvas.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent arg0) {

			}

			@Override
			public void mouseDown(MouseEvent arg0) {
				if (gameOn) {
					game.fillEntitiesQueue();
					game.draw();
				}

			}

			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		//Create the menu bar.
		Menu menuBar = new Menu(shell, SWT.BAR | SWT.LEFT_TO_RIGHT);
		//Create a menu head.
		Menu gameMenu = new Menu(menuBar);
		MenuItem gameItems = new MenuItem(menuBar, SWT.CASCADE);
		gameItems.setText("&Game");
		gameItems.setMenu(gameMenu);
		//Adding item to the menu.
		MenuItem newGame = new MenuItem(gameMenu, SWT.NONE);
		newGame.setText("New Game");

		newGame.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				switch (e.type) {
				case SWT.Selection:
					TextScreen t = new TextScreen(display, "new maze 3D game", 200, 165);
					t.run();
					setChanged();
					if (((t.getName() != "") && (t.getLayer() != "") && (t.getRows() != "") && (t.getCulomns() != ""))
							&& ((t.getName() != null) && (t.getLayer() != null) && (t.getRows() != null)
									&& (t.getCulomns() != null))) {
						notifyObservers("generate 3d maze" + " " + t.getTextCommand());
						setChanged();
						notifyObservers("display " + t.getName());
						gameName = t.getName();
						gameOn = true;
						new Thread(new Runnable() {
							public void run() {

								Display.getDefault().asyncExec(new Runnable() {
									public void run() {
										if (gameOn) {
											if (!hasListeners) {

												canvas.addKeyListener(new KeyListener() {

													@Override
													public void keyReleased(KeyEvent arg0) {

													}

													@Override
													public void keyPressed(KeyEvent arg0) {
														if (arg0.keyCode == SWT.ARROW_UP)
															game.moveCharacter(DIRECTION.UP);

														else if (arg0.keyCode == SWT.ARROW_DOWN)
															game.moveCharacter(DIRECTION.DOWN);

														else if (arg0.keyCode == SWT.ARROW_LEFT)
															game.moveCharacter(DIRECTION.LEFT);

														else if (arg0.keyCode == SWT.ARROW_RIGHT)
															game.moveCharacter(DIRECTION.RIGHT);

														else if (arg0.keyCode == 122)
															game.moveCharacter(DIRECTION.UP_LAYER);

														else if (arg0.keyCode == 120)
															game.moveCharacter(DIRECTION.DOWN_LAYER);

														game.setGameBoard(canvas);
														game.fillEntitiesQueue();
														game.draw();
													}
												});
												hasListeners = true;
											}
										}
									}
								});

							}
						}).start();

						break;
					}
				}
			}
		});
		MenuItem ImportMaze = new MenuItem(gameMenu, SWT.NONE);
		ImportMaze.setText("Import");
		for (Listener lis : ImportMaze.getListeners(SWT.Selection))
			ImportMaze.removeListener(SWT.Selection, lis);
		ImportMaze.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event args) {
				FileDialog fd = new FileDialog(shell, SWT.OPEN);
				fd.setText("Open");
				fd.setFilterPath("C:/");
				String[] filterExt = { "*.maze" };
				fd.setFilterExtensions(filterExt);
				String selected = fd.open();

				try {
					Maze3D newMaze = new Maze3D(new MyDecompressorInputStream<Maze3D>(new FileInputStream(selected))
							.readObject(new Maze3D(3, 3, 3)));
					displayMaze(newMaze);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				gameOn = true;
				new Thread(new Runnable() {
					public void run() {
						Display.getDefault().asyncExec(new Runnable() {
							public void run() {
								if (gameOn) {

									canvas.addKeyListener(new KeyListener() {

										@Override
										public void keyReleased(KeyEvent arg0) {

										}

										@Override
										public void keyPressed(KeyEvent arg0) {
											if (arg0.keyCode == SWT.ARROW_UP)
												game.moveCharacter(DIRECTION.UP);

											else if (arg0.keyCode == SWT.ARROW_DOWN)
												game.moveCharacter(DIRECTION.DOWN);

											else if (arg0.keyCode == SWT.ARROW_LEFT)
												game.moveCharacter(DIRECTION.LEFT);

											else if (arg0.keyCode == SWT.ARROW_RIGHT)
												game.moveCharacter(DIRECTION.RIGHT);

											else if (arg0.keyCode == 122)
												game.moveCharacter(DIRECTION.UP_LAYER);

											else if (arg0.keyCode == 120)
												game.moveCharacter(DIRECTION.DOWN_LAYER);

											game.setGameBoard(canvas);
											game.fillEntitiesQueue();
											game.draw();
										}
									});
								}
							}
						});

					}
				}).start();

			}

		});

		MenuItem exportMaze = new MenuItem(gameMenu, SWT.NONE);
		exportMaze.setText("Export");
		for (Listener lis : exportMaze.getListeners(SWT.Selection))
			exportMaze.removeListener(SWT.Selection, lis);
		exportMaze.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event args) {
				FileDialog fd = new FileDialog(shell, SWT.SAVE);
				fd.setText("Save");
				fd.setFilterPath("C:/");
				String[] filterExt = { "*.maze" };
				fd.setFilterExtensions(filterExt);
				String selected = fd.open();
				try {
					new MyCompressorOutputStream<>(new FileOutputStream(new File(selected)))
							.writeObject(game.getMaze());
					;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});

		MenuItem Exit = new MenuItem(gameMenu, SWT.NONE);
		Exit.setText("Exit");
		for (Listener lis : Exit.getListeners(SWT.Selection))
			Exit.removeListener(SWT.Selection, lis);
		Exit.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event args) {
				shell.dispose();
			}
		});

		Menu mazeMenu = new Menu(menuBar);
		MenuItem mazeItems = new MenuItem(menuBar, SWT.CASCADE);
		mazeItems.setText("&Maze");
		mazeItems.setMenu(mazeMenu);
		MenuItem toggelHints = new MenuItem(mazeMenu, SWT.CHECK);
		toggelHints.setText("Hints");
		for (Listener lis : toggelHints.getListeners(SWT.Selection))
			toggelHints.removeListener(SWT.Selection, lis);
		toggelHints.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				if (game.isShowHints())
					game.setShowHints(false);
				else {
					if (!hasSolution) {
						MaxTextScreen f = new MaxTextScreen(display, "give solution method", 400, 400);
						f.run();
						setChanged();
						notifyObservers("solve " + gameName + " " + f.getSolveName());
						if (connectedToServer) {
							setChanged();
							notifyObservers("display solution " + gameName);
							hasSolution = true;
							game.setShowHints(true);
						}
					} else {
						game.setShowHints(true);
					}
				}
				game.fillEntitiesQueue();
				game.draw();
			}
		});

		Menu helpMenu = new Menu(menuBar);
		MenuItem helpItems = new MenuItem(menuBar, SWT.CASCADE);
		helpItems.setText("&Help");
		helpItems.setMenu(helpMenu);

		MenuItem openVideo = new MenuItem(helpMenu, SWT.NONE);
		openVideo.setText("Video Explenation");
		openVideo.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event arg0) {

				try {
					Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=EJCFNww_Iyc"));
				} catch (IOException e) {
					e.printStackTrace();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}

			}

		});
		MenuItem sep3 = new MenuItem(helpMenu, SWT.SEPARATOR);
		MenuItem openAbout = new MenuItem(helpMenu, SWT.CHECK);
		openAbout.setText("About");
		openAbout.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {

			}
		});

		shell.setMenuBar(menuBar);
	}

	@Override
	public void displayMaze(Maze3D maze) {
		if (game != null)
			game.newGame(maze);
		else {
			game = new Game(display, canvas, maze);
			game.fillEntitiesQueue();
			game.draw();
		}

		for (Listener lis : shell.getListeners(SWT.Resize))
			shell.removeListener(SWT.Resize, lis);

		shell.addListener(SWT.Resize, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				game.fillEntitiesQueue();
				game.draw();
			}
		});
	}

	@Override
	public void displayMessage(String message) {

		new Thread(new Runnable() {
			public void run() {

				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						MessageBox dialog = new MessageBox(shell, SWT.OK);
						dialog.setText("Message");
						dialog.setMessage(message);
						dialog.open();
					}
				});

			}
		}).start();

	}

	@Override
	public void getUserCommand() {
		// TODO Auto-generated method stub

	}

	public void startGame() {

	}

	@Override
	public void displaySolution(Solution sol) {
		game.setPathSolutin(sol);
		game.setShowHints(true);

	}
}