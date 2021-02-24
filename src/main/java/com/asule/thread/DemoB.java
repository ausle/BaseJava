package com.asule.thread;

public class DemoB {


    /*
        关于懒汉式的线程安全解决方法
     */


    /*
        死锁的问题
            死锁出现的原因是，相持不下，比如两个人过独木桥，一个说你先退回去，一个说你来退回去。这样相持不下的场景就会导致死锁。
            而在多线程中，同步的嵌套容易出现死锁。见DeadLock
     */

    public static void main(String[] args) {
        new DemoB().testDeadlock();
    }

    public void testDeadlock(){

        DeadLock deadLock1 = new DeadLock(true);
        DeadLock deadLock2 = new DeadLock(false);

        Thread thread1 = new Thread(deadLock1);
        Thread thread2 = new Thread(deadLock2);


        thread1.start();
        thread2.start();
    }
}

//单例的两种写法
//饿汉式
class Single1{
    private static final Single1 single=new Single1();

    public Single1(){

    }

    public static Single1 getSingle(){
        return single;
    }
}

//懒汉式
class Single2{
    private static Single2 single=null;

    public Single2(){

    }

    //当然可以写同步方法
    //懒汉式的同步代码块的写法
    public static Single2 getSingle(){
        if (single==null){   //减少判断锁的操作，解决效率问题
            synchronized (Single2.class){   //解决线程安全问题
                if (single==null) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                single=new Single2();
            }
        }
        return single;
    }
}

class DeadLock implements Runnable{

    private boolean flag;


    public DeadLock(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        /*
            两个线程
            线程1拿了lockA，准备去拿lockB。
            线程2拿了lockB，准备去拿lockA。
            此时两个线程都没有执行完同步代码块，无法释放各自的锁，就会导致死锁问题。
         */
        if (flag){
            synchronized (Lock.lockA){
                System.out.println(Thread.currentThread().getName()+"有lockA锁------>准备去拿lockB");
                synchronized (Lock.lockB){
                    System.out.println(Thread.currentThread().getName()+"有lockA和lockB锁");
                }
            }

        }else{
            synchronized (Lock.lockB){
                System.out.println(Thread.currentThread().getName()+"有lockB锁------>准备去拿lockA");
                synchronized (Lock.lockA){
                    System.out.println(Thread.currentThread().getName()+"有lockA和lockB锁");
                }
            }
        }
    }
}

class Lock{

    public static final Lock lockA=new Lock();
    public static final Lock lockB=new Lock();
}