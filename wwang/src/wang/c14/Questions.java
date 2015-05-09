package wang.c14;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Questions {

    public static void main(String[] args) {

        /*******14.5 reflection example****************/
        try{
            //parameter
            Object[] doubleArgs=new Object[]{4.0, 2.5};

            //get class
            Class rectangle=Class.forName("wang.c14.Rectangle");

            //Equivalent: Rectangle rectangle=new Rectangle(4.0, 2.5);
            Class[] doubleArgsClass=new Class[]{double.class, double.class};
            Constructor doubleArgsConstructor = rectangle.getConstructor(doubleArgsClass);
            Rectangle obj=(Rectangle)doubleArgsConstructor.newInstance(doubleArgs);

            //Equivalent: Double are=obj.getArea();
            Method m=rectangle.getDeclaredMethod("getPerimeter");
            Double area=(Double)m.invoke(obj);
            System.out.println(area);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }catch (NoSuchMethodException e) {
            System.out.println(e);
        }catch (IllegalArgumentException e) {
            System.out.println(e);
        }catch (IllegalAccessException e) {
            System.out.println(e);
        }catch (InvocationTargetException e) {
            System.out.println(e);
        }catch (InstantiationException e) {
            e.printStackTrace();
        }
        /*******14.5 reflection example****************/

        /*******14.6 rotate the array****************/
        int size=10;
        CircularArray<String> array=new CircularArray<String>(size);
        for(int i=1; i<=size;i++){
            array.set(i-1, String.valueOf(i));
        }

        array.rotate(3);
        for (int i = 0; i < size; i++) {
            System.out.println(array.get(i));
        }
        /*******14.6 circular array****************/


    }

}
