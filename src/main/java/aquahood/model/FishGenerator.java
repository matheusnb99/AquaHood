package aquahood.model;


import aquahood.Constants;

import java.util.ArrayList;
import java.util.List;

public class FishGenerator {

    private List<Fish> fish;

    public FishGenerator(int nbBoids){
        fish = new ArrayList<>();
        for (int i = 0; i < nbBoids; i++){
            fish.add(new Fish());
        }
    }

    public void updateBoidsDirections(){
        for (Fish b : fish){
            //Fish b = fish.get(0);
            List<Fish> closeFish = new ArrayList<>();
            for (Fish b1 : fish){
                if (!b1.equals(b)){
                    Direction distanceVector = Direction.fromPositions(
                        b1.pos, b.pos);
                    if (distanceVector.norm() < Constants.MAX_DISTANCE_DETECTION &&
                        distanceVector.dot(b.dir) > -0.5)
                        closeFish.add(b1);
                }
            }
            b.updateDirection(closeFish);
        }
    }

    public void updateBoidsPositions(){ fish.forEach(Fish::updatePosition); }

    public List<Fish> getBoids(){ return fish; }
    
}
