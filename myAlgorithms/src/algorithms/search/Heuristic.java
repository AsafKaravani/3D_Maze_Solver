package algorithms.search;

public interface Heuristic<T extends Comparable<T>> {
	/**
	*@author Yaniv and Asaf
	*@return The cost for the algo to reach the goal state.
	 */
 public int costEvaluetion(State<T> s, State<T> goal);
 
}
