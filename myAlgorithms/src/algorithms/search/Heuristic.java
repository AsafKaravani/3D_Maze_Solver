package algorithms.search;

public interface Heuristic<T extends Comparable<T>> {
	
 public int costEvaluetion(State<T> s, State<T> goal);
 
}
