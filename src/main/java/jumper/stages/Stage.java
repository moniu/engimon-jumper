package jumper.stages;

import engimon.core.Game;
import engimon.core.Scene;

public class Stage extends Scene {

    public Stage() {
        setup();
    }

    public void setup() {
        removeAllChildren();
        getGraphicObjects().clear();
        getPhysicalObjects().clear();
        
        camera.setX(0);
        camera.setY(0);
    }
}
