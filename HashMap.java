/**

 public interface Map<K, V> {
 void clear();
 boolean containsKey(K key);
 V get(K key);
 boolean isEmpty();
 void put(K key, V value);
 void remove(K key);
 int size();
 }

 **/

import java.io.*;
import java.util.*;

public class HashMap<K, V> implements Cloneable {
    private static final double MAX_LOAD_FACTOR = 0.75;
    private HashEntry[] hashTable;
    private int size;

    @SuppressWarnings("unchecked")
    public HashMap() {
        hashTable = (HashEntry[]) new HashMap.HashEntry[10];
        size = 0;
    }

    // Removes all elements from the set.
    public void clear() {
        for (int i = 0; i < hashTable.length; i++) {
            hashTable[i] = null;
        }
        size = 0;
    }

    //Returns true if the map contains a mapping for the given key.
    public boolean containsKey(K key) {
        int bucket = hashFunction(key);
        HashEntry current = hashTable[bucket];
        while (current != null) {
            if (current.key != null && current.key.equals(key)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Obtain the value associated with a given key..
     *
     * @param key The key being sought
     * @return The value associated with this key if found; otherwise, null
     */
    public V get(K key) {
        int bucket = hashFunction(key);
        if (hashTable[bucket] != null) {
            // Search the list at table[index] to find the key.
            HashEntry current = hashTable[bucket];
            while(current != null) {
                if (current.key.equals(key))
                    return current.value;
            }
        }
        // assert: key is not in the table.
        return null;
    }

    /**
     * Associates the specified value with the given key in the map.  If the
     * key is already present in the map, the old value is replaced with the
     * new value.
     **/

    public void put(K key, V value) {
        int bucket = hashFunction(key);
        HashEntry current = hashTable[bucket];
        while (current != null) {
            if (current.key != null && current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }
        hashTable[bucket] = new HashEntry(key, value, hashTable[bucket]);
        size++;
    }

    // Returns true if there are no elements in this queue.
    public boolean isEmpty() {
        return size == 0;
    }

    //Returns the number of elements in this set (its cardinality)
    public int size() {
        return size;
    }

    /**
    public void remove(K key) {
        int index = indexOf(key);
        Entry<K, V> currentEntry = this.entries[index];

        while (currentEntry != null && currentEntry.next != null && !matches(key, currentEntry.next.key)) {
            currentEntry = currentEntry.next;
        }

        if (currentEntry != null) {
            // this case can only occur if there is only one non-null entry at the index
            if (matches(key, currentEntry.key)) {
                this.entries[index] = null;
                // this case can only occur because the next entry's key matched
            } else if (currentEntry.next != null) {
                currentEntry.next = currentEntry.next.next;
            }
            this.size--;
         }
    } **/

    // Removes the key and its associated value from the table.
    // If the table does not contain the key, has no effect.
    public void remove(K key) {
        int bucket = hashFunction(key);
        // if bucket not null
        if (hashTable[bucket] != null) {
            // check front of list
            if (hashTable[bucket].key.equals(key)) {
                hashTable[bucket] = hashTable[bucket].next;
                size--;
            } else {
                // check rest of list
                HashEntry current = hashTable[bucket];
                while (current.next != null && !current.next.key.equals(key)) {
                    current = current.next;
                }
                // if the element is found, remove it
                if (current.next != null && current.next.key.equals(key)) {
                    current.next = current.next.next;
                    size--;
                }
            }
        }
    }

    private int hashFunction(K key) { return Math.abs(key.hashCode() % hashTable.length); }

    private double loadFactor() {
        return (double) size / hashTable.length;
    }

    // Inner private class
    private class HashEntry {
        public K key;
        public V value;
        public HashEntry next;

        public HashEntry(K key, V value) {
            this(key, value, null);
        }

        public HashEntry(K key, V value, HashEntry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
