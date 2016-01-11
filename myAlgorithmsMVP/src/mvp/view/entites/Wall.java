package mvp.view.entites;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;

import algorithms.mazeGenerators.Position;

public class Wall extends Entity {

	public Wall(Position pos, Device device) {
		this.device = device;
		this.pos = pos;
		this.sprite = new Image(device, "assets\\brick_wall_tiled_perfect.png");
		this.layer=0;
	}




}
