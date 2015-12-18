package algorithms.mazeGenerators;

import java.io.IOException;
import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.util.ByteSequence;

import algorithms.io.Compressible;
import algorithms.search.Searchable;
import algorithms.search.State;

public class Maze3D implements Searchable<Position>, Compressible {
	// ---------Variables---------//
	private int[][][] maze;
	private Position startPoint;
	private Position endPoint;

	// ----------Methods---------//
	// Constructors:
	/**
	*@author Yaniv and asaf
	*@return This is the first constructor to set the maze and set the wall
	 */
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
	/**
	*@author Yaniv and asaf
	*@return This is the second constructor to set the maze to a byte array
	 */
	public Maze3D(byte[] thebyte) {
		int count = 9;
		startPoint = new Position((int)thebyte[3],(int)thebyte[4],(int)thebyte[5] , this);
		endPoint = new Position((int) thebyte[6], (int) thebyte[7], (int) thebyte[8], this);
		maze = new int[(int) thebyte[0]][(int) thebyte[1]][(int) thebyte[2]];

		for (int layerIndex = 0; layerIndex < (int) thebyte[0]; layerIndex++) {
			for (int rowIndex = 0; rowIndex < (int) thebyte[1]; rowIndex++) {
				for (int columnIndex = 0; columnIndex < (int) thebyte[2]; columnIndex++) {
					maze[layerIndex][rowIndex][columnIndex] = (int) thebyte[count];
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
	/**
	*@author Yaniv and asaf
	*@return this function takes the maze and change it into a string
	 */
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

	/**
	*@author Yaniv and asaf
	*@return This get the all possible states that the maze can go forward
	 */
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
	
	/**
	*@author Yaniv and asaf
	*@return Takes the maze and put it in a array of bytes.
	 */
	public byte[] toByteArray() {

		byte[] theByte = new byte[maze.length * maze[0].length * (maze[0][0].length + 3)];
		theByte[0] = (byte) maze.length;
		theByte[1] = (byte) maze[0].length;
		theByte[2] = (byte) maze[0][0].length;
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
	/**
	*@author Yaniv and asaf
	*@return This takes the maze and compress it into along line of bytes like 1,13,0,3,1,4...
	 */
	@Override
	public byte[] compress() {
		int count = -1;
		int lastBit = -1;
		int j = 0;
		ArrayList<Byte> compressedMaze = new ArrayList<Byte>();
		compressedMaze.add((byte) maze.length);
		compressedMaze.add((byte) maze[0].length);
		compressedMaze.add((byte) maze[0][0].length);
		compressedMaze.add((byte)startPoint.getLayer());
		compressedMaze.add((byte)startPoint.getRow());
		compressedMaze.add((byte)startPoint.getColumn());
		compressedMaze.add((byte)endPoint.getLayer());
		compressedMaze.add((byte)endPoint.getRow());
		compressedMaze.add((byte)endPoint.getColumn());
		for (int indexLayer = 0; indexLayer < maze.length; indexLayer++) {
			for (int indexRow = 0; indexRow < maze[0].length; indexRow++) {
				for (int indexColumn = 0; indexColumn < maze[0][0].length; indexColumn++) {
					j++;
					
					  if(j  == (maze.length * maze[0].length * maze[0][0].length)){
							compressedMaze.add((byte) lastBit);
							compressedMaze.add((byte) (count + 2));
							lastBit = maze[indexLayer][indexRow][indexColumn];
							count = 0;
						}
					 else if (lastBit == maze[indexLayer][indexRow][indexColumn] || lastBit == -1) {
						count++;
						lastBit = maze[indexLayer][indexRow][indexColumn];
						
					}
					
					else {
						count++;
						compressedMaze.add((byte) lastBit);
						compressedMaze.add((byte) count);
						lastBit = maze[indexLayer][indexRow][indexColumn];
						count = 0;
					}
				}
			}
		}
		byte[] bytes = new byte[compressedMaze.size()];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = compressedMaze.get(i);
		}
		return bytes;
	}
	/**
	*@author Yaniv and asaf
	*@return Takes the byte array and decompress it into a huge line of 1 and 0
	 */
	@Override
	public byte[] deCompress(byte[] compressed) {
		int serial = 0;
		int count = 0;
		int keeper = 0;
		ArrayList<Byte> longMaze = new ArrayList<Byte>();
		
		longMaze.add((byte) compressed[0]);
		longMaze.add((byte) compressed[1]);
		longMaze.add((byte) compressed[2]);
		longMaze.add((byte) compressed[3]);
		longMaze.add((byte) compressed[4]);
		longMaze.add((byte) compressed[5]);
		longMaze.add((byte) compressed[6]);
		longMaze.add((byte) compressed[7]);
		longMaze.add((byte) compressed[8]);
		for (int i = 9; i < compressed.length; i++) {
			if(i%2!=0){
			keeper = compressed[i];
			}
			if (i % 2 == 0) {
				serial = compressed[i];
				for (int j = 0; j < serial; j++) {
					if (keeper == 1) {
						longMaze.add((byte) 1);
					} else {
						longMaze.add((byte) 0);
					}
				}
			}
		}
		byte[] theByte = new byte[longMaze.size()];
		for (int i = 0; i < theByte.length; i++) {
			theByte[i]=longMaze.get(i);
		}
		return theByte;
	}
	// Other Methods:

}
