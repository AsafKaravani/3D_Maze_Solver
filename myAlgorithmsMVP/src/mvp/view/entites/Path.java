package mvp.view.entites;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;

import algorithms.mazeGenerators.Position;

public class Path extends Entity {

	public Path(Position pos, Device device) {
		this.device = device;
		this.pos = pos;
		this.sprite = new Image(device, "assets\\grass2.jpg");
		this.layer=0;
	}
}
