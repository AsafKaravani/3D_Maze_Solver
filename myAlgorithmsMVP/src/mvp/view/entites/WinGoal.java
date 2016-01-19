package mvp.view.entites;
 
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import algorithms.mazeGenerators.Position;
 
public class WinGoal extends Entity {
    public WinGoal(Position pos, Device device) {
        this.device = device;
        this.pos = pos;
        this.sprite = new Image(device, "assets\\treasure.png");
        this.layer=2;
    }
}