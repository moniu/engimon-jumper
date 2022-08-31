package jumper.stages;

import engimon.core.Game;
import engimon.graphics.ImageObject;
import jumper.menu.GameWon;
import engimon.core.Scene;
import engimon.core.objects.GameObject;
import engimon.physics.hitbox.RectangleHitbox;

public class Goal extends GameObject {

    private final ImageObject graphicObject;
    private final RectangleHitbox hitbox;
    private final Player player;

    public Goal(Scene scene, Player player, double posX, double posY, double sizeX, double sizeY) {
        super(scene);
        this.player = player;
        setLocalX(posX);
        setLocalY(posY);

        graphicObject = new ImageObject(scene, "./Images/goal.png");
        graphicObject.setSizeX(sizeX*1.5);
        graphicObject.setSizeY(sizeY*1.5);
        graphicObject.setLayer(50);
        graphicObject.setRelative(true);

        addChild(graphicObject);
        scene.registerGraphicObject(graphicObject);
        hitbox = new RectangleHitbox(scene, sizeX, sizeY);
        addChild(hitbox);
    }

    public void customTick(double deltaTime) {
        if(hitbox.checkCollision(player.getHitbox())) {
            Game.instance.setScene(new GameWon());
        }
    }


    public RectangleHitbox getHitbox() {
        return this.hitbox;
    }

    public Player getPlayer() {
        return this.player;
    }

}
