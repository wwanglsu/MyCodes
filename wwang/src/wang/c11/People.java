package wang.c11;

public class People implements Comparable{
    private int height;
    private int weight;

    public People(int h, int w){
        this.height=h;
        this.weight=w;
    }

    @Override
    public int compareTo(Object s){
        People second=(People)s;
        if(this.height != second.height) {
            return ((Integer)this.height).compareTo(second.height);
        } else {
            return ((Integer)this.weight).compareTo(second.weight);
        }        
    }

    @Override
    public String toString(){
        return "( "+height+", "+weight+" )";
    }

    public boolean isBefore(People people){
        if(this.height < people.height && this.weight < people.weight) {
            return true;
        } else {
            return false;
        }
    }
}
