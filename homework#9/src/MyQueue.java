


public class MyQueue <E>{
    private Object[] array;
    private int front=0;
    private int rear=-1;
    private int capacity =10;
    private int count=0;

    public MyQueue() {
        array = new Object[capacity];
    }



    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return (size() == 0);
    }


    public boolean isFull() {
        return (size() == capacity);
    }
    public void clear() {

        final Object[] es =array ;
        for (int to = count, i = count = 0; i < to; i++)
            es[i] = Integer.parseInt(null);
    }
    public int poll()
    {

        if (isEmpty())
        {
            System.out.println("Underflow\nProgram Terminated");
            System.exit(-1);
        }

        int x = (int) array[front];

        System.out.println("Removing " + x);

        front = (front + 1) % capacity;
        count--;

        return x;
    }
    public void add(E item)
    {

        if(count == array.length-1)
            resize(array.length*2);


        System.out.println("Inserting " + item);

        rear = (rear + 1) % capacity;
        array[rear] = item;
        count++;
    }
    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, count);
        array = newArray;
    }
    public E peek()
    {
        if (isEmpty())
        {
            System.out.println("Underflow\nProgram Terminated");
            System.exit(-1);
        }
        return (E) array[front];
    }
}





