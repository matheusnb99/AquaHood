package aquahood;

import aquahood.ui_elems.FishGeneratorUi;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private Scene scene;
    private AnimationTimer gameloop;
    private FishGeneratorUi fishGeneratorUi;

    private long lastUpdateTime = 0;

    @Override
    public void start(Stage stage) throws IOException {
        fishGeneratorUi = new FishGeneratorUi(Constants.NB_BOIDS);

        scene = new Scene(fishGeneratorUi,
            Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);


        stage.setTitle("Floque de poissons");
        stage.setScene(scene);
        stage.centerOnScreen();

        // generate two cirlces with radius R and r
        Circle circle1 = new Circle(Constants.SCENE_WIDTH/2, Constants.SCENE_HEIGHT/2, Constants.MAJOR_RADIUS + 10)  ;
        circle1.setFill(Color.TRANSPARENT);
        circle1.setStroke(Color.BLACK);

        Circle circle2 = new Circle(Constants.SCENE_WIDTH/2, Constants.SCENE_HEIGHT/2, Constants.MINOR_RADIUS - 10);
        circle2.setFill(Color.TRANSPARENT);
        circle2.setStroke(Color.BLACK);


        // adding two cercles to scene
        Group group = new Group();
        group.getChildren().add(circle1);
        group.getChildren().add(circle2);
        group.getChildren().add(fishGeneratorUi);
        scene.setRoot(group);

        stage.show();

        runSimulation();
    }

    private void runSimulation(){
        fishGeneratorUi.setCanvasSize(
            Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);
        gameloop = new AnimationTimer() {

            @Override
            public void handle(long now) {
                if ((now - lastUpdateTime) >= 100000000/ Constants.FPS) {
                    lastUpdateTime = now;
                    fishGeneratorUi.update();
                    System.out.println("FPS: ");
                }
            }
        };

        gameloop.start();
    }


    public static void main(String[] args) {
        launch();
    }

}