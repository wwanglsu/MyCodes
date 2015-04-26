package wang.c11;

public class RankNode {
    public int leftSize=0;
    public RankNode left;
    public RankNode right;
    public int data=0;

    public RankNode(int d){
        this.data=d;
    }

    @Override
    public String toString(){
        return String.valueOf(data);
    }

    public void insert(int d){
        if(d<=data){
            if(left !=null) {
                left.insert(d);
            } else {
                left=new RankNode(d);
            }
            leftSize++;
        }else{
            if(right!=null) {
                right.insert(d);
            } else {
                right=new RankNode(d);
            }
        }
    }

    public int getRank(int d){
        if(d==data) {
            return leftSize;
        } else if(d<data){
            if(left==null) {
                return -1;
            } else {
                return left.getRank(d);
            }
        }else{
            int right_rank= right==null ? -1:right.getRank(d);
            if(right_rank == -1) {
                return -1;
            } else {
                return leftSize+1+right_rank;
            }
        }
    }
}
