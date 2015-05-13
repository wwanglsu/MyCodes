package wang.c17;

public class Wrapper implements Cloneable{
    public int invalid=Integer.MAX_VALUE;
    public String parsed="";

    public Wrapper(int inv, String p){
        invalid=inv;
        parsed=p;
    }


    @Override
    public Object clone(){
        return new Wrapper(invalid, parsed);
    }

    public static Wrapper min(Wrapper r1, Wrapper r2){
        if(r1==null) {
            return r2;
        } else if(r2==null) {
            return r1;
        }

        return r2.invalid < r1.invalid ? r2 : r1;
    }

    @Override
    public String toString(){
        return "("+invalid+" : "+parsed+")";
    }

}
