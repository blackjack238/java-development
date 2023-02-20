public class Main {
    public static void main(String[] args) {
MyQueue<String> list=new MyQueue<>();
list.add("a");
list.add("d");
list.add("b");
list.add("c");
        System.out.println(list.size());
        System.out.println(list.peek());
        System.out.println(list.poll());
        System.out.println(list.size());
        list.clear();
        System.out.println(list.size());
    }}
