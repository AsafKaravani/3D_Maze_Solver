package mvp.model.notifers;

public class MazeSolutionNotifier extends ModelNotifier {
	private String mazeName;
	private boolean succeed;
	/**
	*@author Yaniv and Asaf
	*@return it return if the solution was successful
	 */
	public MazeSolutionNotifier(String mazeName, boolean succeed){
		this.mazeName = mazeName;
		this.succeed = succeed;
	}
	
	public String getMazeName() {
		return mazeName;
	}
	public boolean isSucceed() {
		return succeed;
	}
	
	
	
}
