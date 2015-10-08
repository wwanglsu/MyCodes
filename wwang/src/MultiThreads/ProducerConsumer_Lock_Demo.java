package MultiThreads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer_Lock_Demo {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("Hello, this is Producer-Consumer example.");
        Resource r=new Resource();
        Producer pro=new Producer(r);
        Consumer cons=new Consumer(r);

        Thread t1=new Thread(pro);
        Thread t2=new Thread(pro);
        Thread t3=new Thread(cons);
        Thread t4=new Thread(cons);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

}

class Resources{
    private String name;
    private int count=1;
    private boolean flag=false;

    private final Lock lock=new ReentrantLock();
    private Condition con_pro=lock.newCondition();
    private Condition con_con=lock.newCondition();

    public void put(String name) throws InterruptedException{
        lock.lock();

        try{
            while(flag){                
                con_pro.await();
            }
            this.name= name+ " -- "+count++;
            System.out.println(Thread.currentThread().getName()+"...Producer: "+this.name);
            flag=true;
            con_con.signal();
        }finally{
            lock.unlock();
        }        
    }

    public void take() throws InterruptedException{
        lock.lock();
        try{
            while(!flag){
                con_con.await();
            }
            System.out.println(Thread.currentThread().getName()+"......Consumer: "+this.name);
            flag=false;
            con_pro.signal();
        }finally{
            lock.unlock();
        }
    }
}
