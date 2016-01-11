package mvp.view.entites;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;

import algorithms.mazeGenerators.Position;

public class Hole extends Entity {
	public Hole(Position pos, Device device) {
		this.device = device;
		this.pos = pos;
		this.sprite = new Image(device, "assets\\hole.png");
		this.layer = 1;
	}
}
