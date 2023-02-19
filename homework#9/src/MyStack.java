public class MyStack <E>{
    private final int CUT_RATE = 4;
    private Object arr[];
    private int  top = -1;
    private int capacity=10;
    private int count=0;

    public MyStack() {
        arr = new Object[capacity];
    }


    public void push(E x)
    {
        if(count == arr.length-1)
            resize(arr.length*2);

        System.out.println("Inserting " + x);
        arr[++top] = x;
    }


    public Object pop()
    {

        if (isEmpty())
        {
            System.out.println("Underflow\nProgram Terminated");
            System.exit(-1);
        }

        System.out.println("Removing " + peek());


        return  arr[top--];
    }

    public Object peek()
    {
        if (!isEmpty()) {
            return  arr[top];
        }
        else {
            System.exit(-1);
        }

        return -1;
    }


    public int size() {
        return top + 1;
    }


    public boolean isEmpty() {
        return top == -1;
    }


    public boolean isFull() {
        return top == capacity - 1;
    }
    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(arr, 0, newArray, 0, count);
        arr = newArray;
    }
    public void clear() {

        final Object[] es =arr ;
        for (int to = count, i = count = 0; i < to; i++)
            es[i] = Integer.parseInt(null);
    }
    public void remove(int index) {
        for (int i = index; i<count; i++)
            arr[i] = arr[i+1];
        arr[count] = null;
        count--;
        if (arr.length > capacity && count < arr.length / CUT_RATE)
            resize(arr.length/2);

    }
}
