package mvp.view;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;

import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;
import javafx.geometry.Pos;
import mvp.view.entites.Character;
import mvp.view.entites.Entity;
import mvp.view.entites.Hint;
import mvp.view.entites.Hole;
import mvp.view.entites.Ladder;
import mvp.view.entites.Path;
import mvp.view.entites.Wall;

public class Game {
	private Canvas gameBoard;
	private GC gc;
	private Maze3D maze;
	private double scale = 1;
	private PriorityQueue<Entity> entityQueue;
	private ArrayList<Entity> usedResources;
	private Character character;
	private Position center;
	private Device device;
	private Solution<Position> sol = null;
	private boolean showHints = false;
	/**
	*@author Yaniv and Asaf
	*@return set the constractor to the device canvas and maze to the screen
	 */
	public Game(Device device, Canvas canvas, Maze3D maze) {
		this.device = device;
		this.gameBoard = canvas;
		this.gc = new GC(canvas);
		this.maze = maze;
		this.entityQueue = new PriorityQueue<>(new Comparator<Entity>() {
			@Override
			public int compare(Entity e2, Entity e1) {
				return e2.getLayer() - e1.getLayer();
			}
		});
		usedResources = new ArrayList<Entity>();
		this.character = new Character(maze.getStartPoint(), device);
		this.center = new Position(maze.getStartPoint().getLayer(), maze.getStartPoint().getRow() - 1,
				maze.getStartPoint().getColumn() - 1, null);

	}

	public boolean isShowHints() {
		return showHints;
	}

	public void setShowHints(boolean showHints) {
		this.showHints = showHints;
	}
	/**
	*@author Yaniv and Asaf
	*@return draws the entity's from the arraylist to the screen
	 */
	public void draw() {
		if (maze.getEndPoint().equals(character.getPos())) {
			 Image i = new Image (device, "assets\\Win_Image.jpg");
			 Image scaledImage = new Image(device, i.getImageData().scaledTo(gameBoard.getSize().x, gameBoard.getSize().y));
			 gc.drawImage (scaledImage, 0, 0);
			 i.dispose();
			 scaledImage.dispose();
		} else {

			while (!entityQueue.isEmpty()) {
				if (entityQueue.peek() instanceof Character) {
					entityQueue.peek().setScaleX(gameBoard.getSize().x / maze.getMaze()[0][0].length);
					entityQueue.peek().setScaleY(gameBoard.getSize().y / maze.getMaze()[0].length);
					entityQueue.poll().draw(gc);
				} else {
					entityQueue.peek().setScaleX(gameBoard.getSize().x / maze.getMaze()[0][0].length);
					entityQueue.peek().setScaleY(gameBoard.getSize().y / maze.getMaze()[0].length);
					entityQueue.peek().draw(gc);
					entityQueue.poll().getSprite().dispose();
				}
			}
		}
	}
	/**
	*@author Yaniv and Asaf
	*@return fill the arraylist of entity's to creats the maze using walls and paths
	 */
	public void fillEntitiesQueue() {
		entityQueue.add(character);

		if (sol != null && showHints == true) {
			Iterator<State<Position>> iterator = sol.getPathToSolution().iterator();

			while (true) {
				State<Position> tmp = iterator.next();
				if (tmp != null) {
					if (tmp.getState().getLayer() == character.getPos().getLayer())
						entityQueue.add(new Hint(tmp.getState(), device));
				} else
					break;
			}
		}

		for (int i = 0; i < maze.getMaze()[0].length; i++) {
			for (int j = 0; j < maze.getMaze()[0][0].length; j++) {
				int cellType = maze.getMaze()[character.getPos().getLayer()][i][j];
				switch (cellType) {
				case 0:
					entityQueue.add(new Path(new Position(character.getLayer(), i, j, null), device));
					if (maze.getMaze()[character.getPos().getLayer() + 1][i][j] == 0)
						entityQueue.add(new Hole(new Position(character.getLayer(), i, j, null), device));
					if (maze.getMaze()[character.getPos().getLayer() - 1][i][j] == 0)
						entityQueue.add(new Ladder(new Position(character.getLayer(), i, j, null), device));
					break;
				case 1:
					entityQueue.add(new Wall(new Position(character.getLayer(), i, j, null), device));
					break;
				default:
					break;
				}

			}
		}
	}
	/**
	*@author Yaniv and Asaf
	*@return every time the client move the character it is submitted here for redraw
	 */
	public boolean moveCharacter(int direction) {
		switch (direction) {
		case DIRECTION.DOWN:
			if (maze.getMaze()[character.getPos().getLayer()][character.getPos().getRow() + 1][character.getPos()
					.getColumn()] == 1) {
				return false;
			} else {
				character.moveDown();
				return true;
			}
		case DIRECTION.UP:
			if (maze.getMaze()[character.getPos().getLayer()][character.getPos().getRow() - 1][character.getPos()
					.getColumn()] == 1) {
				return false;
			} else {
				character.moveUp();
				return true;
			}
		case DIRECTION.LEFT:
			if (maze.getMaze()[character.getPos().getLayer()][character.getPos().getRow()][character.getPos()
					.getColumn() - 1] == 1) {
				return false;
			} else {
				character.moveLeft();
				return true;
			}
		case DIRECTION.RIGHT:
			if (maze.getMaze()[character.getPos().getLayer()][character.getPos().getRow()][character.getPos()
					.getColumn() + 1] == 1) {
				return false;
			} else {
				character.moveRight();
				return true;
			}
		case DIRECTION.UP_LAYER:
			if (maze.getMaze()[character.getPos().getLayer() - 1][character.getPos().getRow()][character.getPos()
					.getColumn()] == 1) {
				return false;
			} else {
				character.moveUpLayer();
				return true;
			}

		case DIRECTION.DOWN_LAYER:
			if (maze.getMaze()[character.getPos().getLayer() + 1][character.getPos().getRow()][character.getPos()
					.getColumn()] == 1) {
				return false;
			} else {
				character.moveDownLayer();
				return true;
			}

		default:
			return false;
		}

	}

	public Canvas getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(Canvas gameBoard) {
		this.gameBoard = gameBoard;
	}

	public GC getGc() {
		return gc;
	}

	public void setGc(GC gc) {
		this.gc = gc;
	}

	public Maze3D getMaze() {
		return maze;
	}

	public void setMaze(Maze3D maze) {
		this.maze = maze;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public PriorityQueue<Entity> getEntityQueue() {
		return entityQueue;
	}

	public Solution<Position> getPathSolutin() {
		return sol;
	}

	public void setPathSolutin(Solution<Position> pathSolutin) {
		this.sol = pathSolutin;
	}

	public void newGame(Maze3D maze) {
		character.getSprite().dispose();
		character = new Character(maze.getStartPoint(), device);
		fillEntitiesQueue();
		draw();
	}
}
