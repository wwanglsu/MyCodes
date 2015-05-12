package wang.c7;

public class Point {
    public double x;
    public double y;
    public Point(double x, double y){
        this.x=x; 
        this.y=y;
    }

    public boolean isEqual(Point p){
        return this.x==p.x && this.y==p.y;
    }

    public String toString(){
        return "( "+ x +", "+y+" )";
    }
}
