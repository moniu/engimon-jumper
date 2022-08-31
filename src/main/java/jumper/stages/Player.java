package jumper.stages;

import engimon.core.Game;
import engimon.graphics.AnimatedObject;
import engimon.graphics.Animation;
import engimon.graphics.Camera;
import jumper.menu.GameOver;
import engimon.core.KeyHandler;
import engimon.core.Scene;
import engimon.core.managers.SoundManager;
import engimon.core.objects.GameObject;
import engimon.physics.hitbox.Hitbox;
import engimon.physics.hitbox.RectangleHitbox;
import engimon.physics.objects.PhysicalObject;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Random;

public class Player extends GameObject {
    private final PhysicalObject physicalObject;
    private final AnimatedObject graphicObject;
    private final Hitbox hitbox;

    private boolean facingRight;

    private final RectangleHitbox leftHitbox;
    private final RectangleHitbox rightHitbox;
    private final RectangleHitbox bottomHitbox;
    private final RectangleHitbox topHitbox;
    private double footstepTimeout;

    private final Random random;
    
    public Player(Scene scene) {
        super(scene);
        random = new Random();
        double playerWidth = 50;
        double playerHeight = 50;

        leftHitbox = new RectangleHitbox(scene, 1, playerHeight-2);
        leftHitbox.setLocalX(-playerWidth/2-2);
        addChild(leftHitbox);
        rightHitbox = new RectangleHitbox(scene, 1, playerHeight-2);
        rightHitbox.setLocalX(playerWidth/2+2);
        addChild(rightHitbox);
        topHitbox = new RectangleHitbox(scene, playerWidth-2, 1);
        topHitbox.setLocalY(-playerHeight/2-2);
        addChild(topHitbox);
        bottomHitbox = new RectangleHitbox(scene, playerWidth-2, 2);
        bottomHitbox.setLocalY(playerHeight/2+2);
        addChild(bottomHitbox);


        HashMap<String, Animation> animations = new HashMap<>();
        String[] filenames;
        Animation animation;

        SoundManager soundManager = Game.instance.getSoundManager();
        soundManager.preloadSound("./Sounds/step0.wav");
        soundManager.preloadSound("./Sounds/step1.wav");
        soundManager.preloadSound("./Sounds/step2.wav");
        soundManager.preloadSound("./Sounds/jump0.wav");

        filenames = new String[]{
            "./Images/DinoIdleLeft0.png",
            "./Images/DinoIdleLeft1.png",
            "./Images/DinoIdleLeft2.png",
            "./Images/DinoIdleLeft3.png",
        };
        animation = new Animation(scene, 0.1, filenames);
        animations.put("IdleLeft", animation);
        
        filenames = new String[]{
            "./Images/DinoIdleRight0.png",
            "./Images/DinoIdleRight1.png",
            "./Images/DinoIdleRight2.png",
            "./Images/DinoIdleRight3.png",
        };
        animation = new Animation(scene, 0.1, filenames);
        animations.put("IdleRight", animation);

        filenames = new String[]{
            "./Images/DinoWalkLeft0.png",
            "./Images/DinoWalkLeft1.png",
            "./Images/DinoWalkLeft2.png",
            "./Images/DinoWalkLeft3.png",
            "./Images/DinoWalkLeft4.png",
            "./Images/DinoWalkLeft5.png",
        };
        animation = new Animation(scene, 0.1, filenames);
        animations.put("WalkLeft", animation);

        filenames = new String[]{
            "./Images/DinoWalkRight0.png",
            "./Images/DinoWalkRight1.png",
            "./Images/DinoWalkRight2.png",
            "./Images/DinoWalkRight3.png",
            "./Images/DinoWalkRight4.png",
            "./Images/DinoWalkRight5.png",
        };
        animation = new Animation(scene, 0.1, filenames);
        animations.put("WalkRight", animation);

        this.graphicObject = new AnimatedObject(scene, animations);
        this.graphicObject.changeAnimation("IdleRight");
        this.graphicObject.setSizeX(playerWidth*1.5);
        this.graphicObject.setSizeY(playerHeight*1.5);
        this.graphicObject.setLayer(50);
        this.graphicObject.setRelative(true);
        
        this.physicalObject = new PhysicalObject(scene);
        this.hitbox = new RectangleHitbox(scene, playerWidth, playerHeight);
        this.physicalObject.setHitbox(hitbox);
        physicalObject.setHitbox(hitbox);
        physicalObject.setGravityY(80);
        physicalObject.setWeight(20);

        getScene().registerGraphicObject(graphicObject);
        getScene().registerPhysicalObject(physicalObject);
        addChild(graphicObject);
        addChild(physicalObject);

    }


