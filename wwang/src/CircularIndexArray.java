import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import wang.utility.SuffixTree;

//Circular Index Array, capital city
public class CircularIndexArray {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] T=new int[]{9, 1, 4, 9, 0, 4, 8, 9, 0, 1};
        System.out.println(Arrays.toString(solution(T)));

        SuffixTree tree=new SuffixTree("BIBS");
        System.out.println(tree.search("IB"));
    }

    static int[] solution(int[] T) {
        // write your code in Java SE 8
        if(T==null || T.length==0) {
            return null;
        }
        int[] ans=new int[T.length-1];
        ArrayList<Integer> list=new ArrayList<Integer>();
        int capital=0;
        for(int i=0; i<T.length; i++){
            if(i==T[i]){
                capital=i;
                break;
            }    
        }
        int cnt=0;
        int index=0;
        for(int i=0; i<T.length; i++){
            if(i==capital) {
                continue;
            }
            index=i;
            while(T[index] != capital){
                index=T[index]; 
                cnt++;
            }
            list.add(cnt);
            cnt=0;
        }
        Collections.sort(list);

        int j=0, cc=1;
        int p=0;
        for(int z=1; z<list.size() ;z++){
            if(list.get(p)==list.get(z) ){
                cc++;  
                continue;
            }
            ans[j]=cc;
            j++;
            p=z;
            cc=1;
        }
        ans[j]=cc;

        return ans;


    }

}
