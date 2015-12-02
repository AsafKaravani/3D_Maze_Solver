package algorithms.search;

import java.util.ArrayList;

public class Solution<T extends Comparable<T>> {
	private ArrayList<State<T>> pathToSolution;
	
	
	public Solution() {
		this.pathToSolution = new ArrayList<State<T>>();
	}

	public ArrayList<State<T>> getPathToSolution() {
		return pathToSolution;
	}
	
	public void setPathToSolution(ArrayList<State<T>> pathToSolution) {
		this.pathToSolution = pathToSolution;
	}
	
	public void addState(State<T> state) {
		pathToSolution.add(state);
	}
}
