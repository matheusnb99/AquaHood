package aquahood.model;

import aquahood.Constants;

import java.util.List;

public class Fish {

    public Direction dir;
    public Position pos;
    public double angle;


    private double max_width = Constants.SCENE_WIDTH;
    private double max_height = Constants.SCENE_HEIGHT;

    private double centerX = Constants.SCENE_WIDTH/2;
    private double centerY = Constants.SCENE_HEIGHT/2;
    public double radius = (Constants.MAJOR_RADIUS + Constants.MINOR_RADIUS) / 2;
    private double angularSpeed = 0.005; // controls the speed of rotation

    private boolean colliding = false;
    public Fish() {
        angle = Math.random() * 2 * Math.PI; // random initial angle
        updatePosition();

    }

    public void setBounds(double width, double height) {
        this.max_width = width;
        this.max_height = height;
    }

    public void updatePosition() {
        // Increment the angle
        angle += angularSpeed;

        // Compute the new position
        double x = centerX + radius * Math.cos(angle);
        double y = centerY + radius * Math.sin(angle);

        // Update position and direction
        pos = new Position(x, y);

    }

    public void updateDirection(List<Fish> closeFish){
        colliding = closeFish.size() != 0;
        Direction correction = new Direction(0,0);
        Direction flockDirection = new Direction(0,0);
        Direction flockCenter = new Direction(dir.x, dir.y);

        for (Fish otherB : closeFish){
            /* collision avoidance */
            Direction distanceVector = Direction.fromPositions(otherB.pos, pos);
            double distance = distanceVector.norm();
            double scaleFactor = (Constants.MAX_DISTANCE_DETECTION - distance)
                / Constants.MAX_DISTANCE_DETECTION;
            // correction.add(distanceVector.scaleBy(-scaleFactor));

            Direction normalProj = distanceVector.projectOn(dir);
            normalProj.sub(distanceVector);
            correction.add(normalProj.scaleBy(scaleFactor));

            /* flock direcion */
            flockDirection.add(otherB.dir);

            /* flock center */
            flockCenter.add(new Direction(otherB.pos.x, otherB.pos.y));
        }
        Direction dirToCenter = flockCenter.scaleBy(closeFish.size());
        dirToCenter.sub(new Direction(dir.x, dir.y));

        // dir.add(dirToCenter.scaleBy(0.01));
        dir.add(correction.scaleBy(0.01));
        // dir.add(flockDirection.scaleBy(0.001));
        dir.normalize();
        updateAngle();
    }


    private void updateAngle() {
        angle = Math.atan2(dir.y, dir.x) * (180 / Math.PI) - 90;
    }

}