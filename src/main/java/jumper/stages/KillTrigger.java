package jumper.stages;

import engimon.core.Scene;
import engimon.core.objects.GameObject;
import engimon.physics.hitbox.RectangleHitbox;

public class KillTrigger extends GameObject {

    private final RectangleHitbox hitbox;
    private final Player player;
    private final double sizeX;
    private final double sizeY;

    public KillTrigger(Scene scene, Player player, double sizeX, double sizeY) {
        super(scene);

        this.player = player;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        
        hitbox = new RectangleHitbox(scene, sizeX, sizeY);
        addChild(hitbox);
    }

    public void customTick(double deltaTime) {
        if(hitbox.checkCollision(player.getHitbox())) {
            player.kill();
        }
    }


    public RectangleHitbox getHitbox() {
        return this.hitbox;
    }

    public Player getPlayer() {
        return this.player;
    }

    public double getSizeX() {
        return this.sizeX;
    }

    public double getSizeY() {
        return this.sizeY;
    }

}
