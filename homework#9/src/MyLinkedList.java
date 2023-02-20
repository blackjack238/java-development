

public class MyLinkedList <T>{

    private Node<T> head;
    private Node<T> tail;
    private int size;
    public void clear() {
        head = null;
        size = 0;
    }
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
        } else {
            Node<T> prev = null;
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                prev = current;
                current = current.next;
            }
            prev.next = current.next;
            if (prev.next == null) {
                tail = prev;
            }
        }
        size--;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}


