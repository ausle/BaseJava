package com.asule.collection;


import java.util.*;

public class DemoB {

    /*
        Set接口：无序(不保证迭代顺序(不同于list接口会按存入的顺序进行排序)，内部有自己的排序顺序)，不可存储重复元素。
            HashSet：内部是哈希表。
            LinkedHashSet：其内部有链表和哈希结构，是HashSet子类，对HashSet进行延展，可以保证有序。
            TreeSet：可以对set集合的元素进行指定排序。TreeSet二叉树数据结构。

        HashSet怎么做到不储存重复元素的原因：
            HashSet存储数据时会对元素进行hash运算，来确定元素的位置。具体是调用hashCode方法。
            若该位置没有元素，就存储该元素。
            若发现该位置已经存在元素，证明两个元素之间哈希值相同，继而再会判断其内容是否相同，会调用equals方法。
            如果内容相同，不会存取该元素。这就保证HashSet不会存储重复元素。

        HashSet查找数据速度很快：
            HashSet这种数据结构，查找速度很快。比如你想查找某个元素，只需要再计算一次hashcode计算，就可以得到该元素的位置。


        HashSet不同元素的哈希值一致的处理：
            通过上面的描述，也有可能发生这样的情况，不同元素的哈希值一致(要知道出现这样的情况几率非常小，因为哈希值一般都很大)。
            如果哈希值一样，内容不一致时，有多种处理方式，比如有——顺延和串联。
                顺延：在当前位置往后顺延(如果长度不够，可以自动增加长度)
                串联：在当前位置往下顺延。(一个位置上有两个元素)
     */


    public static void main(String[] args) {
//        new DemoB().test1();
        new DemoB().test5();


    }


    //HashSet添加数据
    public void test1(){
        Set<String> set= new HashSet<>();
        set.add("bbb");
        set.add("ccc");
        set.add("eee");
        set.add("bbb");
        set.add("aaa");
        set.add("ccc");

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            String code=iterator.next();
            System.out.println(code+"    "+code.hashCode());
        }
    }


    /*
        HashSet添加自定义对象。

        自定义对象计算哈希值使用的Object的hashcode方法，而不同对象的内存地址不同，这种计算哈希值，必然哈希值不会相同。
        而上面的hashset添加字符串时，String类型的其实已经复写过hashcode，它是按照字典顺序来计算哈希值。

        对于HashSet添加自定义对象，并且保持不重复，则需要复写hashcode和equals。
        (记住，hashcode相同则不会再比较equals。可以好好利用这点，节省效率。所以尽量hash值要大一点)
     */
    public void test2(){
        Set<Person> lists = new HashSet<>();
        lists.add(new Person("asule0",28));
        lists.add(new Person("asule3",10));
        lists.add(new Person("asule1",20));
        lists.add(new Person("asule2",21));
        lists.add(new Person("asule1",20));

        Iterator<Person> iterator = lists.iterator();

        while (iterator.hasNext()){
            Person person = iterator.next();
            System.out.println(person.hashCode()+"    "+person.toString());
        }
    }


    //LinkedHashSet演示，保证有序。记住你存放的位置，链表结构。
    public void test3(){
        Set<String> set= new LinkedHashSet<>();
        set.add("bbb");
        set.add("ccc");
        set.add("eee");
        set.add("bbb");
        set.add("aaa");
        set.add("ccc");

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            String code=iterator.next();
            System.out.println(code+"    "+code.hashCode());
        }
    }


    /*
        TreeSet可以对set集合的元素进行指定排序。
        TreeSet内部是按照二叉树来存储数据，它的树的判断根据就是比较器的返回值。
        (存储string类型数据时，默认会按照字典顺序进行排序。String.compareTo)
     */
    public void test4(){
        Set<String> set= new TreeSet<>();
        set.add("bbb");
        set.add("ccc");
        set.add("eee");
        set.add("bbb");
        set.add("aaa");
        set.add("ccc");

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            String code=iterator.next();
            System.out.println(code+"    "+code.hashCode());
        }
    }

    //TreeSet排序(使用Comparable使其具备比较性)
    public void test5(){
        Set<Student> set= new TreeSet<>();

        set.add(new Student("asule0",28));
        set.add(new Student("asule1",28));
        set.add(new Student("asule2",28));
        set.add(new Student("asule2",28));
        set.add(new Student("asule2",29));

        Iterator<Student> iterator = set.iterator();
        while (iterator.hasNext()){
            Student student=iterator.next();
            System.out.println(student.hashCode()+"   "+student);
        }
    }


    //要使得对象具备比较性，实现Comparable接口。
    class Student extends Person implements Comparable<Student>{
        public Student(String name, int age) {
            super(name, age);
        }
        @Override
        public int compareTo(Student stu) {
            if (this==stu)return 0;
            if (this.getAge()==stu.getAge()){
                //使用String的排序逻辑
                return this.getName().compareTo(stu.getName());
            }
            return this.getAge()-stu.getAge();
        }
    }

    /*
        第一种TreeSet排序使得要进行比较的类要实现接口，但如果该类不可修改，那么就需要使用TreeSet第二种排序。
        可以让集合具备比较性。
    */
    public void test6(){
        Set<UniversityStudent> set= new TreeSet(new MyComparator());  //根据比较器，让集合具备比较性。

        set.add(new UniversityStudent("asule0",28));
        set.add(new UniversityStudent("asule1",28));
        set.add(new UniversityStudent("asule2",28));
        set.add(new UniversityStudent("asule2",28));

        Iterator<UniversityStudent> iterator = set.iterator();
        while (iterator.hasNext()){
            UniversityStudent student=iterator.next();
            System.out.println(student.hashCode()+"   "+student);
        }
    }

    class MyComparator implements Comparator<UniversityStudent>{
        @Override
        public int compare(UniversityStudent o1, UniversityStudent o2) {
            if (o1.getAge()==o2.getAge()){
                return o1.getName().compareTo(o2.getName());
            }
            return o1.getAge()-o2.getAge();
        }
    }

    class UniversityStudent extends Person{
        public UniversityStudent(String name, int age) {
            super(name, age);
        }

        public UniversityStudent() {

        }
    }
}
