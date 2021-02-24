package com.asule.thread;

public class Ticket1 extends Thread{

    public static int num=40;

    @Override
    public void run() {
        show();
    }

    public void show(){
        while (true){
            if (num>0){
                System.out.println(Thread.currentThread().getName()+"  "+num--);
            }
        }
    }

}
