package mvp.model.notifers;

public class ServerStateNotifier extends ModelNotifier {
	private boolean connectionSucceed;
	public boolean isConnectionSucceed() {
		return connectionSucceed;
	}
	public void setConnectionSucceed(boolean connectionSucceed) {
		this.connectionSucceed = connectionSucceed;
	}
	public ServerStateNotifier(boolean connectionSucceed) {
		this.connectionSucceed = connectionSucceed;
	}
}
