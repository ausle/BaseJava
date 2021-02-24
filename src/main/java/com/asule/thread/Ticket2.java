package com.asule.thread;

public class Ticket2 implements Runnable{

    public int num=40;

    @Override
    public void run() {
        show();
    }

    public void show(){
        while (true){
            if (num>0){
                System.out.println(Thread.currentThread().getName()+"  "+num--);
            }else{
                break;
            }
        }
    }
}
