package com.asule.execption;

public class Overview {


    /*
        1、何为异常
           在程序运行时出现的不正常情况。


        2、异常体系
            根据面向对象的设计，把异常通过一个类来描述。【包括异常的信息】
            不同的异常用不同的类来进行描述。【异常有许多种，比如空指针、角标越界等】
            异常慢慢的进行向上抽取，就形成了异常的体系。
            异常体系可以分为：
                Throwable【不是所有对象都可以抛出，只有Throwable及其子类才具备可抛性】
                (1)不可处理的，Error
                (2)可以处理的，Exception


        3、Exception
            Exception分为：
                (1)编译时检查的异常【该异常需要在方法上抛出或进行处理，否则编译不通过】
                (2)运行时异常【编译时不检查异常，无论是否处理都可以编译通过】


                运行时异常不需要在方法上抛出或进行处理，原因是这些异常是由于调用者导致的异常。
                此时直接抛出，让调用者的程序停止。
                若这种异常需要显式的告诉你去处理，那么就等同于——你的程序虽然写的有问题，但你可以通过捕捉而继续下去。继续下去时，则可能会影响下面的程序。

                自定义异常时，继承Exception或RuntimeException。

        throw【在方法内部抛出异常】
        throws【在方法上抛出】


        4、try...catch{}
           多catch情况下，父类的catch放在最下面。【如果放在最上面，则会处理子异常，那么下面针对子异常的处理就没有意义。会出现编译问题】
           抛出多个异常，就相应的处理几个异常。【如果怕麻烦，也可以用单个exception去接收】


        5、finally



        6、必要时异常转换
           不一定处理某异常时就抛出该异常，可以进行异常转换，抛出一个调用者熟知的异常。


        7、异常在子父类中的注意事项

            （1）子类只能抛出父类的异常或其子异常











     */
    public int show(int index)throws FushuException,BigNumberException{
        if (index<0)
            throw new FushuException();
        if (index==0)
            throw new NullPointerException();
        if (index>1000)
            throw new BigNumberException();
        return index;
    }


    class FushuException extends Exception{

    }

    class BigNumberException extends Exception{

    }


    public static void main(String[] args) {
        Overview overview = new Overview();
        try {
            overview.show(1);
        }
        catch (FushuException e) {
            e.printStackTrace();
        }
        catch (BigNumberException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            //一定会执行的代码【释放资源】
        }
    }





}
