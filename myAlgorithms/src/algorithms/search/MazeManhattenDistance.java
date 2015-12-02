package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeManhattenDistance implements Heuristic<Position> {

	@Override
	public int costEvaluetion(State<Position> s, State<Position> goal) {
		int deltaColumn = Math.abs(s.getState().getColumn() - goal.getState().getColumn());
		int deltaRow = Math.abs(s.getState().getRow() - goal.getState().getRow());
		int deltaLayer = Math.abs(s.getState().getLayer() - goal.getState().getLayer());
		return deltaColumn + deltaRow + deltaLayer;
	}

}
