package mvp.view;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;

import algorithms.mazeGenerators.Position;

public class Hint extends Entity {
		public Hint(Position pos, Device device) {
			this.device = device;
			this.pos = pos;
			this.sprite = new Image(device, "assets\\Hint_Coin.png");
		}
}
