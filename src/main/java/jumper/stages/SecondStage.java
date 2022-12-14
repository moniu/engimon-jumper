package jumper.stages;

import engimon.core.Game;
import engimon.core.KeyHandler;
import engimon.graphics.ImageObject;
import jumper.menu.PauseMenu;

import java.awt.*;
import java.awt.event.KeyEvent;


public class SecondStage extends Stage {
    private Player player;
    private Platform rotatingPlatform;

    private ImageObject background0;

    
    public SecondStage() {
        backgroundColor = Color.BLUE;
    }

    public void setup() {
        getGraphicObjects().clear();
        getPhysicalObjects().clear();
        removeAllChildren();
        camera.setX(0);
        camera.setY(0);

        player = new Player(this);
        player.setLocalX(400);
        player.setLocalY(1900);
        addChild(player);

        background0 = new ImageObject(this, "./Images/gradient.png");
        background0.setSizeX(20000);
        background0.setSizeY(12000);
        background0.setRelative(true);
        background0.setLayer(0);
        addChild(background0);
        registerGraphicObject(background0);


        Platform platform;
        platform = new Platform(this, 400, 2000, 300, 50);
        addChild(platform);

        rotatingPlatform = new Platform(this, 200, 2000, 30, 500);
        addChild(rotatingPlatform);
        platform = new Platform(this, 650, 1900, 200, 50);
        addChild(platform);
        platform = new Platform(this, 850, 1750, 200, 100);
        addChild(platform);
        platform = new Platform(this, 1150, 1700, 200, 50);
        addChild(platform);
        platform = new Platform(this, 1500, 1700, 100, 50);
        addChild(platform);
        platform = new Platform(this, 2150, 1600, 200, 50);
        addChild(platform);
        platform = new Platform(this, 1850, 1700, 100, 50);
        addChild(platform);
        platform = new Platform(this, 2000, 1300, 200, 50);
        addChild(platform);
        platform = new Platform(this, 1850, 1100, 100, 50);
        addChild(platform);
        platform = new Platform(this, 1450, 1100, 200, 50);
        addChild(platform);
        platform = new Platform(this, 950, 1100, 400, 50);
        addChild(platform);
        platform = new Platform(this, 200, 1000, 200, 50);
        addChild(platform);
        Goal goal = new Goal(this, player, 200, 950, 50, 50);
        addChild(goal);
        KillTrigger killTrigger = new KillTrigger(this, player, 5000, 100);
        killTrigger.setLocalY(2500);
        addChild(killTrigger);
    }

    public void customTick(double deltaTime) {
        KeyHandler keyHandler = Game.instance.getKeyHandler();
        if (keyHandler.getKeyPressed(KeyEvent.VK_ESCAPE)) {
            Game.instance.setScene(new PauseMenu(this));
        }

        //background parallax
        double cameraX, cameraY;
        cameraX = getCamera().getX() - 400;
        cameraY = getCamera().getY() - 1900;

        background0.setLocalX(cameraX*0.8);
        background0.setLocalY(cameraY*0.8);

        synchronized (rotatingPlatform) {

            rotatingPlatform.setLocalAngle(rotatingPlatform.getLocalAngle() + 0.1 * deltaTime);
        }


    }
    
}
