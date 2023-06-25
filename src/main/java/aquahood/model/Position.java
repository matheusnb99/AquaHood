package aquahood.model;

import aquahood.Constants;

public class Position {

    public double x;
    public double y;

    public Position(double x, double y){
        this.x = x;
        this.y = y;
    }


    public static Position random(double maxWidth, double maxHeight){
        double angle = Math.random() * 2 * Math.PI;
        double radius = Constants.MINOR_RADIUS + Math.random() * (Constants.MAJOR_RADIUS - Constants.MINOR_RADIUS);

        double x = radius * Math.cos(angle) + Constants.SCENE_WIDTH/2;
        double y = radius * Math.sin(angle) + Constants.SCENE_HEIGHT/2;
        return new Position(x, y);
    }


    public void moveBy(Direction d){
        this.x += d.x;
        this.y += d.y;
    }

    @Override
    public String toString(){
        return "(" + x + "," + y + ")";
    }
    
}
