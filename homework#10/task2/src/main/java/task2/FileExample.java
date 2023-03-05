package task2;





import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileExample {
    public static void main(String[] args) throws FileNotFoundException {
        String absolutepath="G:\\колежд\\Новая папка\\untitled1\\src\\main\\resources\\task2Text";
        File file=new File(absolutepath);
        Scanner scanner = new Scanner(file);
        String pathJSON="G:\\колежд\\Новая папка\\untitled1\\src\\main\\resources\\user.json";

        String header = scanner.nextLine();
        List<User> users = new ArrayList<>();
while (scanner.hasNextLine()){
    String line = scanner.nextLine();
    String[] parts = line.split(" ");
    String name = parts[0];
    int age = Integer.parseInt(parts[1]);


    User user = new User(name, age);
    users.add(user);
} scanner.close();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String json = gson.toJson(users);

        try (PrintWriter out = new PrintWriter(new FileWriter(pathJSON))) {


            out.write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


class User{
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private String name;
    private int age;
        }