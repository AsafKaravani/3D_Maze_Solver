package mvp.view;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;

import algorithms.mazeGenerators.Position;

public class Character extends Entity {

	public Character(Position pos, Device device) {
		this.device = device;
		this.pos = pos;
		this.sprite = new Image(device, "assets\\Luffy_Front.png");
	}

	public void moveRight() {
		this.sprite = new Image(device, "assets\\Luffy_Right.png");
		this.pos.setColumn(this.pos.getColumn() + 1);
	}

	public void moveLeft() {
		this.sprite = new Image(device, "assets\\Luffy_Left.png");
		this.pos.setColumn(this.pos.getColumn() - 1);
	}

	public void moveUp() {
		this.sprite = new Image(device, "assets\\Luffy_Back.png");
		this.pos.setRow(this.pos.getRow() - 1);
	}

	public void moveDown() {
		this.sprite = new Image(device, "assets\\Luffy_Front.png");
		this.pos.setRow(this.pos.getRow() + 1);
	}

	public void moveUpLayer() {
		this.sprite = new Image(device, "assets\\Luffy_Right.png");
		this.pos.setLayer(this.pos.getLayer() - 1);
	}

	public void moveDownLayer() {
		this.sprite = new Image(device, "assets\\Luffy_Right.png");
		this.pos.setLayer(this.pos.getLayer() + 1);
	}

}
