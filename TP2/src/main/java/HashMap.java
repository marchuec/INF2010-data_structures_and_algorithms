import java.util.Arrays;
import java.util.Iterator;

public class HashMap<KeyType, DataType> implements Iterable<KeyType> {

    // DEFAULT_CAPACITY has to be a prime number
    private static final int DEFAULT_CAPACITY = 23;
    private static final float DEFAULT_LOAD_FACTOR = 0.5f;
    private static final int CAPACITY_INCREASE_FACTOR = 2;

    private Node<KeyType, DataType>[] map;
    private int size = 0;
    private int capacity;
    private final float loadFactor; // Compression factor

    public HashMap() { this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR); }

    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

     /** TODO (DONE)
     * Constructeur par parametres
     * @param initialCapacity
     * @param loadFactor
     */
    public HashMap(int initialCapacity, float loadFactor) {
        capacity = (initialCapacity > 0) ? nextPrime(initialCapacity) : DEFAULT_CAPACITY;
        this.loadFactor = 1 / loadFactor;
        map = new Node[capacity];
    }

    /**
     * Finds the index attached to a particular key
     * This is the hashing function ("Fonction de dispersement")
     * @param key Value used to access to a particular instance of a DataType
     * @return Index value where this key should be placed in `map`
     */
    private int hash(KeyType key){
        int keyHash = key.hashCode() % capacity;
        return Math.abs(keyHash);
    }

    /**
     * @return if it should be rehashed
     */
    private boolean needRehash() {
        return size * loadFactor > capacity;
    }

    /**
     * @return Number of elements
     */
    public int size() {
        return size;
    }

    /**
     * @return Current reserved space
     */
    public int capacity(){
        return capacity;
    }

    /**
     * @return if it is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /** TODO (DONE)
     * Finds the next prime
     * @param number number to start the search to the next prime
     * @return Next closest prime
     */
    private int nextPrime(int number) {
        for (int i=2; i<number; i++) {
            if (number % i == 0) {
                number++;
            }
        }
        return number;
    }

    /** TODO Worst Case : O(n) (DONE)
     * Increases capacity to the next prime number after capacity * CAPACITY_INCREASE_FACTOR and
     * reassigns all contained values
     */
    private void rehash() {
        capacity = nextPrime(capacity * CAPACITY_INCREASE_FACTOR);
        Node<KeyType, DataType>[] oldMap = map;
        map = new Node[capacity];
        size = 0;
        for (int i = 0; i < oldMap.length; i++) {
            for (Node<KeyType, DataType> it = oldMap[i]; it != null; it = it.next){
                put(it.key, it.data);
            }
        }
    }

    /** TODO Average Case : O(1) (DONE)
     * Finds if the key is already assigned
     * @param key Key which we want to know if exists already
     * @return if key is already used
     */
    public boolean containsKey(KeyType key) {
        return get(key) != null;
    }

    /** TODO Average Case : O(1) (DONE)
     * Finds the value attached to a key
     * @param key Key which we want to have its value
     * @return DataType instance attached to key (null if not found)
     */
    public DataType get(KeyType key) {
        int index = hash(key);
        if (map[index] == null) {
            return null;
        }
        Iterator<Node<KeyType, DataType>> it = map[index].iterator();
        while (it.hasNext()) {
            Node<KeyType, DataType> node = it.next();
            if (node.key.equals(key)) {
                return node.data;
            }
        }
        return null;
    }


    /** TODO Average Case : O(1) , Worst case : O(n) (DONE)
     * Assigns a value to a key
     * @param key Key which will have its value assigned or reassigned
     * @return Old DataType instance at key (null if none existed)
     */
    public DataType put(KeyType key, DataType value) {
        int index = hash(key);
        Node<KeyType, DataType> newNode = new Node<>(key, value);
        if (map[index] == null) {
            map[index] = newNode;
            size++;
            if (needRehash()) {rehash(); }
            return null;
        }
        else {
            Iterator<Node<KeyType, DataType>> it = map[index].iterator();
            Node<KeyType, DataType> current = null;
            while (it.hasNext()) {
                current = it.next();
                if (current.key == key) {
                    DataType oldData = current.data;
                    current.data = value;
                    return oldData;
                }
            }
            // If key is not present
            current.next = newNode;
            size++;
            if (needRehash()) {rehash(); }
            return null;
        }
    }


    /** TODO Average Case : O(1) (DONE)
     * Removes the node attached to a key
     * @param key Key which is contained in the node to remove
     * @return Old DataType instance at key (null if none existed)
     */
    public DataType remove(KeyType key) {
            int index = hash(key);
            Node<KeyType, DataType> current = map[index];

            if (current == null)
                return null;

            //First verification
            if (key.equals(current.key)){
                DataType previousValue = current.data;
                map[index] = current.next;
                size--;
                return previousValue;
            }

            for(;current.next != null; current = current.next){
                if (key.equals(current.next.key)){
                    DataType previousValue = current.next.data;
                    current.next = current.next.next;
                    size--;
                    return previousValue;
                }
            }
            return null;
        }

    /** TODO Worst Case : O(1)
     * Removes all nodes
     */
    public void clear() {
            map = new Node[capacity];
            size = 0;
    }

    static class Node<KeyType, DataType> implements Iterable<Node<KeyType, DataType>> {
        final KeyType key;
        DataType data;
        Node<KeyType, DataType> next; // Pointer to the next node within a Linked List

        Node(KeyType key, DataType data)
        {
            this.key = key;
            this.data = data;
            next = null;    // next = nextNode after add
        }

        @Override
        public Iterator<Node<KeyType, DataType>> iterator() {
            return new NodeIterator();
        }
        private class NodeIterator implements Iterator<Node<KeyType, DataType>> {
            private Node<KeyType, DataType> next = null;

            /** TODO (DONE)
             * Set next to the current node
             */
            NodeIterator() { next = Node.this; }

            @Override
            public boolean hasNext() {
                return next != null;
            }

            /** TODO (DONE)
             * Defines `next` to next element, null if none
             * @return Old next Node
             */
            @Override
            public Node<KeyType, DataType> next() {
                if (!hasNext()) {
                    return null;
                }
                Node<KeyType, DataType> current = next;
                next = next.next;
                return current;
            }
        }
    }

    @Override
    public Iterator<KeyType> iterator() {
        return new HashMapIterator();
    }

    private class HashMapIterator implements Iterator<KeyType> {
        private int nextIndex = 0;
        private Node<KeyType, DataType> next = null;

        /** TODO (DONE)
         * Defines next to first element, null if none
         */
        HashMapIterator() {
            for (int i = 0; i < capacity; i++) {
                if (map[i] != null) {
                    next = map[i];
                    nextIndex = i;
                    break;
                };
            };
        }

        @Override
        public boolean hasNext() { return next != null; }

        /** TODO (DONE)
         * Defines `next` to next element, null if none
         * @return Old next key value
         */
        @Override
        public KeyType next() {
            if (!hasNext()) {
                return null;
            }
            KeyType currentKey = next.key;
            // Si le noeud possède un next
            if (next.next != null) {
                next = next.next;
                return currentKey;
            }
            nextIndex++;
            // Sinon, on itère sur le reste de l'array
            for (int i = nextIndex; i < capacity; i++) {
                if (map[i] != null) {
                    next = map[i];
                    nextIndex = i;
                    return currentKey;
                }
            }
            // Si il n'y a pas de prochain élément
            next = null;
            return currentKey;
        }
    }
}