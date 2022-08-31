package jumper.menu;

import engimon.core.Game;
import engimon.core.KeyHandler;
import engimon.core.Scene;
import engimon.graphics.SquareGraphicObject;
import jumper.Jumper;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameWon extends Scene {
    
    private final Title title;
    private final Option option;
    private final SquareGraphicObject background;

    public GameWon() {

        background = new SquareGraphicObject(this);
        background.setColor(Color.DARK_GRAY);
        background.setSizeX(Game.instance.getGameWidth());
        background.setSizeY(Game.instance.getGameHeight());
        addChild(background);
        registerGraphicObject(background);

        title = new Title(this,"Game Won", "Good job!");
        title.setLocalY(-300);
        addChild(title);

        option = new Option(this, "Main Menu");
        option.setLocalY(0);
        option.setSelected(true);
        addChild(option);
        
    }

    public void customTick(double deltaTime) {
        KeyHandler keyHandler = Game.instance.getKeyHandler();
        if (keyHandler.getKeyPressedOnce(KeyEvent.VK_ENTER)) {
                Jumper.jumperInstance.changeToMenu();
        }
    }
    
}
