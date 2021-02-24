package com.asule.thread;

public class DemoC {

/*

    1、线程间通信
        线程间通信：多线程处理同一个资源，但是任务不同。【比如一个线程拉煤炭，一个线程卸煤炭】


    2、
        (1)intput输入和output输出的资源内容不是配套的。原因在于操作一组资源属性时，没有把这一组操作示为整体，导致线程刚给某个资源属性输入时，
           执行权就被输出线程抢夺了。解决方法是使用同步锁。

 */

    public static void main(String[] args) {
        new DemoC().test1();
    }

    public void test1(){
        Resource resource=new Resource();
        Input input = new Input(resource);
        Output output = new Output(resource);

        new Thread(input).start();
        new Thread(output).start();
    }


    class Resource{
        private String name;
        private int age;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
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
                synchronized (resource){
                    if (flag){
                        resource.setName("asule===>");
                        resource.setAge(25);
                    }else{
                        resource.setName("阿苏勒======>");
                        resource.setAge(29);
                    }
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
                synchronized (resource){
                    System.out.println(resource.getName()+resource.getAge());
                }
            }
        }
    }
}
