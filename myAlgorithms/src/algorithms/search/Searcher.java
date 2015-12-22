package algorithms.search;

public interface Searcher {
	//the search method
	/**
	 * @author Yaniv and asaf
	 * @return A solution the contains the states needed to pass in order to get
	 *         form the start point to the end point.
	 */
	public Solution search(Searchable s);
	//get how many nodes were evaluated by the algorithm
	/**
	*@author Yaniv and asaf
	*@return Get how many nodes were evaluated by the algo.
	 */
	public int getNumberOfNodesEvalueted();

}
