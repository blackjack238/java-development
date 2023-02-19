public class MyHashMap <K,V>{
    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;
    private int size = 0;

    private Node<K, V>[] table;

    private static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public MyHashMap() {
        table = (Node<K, V>[]) new Node[INITIAL_CAPACITY];
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        Node<K, V> currentNode = table[index];
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                currentNode.value = value;
                return;
            }
            currentNode = currentNode.next;
        }
        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = table[index];
        table[index] = newNode;
        size++;
        if (size > table.length * LOAD_FACTOR) {
            resize();
        }
    }

    public V get(K key) {
        int index = getIndex(key);
        Node<K, V> currentNode = table[index];
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                return currentNode.value;
            }
            currentNode = currentNode.next;
        }
        return null;
    }

    public void remove(K key) {
        int index = getIndex(key);
        Node<K, V> prevNode = null;
        Node<K, V> currentNode = table[index];
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                if (prevNode == null) {
                    table[index] = currentNode.next;
                } else {
                    prevNode.next = currentNode.next;
                }
                size--;
                return;
            }
            prevNode = currentNode;
            currentNode = currentNode.next;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode() % table.length);
    }

    private void resize() {
        int newCapacity = table.length * 2;
        Node<K, V>[] newTable = (Node<K, V>[]) new Node[newCapacity];
        for (int i = 0; i < table.length; i++) {
            Node<K, V> currentNode = table[i];
            while (currentNode != null) {
                int newIndex = Math.abs(currentNode.key.hashCode() % newCapacity);
                Node<K, V> newNode = new Node<>(currentNode.key, currentNode.value);
                newNode.next = newTable[newIndex];
                newTable[newIndex] = newNode;
                currentNode = currentNode.next;
            }
        }
        table = newTable;
    }
}

