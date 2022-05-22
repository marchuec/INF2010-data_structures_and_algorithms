import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.NoSuchElementException;

class HashMapTest {
    @Test
    void ctorRefusesInvalidCapacity() {
        HashMap<String, Integer> map = new HashMap<>(-1);
        assertEquals(23, map.capacity());

        map = new HashMap<>(0);
        assertEquals(23, map.capacity());
    }

    @Test
    void ctorCapacityIsPrime() {
        HashMap<String, Integer> map = new HashMap<>(11);
        assertEquals(11, map.capacity());

        map = new HashMap<>(10);
        assertEquals(11, map.capacity());
    }

    @Test
    void getReturnsNullIfNotThere() throws Exception {
        HashMap<KeyMock, Integer> map = new HashMap<>();

        KeyMock firstKey = new KeyMock(1, true);

        boolean nullWithoutValue = map.get(firstKey) == null;
        assertTrue(nullWithoutValue);
    }

    @Test
    void getReturnsValue() throws Exception {
        HashMap<KeyMock, Integer> map = new HashMap<>(11);

        Integer value = 1;
        KeyMock firstKey = new KeyMock(1, true);

        //Create a fake array of nodes for testing
        HashMap.Node<KeyMock, Integer>[] mockMap = new HashMap.Node[11];
        mockMap[1] = new HashMap.Node<>(firstKey, value);

        //Use reflection to access the size
        Field sizeField = map.getClass().getDeclaredField("size");
        sizeField.setAccessible(true);

        //Sice we added an entry size should now be 1
        sizeField.set(map, 1);

        //Use reflection to access private field
        //Allows to test get() before put() is implemented
        Field mapField = map.getClass().getDeclaredField("map");
        mapField.setAccessible(true);
        mapField.set(map, mockMap);

        Integer valueInMap = map.get(firstKey);
        assertEquals(value, valueInMap);
    }

    @Test
    void getHandlesCollisions() throws Exception {
        HashMap<KeyMock, Integer> map = new HashMap<>(23);

        int n = 9;

        // Place the first node at position 1 since hashcode is forced at 1
        HashMap.Node<KeyMock, Integer>[] mockMap = new HashMap.Node[23];
        mockMap[1] = new HashMap.Node<>(new KeyMock(0), 0);

        // Place subsequent nodes in linked list of first node
        HashMap.Node<KeyMock, Integer> currentNode = mockMap[1];
        for (int i = 1; i < n; i++) {
            currentNode.next = new HashMap.Node<>(new KeyMock(i), i);
            currentNode = currentNode.next;
        }

        //Use reflection to access the size
        Field sizeField = map.getClass().getDeclaredField("size");
        sizeField.setAccessible(true);

        //Sice we added an 9 entries size should now be 9
        sizeField.set(map, 9);

        // Use reflection to access private field
        // Allows to test get() before put() is implemented
        Field mapField = map.getClass().getDeclaredField("map");
        mapField.setAccessible(true);
        mapField.set(map, mockMap);

        for (int i = 0; i < n; i++) {
            assertEquals(map.get(new KeyMock(i)), i);
        }
    }

    @Test
    void putReturnsOldValue() {
        HashMap<String, Integer> map = new HashMap<>();

        Integer oldValue = 1;
        map.put("myKey", oldValue);

        Integer returnedOldValue = map.put("myKey", 2);

        assertEquals(oldValue, returnedOldValue);
    }

    @Test
    void putReturnsNullIfNoOldValue() {
        HashMap<String, Integer> map = new HashMap<>();
        Integer value = map.put("myKey", 1);

        assertNull(value);
    }

    @Test
    void putReplacesValue() throws Exception{
        HashMap<KeyMock, Integer> map = new HashMap<>();

        //Forcing a hash code so we know where to manually look for the data
        KeyMock key = new KeyMock(4, true);

        Integer value = 2;
        map.put(key, 1);
        map.put(key, value);

        //Uses reflection to access private fields
        //Allows to test put() without having implemented get()
        Field mapField = map.getClass().getDeclaredField("map");
        mapField.setAccessible(true);
        HashMap.Node<KeyMock, Integer>[] nodeArray = (HashMap.Node<KeyMock, Integer>[]) mapField.get(map);

        assertEquals(value, nodeArray[1].data);
        assertEquals(1, map.size());
    }

