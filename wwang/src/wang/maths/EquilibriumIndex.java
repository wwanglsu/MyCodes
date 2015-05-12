package wang.maths;

public class EquilibriumIndex {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] A=new int[]{-1,3,-4,5,1,-6,2,1};
        System.out.println(getEquilibriumIndex(A));
    }

    static int getEquilibriumIndex(int[] A)
    {
        // Default function result
        int equilibrium = -1;
        // Strategy: Consider the input array two separate sub-arrays, one to the
        // left of the element being considered, the other to the right. We step 
        // through the array sequentially until the sums of the sub-arrays are equal.

        // Get initial left and right sums
        int sumLeft = 0;
        int sumRight = 0;
        for (int i = 0; i < A.length; i++)
        {
            sumRight += A[i];
        }

        // Traverse the array, looking for the first equilibrium
        for (int i = 0; i < A.length; i++)
        {
            int tempRight = sumRight - A[i];
            if (sumLeft == tempRight)
            {
                // We have found a solution at the i-th element
                equilibrium = i;
                break;
            }
            else
            {
                // Prepare for next comparison
                sumLeft += A[i];
                sumRight = tempRight;
            }
        }


        // Return the result
        return equilibrium;
    }

}
