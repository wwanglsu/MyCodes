package wang.utility;

import java.util.Random;

public class ArrayUtility {

    private static volatile ArrayUtility Instance=null;
    Random generator =new Random();

    private ArrayUtility(){}

    public static synchronized ArrayUtility getInstance(){
        if(Instance==null){
            synchronized (ArrayUtility.class) {
                if(Instance == null) {
                    Instance=new ArrayUtility();
                }
            }
        }
        return Instance;
    }

    public int[] generateRandomArray(int length, int maxNum){
        int[] a=new int[length];
        for(int i=0; i<length; i++){
            a[i]=generator.nextInt(maxNum+1);
        }
        return a;
    }

    public int randomInt(int num){
        return (int)(Math.random()*num);
    }

    public int randomIntRange(int min, int max){
        return randomInt(max+1-min) + min;
    }

    public int[][] randomMatrix(int row, int collum, int min, int max){
        int[][] matrix=new int[row][collum];
        for(int i=0; i<row; i++){
            for(int j=0; j<collum; j++){
                matrix[i][j]=randomIntRange(min, max);
            }
        }
        return matrix;
    }

    public String printMatrix(int[][] matrix){
        StringBuilder sb=new StringBuilder();
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                sb.append(matrix[i][j]+"  ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
