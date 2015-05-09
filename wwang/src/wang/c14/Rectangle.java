package wang.c14;

public class Rectangle {
    private double length;
    private double width;

    public Rectangle(double l, double w){
        this.length=l;
        this.width=w;
    }

    public double getArea(){
        return length*width;
    }

    public double getPerimeter(){
        return 2*(length+width);
    }

    public double magnify(int level){
        return getArea()*level*level;
    }
}
