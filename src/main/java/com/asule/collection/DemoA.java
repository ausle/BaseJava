package com.asule.collection;



import java.util.*;

/*

    (1)集合框架体系的由来
    (2)Collection接口方法演示
    (3)List接口方法演示(包括有:ListIterator迭代器的使用)
    (4)List常用子类Vector、ArrayList、LinkedList相关方法演示
       (4.1)ArrayList和LinkedList两种不同数据结构集合的比较


*/
public class DemoA {
    /*
        集合的由来：
            java数组的数据都是相同类型的，无法存储不同类型的数据。
            而若使用StringBuffer，它是以字符串的形式将它们拼接起来，日后取出来十分不易。
            这样集合就应运而生，它用来存储java对象。相比较数组而言：
                (1)集合长度可变
                (2)存储不同的数据类型
                (3)集合中不可以存储基本数据类型值。(在JDK1.5版本前是不可以的，但是1.5可以存储，因为它进行了自动装箱)
                                               (基本数据类型赋值给引用类型时会进行自动装箱，引用类型和基本类型进行运算时会自动拆箱)

            Collection集合框架：
                容器按什么方式来存储数据，这称为它们的数据结构。
                我们可以依据不同的需求来选择不同的容器。
                当然各个容器之间有一些共性的地方，可以一直向上抽取，形成一个集合框架体系。
    */
//======================================================================================================================
    /*
        List:有序(怎么存进去的，就可以怎么取出来),元素都有角标，元素可以重复。

        List常用子类介绍：
            Vector：JDK1.0推出的，是数组数据结构，是同步的。
            ArrayList：取代Vector，是不同步的。若线程不安全，自己来加锁。
            LinkedList：链表数据结构。

       ArrayList和LinkedList比较：
            直接说结论，ArrayList查询数据较快，增删数据较慢。而LinkedList增删较快，而查询较慢。

            ArrayList是数组结构，若要增删某个元素时，数组内的其他元素会进行往前或往后顺延。
            比如要往索引为3的位置添加某个元素，此时ArrayList内有30个元素，那么从索引为3到30的元素都要往后顺延，删除同理。
            而LinkedList是链表结构，前一个元素记录着后一个元素的内存地址，删某个元素时，只需要将该元素记录的内存地址赋予给它之前的那个元素。
            而增加某个元素时，它的前一个元素记录其内存地址，它自己记录后一个元素地址值即可，对集合中的元素影响很小。

            查询数据时，ArrayList和LinkedList都会从索引为1的元素开始遍历查询，ArrayList的对象是连续的存储在一块空间内，而链表结构
            的LinkedList的对象分散在堆内存中，所以ArrayList查询要较快一些。
     */

    //Collection接口方法演示
    public void test1(){
        Collection collection=new ArrayList();
        collection.add("aaa");
        collection.add("bbb");

        Collection collection1=new ArrayList();
        collection1.add("ccc");
        collection1.addAll(collection);

        Collection collection2=new ArrayList();
        collection2.add("ccc");
//        collection2.add("ddd");
//        collection2.removeAll(collection1);//removeAll删除该集合与指定集合相同的元素。

        boolean containsAll = collection1.containsAll(collection2);//containsAll为true的条件是，指定集合是本集合的子集合。全部包含。
        System.out.println(containsAll);

        System.out.println(collection2);
    }


    //iterator取出元素
    /*
        理解Iterator:
            取出容器中的元素的迭代器，它是以容器内部类的方式去取出元素。(因为它可以直接访问容器中的元素)。
            而iterator是容器迭代器的向上抽取的共性接口，定义了一些共性方法。
            我们使用时，只需要获取各个容器的迭代器实现，那么就可以使用该迭代器去取出容器呢。
    */
    public void test2(){
        Collection collection=new ArrayList();
        collection.add("aaa");
        collection.add("bbb");

        //获取集合中的迭代器对象，利用迭代器来遍历集合元素
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }


