package com.imooc.day02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Distinct {
    public static void main(String[] args) {
        distinct();
    }
    //O(n)去重
    private static void demo(){
        String str =  "agdbadbjja";
        StringBuffer strb = new StringBuffer();
        List lists = new ArrayList<Character>();
        for (int i = 0; i < str.length(); i++) {
            if (!lists.contains(str.charAt(i))) {
                lists.add(str.charAt(i));
                strb.append(str.charAt(i));
            }
        }
        System.out.println(strb.toString());
    }
    //O(n)取出重复字母
    public static void distinct(){
        String str =  "agdbadbjja";
        HashMap<Character, Integer> countMap = new HashMap<Character, Integer>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            Integer count = countMap.get(c);
            if(count != null){
                countMap.put(c,++count);
            }else{
                countMap.put(c,1);
            }
        }
    }
}

