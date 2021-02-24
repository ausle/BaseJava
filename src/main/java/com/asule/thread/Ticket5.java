package com.asule.thread;

public class Ticket5 implements Runnable{

    public int num=40;

    private boolean flag=false;


    Object obj=new Object();


    @Override
    public void run() {
        show();
    }


    //此例子用于验证同步方法使用的锁是什么
    public void show(){
        while (true){
            if (flag){
                sellTicket();
            }else{
                synchronized (this){
                    if (num>0) {  //num=1
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
            flag=!flag;
        }
    }

    public synchronized void sellTicket(){
        if (num>0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"  "+num--);
        }
    }
}
