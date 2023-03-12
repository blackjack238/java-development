package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Main {

static Logic getSortedList= namesNum -> (!Character.isAlphabetic(namesNum));
    static Logic getName= namesNum->{
        int x=Character.getNumericValue(namesNum);
        return x%2!=0;
    };
    private static List<String> names= Arrays.asList("1. Aleksiy","2. Kaparov","3. Zodic");
    public static void main(String[] args) {
        System.out.println(getGmails(names, getName));
        System.out.println(sortArray(names,getSortedList));
    }
    private static List<String> getGmails(List<String>names,Logic function){
        List<String> result=new ArrayList<>();
        for (String name: names) {
            char x=name.charAt(0);

                if(function.checkEmail(x)){
                    result.add(name);

            }



        }
        return result;
    }
    private static  List<String>sortArray(List<String> names,Logic function){
        String filterName="";
        List<String> result=new ArrayList<>();
        for (String name:names) {
            filterName=name;
            for (int i = 0; i < name.length(); i++) {
                char x=name.charAt(i);
                if (function.checkEmail(x)){
                    filterName=filterName.replace(String.valueOf(x),"");
                }


            }
            result.add(filterName);
        }
        Collections.sort(result);
        Collections.reverse(result);
        return result;
    }
}