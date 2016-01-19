package mvp.model.notifers;

public class MazeCreationNotifier extends ModelNotifier {
	private String mazeName;
	private boolean succeed;
	/**
	*@author Yaniv and Asaf
	*@return it return if the maze was created successful
	 */
	public MazeCreationNotifier(String mazeName, boolean succeed){
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
