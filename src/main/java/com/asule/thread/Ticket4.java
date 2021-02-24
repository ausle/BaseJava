package com.asule.thread;

public class Ticket4 implements Runnable{

    public int num=40;

    @Override
    public void run() {
        show();
    }

    public synchronized void show(){   //同步方法演示，也可以解决线程安全问题
        while (true){
            if (num>0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"  "+num--);
            }else {
                break;
            }
        }
    }


}
