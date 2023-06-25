package aquahood.model;

import aquahood.Constants;

import java.util.ArrayList;
import java.util.List;

public class Fish {

    public Direction dir;
    public Position pos;
    public double angle;
    public double speed;

    private double max_width = Constants.SCENE_WIDTH;
    private double max_height = Constants.SCENE_HEIGHT;

    private double centerX = Constants.SCENE_WIDTH/2;
    private double centerY = Constants.SCENE_HEIGHT/2;
    public double radius = (Constants.MAJOR_RADIUS + Constants.MINOR_RADIUS) / 2;
    private double angularSpeed = 0.005;
    private double avoidRadius = 20;

    private boolean colliding = false;
    public Fish() {
        angle = Math.random() * 2 * Math.PI; // random initial angle
        speed = angularSpeed;

        this.angle = 2 * Math.PI * Math.random();
        this.angularSpeed = (Math.random() - 0.5) / 30;
        this.pos = new Position(centerX + radius * Math.cos(angle), centerY + radius * Math.sin(angle));
        this.dir = new Direction(Math.cos(angle), Math.sin(angle));
        updateDirection(new ArrayList<>());

    }

    public void setBounds(double width, double height) {
        this.max_width = width;
        this.max_height = height;
    }

    public void updateDirection(List<Fish> closeFish){
        colliding = closeFish.size() != 0;
        Direction correction = new Direction(0,0);
        Direction avoidance = new Direction(0, 0);

        double avoidanceFactor = 0.05; // Controls the strength of avoidance

        for (Fish otherB : closeFish){
            Direction distanceVector = Direction.fromPositions(otherB.pos, pos);
            double distance = distanceVector.norm();

            if (distance < avoidRadius) {
                double dx = pos.x - otherB.pos.x;
                double dy = pos.y - otherB.pos.y;
                double factor = (avoidRadius - distance) /avoidRadius;
                avoidance.add(new Direction(dx * factor, dy * factor));
            }
        }

        dir.add(avoidance.scaleBy(avoidanceFactor));
        dir.normalize();
        updateAngle();
    }



    public void updatePosition(List<Fish> closeFish) {
        // Compute avoidance force
        double avoidanceX = 0;
        double avoidanceY = 0;

        for (Fish otherFish : closeFish) {
            double distance = Math.sqrt(Math.pow(otherFish.pos.x - this.pos.x, 2) + Math.pow(otherFish.pos.y - this.pos.y, 2));
            if (distance < avoidRadius) {
                double dx = this.pos.x - otherFish.pos.x;
                double dy = this.pos.y - otherFish.pos.y;
                avoidanceX += dx / distance;
                avoidanceY += dy / distance;
            }
        }

        // Normalize the avoidance vector
        double avoidanceMagnitude = Math.sqrt(avoidanceX * avoidanceX + avoidanceY * avoidanceY);
        if (avoidanceMagnitude > 0) {
            avoidanceX /= avoidanceMagnitude;
            avoidanceY /= avoidanceMagnitude;
        }

        // Calculate angle adjustment based on avoidance, but keep the forward movement
        double angleAdjustment = 0.01 * Math.atan2(avoidanceY, avoidanceX);

        // Increment angle by angular speed and avoidance adjustment
        angle += angularSpeed + angleAdjustment;
        angularSpeed += Math.abs(0.05 * Math.atan2(avoidanceY, avoidanceX));

        // Compute new position based on the angle and radius
        pos.x = centerX + radius * Math.cos(angle);
        pos.y = centerY + radius * Math.sin(angle);

        // Make sure the fish stays between circle1 and circle2
        double distanceToCenter = Math.sqrt((pos.x - centerX) * (pos.x - centerX) + (pos.y - centerY) * (pos.y - centerY));
        if (distanceToCenter < Constants.MINOR_RADIUS) {
            radius = Constants.MINOR_RADIUS;
        } else if (distanceToCenter > Constants.MAJOR_RADIUS) {
            radius = Constants.MAJOR_RADIUS;
        } else {
            radius = distanceToCenter;
        }
    }






    private void updateAngle() {
        angle = Math.atan2(dir.y, dir.x) * (180 / Math.PI) - 90;
    }

}