    @Test
    void putHandlesCollisions() throws Exception{
        HashMap<KeyMock, Integer> map = new HashMap<>();

        int n = 9;

        for (int i = 0; i < n; ++i) {
            map.put(new KeyMock(i), i);
        }

        //Uses reflection to access private fields
        //Allows to test put() without having implemented get()
        Field mapField = map.getClass().getDeclaredField("map");
        mapField.setAccessible(true);
        HashMap.Node<KeyMock, Integer>[] nodeArray = (HashMap.Node<KeyMock, Integer>[]) mapField.get(map);

        //We know values are at position 1 since we forced the hash to 1
        HashMap.Node<KeyMock, Integer> currentNode = nodeArray[1];

        for(int i = 0; i < n && currentNode != null; i++) {
            assertEquals(currentNode.key.key, i);
            currentNode = currentNode.next;
        }
    }

    @Test
    void nodeIteratorConstructor() {
        HashMap.Node<Integer, Integer> node = new HashMap.Node<>(0, 0);
        assertTrue(node.iterator().hasNext());
    }

    @Test
    void nodeIteratorNextWorking() {
        HashMap.Node<Integer, Integer> node = new HashMap.Node<>(0, 0);

        int n = 5;
        HashMap.Node<Integer, Integer> currentNode = node;
        for (int i = 1; i < n; i++) {
            currentNode.next = new HashMap.Node<>(i, i);
            currentNode = currentNode.next;
        }

        int i = 0;
        Iterator<HashMap.Node<Integer, Integer>> it = node.iterator();
        while (it.hasNext()) {
            assertEquals(it.next().key, i);
            i++;
        }
    }

    @Test
    void hashMapIteratorConstructor() {
        HashMap<String, Integer> map = new HashMap<>();
        assertFalse(map.iterator().hasNext());

        map.put("key", 1);
        assertTrue(map.iterator().hasNext());
    }

    @Test
    void hashMapIteratorGoesIntoLikedList() {
        HashMap<KeyMock, Integer> map = new HashMap<>();

        int n = 9;
        for (int i = 0; i < n; i++) {
            if (i < 3)
                map.put(new KeyMock(i), i);
            else
                map.put(new KeyMock(i, false), i);
        }

        Iterator<KeyMock> it = map.iterator();
        int i = 0;
        while (it.hasNext()) {
            assertEquals(it.next().key, i);
            i++;
        }
    }

    @Test
    void iteratorHasNextWithNoNext() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("key", 1);

        Iterator<String> it = map.iterator();
        it.next();

