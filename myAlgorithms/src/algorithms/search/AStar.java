package algorithms.search;

import java.util.Comparator;

public class AStar extends BestFirstSearch {
	Heuristic h;

	/**
	 * @author Yaniv and asaf
	 * @return Using the Astar algorithm the algo uses the cost of evaluated
	 *         nodes and give the the result to the solve of the maze.
	 */
	public AStar(Heuristic h, State goal){
			this.h = h;
			setPriorityQueueComperator(new Comparator<State>() {

				@Override
				public int compare(State s1, State s2) {
					return (h.costEvaluetion(s1, goal) - h.costEvaluetion(s2, goal)) + s1.compareTo(s2);
				}		
			});
	}
}
