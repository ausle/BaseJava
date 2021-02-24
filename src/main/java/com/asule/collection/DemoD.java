package com.asule.collection;

import java.util.*;

public class DemoD {

     /*
        Collections---->操作集合的工具类。
        Arrays----->操作数组的工具类。
    */


    //对List集合(重复元素)进行排序
    public void test1(){
        List<Person> lists=new ArrayList<>();

        lists.add(new Person("asule09999",30)); //
        lists.add(new Person("asule12",20)); //
        lists.add(new Person("asule022",30)); //
        lists.add(new Person("asule1111",40)); //


//        Collections.sort(lists);//集合对象需要实现Comparable接口
        Collections.sort(lists, new Comparator<Person>() {  //传入比较器
            @Override
            public int compare(Person o1, Person o2) {
//                if (o1.getAge()==o2.getAge()){
//                    return o2.getName().compareTo(o1.getName());
//                }
//
//                return o2.getAge()-o1.getAge();
                return o1.getName().length()-o2.getName().length();
            }
        });

        System.out.println(lists);
    }

    //二分查找binarySearch
    public void test2(){
        List<String> lists=new ArrayList<>();

        lists.add("baa");
        lists.add("caa");
        lists.add("aca");
        lists.add("acb");
//        Collections.sort(lists);//按照字典排序

        //二分查找时，插入的集合需要进行排序。
        //由于List<String>String实现过Comparable，binarySearch可以接收，但若不进行排序，则无法查找到元素正确的位置
//        int binarySearch = Collections.binarySearch(lists, "acb");
//        System.out.println(binarySearch);


        String max = Collections.max(lists);//默认字典顺序最大
        System.out.println(max);
        String maxComparator = Collections.max(lists, new StringComparator());//按照自定义排序的规则来获取最大值
        System.out.println(maxComparator);

        System.out.println(lists);
    }

    class StringComparator implements Comparator<String>{

        //字典顺序反转
        @Override
        public int compare(String o1, String o2) {
            return o2.compareTo(o1);
        }
    }


    //反转比较器reverseOrder
    public void  test3(){
        List<String> lists=new ArrayList<>();

        lists.add("baa");
        lists.add("caa");
        lists.add("aca");
        lists.add("acb");

//        Collections.sort(lists,new StringComparator());
        Collections.sort(lists,Collections.reverseOrder());//返回的是“对应元素排序”反转比较器

//        Collections.sort(lists,Collections.reverseOrder(new StringComparator()));//对比较器进行反转
        System.out.println(lists);
    }


    public static void main(String[] args) {
        new DemoD().test5();
    }


    //shuffle
    //fill
    public void  test4() {
        List<String> lists = new ArrayList<>();

        lists.add("baa");
        lists.add("caa");
        lists.add("aca");
        lists.add("acb");
        System.out.println(lists);

        Collections.shuffle(lists);//随机顺序
        System.out.println(lists);

//        Collections.fill(lists,"ccc");//该集合替换为指定元素
//        System.out.println(lists);

        Collections.replaceAll(lists,"caa","nba");//内部查出oldElement位置，set一个新元素。
        System.out.println(lists);
    }


    //Arrays方法演示
    //Arrays.toString           输出数组的字符串形式，内部使用StringBuffer.
    //Arrays.asList             数组转集合
    //Collection接口的toArray    集合转数组
    public void  test5() {
        int [] ints={10,2,1,9};
//        System.out.println(ints);   //无法直接输出数组的字符串形式
        //Arrays.toString
        System.out.println(Arrays.toString(ints));  //输出数组的字符串形式

        /*
            Arrays.asList可以把数组转换为集合，转成集合后，可以很方便的使用集合中的方法操作数组中元素。

            如果数组内的元素是基本数据类型，那么集合会把该数组作为集合元素存储。
            若元素是对象，则会把数组内的元素当作集合内的元素存储。

            可以int[]数组转换为集合后，其数组长度依然为1。
        */
        List<int[]> intList = Arrays.asList(ints);
        //无法对集合进行添加操作，原因在于数组的长度是固定的。
//        asList.add(new int[]{10, 1});//throw java.lang.UnsupportedOperationException
        System.out.println(intList.size());

        String[] strings={"aaa","ava","aac","caa"};
        List<String> stringList = Arrays.asList(strings);
        System.out.println(stringList.size());


        //
        List<String> lists = new ArrayList<>();

        lists.add("baa");
        lists.add("caa");
        lists.add("aca");
        lists.add("acb");

        //集合转数组，目的可以是，对操作集合的元素进行限定，不可以对集合内元素进行增删

        String[] toArray = lists.toArray(new String[lists.size()]);//toArray需传入一个指定类型数组。


        System.out.println(Arrays.toString(toArray));
    }

}
