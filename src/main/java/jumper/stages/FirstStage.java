package jumper.stages;

import engimon.core.Game;
import engimon.core.KeyHandler;
import jumper.menu.PauseMenu;

import java.awt.*;
import java.awt.event.KeyEvent;


public class FirstStage extends Stage {
    private Player player;
    public FirstStage() {
        backgroundColor = Color.BLUE;
    }

    @Override
    public void setup() {
        removeAllChildren();
        getGraphicObjects().clear();
        getPhysicalObjects().clear();
        camera.setX(0);
        camera.setY(0);

        player = new Player(this);
        addChild(player);

        Platform platform = new Platform(this, 0, 150, 5000, 50);
        addChild(platform);
        
        platform = new Platform(this, 200, 50, 50, 50);
        addChild(platform);
        Fire fire = new Fire(this);
        addChild(fire);
        fire.setLocalX(100);
        fire.setLocalY(100);

        fire = new Fire(this);
        addChild(fire);
        fire.setLocalX(200);
        fire.setLocalY(100);
    }

    public void customTick(double deltaTime) {
        KeyHandler keyHandler = Game.instance.getKeyHandler();
        if (keyHandler.getKeyPressed(KeyEvent.VK_ESCAPE)) {
            Game.instance.setScene(new PauseMenu( this));
        }
    }
    
}
