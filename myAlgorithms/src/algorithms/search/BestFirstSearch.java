package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class BestFirstSearch extends CommonSearcher {
	//---------Variables---------//
	//PriorityQueue<State> openList from "CommonSearcher"
	//Solution solution from "CommonSearcher"

	//----------Methods---------//
	/**
	 * @author Yaniv and Asaf
	 * @return The BFS is the algorithm that uses two arrays one for the point
	 *         that hasn't been visited and the second is for points that has been
	 *         visited and for every point it change its place in the arrays
	 *         after that it checks the low cost way from initial state to goal
	 *         state.
	 */
	@Override
	public Solution search(Searchable s) {

		addToOpenList(s.getInitialState());
		HashSet<State> closedSet = new HashSet<State>();
		
		while (openList.size() > 0) {
			State state = popOpenList(); //Getting the first state from openList and removing it.
			closedSet.add(state);
			
			if(state.equals(s.getGoalState())){
			
				return backTrace(state, s.getInitialState());
			}
			
			ArrayList<State> successors = s.getAllPossibleStates(state);
			for (State successor : successors) {
 				if (!closedSet.contains(successor) && !openList.contains(successor)) {
					successor.setParentState(state);
					addToOpenList(successor);
				}
				
				else {
					if(closedSet.contains(successor)){
					    for (Iterator<State> it = closedSet.iterator(); it.hasNext(); ) {
					        State tmp = it.next();
					        if (tmp.equals(successor)){
					            successor = tmp;
					            break;
					        }
					    }
					}
					else{
						  for (Iterator<State> it = openList.iterator(); it.hasNext(); ) {
						        State tmp = it.next();
						        if (tmp.equals(successor))
						            successor = tmp;
						    }
					}
					
					if(successor.getParentState() != null){ 
						if(state.getDistance() < successor.getParentState().getDistance()){
							successor.setParentState(state);
							if(!openList.contains(successor))
								addToOpenList(successor);	
						}	
					}
				}
			}
		}
		
		return solution;
	}
	/**
	*@author Yaniv and Asaf
	*@return Part of the BFS it gets the initial state and give an new array the low cost way and return it .
	 */
	private Solution backTrace(State goalState, State initialState) {
		//Back tracing through the parantStates statring with the goalState until getting to the initialState.
		Solution solution = new Solution();
		State tmpState = new State(goalState);
			solution.addState(tmpState);
		
		while (tmpState != initialState && tmpState != null){
			
			tmpState = tmpState.getParentState();
				solution.addState(tmpState);
		}
			
		return solution;
	}
	
	
	
}
