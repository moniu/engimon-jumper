package jumper.menu;

import engimon.core.Game;
import engimon.core.KeyHandler;
import engimon.core.Scene;
import engimon.graphics.SquareGraphicObject;
import jumper.Jumper;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuScene extends Scene {

    private int selectedNr;
    
    private final Title title;
    private final Option[] options;
    private final SquareGraphicObject background;

    public MenuScene() {

        background = new SquareGraphicObject(this);
        background.setColor(Color.DARK_GRAY);
        background.setSizeX(Game.instance.getGameWidth());
        background.setSizeY(Game.instance.getGameHeight());
        addChild(background);
        registerGraphicObject(background);

        title = new Title(this,"Jumper", "An amazing game");
        title.setLocalY(-300);
        addChild(title);

        options = new Option[3];
        options[0] = new Option(this, "Stage 1");
        options[1] = new Option(this, "Stage 2");
        options[2] = new Option(this, "Exit");

        options[0].setLocalY(0);
        options[1].setLocalY(150);
        options[2].setLocalY(300);

        addChild(options[0]);
        addChild(options[1]);
        addChild(options[2]);

    }

    public void customTick(double deltaTime) {
        KeyHandler keyHandler = Game.instance.getKeyHandler();
        if(keyHandler.getKeyPressedOnce(KeyEvent.VK_DOWN)) {
            selectedNr += 1;
            if (selectedNr > 2) {
                selectedNr = 0;
            }
        }
        if(keyHandler.getKeyPressedOnce(KeyEvent.VK_UP)) {
            selectedNr -= 1;
            if (selectedNr < 0) {
                selectedNr = 2;
            }  
        }
        if (selectedNr == 0) {
            options[0].setSelected(true);
            options[1].setSelected(false);
            options[2].setSelected(false);
        }
        if (selectedNr == 1) {
            options[0].setSelected(false);
            options[1].setSelected(true);
            options[2].setSelected(false);
        }
        if (selectedNr == 2) {
            options[0].setSelected(false);
            options[1].setSelected(false);
            options[2].setSelected(true);
        }
        if (keyHandler.getKeyPressedOnce(KeyEvent.VK_ENTER)) {
            if (selectedNr == 0 || selectedNr == 1) {
                ((Jumper)Jumper.instance).changeToStage(selectedNr);
            }

            if (selectedNr == 2) {
                Game.instance.exit();
            }
        }
    }


}
