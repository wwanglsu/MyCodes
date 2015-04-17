package wang.c7;

public class LineV2 {
    public Point start;
    public Point end;

    public LineV2(Point start, Point end){
        this.start=start;
        this.end=end;
    }

    @Override
    public String toString(){
        return "Line made by: " + start+" , "+end;
    }
}
