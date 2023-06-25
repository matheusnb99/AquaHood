package aquahood.ui_elems;

import aquahood.model.Fish;
import aquahood.model.FishGenerator;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class FishGeneratorUi extends Pane {

    private FishGenerator fishGenerator;
    List<FishUi> boidsShapes;

    public FishGeneratorUi(int nbBoids){
        super();
        this.fishGenerator = new FishGenerator(nbBoids);
        boidsShapes = new ArrayList<>();
        for (Fish b : fishGenerator.getBoids()){
            boidsShapes.add(new FishUi(b));
        }
        this.getChildren().addAll(boidsShapes);
    }

    public void setCanvasSize(double width, double height){
        for (FishUi bui : boidsShapes){
            bui.setBounds(width, height);
        }
    }

    public void update(){
        //fishGenerator.updateBoidsDirections();
        // fishGenerator.updateBoidsPositions();

        fishGenerator.getBoids().forEach(Fish::updatePosition);
        boidsShapes.forEach(FishUi::draw);
    }
    
}
