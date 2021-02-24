package com.asule.collection;


import java.util.*;

/**
 * created by asule on 2020-02-28 10:14
 */
public class DemoC {


    /*

        Map常用子类：
            HashTable：不允许null作为键值。HashTable有个子类Properties，实际应用的比较多。
            HashMap：取代HashTable，内部是Hash结构。允许null作为键值。
            TreeMap：对Map进行指定排序，让Map内元素(key或value)具备比较性(即实现Comparable接口)。
                     也可以让TreeMap集合具备比较性，传入一个比较器来完成排序。


        HashMap存储的是键值对，它只需要操作键，就可以实现set集合的功能。(key是唯一的，已经满足set集合元素唯一)
        之所以还需要有Set集合，是因为为了保证单列集合还需要有唯一性，才衍生出来的Set集合。
        (Set集合内部是HashMap实例在支持。)

    */

    public static void main(String[] args) {
        new DemoC().test1();
    }



    //演示Map接口方法
    public void test1(){
        Map<Object, Object> map = new HashMap<>();


        map.put("name","asule");
        map.put("age","28");
        map.put("gender","men");
        map.put("email","123@163.com");

        System.out.println(map.remove("email"));
        System.out.println(map.get("email"));
        System.out.println(map.containsKey("email"));

        System.out.println(map.size());
        map.clear();
        System.out.println(map.size());
    }


    //keySet方法演示
    public void test2(){
        Map<Object, Object> map = new HashMap<>();

        map.put("name","asule");
        map.put("age","28");
        map.put("gender","men");
        map.put("email","123@163.com");

        Set<Object> keySet = map.keySet();//获取map集合的所有key组成的set集合
        Iterator<Object> iterator = keySet.iterator();//获取set集合元素，就可以使用迭代器功能

        while (iterator.hasNext()){
            Object key = iterator.next();
            System.out.println(key+"   "+map.get(key));
        }
    }

    //entrySet方法演示
    public void test3(){
        Map<Object, Object> map = new HashMap<>();

        map.put("name","asule");
        map.put("age","28");
        map.put("gender","men");
        map.put("email","123@163.com");

        Set<Map.Entry<Object, Object>> entries = map.entrySet();//将键和值的映射关系作为对象存储在set集合中
        Iterator<Map.Entry<Object, Object>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<Object, Object> entry = iterator.next();
            System.out.println(entry.getKey()+"   "+entry.getValue());
        }
    }


    //values方法演示
    public void test4(){
        Map<Object, Object> map = new HashMap<>();

        map.put("name","asule");
        map.put("age","28");
        map.put("gender","men");
        map.put("email","123@163.com");

        Collection<Object> values = map.values();   //获取map值的集合，由于值不一定唯一，返回的是Collection接口实现
        Iterator<Object> iterator = values.iterator();
        while (iterator.hasNext()){
            Object value = iterator.next();
            System.out.println(value);
        }
    }


    //HashMap存储自定义对象
    /*
        HashMap内部同样也是Hash结构，保证其key唯一性的，也是通过hashCode和equals来判断是否相等。
        那么自定义对象作为key时，需要对该对象hashCode和equals进行重写。
     */
    public void test5(){
        Map<HighSchoolStudent, String> map = new HashMap<>();

        map.put(new HighSchoolStudent("nianzhen",50),"xingou");
        map.put(new HighSchoolStudent("asule",28),"beijing");
        map.put(new HighSchoolStudent("wcx",20),"wuhan");
        map.put(new HighSchoolStudent("asule",28),"beijing");

        Set<HighSchoolStudent> keySet = map.keySet();
        Iterator<HighSchoolStudent> iterator = keySet.iterator();

        while (iterator.hasNext()){
            HighSchoolStudent key = iterator.next();
            System.out.println(key.toString()+"   "+map.get(key));
        }

    }

    class HighSchoolStudent extends Person{
        public HighSchoolStudent(String name, int age) {
            super(name, age);
        }

        public HighSchoolStudent() {
        }
    }


    //LinkedHashMap，可以有序存储
    public void test6(){
        Map<HighSchoolStudent, String> map = new LinkedHashMap<>();

        map.put(new HighSchoolStudent("wcx",20),"wuhan");
        map.put(new HighSchoolStudent("nianzhen",50),"xingou");
        map.put(new HighSchoolStudent("asule",28),"beijing");
        map.put(new HighSchoolStudent("asule",28),"beijing");

        Set<HighSchoolStudent> keySet = map.keySet();
        Iterator<HighSchoolStudent> iterator = keySet.iterator();

        while (iterator.hasNext()){
            HighSchoolStudent key = iterator.next();
            System.out.println(key.toString()+"   "+map.get(key));
        }
    }


    //TreeMap两种方式排序演示
    public void test7(){
        Map<HighSchoolStudent1, String> map = new TreeMap<>(new HighSchoolStudent1Comparable());

        map.put(new HighSchoolStudent1("asule",28),"beijing");
        map.put(new HighSchoolStudent1("wcx",20),"wuhan");
        map.put(new HighSchoolStudent1("nianzhen",50),"xingou");
        map.put(new HighSchoolStudent1("asule",28),"beijing");

        Set<HighSchoolStudent1> keySet = map.keySet();
        Iterator<HighSchoolStudent1> iterator = keySet.iterator();

        while (iterator.hasNext()){
            HighSchoolStudent1 key = iterator.next();
            System.out.println(key.toString()+"   "+map.get(key));
        }
    }

    class HighSchoolStudent1Comparable implements Comparator<HighSchoolStudent1>{

        @Override
        public int compare(HighSchoolStudent1 o1, HighSchoolStudent1 o2) {
            if (o1.getAge()==o2.getAge()){

                return o1.getName().compareTo(o2.getName());
            }

            return o1.getAge()-o2.getAge();
        }
    }

    class HighSchoolStudent1 extends Person
//            implements Comparable<HighSchoolStudent1>
    {
        public HighSchoolStudent1(String name, int age) {
            super(name, age);
        }

        public HighSchoolStudent1() {

        }

//        @Override
//        public int compareTo(HighSchoolStudent1 o) {
//            if (this.getAge()==o.getAge()){
//                return this.getName().compareTo(o.getName());
//            }
//            return this.getAge()-o.getAge();
//        }
    }

}
