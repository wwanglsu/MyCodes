package wang.c7;

import java.util.HashMap;

import wang.maths.PrimeHelper;

public class Questions {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("is prime 1: "+PrimeHelper.getInstance().isPrime1(49));
        System.out.println("is prime 2: "+PrimeHelper.getInstance().isPrime1(17));
        System.out.println(PrimeHelper.getInstance().sieveOfEratosthenes1(50));
        System.out.println(PrimeHelper.getInstance().sieveOfEratosthenes2(50));

        Line l1=new Line((int)(Math.random()*15), (int)(Math.random()*10));
        Line l2=new Line((int)(Math.random()*25), (int)(Math.random()*10));
        System.out.println(l1+"\n"+l2+"\n"+l1.isIntersect(l2));

    }

    /*****7.4 add minus multiply divide********/
    public static int negate(int a) {
        int neg = 0;
        int d = a < 0 ? 1 : -1;
        while (a != 0) {
            neg += d;
            a += d;
        }
        return neg;
    }

    public static int minus(int a, int b) {
        return a + negate(b);
    }

    public static int abs(int a) {
        if (a < 0) {
            return negate(a);
        } else {
            return a;
        }
    }

    public static int multiply(int a, int b) {
        if (a < b) {
            return multiply(b, a);
        }
        int sum = 0;
        for (int i = abs(b); i > 0; i--) {
            sum += a;
        }
        if (b < 0) {
            sum = negate(sum);
        }
        return sum;
    }

    public static int divide(int a, int b) throws java.lang.ArithmeticException {
        if (b == 0) {
            throw new java.lang.ArithmeticException("ERROR: Divide by zero.");
        }
        int absa = abs(a);
        int absb = abs(b);

        int product = 0;
        int x = 0;
        while (product + absb <= absa) { /* don't go past a */
            product += absb;
            x++;
        }

        if ((a < 0 && b < 0) || (a > 0 && b > 0)) {
            return x;
        } else {
            return negate(x);
        }
    }
    /*****7.4 add minus multiply divide********/

    /*****7.6 find best line passing most of points*******************/
    static Line findBestLine(Point[] points){
        Line bestLine=null;
        int count=0;
        HashMap<Line, Integer> map=new HashMap<Line, Integer>(); 
        for(int i=0; i<points.length;i++){
            for(int j=i+1; j<points.length;j++){
                Line line=new Line(points[i], points[j]);
                if(! map.containsKey(line)){
                    map.put(line, 0);
                }
                map.put(line, map.get(line)+1);
                if(bestLine==null || count<map.get(line)){
                    bestLine=line;
                    count=map.get(line);
                }
            }
        }
        return bestLine;
    }
    /*****7.6 find best line passing most of points*******************/



}
