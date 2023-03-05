package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException { String absolutepath="G:\\колежд\\Новая папка\\untitled2\\src\\main\\resources\\task3";
        List<String> list=new ArrayList<>();
        String[] words;
        List<String> list2=new ArrayList<>();

        File file=new File(absolutepath);
        Scanner scanner = new Scanner(file);
        String allphrase = "";
        while (scanner.hasNextLine()){
            list.add(scanner.nextLine());
        }scanner.close();
        for (int i = 0; i < list.size(); i++) {
            allphrase+=list.get(i)+'\n';
        }
        String a=allphrase.replaceAll("\n"," ");
       words=a.split(" ");
        list.clear();
        for (String word:words) {
            list.add(word);

        }
        for (int i = 0; i < list.size(); i++) {

            int count=1;
            String word=list.get(i);
            for (int j = i+1; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j))){
                    count++;

                    list.remove(j);
j--;
                }
            }

list2.add(count+" "+word);
Collections.sort(list2);
Collections.reverse(list2);
        }int[]num=new int[list2.size()];
        String []word2=new String[list2.size()];
        for (int i=0;i< list2.size();i++) {
            for (int j = 0; j < list2.get(i).length(); j++) {
                if(Character.isDigit(list2.get(i).charAt(j))){
                    num[i]=Character.getNumericValue(list2.get(i).charAt(j));
                }
                if(Character.isLetter(list2.get(i).charAt(j))){
                    word2[i]+=list2.get(i).charAt(j);
                }
            }
        }
        for (int i = 0; i < list2.size(); i++) {
            System.out.println(word2[i].replaceAll("null","")+" "+num[i]);
        }
               }
                }




