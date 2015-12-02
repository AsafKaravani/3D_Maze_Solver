package algorithms.search;

import java.util.ArrayList;

public interface Searchable<T extends Comparable<T>> {
	State getInitialState();
	State getGoalState();
	ArrayList<State<T> > getAllPossibleStates(State<T> s);
	
	
}
