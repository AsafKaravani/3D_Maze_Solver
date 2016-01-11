package mvp.view;

import java.util.Comparator;
import java.util.PriorityQueue;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.Position;
import mvp.view.entites.Character;
import mvp.view.entites.Entity;
import mvp.view.entites.Path;
import mvp.view.entites.Wall;

public class GameManager {
	private Canvas canvas;
	private Maze3D gameMaze3D;
	private double scale = 1;
	private PriorityQueue<Entity> entityQueue;
	private Character player;
	private GC gc;
	private Device device;

	public GameManager(Canvas canvas, Maze3D gaMaze3d, GC gc, double scale, Device device) {
		this.canvas = canvas;
		this.gc = gc;
		this.device = device;
		this.gameMaze3D = gaMaze3d;
		this.player = new Character(gameMaze3D.getStartPoint(), this.device);
		this.scale = scale;
		entityQueue = new PriorityQueue<>(new Comparator<Entity>() {

			@Override
			public int compare(Entity o1, Entity o2) {
				return o1.getPos().getLayer() - o2.getPos().getLayer();
			}
		});
	}

	public void draw() {
		for (Entity entity : entityQueue) {
			//entity.setScale(scale);
			entity.draw(gc);
		}

	}

	public void addEntity(Entity entity) {
		entityQueue.add(entity);
	}

	public Maze3D getGameMaze3D() {
		return gameMaze3D;
	}

	public void setGameMaze3D(Maze3D gameMaze3D) {
		this.gameMaze3D = gameMaze3D;
	}

	public void manageEntities() {
//		addEntity(player);
//		int numOfEntity = (int) (512 / (scale * 64));
//		int[][] maze2DSlice = new int[numOfEntity][numOfEntity];
//		for (int i = 0; i < maze2DSlice.length; i++) {
//			for (int j = 0; j < maze2DSlice[0].length; j++) {
//				if ((player.getPos().getRow() - (numOfEntity / 2) + i < 0
//						|| player.getPos().getRow() - (numOfEntity / 2) + i > gameMaze3D.getMaze()[0].length - 1)
//						|| (player.getPos().getColumn() - (numOfEntity / 2) + j < 0 || player.getPos().getColumn()
//								- (numOfEntity / 2) + j > gameMaze3D.getMaze()[0][0].length - 1)) {
//					maze2DSlice[i][j] = -1;
//				} else {
//					System.out.println((player.getPos().getRow() + (numOfEntity / 2) + i) + ", " + (player.getPos().getColumn() - (numOfEntity / 2) + j));
//					maze2DSlice[i][j] = gameMaze3D.getMaze()[player.getPos().getLayer()][player.getPos().getRow() + i][player.getPos().getColumn() - (numOfEntity / 2) + j];
//				}
		addEntity(player);
		int numOfEntity = (int) (512 / (scale * 64));
		int[][] maze2DSlice = new int[gameMaze3D.getMaze()[0].length][gameMaze3D.getMaze()[0][0].length];
		for (int i = 0; i < maze2DSlice.length; i++) {
			for (int j = 0; j < maze2DSlice[0].length; j++) {
				if(i<0||i>maze2DSlice.length&&j<0||j>maze2DSlice[0].length){
					maze2DSlice[i][j] = -1;
			}else{
				maze2DSlice[i][j] = gameMaze3D.getMaze()[player.getPos().getLayer()][i][j];
			}

				if (maze2DSlice[i][j] == 1) {
					addEntity(new Wall(new Position(player.getPos().getLayer(), i, j, null), this.device));
				} else  {
					addEntity(new Path(new Position(player.getPos().getLayer(), i, j, null), this.device));
				} 
			}
		}
	}
	
	
//	public void moveKey(){
//		Button moveUp=new Button(canvas, SWT.PUSH);
//		Button moveDown=new Button(canvas, SWT.PUSH);
//		Button moveLeft=new Button(canvas, SWT.PUSH);
//		Button moveRight=new Button(canvas, SWT.PUSH);
//		moveUp.addKeyListener(new KeyListener() {
//			
//			@Override
//			public void keyReleased(KeyEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void keyPressed(KeyEvent arg0) {
//				int tempMove=player.getPos().getColumn()-1;
//				if(gameMaze3D.getMaze()[player.getPos().getLayer()][player.getPos().getRow()][player.getPos().getColumn()-1]==1){
//					
//				}
//				else{
//				player.getPos().setColumn(tempMove);
//	Character playerTemp=new Character(player.getPos(), device);
//	playerTemp.moveUp();
//	manageEntities();
//				}
//			}
//		}); 
//		moveDown.addListener(SWT.Selection, new Listener() {
//			
//			@Override
//			public void handleEvent(Event arg0) {
//				int tempMove=player.getPos().getColumn()+1;
//				if(gameMaze3D.getMaze()[player.getPos().getLayer()][player.getPos().getRow()][player.getPos().getColumn()+1]==1){
//					
//				}
//				else{
//				player.getPos().setColumn(tempMove);
//	Character playerTemp=new Character(player.getPos(), device);
//	playerTemp.moveDown();
//	manageEntities();
//				}
//			}
//		});
//	
	}
