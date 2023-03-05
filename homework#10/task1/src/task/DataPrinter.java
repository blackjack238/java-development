package task1;

import java.util.HashMap;
import java.util.List;

public class DataPrinter {
    public void print(List numbers){
        String[] listNumber=new String[numbers.size()];
        for (int i = 0; i < listNumber.length; i++) {
            listNumber[i]= String.valueOf(numbers.get(i));
            if(numbers.get(i)==null){
                listNumber[i]="";
            } if (!listNumber[i].equals(""))
                System.out.println(listNumber[i]);
        }
    }
}
