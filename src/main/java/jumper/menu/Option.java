package jumper.menu;

import engimon.core.Scene;
import engimon.core.objects.GameObject;
import engimon.graphics.SquareGraphicObject;
import engimon.graphics.Text;

import java.awt.*;

public class Option extends GameObject {
    private boolean selected;
    private final Text text;
    private final SquareGraphicObject bigRectangle;
    private final SquareGraphicObject smallRectangle;

    public Option(Scene scene, String str) {
        super(scene);

        bigRectangle = new SquareGraphicObject(scene);
        bigRectangle.setSizeX(350);
        bigRectangle.setSizeY(120);
        bigRectangle.setColor(Color.ORANGE);
        bigRectangle.setLayer(10);

        smallRectangle = new SquareGraphicObject(scene);
        smallRectangle.setSizeX(330);
        smallRectangle.setSizeY(100);
        smallRectangle.setColor(Color.YELLOW);
        smallRectangle.setLayer(11);

        text = new Text(scene, str, 20, Color.BLACK);
        text.setLayer(12);
        
        addChild(bigRectangle);
        addChild(smallRectangle);
        bigRectangle.addChild(text);

        scene.registerGraphicObject(bigRectangle);
        scene.registerGraphicObject(smallRectangle);
        scene.registerGraphicObject(text);
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        if (selected) {
            this.bigRectangle.setColor(Color.YELLOW);
            this.smallRectangle.setColor(Color.WHITE);
        }
        if (!selected) {
            this.bigRectangle.setColor(Color.ORANGE);
            this.smallRectangle.setColor(Color.YELLOW);
        }
    }
    public boolean getSelected() {
        return selected;
    }
    
}