    //List接口方法演示
    public void test3(){
        List collection=new ArrayList();
        collection.add("aaa");
        collection.add("bbb");


        //指定位置插入数据，占据该位置的元素会顺延。超过集合索引时，会发生异常。
        collection.add(2,"ccc");
        collection.remove(2);

        Object o = collection.get(1);
        System.out.println(o);

        collection.add("ccc");
        collection.add("ddd");

        //根据元素返回索引
        System.out.println(collection.lastIndexOf("ccc"));
        System.out.println(collection.indexOf("aaa"));

        System.out.println(collection);
    }


    //ListIterator迭代器的使用
    public void test4(){
        List list=new ArrayList();
        list.add("aaa");
        list.add("bbb");

        /*
            迭代器在迭代数据时，往集合内插入了一条元素。这个动作迭代器是不知情的。
            这个时候就会引发异常。迭代器方法检测到了元素的并发修改。

            那么如何可以在迭代的时候，对元素进行操作呢?
            要么迭代时，不修改元素，要么修改元素时，不进行迭代。满足这样的条件就不会出现问题。
            基于此，诞生了列表迭代器，List接口独有的列表迭代器。该迭代器可以进行除迭代之外的其他操作。
        */

        //Iterator迭代时修改数据时throw java.util.ConcurrentModificationException
//        Iterator iterator = list.iterator();
//        while (iterator.hasNext()){
//            Object next = iterator.next();
//            if (next instanceof String){
//                if(next.equals("aaa")){
//                    list.add("ccc");
//                }
//            }else{
//                System.out.println(next);
//            }
//        }

        //使用列表迭代器，迭代时对元素进行操作
        ListIterator listIterator = list.listIterator();
        while (listIterator.hasNext()){
            Object next = listIterator.next();
            if (next instanceof String){
                if(next.equals("aaa")){
                    listIterator.add("ccc");
                }
            }else{
                System.out.println(next);
            }
        }
    }




    //List常用子类Vector、ArrayList、LinkedList相关方法演示

    //演示Vector
    public void test5(){
        Vector<String> vector = new Vector<>();
        vector.add("aaa");
        vector.add("bbb");

        //与iterator接口功能，已经被前者替代。
        Enumeration<String> elements = vector.elements();
        while (elements.hasMoreElements()){
            String nextElement = elements.nextElement();
            System.out.println(nextElement);
        }
    }


    //演示LinkedList。堆栈：最后存进去的最先取出来。队列：按插入的顺序取出来。
    public void test6(){
        LinkedList list=new LinkedList();

        list.addFirst("aaa");
        list.addFirst("bbb");
        list.addFirst("ccc");

        System.out.println(list.getFirst());
        System.out.println(list.removeLast());
        System.out.println(list);
    }


    public static void main(String[] args) {
        new DemoA().test7();
    }



    //ArrayList存储自定义对象
    public void test7(){

        ArrayList<Person> lists = new ArrayList<>();
        lists.add(new Person("asule0",28));
        lists.add(new Person("asule1",20));
        lists.add(new Person("asule1",20));
        lists.add(new Person("asule2",21));

        Iterator<Person> iterator = lists.iterator();

        while (iterator.hasNext()){
            Person person = iterator.next();
            System.out.println(person.toString());
        }
    }


    //去除ArrayList的重复元素
    public void test8(){

        List<Person> lists = new ArrayList<>();
        lists.add(new Person("asule0",30)); //
        lists.add(new Person("asule1",20)); //
        lists.add(new Person("asule0",30)); //


        /*
            通过翻看contains方法源码可以得知，
            它内部进行比较是否包含用的方法是equals方法。
            会遍历该集合的每个元素和被包含元素进行比较。

            与contains相似的remove方法，同样也会进行equals操作。
            原因很简单，你要删除某个元素，得知道该元素是否在集合内存在，使用的同样是equals方法。

            而add操作并不会调用equals方法。什么元素添加都会返回true。

        */
        List<Person> temp = new ArrayList<>();
        Iterator<Person> iterator = lists.iterator();
        while (iterator.hasNext()){
            Person person = iterator.next();
            if (!temp.contains(person)){
                temp.add(person);
            }
        }

        System.out.println(temp);
    }



}
