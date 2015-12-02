package algorithms.search;

import java.util.Comparator;
import java.util.PriorityQueue;

public abstract class CommonSearcher implements Searcher {
	//---------Variables---------//
	protected PriorityQueue<State> openList;
	private int evaluetedNodes;
	protected Solution solution;
	
	//----------Methods---------//
	public CommonSearcher() {
		openList = new PriorityQueue<State>();
		evaluetedNodes = 0;
	}
	
	protected State popOpenList() {
		evaluetedNodes++;
		return openList.poll();
	}

	@Override
	public abstract Solution search(Searchable s);
	
	@Override
	public int getNumberOfNodesEvalueted() {
		return evaluetedNodes;
	}
	
	protected void addToOpenList(State state) {
		openList.add(state);
	}
	
	protected  void setPriorityQueueComperator(Comparator comp) {
		openList = new PriorityQueue(comp);
	}
}
	