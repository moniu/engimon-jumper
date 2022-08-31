package jumper;

import engimon.core.Scene;
import engimon.core.objects.GameObject;
import engimon.physics.hitbox.RectangleHitbox;
import engimon.physics.objects.PhysicalObject;
import jumper.stages.Platform;
import jumper.stages.Stage;

import java.awt.*;

public class TestStage1 extends Stage {
    public static void main(String[] args) {
        new Jumper(1000, 800);

        Jumper.instance.setScene(new TestStage1());
        Jumper.instance.start();
    }

    public TestStage1() {
        getGraphicObjects().clear();
        getPhysicalObjects().clear();
        removeAllChildren();
        setBackgroundColor(Color.gray);
        camera.setX(400);
        camera.setY(2000);

        Block block = new Block(this);
        block.setLocalX(400);
        block.setLocalY(1000);
        block.physicalObject.setGravityY(200);
        block.physicalObject.setWeight(100);
        block.physicalObject.setBounciness(20);
        addChild(block);

        Platform platform;
        platform = new Platform(this, 400, 2000, 300, 50);
        addChild(platform);
    }

    private class Block extends GameObject {
        public PhysicalObject physicalObject;
        public Block(Scene scene) {
            super(scene);
            physicalObject = new PhysicalObject(scene);
            physicalObject.setHitbox(new RectangleHitbox(scene, 100, 100));
            registerPhysicalObject(physicalObject);
            addChild(physicalObject);
        }

    }
}
