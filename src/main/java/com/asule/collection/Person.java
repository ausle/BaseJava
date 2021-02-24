package com.asule.collection;

/**
 * created by asule on 2020-02-26 17:00
 */
public class Person {


    private String name;
    private int age;

    private Person(String name) {
        this.name = name;
    }


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

    public Person(String name, int age) {
        System.out.println("======>"+this.hashCode());
        this.name = name;
        this.age = age;
    }



    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Person person = (Person) o;
//        if (age != person.age) return false;
//
//        //name为null，比较的person的name为null，视为相同。age相同，再比较name内容
//        return name != null ? name.equals(person.name) : person.name == null;
//    }
//
//
//    //为了保证不同的name和age计算的哈希值相同，所以需要加大哈希的值，可以乘以一个随机值。
//    @Override
//    public int hashCode() {
//        int result = name != null ? name.hashCode() : 0;
//        result = 31 * result + age;
//        return result;
//    }
}
