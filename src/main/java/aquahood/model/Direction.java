package aquahood.model;

import aquahood.Constants;

public class Direction {

    public double x;
    public double y;

    private double centerX = Constants.SCENE_WIDTH/2;
    private double centerY = Constants.SCENE_HEIGHT/2;
    private double radius = this.y - centerY;
    private double angle = 90;
    private double speed = 0.1;
    public Direction(double x, double y){
        this.x = x;
        this.y = y;
    }


    public static Direction fromPositions(Position p1, Position p2){
        return new Direction(p1.x - p2.x, p1.y - p2.y);
    }

    public void normalize(){
        if (x == 0 || y == 0){
            if (!(x == 0 && y == 0))
                if (x == 0)
                    y = 1;
                else
                    x = 1;
        } else {   
            double norm = Math.sqrt(x*x + y*y);
            x = x / norm;
            y = y / norm;
        }
    }


    public void add(Direction other){
        x = x + other.x;
        y = y + other.y;
    }

    public void sub(Direction other){
        x = x - other.x;
        y = y - other.y;
    }

    public Direction scaleBy(double s){
        return new Direction(x*s, y*s);
    }


    public double dot(Direction other){
        return this.x * other.x + this.y * other.y;
    }

    public double norm(){
        return Math.sqrt(x*x + y*y);
    }

    public Direction projectOn(Direction other){
        return other.scaleBy(this.dot(other) / other.norm());
    }

    @Override
    public String toString(){
        return "(" + x + "," + y + ")";
    }

}
