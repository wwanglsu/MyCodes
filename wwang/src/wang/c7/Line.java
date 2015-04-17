package wang.c7;

public class Line {
    public double slop;
    public double intersect;
    public double epsilon=0.00000001;
    public boolean infiniteSlop;

    public Line(double slop, double intersect){
        this.slop=slop;
        this.intersect=intersect;
    }

    public Line(Point p1, Point p2){
        if(Math.abs(p1.x-p2.x)>epsilon){
            slop=(p1.y-p2.y)/(p1.x-p2.x);
            intersect=p1.y- slop * p1.x;
            infiniteSlop=false;
        }else{
            infiniteSlop=true;
            intersect=p1.x;
        }
    }

    public boolean isIntersect(Line line){
        return Math.abs(this.slop- line.slop)>epsilon ||
                Math.abs(this.intersect-line.intersect)<epsilon;
    }

    @Override
    public String toString(){
        return "y= "+ slop + "*X +"+intersect;
    }
}
