package algorithms.search;

import java.util.ArrayList;

public interface Searchable<T extends Comparable<T>> {
	/**
	*@author Yaniv and Asaf
	*@return Return you the start point.
	 */
	State getInitialState();
	/**
	*@author Yaniv and Asaf
	*@return Return you the end point.
	 */
	State getGoalState();
	/**
	*@author Yaniv and asaf
	*@return Gives you all the possible states from a given states.
	 */
	ArrayList<State<T> > getAllPossibleStates(State<T> s);
	
	
}
