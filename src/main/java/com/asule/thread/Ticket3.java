package com.asule.thread;

public class Ticket3 implements Runnable{

    public int num=40;
    private Object o = new Object();


    @Override
    public void run() {
        show();
    }

    public void show(){
        while (true){
            synchronized (o){   //使用同步代码块来解决线程安全问题，此时线程之间使用的同一个锁
                if (num>0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"  "+num--);
                }else{
                    break;
                }
            }

        }
    }
}
