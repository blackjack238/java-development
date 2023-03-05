package task1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class FileData {







  private   String A="";
    public List Saver(String Path) throws FileNotFoundException {

        File file=new File(Path);
        Scanner scanner = new Scanner(file);
List<String>list=new ArrayList<>();

        while (scanner.hasNextLine()){
            list.add(scanner.nextLine());
        }scanner.close();


        for (int i = 0; i < list.size(); ) {
            int count=0;
            int f=0;
            for (int j = 0; j < list.get(i).length(); j++) {
                if (Character.isDigit(list.get(i).charAt(j))){
                    count++;
                }
if (Character.isLetter(list.get(i).charAt(j))){



break;
}
if (j==0){
    if (Character.isWhitespace(list.get(i).charAt(j))){
        break;
    }


}
            }
            if (count!=10){
                list.remove(i);
                f++;
            }
            if(f==0)i++;

        }


        /*try(FileInputStream inputStream=new FileInputStream(Path)) {
            int a= inputStream.read();
            HashMap<Integer,String > number=new HashMap<>();
            while (a!=-1){
                if(Character.isDigit((char)a)) {
                    count++;
                }

                A+=(char)a;
                String j= A.replaceAll("\r","");
                String h= j.replaceAll("\n","");
                number.put(key,h);
                if(count==10)  {
                    key++;
                    A="";
                    count=0;
                }
                a=inputStream.read();
            }

            return number;
}catch (IOException e){throw new RuntimeException(e);}
    */return list;}}
