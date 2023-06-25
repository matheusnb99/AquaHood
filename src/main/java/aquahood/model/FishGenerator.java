package aquahood.model;


import aquahood.Constants;

import java.util.ArrayList;
import java.util.List;

public class FishGenerator {

    private double visionDistance = 50; // Define the vision distance
    private List<Fish> fish;

    public FishGenerator(int nbFish){
        fish = new ArrayList<>();
        for (int i = 0; i < nbFish; i++){
            fish.add(new Fish());
        }
    }
    public void updateFishPositions() {
        for (Fish b : fish) {
            List<Fish> closeFish = new ArrayList<>();
            for (Fish b1 : fish) {
                if (!b1.equals(b)) {
                    double distance = Math.sqrt(Math.pow(b1.pos.x - b.pos.x, 2) + Math.pow(b1.pos.y - b.pos.y, 2));
                    if (distance < visionDistance) {
                        closeFish.add(b1);
                    }
                }
            }

            // Calculate average speed of nearby fish
            double avgSpeed = 0;
            if (!closeFish.isEmpty()) {
                for (Fish nearbyFish : closeFish) {
                    avgSpeed += nearbyFish.speed;
                }
                avgSpeed /= closeFish.size();
            } else {
                avgSpeed = b.speed; // Maintain current speed if no fish nearby
            }

            // Set the current fish's speed to the average speed of nearby fish
            b.speed = avgSpeed;
            b.updatePosition(closeFish);
        }
    }

    public void updateFishDirections(){
        for (Fish b : fish){
            List<Fish> closeFish = new ArrayList<>();
            for (Fish b1 : fish){
                if (!b1.equals(b)){
                    Direction distanceVector = Direction.fromPositions(
                            b1.pos, b.pos);
                    if (distanceVector.norm() < Constants.MAX_DISTANCE_DETECTION &&
                            distanceVector.dot(b.dir) > -0.5) {
                        closeFish.add(b1);
                    }
                }
            }
            b.updateDirection(closeFish);
        }
    }



    public List<Fish> getBoids(){ return fish; }
    
}
