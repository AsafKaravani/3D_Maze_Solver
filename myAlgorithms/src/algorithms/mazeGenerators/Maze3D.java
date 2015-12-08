package algorithms.mazeGenerators;

import java.io.IOException;
import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.util.ByteSequence;

import algorithms.io.Compressible;
import algorithms.search.Searchable;
import algorithms.search.State;

public class Maze3D implements Searchable<Position>, Compressible{
	// ---------Variables---------//
	private int[][][] maze;
	private Position startPoint;
	private Position endPoint;

	// ----------Methods---------//
	// Constructors:
	public Maze3D(int layer, int row, int column) {
		maze = new int[layer][row][column];

		for (int layerIndex = 0; layerIndex < layer; layerIndex++) {
			for (int rowIndex = 0; rowIndex < row; rowIndex++) {
				for (int columnIndex = 0; columnIndex < column; columnIndex++) {
					if (layerIndex == 0 || layerIndex == (layer - 1))
						setCellAsWall(layerIndex, rowIndex, columnIndex);
					if (rowIndex == 0 || rowIndex == (row - 1) || columnIndex == 0 || columnIndex == (column - 1))
						setCellAsWall(layerIndex, rowIndex, columnIndex);
				}
			}
		}
	}

	public Maze3D(byte[] thebyte) {
		int count=3;
		startPoint = new Position(1,1,1,this);
		endPoint = new Position((int) thebyte[0] - 2,(int)thebyte[1] - 2,(int)thebyte[2] - 2,this);
		maze = new int[(int) thebyte[0]][(int)thebyte[1]][(int)thebyte[2]];
		
		for (int layerIndex = 0; layerIndex <(int) thebyte[0]; layerIndex++) {
			for (int rowIndex = 0; rowIndex <(int)thebyte[1] ; rowIndex++) {
				for (int columnIndex = 0; columnIndex <(int)thebyte[2]; columnIndex++) {
					maze[layerIndex][rowIndex][columnIndex]=(int)thebyte[count];
					count++;
				}
			}
		}
	}

	// Getters & Setters:
	public int[][][] getMaze() {
		return maze;
	}

	public void setMaze(int[][][] maze) {
		this.maze = maze;
	}

