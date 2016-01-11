package mvp.view.entites;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import algorithms.mazeGenerators.Position;

public abstract class Entity {
	protected Position pos;
	protected Image sprite;
	protected Device device;
	protected double scaleX = 1;
	protected double scaleY = 1;
	protected int layer;

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public Position getPos() {
		return pos;
	}

	public Image getSprite() {
		return sprite;
	}

	public double getScaleX() {
		return scaleX;
	}

	public void setScaleX(double scaleX) {
		this.scaleX = scaleX;
	}

	public double getScaleY() {
		return scaleY;
	}

	public void setScaleY(double scaleY) {
		this.scaleY = scaleY;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}

	public void draw(GC gc) {
		final Image scaledImage = new Image(device, sprite.getImageData().scaledTo((int) (scaleX), (int) (scaleY)));
		gc.drawImage(scaledImage, (int) (pos.getColumn() * (scaleX)), (int) (pos.getRow() * (scaleY)));

		scaledImage.dispose();

	}

}
