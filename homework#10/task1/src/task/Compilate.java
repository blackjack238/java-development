package task1;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Compilate {
    public static void main(String[] args) throws FileNotFoundException {
        String Path="G:\\колежд\\Новая папка\\untitled\\src\\resource\\txttex";
        FileData fileData=new FileData();
        DataFilter dataFilter=new DataFilter();
        DataPrinter dataPrinter=new DataPrinter();
     List b= fileData.Saver(Path);
     List v=   dataFilter.Filter(b);
     dataPrinter.print(v);

    }
}
