package com.asule.thread;

public class Overview {


    /*
        1、进程和线程概念：
            进程指的是某个程序运行在内存中的空间。进程不负责执行，负责执行的是线程。
            线程负责执行进程中内容，可以称它为执行单元(也可以理解为，程序再执行的执行路径)。
            (线程是用来执行内容的。)
            一个进程不可能没有线程。一个进程可能有多个执行路径，称之为多线程。

        2、多线程的利与弊：
            多线程解决了多个部分同时执行的问题。但它也存在弊端，切换线程会影响效率。【cpu不停的切换到进程中的某个线程】

            JVM中就启动了多线程，分析就可以得出至少有两个线程，一个是主线程执行main函数，一个不定时的清除垃圾。
            打开某个程序，操作系统为进程分配内存空间。

     */

    public static void main(String[] args) {
//        new Overview().test1();
        new Overview().test2();
    }



    class Dog extends Thread{
        private String name;

        public Dog(String name) {
            super();
            this.name = name;
        }

        public void show(){
            for (int i = 0; i <10; i++) {
                System.out.println(name+"  "+i+"  "+getName());
            }
        }

        @Override
        public void run() {
            show();
        }
    }


    /*
       3、创建线程第一种方法：
           让需要被多线程执行任务的类继承Thread，复写run方法。
           创建该类对象，调用start方法，就会创建线程，执行run方法任务。

           【有需要被多线程处理的内容。】
           【继承Thread，把需要多线程处理的内容写到run中。】

       JVM启动的主线程执行的任务是main方法的内容，那么我们自己创建的线程的执行任务在哪儿?
       Thread类用于描述线程，同样也封装了线程要执行的任务，这个执行任务就是Thread的run方法。
       让一个类继承Thread类,复写run方法。
       当创建该类对象时，就创建了一个线程。                      //Dog dog1 = new Dog("dog-1");
       调用其start方法，就会启动线程，具体会调用覆盖的run方法。    //dog1.start();

       (1)继承Thread类，当创建对象时。会获取主线程的线程组(groupThread)，线程组中有个属性用于描述未开始的线程数，此时，该值会加1。
       会把主线程的相关属性(比如优先级priority、是否是后台线程daemon)设置给当前Thread。
       (2)当调用start时，会把该thread添加到线程组，start0然后调用复写的run方法。


       主线程创建线程时，栈中有多条执行路径【每条线程对应一个执行路径】
   */
    public void test1(){
        Dog dog1 = new Dog("dog-1");
        Dog dog2 = new Dog("dog-2");

        //此时主线程开辟了两条执行路径。堆内存中有三块开辟的空间。如果主线程执行过程中发生异常，不影响其他线程的执行过程。
        dog2.start();
        dog1.start();

        System.out.println(4/0+"");

        for (int i = 0; i <10; i++) {
            System.out.println("main exec "+Thread.currentThread().getName());
        }
    }


    /*
        4、线程的状态分析
            start()，线程开始运行任务，此时线程处于运行状态。【cpu正在执行，拥有cpu执行权】
            任务执行完毕后，线程销毁。
            线程sleep时，线程睡眠【从运行到冻结状态】，sleep指定的时间到之后【从冻结到线程运行】。
            线程wait时，需要notify去唤醒。

            线程的临时阻塞状态：
                开启多个线程，它们都具备CPU执行资格，但某一刻只有一个线程再执行，其他线程都很处于临时阻塞状态。
                线程就在运行和临时阻塞状态，来回切换。
     */


    /*
        5、创建线程的第二种方法
            创建线程的第一种方法是要类去继承Thread，复写子线程会执行run方法。
            但如果一个类不可以被修改或已经有了父类，但该类有需要有多线程执行的任务，此时怎么办呢?
            可以让该类实现Runnable，那么表示它是一个任务类，需要多线程处理的内容放到run中。
            第一种是让类继承Thread，表示它是个线程。而第二种类此时是个任务，我们直接通过new Thread创建线程后，通过构造方法传入你需要执行的任务。
            前者执行线程的run，后者执行任务的run方法内容。


        6、两种创建线程的比较
            如果仅仅只是需要多线程执行任务，那么不需要通过继承Thread来创建线程。
            可以定义一个任务，来使用多线程执行。
     */

    public void test2(){
        Kitten kitten1 = new Kitten("Kitten-1");
        Kitten kitten2 = new Kitten("Kitten-2");

        Thread thread1 = new Thread(kitten1);//创建线程
        Thread thread2 = new Thread(kitten2);

        thread1.start();        //start会调用Thread的run方法，run会去调用传入的runnable对象的run方法。
        thread2.start();

        for (int i = 0; i <10; i++) {
            System.out.println("main exec "+Thread.currentThread().getName());
        }
    }

    class Kitten implements Runnable{
        private String name;

        public Kitten(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            show();
        }

        public void show(){
            for (int i = 0; i <10; i++) {
                System.out.println(name);
            }
        }
    }
}
