package jumper.stages;

import engimon.core.Scene;
import engimon.core.objects.GameObject;
import engimon.graphics.ImageObject;
import engimon.physics.hitbox.RectangleHitbox;
import engimon.physics.objects.PhysicalObject;


public class Platform extends GameObject {
    private final ImageObject grass;
    private final ImageObject dirt;
    private final RectangleHitbox hitbox;
    private final PhysicalObject physicalObject;
    public Platform(Scene scene, double posX, double posY, double sizeX, double sizeY) {
        super(scene);

        this.setLocalX(posX);
        this.setLocalY(posY);

        grass = new ImageObject(scene, "./Images/grass.png");
        grass.setSizeX(sizeX+20);
        grass.setSizeY(sizeY*0.9);
        grass.setLocalY(sizeY*-0.05);
        grass.setLayer(31);
        grass.setRelative(true);
        
        dirt = new ImageObject(scene, "./Images/dirt.png");
        dirt.setSizeX(sizeX);
        dirt.setSizeY(sizeY*0.9);
        dirt.setLocalY(sizeY*0.05);
        dirt.setLayer(30);
        dirt.setRelative(true);
        
        hitbox = new RectangleHitbox(scene, sizeX, sizeY);
        
        physicalObject = new PhysicalObject(scene);
        physicalObject.setFrozen(true);
        physicalObject.setHitbox(hitbox);
        
        addChild(grass);
        addChild(dirt);
        addChild(hitbox);
        addChild(physicalObject);
        scene.registerGraphicObject(grass);
        scene.registerGraphicObject(dirt);
        scene.registerPhysicalObject(physicalObject);

    }
    
}
