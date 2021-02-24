package com.asule.collection;

import java.util.*;

public class Overview {


    public static void main(String[] args) {


        new Overview().test1("fnirnfirnfxdaaoeccc");

    }




    /*

        计算字符串，每个字母出现的次数。以a(1)b(3)...这种形式输出

     */
    public void test1(final String str){
        //String类型元素已经具备比较性，此时TreeMap会自动按照字典顺序进行排序。
        TreeMap<String,Integer> map=new TreeMap<>();

        for (int i = 0; i <str.length(); i++) {
            String temp=str.substring(i,i+1);;
            if (!map.containsKey(temp)){
                map.put(temp,1);
            }else{
                int newCount=map.get(temp)+1;
                map.put(temp,newCount);
            }
        }

        StringBuffer stringBuffer=new StringBuffer();

        Set<String> keys = map.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            int cout = map.get(key);

            stringBuffer.append(key+"("+cout+")");
        }

        System.out.println(stringBuffer.toString());
    }








}
