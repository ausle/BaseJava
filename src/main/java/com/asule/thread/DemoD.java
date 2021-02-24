package com.asule.thread;

public class DemoD {


    /*
        现在的问题是，线程不是一个输入一个输出的交替执行，而是某个线程可能执行很多遍。如何保证一个线程输入后，然后一个线程输出?

        解决思路：
            当资源没有输出时，输入线程等待，输出线程进行输出，输出后，唤醒输入线程。
            当资源输出后，输出线程等待，唤醒输入线程，输入后，唤醒输出线程。
            【需要相应的唤醒，否则输入一次后，线程都进行等待。】


        wait和notify都必须定义在同步中。【因为这两个方法都用于操作线程的状态】
        【A锁的notify唤醒A锁的wait，它不可以唤醒其他锁的线程。】


        生产者和消费者的问题：
            单生产和单消费的情况，生产和消费交替进行没有问题。
            多生产和多消费时，就可能连续出现多次生产和多次消费的问题。
            原因是什么?下面通过程序的执行步骤来进行分析：

                    t0生产，t0唤醒【无wait线程】，t0等待。【具备执行资格的有：t1,t2,t3】
                    t1获得执行权，t1等待。【具备执行资格的有：t2,t3】

                    t2获得执行权,t2消费，t2唤醒【唤醒了t0】，t2等待。【具备执行资格的有：t0,t3】
                    t3获得执行权，t3等待。【具备执行资格的有：t0】

                    t0无需判断标记，t0生产,t0唤醒了t1，t0等待。
                    t1无需判断标记，t1生产。【于是多次生产的bug就出现了】



        解决方法就是，唤醒的线程需要再次判断下标记。比如上面所说的t0。把if改为while。
        但是改成为while后，此时t0则会继续wait。此时，4个线程全部处于wait状态，程序就处于死锁呢。
        基于此，我们不能使用notify来唤醒单个，而是要唤醒所有。

     */


    public static void main(String[] args) {
        new DemoD().test1();
    }


    public void test1(){
        Resource resource=new Resource();
        Input input = new Input(resource);
        Output output = new Output(resource);

        Thread thread0 = new Thread(input,"thread0");
        Thread thread1 = new Thread(input,"thread1");

        Thread thread2 = new Thread(output,"thread2");
        Thread thread3 = new Thread(output,"thread3");

        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
    }


}

class Resource{
    private String name;
    private String age;
    public boolean isInput=false;//代表是否有资源输入，默认没有

    private int count=1;


    public synchronized void set(String name,String age){   //to,t1
        while (isInput){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.name=name+"["+count+"]";
        this.age=age;

        System.out.println(Thread.currentThread().getName()+"..生产者.."+this.name+"  "+this.age);
        count++;
        isInput=!isInput;
        notifyAll();
    }


    public synchronized void print(){           //t2,t3
        while (!isInput){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"..消费者.."+this.name+"  "+this.age);
        isInput=!isInput;
        notifyAll();
    }
}

class Input implements Runnable{

    private Resource resource;
    private boolean flag=false;

    public Input(Resource resource){
        this.resource=resource;
    }

    @Override
    public void run() {
        while (true){
            if (flag){
                resource.set("asule","twenty-five");
            }else{
                resource.set("阿苏勒","29");
            }
            flag=!flag;
        }
    }
}

class Output implements Runnable{
    private Resource resource;
    public Output(Resource resource){
        this.resource=resource;
    }

    @Override
    public void run() {
        while (true){
            resource.print();
        }
    }
}