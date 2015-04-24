package wang.c11;

public class Questions {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        int[] a = { 2, 3, 6, 2, 2, 2, 2, 2 , 2 , 2 };

        System.out.println(search(a, 0, a.length - 1, 2));
        System.out.println(search(a, 0, a.length - 1, 3));
        System.out.println(search(a, 0, a.length - 1, 4));
        System.out.println(search(a, 0, a.length - 1, 1));
        System.out.println(search(a, 0, a.length - 1, 6));

    }

    /****11.3 search within inflection point array***********/
    static int search(int[] a, int left, int right, int target){
        if(left>right) {
            return -1;
        }
        int mid=(left+right)/2;

        if(target==a[mid]) {
            return mid;
        }

        if(a[left] < a[mid]){
            if(target >=a[left] && target <=a[mid]){
                return search(a, left, mid-1, target);
            }else{
                return search(a, mid+1, right, target);
            }
        }else if(a[left]>a[mid]){
            if(target >=a[mid] && target<=a[right]){
                return search(a, mid+1, right, target);
            }else{
                return search(a, left, mid-1, target);
            }
        }else if(a[left]==a[mid]){
            if(a[mid]!=a[right]) {
                return search(a, mid+1, right, target);
            }else{ //search both half sides.
                int result=search(a, left, mid-1, target);
                if(result==-1) {
                    return search(a, mid+1, right, target);
                } else {
                    return result;
                }
            }
        }
        return -1;
    }
    /****11.3 search within inflection point array***********/
}
