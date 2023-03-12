package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
   static Logic function= digit -> Character.isDigit(digit);

    public static List<String> numbers= Arrays.asList("1, 2, 0","4, 5");
    public static void main(String[] args) {
        System.out.println(resultString(sortNumb(numbers, function)));
    }
    static List<String> sortNumb(List<String> number,Logic function){
        List<String>result=new ArrayList<>();
        for (String numb: number) {
            for (int i = 0; i < numb.length(); i++) {
char x=numb.charAt(i);
                if (function.sort(x)){
                    result.add(String.valueOf(x));
                }
            }

        }
        Collections.sort(result);
        return result;


    }
    static String resultString(List<String> sortedList){
        String result="";
        for (int i = 0; i < sortedList.size(); i++) {
            if (i!=sortedList.size()-1){
              result+= sortedList.get(i)+", ";
            }else if(i==sortedList.size()-1){
              result+=sortedList.get(i);
            }

        }
        return result;
    }
}