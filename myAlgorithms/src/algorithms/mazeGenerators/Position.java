package algorithms.mazeGenerators;

import java.io.Serializable;

public class Position implements Comparable<Position>, Serializable{
	//---------Variables---------//
	private int layer;
	private int row;
	private int column;
	private Maze3D insideOf;
	
	
	//-----------Methods---------//
	//	Constructors:
	//		Default constructor
	/**
	*@author Yaniv and asaf
	*@return This is the first constructor to initialize a position.
	 */
	public Position(Maze3D insideOf){
		layer = 0;
		row = 0;
		column = 0;
		this.insideOf = insideOf;
		
	}
	
	//		Constructor
	
	public Maze3D getInsideOf() {
		return insideOf;
	}

	public void setInsideOf(Maze3D insideOf) {
		this.insideOf = insideOf;
	}

	public Position(int layer, int row, int column,Maze3D insideOf){
		this.layer = layer;
		this.row = row;
		this.column = column;
		this.insideOf = insideOf;
	}
	
	//		Copy Constructor
	public Position(Position cell){
		this.layer = cell.layer;
		this.row = cell.row;
		this.column = cell.column;
		this.insideOf = cell.insideOf;
	}
	
	//	Getters & Setters:
	public int getLayer() {
		return layer;
	}
	
	public void setLayer(int layer) {
		this.layer = layer;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	//	Moving methods:
	/**
	*@author Yaniv and asaf
	*@return This checks if the algo can move in layers, if he can the position moves X layers.
	 */
	public boolean moveInLayers(int jumps){
		if(layer + jumps >= insideOf.getMaze().length - 1 || layer + jumps <= 0)
			return false;
		else{
			this.layer = this.layer + jumps;
			return true;
		}
	}
	/**
	*@author Yaniv and asaf
	*@return This checks if the algo can move in rows, if he can the position moves X rows.
	 */
	public boolean moveInRows(int jumps){
		if(row + jumps >= insideOf.getMaze()[0].length - 1 || row + jumps <= 0)
			return false;
		else{
			this.row = this.row + jumps;
			return true;
		}
	}
	/**
	*@author Yaniv and asaf
	*@return This checks if the algo can move in column, if he can the position moves X columns.
	 */
	public boolean moveInColumns(int jumps){
		if(column + jumps >= insideOf.getMaze()[0][0].length - 1 || column + jumps <= 0)
			return false;
		else{
			this.column = this.column + jumps;
			return true;
		}
	}
	
	/**
	*@author Yaniv and asaf
	*@return Another to string for giving back a string of the maze.
	 */
	public String toString(){
		return "(" + layer + ", " + row + ", " + column + ")";
	}

	@Override
	public int compareTo(Position pos) {
		
		return Math.abs(this.toString().compareTo(pos.toString()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + layer;
		result = prime * result + row;
		return result;
	}
	/**
	*@author Yaniv and asaf
	*@return The method equals that get an object and check if it is equal or not.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (column != other.column)
			return false;
		if (layer != other.layer)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	



	
}
