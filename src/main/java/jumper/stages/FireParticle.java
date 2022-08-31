package jumper.stages;

import engimon.core.Scene;
import engimon.graphics.Animation;
import engimon.particles.Particle;
import engimon.physics.hitbox.RectangleHitbox;
import engimon.physics.objects.PhysicalObject;

public class FireParticle extends Particle {

    protected PhysicalObject physicalObject;
    protected Animation graphicObject;
    protected RectangleHitbox hitbox;
    protected double shakingAngle;

    public FireParticle(Scene scene) {
        super(scene);
        setLifeSpan(1);

        String[] frames = {
            "./Images/fire0.png",
            "./Images/fire1.png",
            "./Images/fire2.png",
            "./Images/fire3.png",
            "./Images/fire4.png",
        };
        graphicObject = new Animation(scene, 0.2, frames);
        graphicObject.setSizeX(40);
        graphicObject.setSizeY(40);
        graphicObject.setRelative(true);
        graphicObject.setLayer(32);
        addChild(graphicObject);
        getScene().registerGraphicObject(graphicObject);


        physicalObject = new PhysicalObject(scene);
        physicalObject.setColliding(false);
        physicalObject.setGravityY(-30);
        physicalObject.setWeight(10);
        addChild(physicalObject);

        hitbox = new RectangleHitbox(scene, 10, 10);
        addChild(hitbox);
        physicalObject.setHitbox(hitbox);

        scene.registerPhysicalObject(physicalObject);

        
    }

    @Override
    public void customTick(double deltaTime) {
        shakingAngle += deltaTime;
        shakingAngle %= 2*Math.PI;

        physicalObject.setSpeedX(10*Math.sin(shakingAngle));
    }

    @Override
    public synchronized void kill() {
        getScene().unregisterGraphicObject(graphicObject);
        getScene().unregisterPhysicalObject(physicalObject);
        getScene().unregisterHitbox(hitbox);
        removeAllChildren();
        getMaster().removeChild(this);
    }
    
}
