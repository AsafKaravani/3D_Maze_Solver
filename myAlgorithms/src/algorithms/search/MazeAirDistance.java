package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeAirDistance implements Heuristic<Position> {

	@Override
	public int costEvaluetion(State<Position>  s, State<Position>  goal) {
		double deltaColumn = Math.abs(s.getState().getColumn() - goal.getState().getColumn());
		double deltaRow = Math.abs(s.getState().getRow() - goal.getState().getRow());
		double deltaLayer = Math.abs(s.getState().getLayer() - goal.getState().getLayer());
		return (int) Math.sqrt(Math.pow(deltaColumn, 2) + Math.pow(deltaRow, 2) + Math.pow(deltaLayer, 2));
	}

}
