package mvp.view.entites;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;

import algorithms.mazeGenerators.Position;

public class Ladder extends Entity {

		public Ladder(Position pos, Device device) {
			this.device = device;
			this.pos = pos;
			this.sprite = new Image(device, "assets\\Ladder.png");
			this.layer = 1;
		}
}
