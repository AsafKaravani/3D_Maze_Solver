package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class myMaze3DGenerator extends Maze3DGeneratorAbstract {
	//---------Variables--------//
	private Stack<Position> stackOfPositions = new Stack<Position>();
	private Maze3D myMaze;
	
	//----------Methods---------//
	//	Inherited methods:
	@Override
	public Maze3D generate(int layers, int rows, int column) {
		myMaze = new Maze3D(layers, rows, column);
		createCells();
		myMaze.setStartPoint(createStartPoint());
		myMaze.getMaze()[myMaze.getStartPoint().getLayer()][myMaze.getStartPoint().getRow()][myMaze.getStartPoint().getColumn()] = 2;
		depthFirstSearch(myMaze.getStartPoint());
		cleanMaze();
		
		do {
			myMaze.setEndPoint(createStartPoint());
		} while (myMaze.getEndPoint() == myMaze.getStartPoint());

		return myMaze;
	}
	//	Private helping methods:
	private void createCells(){
		
		for (int layerIndex = 1; layerIndex < myMaze.getMaze().length - 1; layerIndex++) {
			for (int rowIndex = 1; rowIndex < myMaze.getMaze()[0].length - 1; rowIndex++) {
				for (int columnIndex = 1; columnIndex < myMaze.getMaze()[0][0].length - 1; columnIndex++) {
					
					if(columnIndex % 2 == 0 || rowIndex % 2 == 0 || layerIndex % 2 == 0)
						myMaze.setCellAsWall(layerIndex, rowIndex, columnIndex);
					
				}
			}
		}
		
		
	}

	private boolean randomMovment(Position pos){
		Random rand = new Random();
		ArrayList<Integer> possibleMoves = new  ArrayList<Integer>();
		//0 - moves up in layers, 1 - moves down in layers, 2 - moves up in rows, 3 - moves down in rows, 4 - moves left in column, 5 - moves right in columns
		possibleMoves.add(0);possibleMoves.add(1);possibleMoves.add(2);
		possibleMoves.add(3);possibleMoves.add(4);possibleMoves.add(5);
		
		while (possibleMoves.isEmpty() == false){
			int randNumber = rand.nextInt(possibleMoves.size());
			
			//Trying too move up in layers
			if(possibleMoves.get(randNumber) == 0){
				if(pos.getLayer() - 2 <= 0|| this.myMaze.getMaze()[pos.getLayer() - 2][pos.getRow()][pos.getColumn()] == 2)
					possibleMoves.remove(randNumber);
				else{
					pos.moveInLayers(-2);
					myMaze.getMaze()[pos.getLayer()][pos.getRow()][pos.getColumn()] = 2;
					myMaze.setCellAsEmpty(pos.getLayer() + 1, pos.getRow(), pos.getColumn());
					return true;
				}
			}
			
			//Trying too move down in layers
			else if(possibleMoves.get(randNumber) == 1){
				if(pos.getLayer() + 2 >= myMaze.getMaze().length - 1 || this.myMaze.getMaze()[pos.getLayer() + 2][pos.getRow()][pos.getColumn()] == 2)
					possibleMoves.remove(randNumber);
				
				else{
					pos.moveInLayers(2);
					myMaze.getMaze()[pos.getLayer()][pos.getRow()][pos.getColumn()] = 2;
					myMaze.setCellAsEmpty(pos.getLayer() - 1, pos.getRow(), pos.getColumn());
					return true;
				}
			}
			
			//Trying too move up in rows
			else if(possibleMoves.get(randNumber) == 2){
				if(pos.getRow() - 2 <= 0 || this.myMaze.getMaze()[pos.getLayer()][pos.getRow() - 2][pos.getColumn()] == 2)
					possibleMoves.remove(randNumber);
			
				else{
					pos.moveInRows(-2);
					myMaze.getMaze()[pos.getLayer()][pos.getRow()][pos.getColumn()] = 2;
					myMaze.setCellAsEmpty(pos.getLayer(), pos.getRow() + 1, pos.getColumn());
					return true;
				}
			}
			//Trying too move down in rows
			else if(possibleMoves.get(randNumber) == 3){
				if(pos.getRow() + 2 >= myMaze.getMaze()[0].length - 1 || this.myMaze.getMaze()[pos.getLayer()][pos.getRow() + 2][pos.getColumn()] == 2)
					possibleMoves.remove(randNumber);
				
				else{
					pos.moveInRows(2);
					myMaze.getMaze()[pos.getLayer()][pos.getRow()][pos.getColumn()] = 2;
					myMaze.setCellAsEmpty(pos.getLayer() , pos.getRow() - 1, pos.getColumn());
					return true;
				}
			}
			
			//Trying to move left in columns
			else if(possibleMoves.get(randNumber) == 4){
				if(pos.getColumn() - 2 <= 0 || this.myMaze.getMaze()[pos.getLayer()][pos.getRow()][pos.getColumn() - 2] == 2)
					possibleMoves.remove(randNumber);

				else{
					pos.moveInColumns(-2);
					myMaze.getMaze()[pos.getLayer()][pos.getRow()][pos.getColumn()] = 2;
					myMaze.setCellAsEmpty(pos.getLayer(), pos.getRow(), pos.getColumn() + 1);
					return true;
				}
			}
			//Trying to move right in columns
			else if(possibleMoves.get(randNumber) == 5){
				if(pos.getColumn() + 2 >= myMaze.getMaze()[0][0].length - 1 || this.myMaze.getMaze()[pos.getLayer()][pos.getRow()][pos.getColumn() + 2] == 2)
					possibleMoves.remove(randNumber);

				else{
					pos.moveInColumns(2);
					myMaze.getMaze()[pos.getLayer()][pos.getRow()][pos.getColumn()] = 2;
					myMaze.setCellAsEmpty(pos.getLayer(), pos.getRow(), pos.getColumn() - 1);
					return true;
				}
			}
		}
		
		return false;	
	}
	
	private void cleanMaze(){
		for (int layerIndex = 1; layerIndex < myMaze.getMaze().length - 1; layerIndex = layerIndex + 2) {
			for (int rowIndex = 1; rowIndex < myMaze.getMaze()[0].length - 1; rowIndex = rowIndex + 2) {
				for (int columnIndex = 1; columnIndex < myMaze.getMaze()[0][0].length - 1; columnIndex = columnIndex + 2) {
					this.myMaze.getMaze()[layerIndex][rowIndex][columnIndex] = 0;
				}
			}
		}
		
	}
	
	private Position createStartPoint(){
		Random rand = new Random();
		int layer, row, column;
		
			layer = rand.nextInt(myMaze.getMaze().length - 2) + 1;
			if(layer % 2 == 0)
				layer++;
		
			row = rand.nextInt(myMaze.getMaze()[0].length - 2) + 1;
			if(row % 2 == 0)
				row++;	
		
			column = rand.nextInt(myMaze.getMaze()[0][0].length - 2) + 1;
			if(column % 2 == 0)
				column++;
			
		return new Position(layer, row, column, myMaze);
	}
	
	//	The DFS algorithms:
	private void depthFirstSearch(Position startPoint){
		
		stackOfPositions.add(new Position(startPoint));
		if(randomMovment(startPoint) == true)
			depthFirstSearch(startPoint);
		else{
			stackOfPositions.pop(); 
			if(stackOfPositions.isEmpty() == true)
				return;
			else
			depthFirstSearch(stackOfPositions.pop());
			
		}
	}
	
	
	
	
	
	
	
	
}
