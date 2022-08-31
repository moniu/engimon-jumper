package jumper;

import engimon.physics.signals.HitboxCollisionSignal;
import engimon.signals.SignalQueue;
import jumper.menu.MenuScene;
import jumper.stages.FirstStage;
import jumper.stages.SecondStage;
import jumper.stages.Stage;
import engimon.core.Game;

public class Jumper extends Game {

    public static Jumper jumperInstance;
    private final MenuScene menuScene;
    private final Stage[] stages;
    
    public Jumper(int width, int height) {
        super(width, height, "Jumper (Engimon test game)");
        jumperInstance = this;
        menuScene = new MenuScene();
        stages = new Stage[2];
        stages[0] = new FirstStage();
        stages[1] = new SecondStage();
        //getSoundManager().playLoop("./Sounds/When_Im_46.wav");
        SignalQueue.registerListener(
                HitboxCollisionSignal.class,
                (sig) -> {
                    HitboxCollisionSignal signal = (HitboxCollisionSignal) sig;
                }
        );
        getGameVariables().setDeltaScale(0.2);
        setScene(menuScene);
    }

    public static void main(String[] args) {
        new Jumper(1000,800);
        Jumper.instance.start();
    }

    public void changeToMenu() {
        setScene(menuScene);
    }
    public void changeToStage(int stageNr) {
        stages[stageNr].setup();
        setScene(stages[stageNr]);
    }

    public MenuScene getMenuScene() {
        return menuScene;
    }
    
}