        assertFalse(it.hasNext());
    }

    @Test
    void iteratorNextWithNoNext() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("key", 1);

        Iterator<String> it = map.iterator();
        it.next();

        assertThrows(NoSuchElementException.class, it::next);
    }


    @Test
    void containsKey() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("myKey", 1);

        assertTrue(map.containsKey("myKey"));
        assertFalse(map.containsKey("unknownKey"));
    }

    @Test
    void removeErasesValue() {
        HashMap<String, Integer> map = new HashMap<>();
        String key = "myKey";

        map.put(key, 1);
        map.remove(key);
        assertNull(map.get(key));
    }

    @Test
    void removeReturnsRemovedValue() {
        HashMap<String, Integer> map = new HashMap<>();

        Integer value = 1;
        map.put("myKey", value);
        Integer removedValue = map.remove("myKey");

        assertEquals(value, removedValue);
    }

    @Test
    void removeReturnsNullIfNotExist() {
        HashMap<String, Integer> map = new HashMap<>();

        Integer removedValue = map.remove("myKey");

        assertNull(removedValue);
    }

    @Test
    void removeHandlesCollisions() {
        HashMap<KeyMock, Integer> map = new HashMap<>(20);
        int n = 9;

        for (int i = 0; i < n; ++i) {
            map.put(new KeyMock(i), i);
        }

        // Did not want tests to have any randomness, so the array is manually created
        // Does not dynamically change with n
        int[] staticRandomOrderNums = {0, 4, 2, 6, 3, 1, 7, 8, 5};

        for (int i = 0; i < n; ++i) {
            Integer removedValue = map.remove(new KeyMock(staticRandomOrderNums[i]));
            assertTrue(removedValue != null && removedValue.equals(staticRandomOrderNums[i]));
        }
    }

    @Test
    void sizeIncrementsAndDecrements() {
        HashMap<String, Integer> map = new HashMap<>();
        assertTrue(map.isEmpty());

        map.put("myKey", 1);
        assertEquals(1, map.size());

        map.remove("myKey");
        assertTrue(map.isEmpty());
    }

    @Test
    void clear() {
        HashMap<String, Integer> map = new HashMap<>();
        int n = 9;

        for (int i = 0; i < n; ++i) {
            String index = String.valueOf(i);
            map.put("myKey" + index, i);
        }

        map.clear();
        assertTrue(map.isEmpty());

        assertFalse(map.iterator().hasNext());
    }

    @Test
    void isEmpty() {
        HashMap<String, Integer> map = new HashMap<>();
        assertTrue(map.isEmpty());

        map.put("myKey", 1);
        assertFalse(map.isEmpty());

        map.remove("myKey");
        assertTrue(map.isEmpty());
    }

    @Test
    void capacityIncreasesWithLoadFactor() {
        HashMap<String, Integer> map = new HashMap<>(11, 0.5f);

        int n = 6;
        for (int i = 0; i < n; ++i) {
            String index = String.valueOf(i);
            map.put("myKey" + index, i);
        }

        // Clear should not put capacity back to its initial value
        map.clear();

        assertEquals(23, map.capacity());
    }

    @Test
    void rehashWorksProperly() {
        HashMap<String, Integer> map = new HashMap<>(10);
        int nextPrimeCapacity = 23;
        int n = 9;

        //Add data to trigger rehash
        for (int i = 0; i < n; ++i) {
            String index = String.valueOf(i);
            map.put("myKey" + index, i);
        }

        //Check we properly added all the data
        assertEquals(n, map.size());

        //Check capacity was increased
        assertEquals(nextPrimeCapacity, map.capacity());

        //Check all previous data is still present
        for (int i = 0; i < n; ++i) {
            String index = String.valueOf(i);
            Integer value = map.get("myKey" + index);
            assertTrue(value == i);
        }
    }

    @Test
    void rehashHandlesCollisions() throws Exception{
        HashMap<KeyMock, Integer> map = new HashMap<>(11);

        //Use reflection to access private field
        Field mapField = map.getClass().getDeclaredField("map");
        mapField.setAccessible(true);

        //Put value and for key hash code to 1
        map.put(new KeyMock(1), 1);

        //We know where the node we juste added is since its hashcode is forced to 1
        HashMap.Node<KeyMock, Integer> node = ((HashMap.Node<KeyMock, Integer>[]) mapField.get(map))[1];

        //Add a node in linked list that should not be there after rehash
        node.next = new HashMap.Node<>(new KeyMock(0, false), 0);

        //Use reflection to access the size
        Field sizeField = map.getClass().getDeclaredField("size");
        sizeField.setAccessible(true);

        //Sice we added an entry size should now be 2
        sizeField.set(map, 2);

        //We shouldnt be able to get node as it is not in the right spot
        assertNull(map.get(new KeyMock(0, false)));

        int n = 9;

        //Add nodes to trigger rehash
        for (int i = 2; i < n; i++) {
            map.put(new KeyMock(i, false), i);
        }

        //After rehash we should be able to find the node
        assertNotNull(map.get(new KeyMock(0, false)));
    }

    // We verify that the get/put/containsKey functions are O(1), we test by adding N items and making sure
    // that the number of the most common operation (barometer) follows a trend that is linear.
    @Test
    void testComplexityWithBarometerOperation() {
        int increaseRate = 10;
        int previousTotalBarometer = 2;
        double totalBarometerRate = 0.0;
        int maxSize = 1000000;
        int totalLoops = 0;

        for (int listSize = increaseRate; listSize < maxSize; listSize *= increaseRate) {
            ++totalLoops;

            HashMap<KeyMock, Integer> map = new HashMap<>(listSize * 2);

            // Checks for barometer operation usage in operations
            for (int i = 0; i < listSize; ++i) map.put(new KeyMock(i, false), i);
            for (int i = 0; i < listSize; ++i) assertTrue(map.containsKey(new KeyMock(i, false)));
            for (int i = 0; i < listSize; ++i) assertEquals(map.get(new KeyMock(i, false)), i);

            // Count the barometer operation for complexity.
            int totalBarometer = 0;
            for (KeyMock key : map) {
                totalBarometer += key.getBarometerCounter();
            }

            totalBarometerRate += (double) totalBarometer / previousTotalBarometer;
            previousTotalBarometer = totalBarometer;
        }

        // The rate should be around the increaseRate because the complexity is O(n).
        assertEquals(increaseRate, totalBarometerRate / totalLoops, 1.0);
    }

    static class KeyMock {
        private int barometerCounter;
        private final Integer key;
        private final boolean forceHashCode;

        public KeyMock(Integer key) {
            this(key, true);
        }

        public KeyMock(Integer key, boolean forceHashCode) {
            this.key = key;
            this.forceHashCode = forceHashCode;
        }

        public int getBarometerCounter() {
            return barometerCounter;
        }

        @Override
        public int hashCode() {
            return forceHashCode ? 1 : key.hashCode();
        }

        @Override
        public boolean equals(Object other) {
            ++barometerCounter;

            if (other instanceof HashMapTest.KeyMock) {
                HashMapTest.KeyMock otherKey = (HashMapTest.KeyMock) other;
                return key.equals(otherKey.key);
            }

            return false;
        }
    }
}
