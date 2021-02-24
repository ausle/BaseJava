package com.asule.thread;

public class DemoA {


    public static void main(String[] args) {
//        new DemoA().test1();
//        new DemoA().test2();
//        new DemoA().test3();
//        new DemoA().test4();
        new DemoA().test5();
    }


    /*
      写了个简单的卖票程序。开启四个线程同时开始卖票。
      如果票数为成员属性，那么对于每个线程而言，都会从该票数开始出售。这显然是不合理的。
      若把属性改为静态的，多个线程共享该票数，就可以解决问题。

      但问题是，在有的场景中，有需要多个线程各自卖票，那这样的情况又不适合呢?
  */
    public void test1(){
        Ticket1 ticket1 = new Ticket1();
        Ticket1 ticket2 = new Ticket1();
        Ticket1 ticket3 = new Ticket1();
        Ticket1 ticket4 = new Ticket1();

        ticket1.start();
        ticket2.start();
        ticket3.start();
        ticket4.start();
    }

    /*
       让卖票来实现Runnable，作为一个任务。
       创建的多个线程执行任务时，都会操作同一个Runnable，这样一来，大家操作的都是同一个票数。

       如果需要多个线程各自卖票，则可以创建多个任务，分别传入多个Thread来进行执行。
   */
    public void test2(){
        Ticket2 ticket = new Ticket2();

        Thread thread1 = new Thread(ticket);
        Thread thread2 = new Thread(ticket);
        Thread thread3 = new Thread(ticket);
        Thread thread4 = new Thread(ticket);

        thread2.start();
        thread3.start();
        thread4.start();
        thread1.start();
    }


    /*
         演示线程的安全问题：
            在某个子线程通过判断继续执行时，线程失去执行资格。此时执行权被另一个线程拥有，它执行完毕后，该线程重新获取执行权时，就无需进行条件判断。
            那么此时就有可能出现安全隐患问题。
        下面使用sleep手动让线程失去执行权，演示出该问题。
     */

    public void test3(){
        Ticket3 ticket = new Ticket3();

        Thread thread1 = new Thread(ticket);
        Thread thread2 = new Thread(ticket);
        Thread thread3 = new Thread(ticket);
        Thread thread4 = new Thread(ticket);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }


    /*
        如何解决上面的线程安全问题?
            解决的思路，就是当一个线程因为某种原因失去执行权时，处于阻塞状态时。其他线程即使获取到了执行权，也无法执行任务。
            而必须等待该线程重新获取执行权，执行完任务后，才有资格执行。

            通过同步代码块，可以达到上面描述的目的。同步代码块中包含的是需要被同步的代码。
                synchronized(对象){    //该对象可以是任何对象
                    //需要被同步的代码
                }

            同步代码块的原理：
                可以把同步代码块的对象当作一把锁，当某个线程执行同步代码块时，其实就上了锁。该线程处于临时阻塞状态，其他线程由于锁而无法执行同步代码。
                注意的是，线程之间要使用的是同一个锁。【如果不是同一个锁，好比进入的是不同的房间】

            同步的好处与缺点：
                好处是可以解决线程安全问题。
                缺点是，降低了效率。同步外的其他线程都会进行判断同步标记的动作，消耗了资源。

            同步的前提：
                单线程操作的代码无需同步。
                同步中多个线程要使用同一个对象，或者叫使用同一个锁。
     */


    /*
        同步函数：
            作用与同步代码块一样，都是为了解决线程安全问题。只不过它是把同步加到了方法上。
     */

    public void test4(){
        Ticket4 ticket = new Ticket4();

        Thread thread1 = new Thread(ticket);
        Thread thread2 = new Thread(ticket);
        Thread thread3 = new Thread(ticket);
        Thread thread4 = new Thread(ticket);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }


    /*
         那么同步方法使用的对象是什么?
            可以做一个测试，让一个线程执行同步代码块的任务，另一个执行同步方法内任务。
            通过不断修改同步代码块的对象，来判断同步方法使用的对象。
            只有当不出现线程安全问题时，就可以得知两者使用的对象是一致的。

            测试时发现当同步代码块的锁对象是this，没有出现卖票的安全问题。
            由此证明同步函数的锁其实就是this。
     */

    /*
        静态同步方法的锁是字节码。
     */
    public void test5(){
        Ticket5 ticket = new Ticket5();

        Thread thread1 = new Thread(ticket);
        Thread thread2 = new Thread(ticket);
        Thread thread3 = new Thread(ticket);
        Thread thread4 = new Thread(ticket);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }



}
