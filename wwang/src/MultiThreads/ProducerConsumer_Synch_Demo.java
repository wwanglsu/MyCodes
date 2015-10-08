package MultiThreads;

public class ProducerConsumer_Synch_Demo {

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

class Resource{
    private String name;
    private int count=1;
    private boolean flag=false;

    public synchronized void put(String name){

        while(flag){ // 'if' just check one time + notify(), use while + notifyAll()
            try{
                this.wait();
            }catch(Exception e){}
        }

        this.name=name + " -- "+count++;        
        System.out.println(Thread.currentThread().getName()+"...Producer: "+this.name);
        flag=true;
        this.notifyAll();
    }

    public synchronized void take(){
        while(!flag){ // 'if' just check one time + notify(), use while + notifyAll()
            try{
                this.wait();
            }catch(Exception e){}
        }
        System.out.println(Thread.currentThread().getName()+"......Consumer: "+this.name);
        flag=false;
        this.notifyAll();
    }
}

class Producer implements Runnable{
    private Resource res;

    public Producer(Resource res) {
        // TODO Auto-generated constructor stub
        this.res=res;
    }

    @Override
    public void run(){
        while(true){
            res.put(" item ");
        }
    }
}

class Consumer implements Runnable{
    private Resource res;

    public Consumer(Resource res) {
        // TODO Auto-generated method stub
        this.res=res;
    }

    @Override
    public void run(){
        while(true){
            res.take();
        }
    }
}
