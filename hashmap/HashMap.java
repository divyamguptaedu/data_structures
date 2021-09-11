import java.util.*;

public class HashMap<K, V> {

    public static final int INITIAL_CAPACITY = 13;

    public static final double MAX_LOAD_FACTOR = 0.67;

    private MapEntry<K, V>[] table;
    private int size;

    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    public HashMap(int initialCapacity) {
        table = new MapEntry[initialCapacity];
    }

    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("The key or the value cannot be null. Cannot perform action.");
        }
        MapEntry<K, V> dummyMap = new MapEntry<K, V>(key, value);
        if ((size + 1) / (double) table.length > MAX_LOAD_FACTOR) {
            resizeBackingTable(2 * table.length + 1);
        }
        int i = Math.abs(key.hashCode() % table.length);
        V oldValueEntry = null;
        if (table[i] == null) {
            table[i] = dummyMap;
            size++;
        } else {
            boolean alreadyExists = false;
            MapEntry<K, V> curr = table[i];
            while (curr != null) {
                if (curr.getKey().equals(key)) {
                    oldValueEntry = curr.getValue();
                    curr.setValue(value);
                    alreadyExists = true;
                }
                curr = curr.getNext();
            }
            if (!alreadyExists) {
                dummyMap.setNext(table[i]);
                table[i] = dummyMap;
                size++;
            }
        }
        return oldValueEntry;
    }

    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The key cannot be null. Cannot perform action.");
        }
        V removedValue = null;
        MapEntry<K, V> map = table[Math.abs(key.hashCode() % table.length)];
        if (map == null) {
            throw new NoSuchElementException("The key was not found in the map. Cannot perform action.");
        }
        if (map.getKey().equals(key)) {
            if (map.getNext() == null) {
                removedValue = map.getValue();
                table[Math.abs(key.hashCode() % table.length)] = null;
            } else {
                removedValue = map.getValue();
                map.setValue(map.getNext().getValue());
                map.setKey(map.getNext().getKey());
                map.setNext(map.getNext().getNext());
            }
        } else {
            while (map.getNext() != null) {
                if (map.getNext().getKey().equals(key)) {
                    removedValue = map.getNext().getValue();
                    map.setNext(map.getNext().getNext());
                } else {
                    map = map.getNext();
                }
            }
        }

        if (removedValue == null) {
            throw new NoSuchElementException("The key was not found in the map. Cannot perform action.");
        }
        size--;
        return removedValue;
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The key cannot be null. Cannot perform action.");
        }
        V gotValue = null;
        MapEntry<K, V> map = table[Math.abs(key.hashCode() % table.length)];
        while (map != null) {
            if (map.getKey().equals(key)) {
                gotValue = map.getValue();
            }
            map = map.getNext();
        }

        if (gotValue == null) {
            throw new NoSuchElementException("Key is not in the map.");
        }
        return gotValue;
    }

    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The key cannot be null. Cannot perform action.");
        }
        boolean present = false;
        MapEntry<K, V> map = table[Math.abs(key.hashCode()) % table.length];
        while (map != null) {
            if (map.getKey().equals(key)) {
                present = true;
            }
            map = map.getNext();
        }

        return present;
    }

    public Set<K> keySet() {
        HashSet<K> keysSet = new HashSet<>(size);
        for (MapEntry<K, V> map : table) {
            while (map != null) {
                keysSet.add(map.getKey());
                map = map.getNext();
            }
        }
        return keysSet;
    }

    public List<V> values() {
        ArrayList<V> valuesList = new ArrayList<>(size);
        for (MapEntry<K, V> map : table) {
            while (map != null) {
                valuesList.add(map.getValue());
                map = map.getNext();
            }
        }
        return valuesList;
    }

    public void resizeBackingTable(int length) {
        if (size > length) {
            throw new IllegalArgumentException("The length should be greater than no. of entries. Cannot perform.");
        }
        MapEntry<K, V>[] newMapEntry = new MapEntry[length];
        for (MapEntry<K, V> map : table) {
            if (map != null) {
                int putIndex = map.getKey().hashCode() % length;
                if (newMapEntry[putIndex] == null) {
                    newMapEntry[putIndex] = map;
                } else {
                    map.setNext(newMapEntry[putIndex]);
                    newMapEntry[putIndex] = map;
                }
            }
        }
        table = newMapEntry;
    }

    public void clear() {
        table = new MapEntry[INITIAL_CAPACITY];
        size = 0;
    }

    public MapEntry<K, V>[] getTable() {
        return table;
    }

    public int size() {
        return size;
    }

    public static boolean findTriplet(int[] array, int array_size, int sum) {
        for (int i = 0; i < array_size - 2; i++) {
            HashSet<Integer> hashSet = new HashSet<>();
            int newSum = sum - array[i];
            for (int j = i + 1; j < array_size; j++) {
                if (hashSet.contains(newSum - array[j])) {
                    System.out.println("Triplet: " + array[i] + ", " + array[j] + ", and " + (newSum - array[j]));
                    return true;
                }
                hashSet.add(array[j]);
            }
        }
        return false;
    }

    public List<V> emptyChain(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The key passed in is a null. Cannot perform action.");
        }
        MapEntry<K, V> entry = table[Math.abs(key.hashCode() % table.length)];
        ArrayList<V> valuesList = new ArrayList<>();
        if (entry == null) {
            return valuesList;
        }
        while(entry != null) {
            valuesList.add(entry.getValue());
            entry = entry.getNext();
        }
        table[Math.abs(key.hashCode() % table.length)] = null;
        size = size - valuesList.size();
        return valuesList;
    }

    public boolean containsAllKeys(HashMap<K, V> other) {
        if (other == null) {
            throw new IllegalArgumentException("The other hashmap passed in is a null. Cannot perform action.");
        }
        K otherKey = null;
        boolean present = false;
        for (MapEntry<K, V> mapEntry : other.getTable()) {
            otherKey = mapEntry.getKey();
            MapEntry<K, V> checkMap = this.table[Math.abs(otherKey.hashCode()) % this.table.length];
            while (checkMap != null) {
                if (checkMap.getKey().equals(otherKey)) {
                    present = true;
                }
                checkMap = checkMap.getNext();
            }
            if (!present) {
                return false;
            }
        }
        return true;
    }

    public Set<K> collidingKeys(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The key passed in was a null. Cannot perform action.");
        }
        MapEntry<K, V> head = table[Math.abs(key.hashCode() % table.length)];
        while (head != null) {
            if (head.getKey() == key) {
                head = table[Math.abs(key.hashCode() % table.length)];
                Set<K> returnSet = new HashSet();
                while (head != null && !head.getKey().equals(key)) {
                    returnSet.add(head.getKey());
                    head = head.getNext();
                }
                return returnSet;
            } else {
                head  = head.getNext();
            }
        }
        throw new NoSuchElementException("No colliding keys");
    }
}
