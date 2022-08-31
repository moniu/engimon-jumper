package jumper.menu;

import engimon.core.Game;
import engimon.core.KeyHandler;
import engimon.core.Scene;
import engimon.graphics.SquareGraphicObject;
import jumper.Jumper;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PauseMenu extends Scene {

    private final Scene returnScene;
    private int selectedNr;
    
    private final Title title;
    private final Option[] options;
    private final SquareGraphicObject background;

    public PauseMenu(Scene returnScene) {

        this.returnScene = returnScene;

        background = new SquareGraphicObject(this);
        background.setColor(Color.DARK_GRAY);
        background.setSizeX(Game.instance.getGameWidth());
        background.setSizeY(Game.instance.getGameHeight());
        addChild(background);
        registerGraphicObject(background);

        title = new Title(this,"Paused", "Need a break?");
        title.setLocalY(-300);
        addChild(title);

        options = new Option[2];
        options[0] = new Option(this, "Return");
        options[1] = new Option(this, "Main menu");
        options[0].setLocalY(0);
        options[1].setLocalY(150);
        addChild(options[0]);
        addChild(options[1]);
        
    }

    public void customTick(double deltaTime) {
        KeyHandler keyHandler = Game.instance.getKeyHandler();
        if(keyHandler.getKeyPressedOnce(KeyEvent.VK_DOWN)) {
            selectedNr += 1;
            if (selectedNr > 1) {
                selectedNr = 0;
            }
        }
        if(keyHandler.getKeyPressedOnce(KeyEvent.VK_UP)) {
            selectedNr -= 1;
            if (selectedNr < 0) {
                selectedNr = 1;
            }  
        }
        if (selectedNr == 0) {
            options[0].setSelected(true);
            options[1].setSelected(false);
        }
        if (selectedNr == 1) {
            options[0].setSelected(false);
            options[1].setSelected(true);
        }
        if (keyHandler.getKeyPressedOnce(KeyEvent.VK_ENTER)) {
            if (selectedNr == 0) {
                Jumper.jumperInstance.setScene(returnScene);
            }
            if (selectedNr == 1) {
                Jumper.jumperInstance.changeToMenu();
            }
        }
    }
    
}