    @Override
    public void customTick(double deltaTime) {

        boolean topCollides, bottomCollides, leftCollides, rightCollides;
        topCollides = false;
        bottomCollides = false;
        leftCollides = false;
        rightCollides = false;
        for (PhysicalObject po : getScene().getPhysicalObjects()) {
            if (po == this.physicalObject || !po.isColliding()) {
                continue;
            }
            Hitbox h = po.getHitbox();
            if (leftHitbox.checkCollision(h)) {
                leftCollides = true;
            }
            if (rightHitbox.checkCollision(h)) {
                rightCollides = true;
            }
            if (topHitbox.checkCollision(h)) {
                topCollides = true;
            }
            if (bottomHitbox.checkCollision(h)) {
                bottomCollides = true;
            }
        }

        SoundManager soundManager = Game.instance.getSoundManager();
        KeyHandler keyHandler = Game.instance.getKeyHandler();
        if(keyHandler.getKeyPressed(KeyEvent.VK_LEFT) && !leftCollides) {
            this.physicalObject.setSpeedX(Math.min(-600, this.physicalObject.getSpeedX() ) );
            this.facingRight = false;
            this.graphicObject.changeAnimation("WalkLeft");
            if (bottomCollides) {
                playFootstep(deltaTime);
            }
        }
        if(keyHandler.getKeyPressed(KeyEvent.VK_RIGHT) && !rightCollides) {
            this.physicalObject.setSpeedX(Math.max(600,  this.physicalObject.getSpeedX() ) );
            this.facingRight = true;
            this.graphicObject.changeAnimation("WalkRight");
            if (bottomCollides) {
                playFootstep(deltaTime);
            }
        }
        if (!keyHandler.getKeyPressed(KeyEvent.VK_LEFT) && !keyHandler.getKeyPressed(KeyEvent.VK_RIGHT) ) {
            this.physicalObject.setSpeedX(0);
            this.footstepTimeout = 0;
            if (facingRight) {
                this.graphicObject.changeAnimation("IdleRight");
            }
            else {
                this.graphicObject.changeAnimation("IdleLeft");
            }
        }
        if(keyHandler.getKeyPressed(KeyEvent.VK_UP) && !topCollides && bottomCollides) {
            this.physicalObject.setSpeedY(-1000);
            soundManager.playSound("./Sounds/jump0.wav");
        }

        //@TODO CHUJNIA TUTAJ
        if(keyHandler.getKeyPressed(KeyEvent.VK_UP)) {
            this.physicalObject.setSpeedY(-1000);
            soundManager.playSound("./Sounds/jump0.wav");
        }
        if(keyHandler.getKeyPressed(KeyEvent.VK_SPACE)) {
            kill();
        }

        
        Camera camera = getScene().getCamera();
        camera.setX(this.getGlobalX());
        camera.setY(this.getGlobalY());
    }

    public void kill() {
        getScene().unregisterGraphicObject(graphicObject);
        getScene().unregisterPhysicalObject(physicalObject);
        removeAllChildren();
        getScene().removeChild(this);
        Game.instance.setScene(new GameOver((Stage)getScene()));
    }

    public void playFootstep(double deltaTime) {
        footstepTimeout-=deltaTime;
        if (footstepTimeout < 0) {
            footstepTimeout = 300_000_000;
            SoundManager soundManager = Game.instance.getSoundManager();
            
            int r = random.nextInt(2);
            soundManager.playSound("./Sounds/step"+r+".wav");

        }
    }


    public PhysicalObject getPhysicalObject() {
        return this.physicalObject;
    }

    public AnimatedObject getGraphicObject() {
        return this.graphicObject;
    }

    public Hitbox getHitbox() {
        return this.hitbox;
    }

    public RectangleHitbox getLeftHitbox() {
        return this.leftHitbox;
    }

    public RectangleHitbox getRightHitbox() {
        return this.rightHitbox;
    }

    public RectangleHitbox getBottomHitbox() {
        return this.bottomHitbox;
    }

    public RectangleHitbox getTopHitbox() {
        return this.topHitbox;
    }
    
    
}