	public Position getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Position startPoint) {
		this.startPoint = startPoint;
	}

	public Position getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Position endPoint) {
		this.endPoint = endPoint;
	}

	public void setCellAsWall(int layer, int row, int column) {
		maze[layer][row][column] = 1;
	}

	public void setCellAsEmpty(int layer, int row, int column) {
		maze[layer][row][column] = 0;
	}

	public void setCellValue(Position pos, int newValue) {

	}

	// Object methods:
	@Override
	public String toString() {
		String mazeAsString = "";

		for (int layerIndex = 0; layerIndex < maze.length; layerIndex++) {
			for (int rowIndex = 0; rowIndex < maze[0].length; rowIndex++) {
				for (int columnIndex = 0; columnIndex < maze[0][0].length; columnIndex++) {
					if (layerIndex == startPoint.getLayer() && rowIndex == startPoint.getRow()
							&& columnIndex == startPoint.getColumn())
						mazeAsString = mazeAsString + "-S-";

					else if (layerIndex == endPoint.getLayer() && rowIndex == endPoint.getRow()
							&& columnIndex == endPoint.getColumn())
						mazeAsString = mazeAsString + "-E-";

					else if (maze[layerIndex][rowIndex][columnIndex] == 0)
						mazeAsString = mazeAsString + "   ";

					else
						mazeAsString = mazeAsString + "[ ]";
				}
				mazeAsString = mazeAsString + "\n";
			}
			mazeAsString = mazeAsString + "\n";
		}

		return mazeAsString;
	}

	// Searchable methods:
	@Override
	public State getInitialState() {
		return new State<Position>(startPoint);
	}

	@Override
	public State getGoalState() {
		return new State<Position>(endPoint);
	}

	@Override
	public ArrayList<State<Position>> getAllPossibleStates(State<Position> s) {
		ArrayList<State<Position>> possibleStates = new ArrayList<State<Position>>();

		//
		if (s.getState().getLayer() - 2 > 0
				&& maze[s.getState().getLayer() - 1][s.getState().getRow()][s.getState().getColumn()] != 1) {
			possibleStates.add(new State<Position>(
					new Position(s.getState().getLayer() - 2, s.getState().getRow(), s.getState().getColumn(), this)));
		}

		if (s.getState().getLayer() + 2 < maze.length
				&& maze[s.getState().getLayer() + 1][s.getState().getRow()][s.getState().getColumn()] != 1) {
			possibleStates.add(new State<Position>(
					new Position(s.getState().getLayer() + 2, s.getState().getRow(), s.getState().getColumn(), this)));
		}

		if (s.getState().getRow() - 2 > 0
				&& maze[s.getState().getLayer()][s.getState().getRow() - 1][s.getState().getColumn()] != 1) {
			possibleStates.add(new State<Position>(
					new Position(s.getState().getLayer(), s.getState().getRow() - 2, s.getState().getColumn(), this)));
		}

		if (s.getState().getRow() + 2 < maze[0].length
				&& maze[s.getState().getLayer()][s.getState().getRow() + 1][s.getState().getColumn()] != 1) {
			possibleStates.add(new State<Position>(
					new Position(s.getState().getLayer(), s.getState().getRow() + 2, s.getState().getColumn(), this)));
		}

		if (s.getState().getColumn() - 2 > 0
				&& maze[s.getState().getLayer()][s.getState().getRow()][s.getState().getColumn() - 1] != 1) {
			possibleStates.add(new State<Position>(
					new Position(s.getState().getLayer(), s.getState().getRow(), s.getState().getColumn() - 2, this)));
		}

		if (s.getState().getColumn() + 2 < maze[0][0].length
				&& maze[s.getState().getLayer()][s.getState().getRow()][s.getState().getColumn() + 1] != 1) {
			possibleStates.add(new State<Position>(
					new Position(s.getState().getLayer(), s.getState().getRow(), s.getState().getColumn() + 2, this)));
		}

		return possibleStates;
	}

	public byte[] toByteArray() {
		
		byte[] theByte = new byte[maze.length * maze[0].length * (maze[0][0].length+3)];
		theByte[0]=(byte) maze.length;
		theByte[1]=(byte) maze[0].length;
		theByte[2]=(byte) maze[0][0].length;
		int count = 3;
		for (int indexLayer = 0; indexLayer < maze.length; indexLayer++) {
			for (int indexRow = 0; indexRow < maze[0].length; indexRow++) {
				for (int indexColumn = 0; indexColumn < maze[0][0].length; indexColumn++) {
					theByte[count] = (byte) maze[indexLayer][indexRow][indexColumn];
					count++;
				}
			}
		}
		return theByte;
	}

	@Override
	public byte[] compress() {
		int count = 0;
		int lastBit = -1;
		ArrayList<Byte> compressedMaze = new ArrayList<Byte>(); 
		for (int indexLayer = 0; indexLayer <maze.length; indexLayer++) {
			for (int indexRow = 0; indexRow < maze[0].length; indexRow++) {
				for (int indexColumn = 0; indexColumn < maze[0][0].length; indexColumn++) {
				
					if (lastBit == maze[indexLayer][indexRow][indexColumn] || lastBit == -1) {
						count++;
						lastBit = maze[indexLayer][indexRow][indexColumn];
					}
					else{
							compressedMaze.add((byte)lastBit);
							compressedMaze.add((byte)count);
							lastBit = maze[indexLayer][indexRow][indexColumn];
						count = 0;	
						}
				}
			}
		}
		byte[] bytes=new byte[compressedMaze.size()];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i]=compressedMaze.get(i);
		}
		return bytes;
	}

	@Override
	public byte[] deCompress(byte[] compressed) {
		int serial=0;
		int count=0;
		int keper=0;
		byte[] longMaze=new byte[compressed.length];
		
		for (int i = 0; i < compressed.length; i++) {
			keper=compressed[i];
			if(i%2==0){
		serial=compressed[i];
				for (int j = 0; j < serial; j++) {
					if(keper==1){
						longMaze[count]=1;
						serial++;
					}
					else{
						longMaze[count]=0;
						serial++;
					}
				}
			}
		}
    		
		
		
		return longMaze;
	}
	// Other Methods:

}
