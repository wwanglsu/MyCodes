package MultiThreads;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer_BlockingQueue_Demo {

    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run(){
                try {
                    producer();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable(){
            @Override
            public void run(){
                try {
                    consumer();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }

    private static void producer() throws InterruptedException{
        Random random = new Random();

        while(true){
            Integer value = random.nextInt(100);
            System.out.println("Produe a number: "+value);
            queue.put(value);
        }
    }

    private static void consumer() throws InterruptedException{
        Random random = new Random();
        while(true){
            //Thread.sleep(100);

            //if(random.nextInt(10)==0){
            Integer value = queue.take();
            System.out.println("Taken value: " + value + "; Queue size is: " +queue.size());
            //}

        }
    }

}
