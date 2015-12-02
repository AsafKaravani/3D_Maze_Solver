package algorithms.search;

public interface Searcher {
	//the search method
	public Solution search(Searchable s);
	//get how many nodes were evaluated by the algorithm
	public int getNumberOfNodesEvalueted();

}
