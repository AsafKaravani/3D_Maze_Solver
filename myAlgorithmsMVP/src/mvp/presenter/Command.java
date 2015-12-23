package mvp.presenter;

public abstract class Command {
	
	String[] args;
	
	public abstract void doCommand(String[] args);
	/**
	*@author Yaniv and Asaf
	*@return Print the command and a brief explanation
	 */

	public Command insertArgs(String[] args){
		this.args = args;
		return this;
	}
	
	public String[] getArgs() {
		return args;
	}
	public void setArgs(String[] args) {
		this.args = args;
	}
	
}
