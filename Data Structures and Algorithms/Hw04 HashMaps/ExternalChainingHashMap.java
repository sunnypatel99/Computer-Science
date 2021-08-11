import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Your implementation of a ExternalChainingHashMap.
 *
 * @author Sunny Patel
 * @version 1.0
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class ExternalChainingHashMap<K, V> {

    /*
     * The initial capacity of the ExternalChainingHashMap when created with the
     * default constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * The max load factor of the ExternalChainingHashMap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final double MAX_LOAD_FACTOR = 0.67;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private ExternalChainingMapEntry<K, V>[] table;
    private int size;

    /**
     * Constructs a new ExternalChainingHashMap.
     *
     * The backing array should have an initial capacity of INITIAL_CAPACITY.
     *
     * Use constructor chaining.
     */
    public ExternalChainingHashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Constructs a new ExternalChainingHashMap.
     *
     * The backing array should have an initial capacity of initialCapacity.
     *
     * You may assume initialCapacity will always be positive.
     *
     * @param initialCapacity the initial capacity of the backing array
     */
    public ExternalChainingHashMap(int initialCapacity) {
        table = new ExternalChainingMapEntry[initialCapacity];
        size = 0;
    }

    /**
     * Adds the given key-value pair to the map. If an entry in the map
     * already has this key, replace the entry's value with the new one
     * passed in.
     *
     * In the case of a collision, use external chaining as your resolution
     * strategy. Add new entries to the front of an existing chain, but don't
     * forget to check the entire chain for duplicate keys first.
     *
     * If you find a duplicate key, then replace the entry's value with the new
     * one passed in. When replacing the old value, replace it at that position
     * in the chain, not by creating a new entry and adding it to the front.
     *
     * Before actually adding any data to the HashMap, you should check to
     * see if the array would violate the max load factor if the data was
     * added. Resize if the load factor (LF) is greater than max LF (it is okay
     * if the load factor is equal to max LF). For example, let's say the
     * array is of length 5 and the current size is 3 (LF = 0.6). For this
     * example, assume that no elements are removed in between steps. If
     * another entry is attempted to be added, before doing anything else,
     * you should check whether (3 + 1) / 5 = 0.8 is larger than the max LF.
     * It is, so you would trigger a resize before you even attempt to add
     * the data or figure out if it's a duplicate. Be careful to consider the
     * differences between integer and double division when calculating load
     * factor.
     *
     * When regrowing, resize the length of the backing table to
     * (2 * old length) + 1. You must use the resizeBackingTable method to do so.
     *
     * Return null if the key was not already in the map. If it was in the map,
     * return the old value associated with it.
     *
     * @param key   the key to add
     * @param value the value to add
     * @return null if the key was not already in the map. If it was in the
     * map, return the old value associated with it
     * @throws java.lang.IllegalArgumentException if key or value is null
     */
    public V put(K key, V value) {
        //Throws exception if key or value is null
        if (key == null || value == null) {
            throw new IllegalArgumentException("Given key or value is null!");
        } //if
        //Checks to see if resize is needed before actually adding
        if ((size + 1.0) / table.length > MAX_LOAD_FACTOR) {
            resizeBackingTable(2 * table.length + 1);
        } //if
        //Compression function to find index
        int index = Math.abs(key.hashCode() % table.length);
        ExternalChainingMapEntry<K, V> item = new ExternalChainingMapEntry<>(key, value);
        ExternalChainingMapEntry<K, V> curr = table[index];
        //Looks through values at index
        //Cannot have same key so if same keys are found replace with value
        while (curr != null) {
            if (curr.getKey().equals(key)) {
                V val = curr.getValue();
                curr.setValue(value);
                return val;
            } //if
            curr = curr.getNext();
        } //while
        //New key value pair will be added at head
        //New items next is set to head
        //Head is then set to new item
        //Increment size since item is added
        item.setNext(table[index]);
        table[index] = item;
        size++;
        return null;
    } //put

    /**
     * Removes the entry with a matching key from the map.
     *
     * @param key the key to remove
     * @return the value associated with the key
     * @throws java.lang.IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException   if the key is not in the map
     */
    public V remove(K key) {
        //Throws exception if key is null
        if (key == null) {
            throw new IllegalArgumentException("Given key to remove is null!");
        } //if
        int index = Math.abs(key.hashCode() % table.length);
        ExternalChainingMapEntry<K, V> curr = table[index];
        //If the first item at this index is the same as the item to be removed
        //then head is set to item's next and item is returned.
        if (curr != null && curr.getKey().equals(key)) {
            V val = curr.getValue();
            table[index] = curr.getNext();
            size--;
            return val;
        //Else item's previous is set to item's next to skip over item to be deleted
        } else if (curr != null) {
            while (curr.getNext() != null) {
                if (curr.getNext().getKey().equals(key)) {
                    V val = curr.getNext().getValue();
                    curr.setNext(curr.getNext().getNext());
                    size--;
                    return val;
                } //if
                curr = curr.getNext();
            } //while
        } //else
        //If nothing happens then key was not in hashmap to begin with.
        throw new java.util.NoSuchElementException("Key is not in hashmap!");
    } //remove

    /**
     * Gets the value associated with the given key.
     *
     * @param key the key to search for in the map
     * @return the value associated with the given key
     * @throws java.lang.IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException   if the key is not in the map
     */
    public V get(K key) {
        //Throws exception if key is null
        if (key == null) {
            throw new IllegalArgumentException("Given key is null!");
        } //if
        int index = Math.abs(key.hashCode() % table.length);
        ExternalChainingMapEntry<K, V> curr = table[index];
        //Goes through items at calculated index
        //Throws exception if key not found
        while (curr != null) {
            if (curr.getKey().equals(key)) {
                return curr.getValue();
            } //if
            curr = curr.getNext();
        } //while
        throw new java.util.NoSuchElementException("Key is not in hashmap!");
    } //get

    /**
     * Returns whether or not the key is in the map.
     *
     * @param key the key to search for in the map
     * @return true if the key is contained within the map, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if key is null
     */
    public boolean containsKey(K key) {
        //This method is similar to the get except this one returns a boolean.
        //Throws exception if key is null
        if (key == null) {
            throw new IllegalArgumentException("Given key is null!");
        } //if
        int index = Math.abs(key.hashCode() % table.length);
        ExternalChainingMapEntry<K, V> curr = table[index];
        while (curr != null) {
            if (curr.getKey().equals(key)) {
                return true;
            } //if
            curr = curr.getNext();
        } //while
        return false;
    } //containsKey

    /**
     * Returns a Set view of the keys contained in this map.
     *
     * Use java.util.HashSet.
     *
     * @return the set of keys in this map
     */
    public Set<K> keySet() {
        Set<K> keys = new HashSet<K>(size);
        //Goes through all pairs in hashmap and gets keys
        for (int i = 0; i < table.length; i++) {
            ExternalChainingMapEntry<K, V> curr = table[i];
            while (curr != null) {
                keys.add(curr.getKey());
                curr = curr.getNext();
            } //while
        } //for
        return keys;
    } //keySet()

    /**
     * Returns a List view of the values contained in this map.
     *
     * Use java.util.ArrayList or java.util.LinkedList.
     *
     * You should iterate over the table in order of increasing index and add
     * entries to the List in the order in which they are traversed.
     *
     * @return list of values in this map
     */
    public List<V> values() {
        List<V> vals = new ArrayList<V>(size);
        //Goes through all pairs in hashmap and gets values
        for (int i = 0; i < table.length; i++) {
            ExternalChainingMapEntry<K, V> curr = table[i];
            while (curr != null) {
                vals.add(curr.getValue());
                curr = curr.getNext();
            } //while
        } //for
        return vals;
    } //values

    /**
     * Resize the backing table to length.
     *
     * Disregard the load factor for this method. So, if the passed in length is
     * smaller than the current capacity, and this new length causes the table's
     * load factor to exceed MAX_LOAD_FACTOR, you should still resize the table
     * to the specified length and leave it at that capacity.
     *
     * You should iterate over the old table in order of increasing index and
     * add entries to the new table in the order in which they are traversed.
     *
     * Since resizing the backing table is working with the non-duplicate
     * data already in the table, you shouldn't explicitly check for
     * duplicates.
     *
     * Hint: You cannot just simply copy the entries over to the new array.
     *
     * @param length new length of the backing table
     * @throws java.lang.IllegalArgumentException if length is less than the
     *                                            number of items in the hash
     *                                            map
     */
    public void resizeBackingTable(int length) {
        //throws exception if length is invalid
        if (length < size || length < 0) {
            throw new IllegalArgumentException("Length must be positive and greater than number of items in map!");
        } //if
        //Creates new table with new length
        ExternalChainingMapEntry<K, V>[] newTable = new ExternalChainingMapEntry[length];
        //Goes through each item in the old table and adds to proper index in newTable
        for (ExternalChainingMapEntry item : table) {
            ExternalChainingMapEntry<K, V> curr = item;
            while (curr != null) {
                int index = Math.abs(curr.getKey().hashCode() % newTable.length);
                newTable[index] = new ExternalChainingMapEntry<K, V>(curr.getKey(), curr.getValue(), newTable[index]);
                curr = curr.getNext();
            } //while
        } //for
        table = newTable;
    } //resizeBackingTable

    /**
     * Clears the map.
     *
     * Resets the table to a new array of the initial capacity and resets the
     * size.
     */
    public void clear() {
        table = new ExternalChainingMapEntry[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Returns the table of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the table of the map
     */
    public ExternalChainingMapEntry<K, V>[] getTable() {
        // DO NOT MODIFY THIS METHOD!
        return table;
    }

    /**
     * Returns the size of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the map
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
