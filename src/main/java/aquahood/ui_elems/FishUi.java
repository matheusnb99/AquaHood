package aquahood.ui_elems;

import aquahood.model.Fish;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class FishUi extends Polygon {

    private Fish fish;
    private Color normalColor = Color.AQUAMARINE;
    private Translate translation;
    private Rotate orientation;

    public FishUi(Fish fish){
        super();
        this.fish = fish;
        getPoints().addAll(new Double[]{        
            0.0, 10.0, 
            5.0, -10.0, 
            -5.0, -10.0,  
            });
        setFill(normalColor);
        orientation = new Rotate();
        translation = new Translate();
        getTransforms().addAll(translation, orientation);
    }

    public void setBounds(double width, double height){
        fish.setBounds(width, height);
    }

    public void draw() {
        translation.setX(fish.pos.x);
        translation.setY(fish.pos.y);
        // orientation.setAngle(fish.angle);

        orientation.setAngle(Math.toDegrees(fish.angle + Math.PI / 2) + 270);
    }
}
