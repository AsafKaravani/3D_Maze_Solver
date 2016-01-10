package mvp.view;


import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;

import algorithms.mazeGenerators.Position;

public abstract class Entity {
	protected Position pos;
	protected Image sprite;
	protected Device device;
	protected double scale = 1;
	


	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}

	public void draw(GC gc) {
		final Image scaledImage = new Image(device,
				sprite.getImageData().scaledTo((int) (scale * 64), (int) (scale * 64)));
		gc.drawImage(scaledImage, (int) (pos.getColumn() * (64 * scale)),
				(int) (pos.getRow() * (64 * scale)));
	}
	
}
