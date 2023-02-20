public class MyHashMap <K,V>{
    private final int INITIAL_CAPACITY = 16;
    private Node<K, V>[] buckets;
    private int size;

    private static class Node<K, V> {
        private final K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            next = null;
        }
    }

    public MyHashMap() {
        buckets = new Node[INITIAL_CAPACITY];
        size = 0;
    }

    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        Node<K, V> head = buckets[bucketIndex];


        Node<K, V> currentNode = head;
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                currentNode.value = value;
                return;
            }
            currentNode = currentNode.next;
        }


        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = head;
        buckets[bucketIndex] = newNode;
        size++;
    }

    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        Node<K, V> currentNode = buckets[bucketIndex];
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                return currentNode.value;
            }
            currentNode = currentNode.next;
        }
        return null;
    }

    public void remove(K key) {
        int bucketIndex = getBucketIndex(key);
        Node<K, V> head = buckets[bucketIndex];

        if (head == null) {
            return;
        }

        if (head.key.equals(key)) {
            buckets[bucketIndex] = head.next;
            size--;
            return;
        }

        Node<K, V> prevNode = head;
        Node<K, V> currentNode = head.next;
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                prevNode.next = currentNode.next;
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

    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = null;
        }
        size = 0;
    }

    private int getBucketIndex(K key) {
        return Math.abs(key.hashCode() % buckets.length);
    }
}

