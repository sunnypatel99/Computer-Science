
package cs1302.genlist;

import java.util.function.*;
import cs1302.genlistadt.GenList;
import java.util.Iterator;
import java.util.Comparator;

/**
 * This is a generic linked list.
 */

public class LinkedGenList<T> implements GenList<T> {
    private Node<T> genList;

    /**
     * Creates an empty node as the first node.
     */

    public LinkedGenList() {
        genList = new Node<T>();
    } // LinkedGenList default constructor

    /**
     * This is the copy constructor.
     *
     * @param other This is the list that is to be copied.
     * @param <U> this is the type for the list to be copied.
     */

    public <U extends T> LinkedGenList(GenList<U> other) {
        genList = new Node<T>();
        Node<T> currentNode = genList; //Everything in other goes into genList.
        for (int i = 0; i < other.size(); i++) {
            T stuff = other.get(i);
            currentNode.setNext(new Node<T>(stuff, null));
            currentNode = currentNode.getNext();
        } //for
    } // LinkedGenList Copy Constructor

    /**
     * Adds everything in the specified list to the end of this list.
     *
     * <p>
     * {@inheritDoc}
     */

    public <U extends T> boolean add(GenList<U> list) {
        boolean add = false;
        if (list == null) {
            throw new NullPointerException("Specified List is null"); //error catching
        } // if
        if (this == list) {
            GenList<U> newList = new LinkedGenList<U>(list);
            add(newList); //self reference
        } else {
            Node<T> currentNode = getNode(size());
            for (int i = 0; i < list.size(); i++) { //adds to end of list
                currentNode.setNext(new Node<T>(list.get(i)));
                currentNode = currentNode.getNext();
            } // for
        } // else
        add = true;
        return add;
    } //add GenList<U> list

    /**
     * Adds everything in the specified list to specified index of this list.
     *
     * <p>
     * {@inheritDoc}
     */

    public <U extends T> boolean add(int index, GenList<U> list) {
        boolean add = false;
        if (list == null) {
            throw new NullPointerException("Specified List is null");
        } //error catching
        if (this == list) {
            GenList<U> newList = new LinkedGenList<U>(list);
            add(index, newList); //self reference
        } else {
            if (index == size()) {
                add(list); //adds to end
            } else {
                if (index == 0) { //if index is 0 add this way
                    Node<T> newList = new Node<T>();
                    for (int i = 0; i < list.size(); i++) {
                        if (i == 0) {
                            newList.setNext(new Node<T>(list.get(i)));
                        } else {
                             newList.getNext().setNext(new Node<T>(list.get(i)));
                         } //else
                     } //for
                     Node<T> newListHead = newList.getNext();
                     for (int i = 0; i < list.size() - 1; i++) {
                         newListHead = newListHead.getNext();
                     } //for
                     Node<T> zCurrent = genList.getNext();
                     for (int i = 0; i < size(); i++) {
                         if (i == 0) {
                             newListHead.setNext(new Node<T>(zCurrent.getStuff()));
                         } else {
                             newListHead.getNext().setNext(new Node<T>(zCurrent.getStuff()));
                             newListHead = newListHead.getNext();
                         } //else
                         zCurrent = zCurrent.getNext();
                     } //for
                     genList = newList;
                 } else { //else for index > 0 do it this way
                     Node<T> front = genList.getNext();
                     for (int i = 0; i < index - 1; i++) {
                         front = front.getNext();
                     } //for
                     Node<T> back = front.getNext();
                     for (int i = 0; i < list.size(); i++) {
                         front.setNext(new Node<T>(list.get(i)));
                         front = front.getNext();
                     } //for
                     front.setNext(back);
                 } //else
             } //else
             add = true;
         }
         return add;
     } //add

      /**
      * Adds specified object to specified index of this list.
      *
      * <p>
      * {@inheritDoc}
      */

     public boolean add(int index, T obj) {
         boolean add = false;
         if (obj == null) {
             throw new NullPointerException("Specified object is null");
         } else if (index < 0 || index > size()) {
             throw new IndexOutOfBoundsException("Index out of range");
         } //error catching
         if (index == 0) {
             Node<T> front = new Node<T>();
             Node<T> temp = new Node<T>(obj, genList.getNext());
             front.setNext(temp);
             genList = front;
         } else if (index == size()) {
             add(obj); //adds to end
         } else {
             Node<T> front = genList.getNext();
             for (int i = 0; i < index - 1; i++) {
                 front = front.getNext();
             } //for
             Node<T> back = new Node<T>(obj, front.getNext());
             front.setNext(back);
         } //else
         add = true;
         return add;
     } // add index obj

     /**
      * Adds specified object to end of this list.
      *
      * <p>
      * {@inheritDoc}
      */

     public boolean add(T obj) {
         boolean add = false;
         if (obj == null) {
             throw new NullPointerException("Specified Object is null");
         } // if and also error catching
         Node<T> currentNode = genList;
         while (currentNode.getNext() != null) { //adds to end
             currentNode = currentNode.getNext();
         } // while
         currentNode.setNext(new Node<T>(obj));
         add = true;
         return add;
     } //add

     /**
      * Determines if all elements pass test in the predicate.
      *
      * <p>
      * {@inheritDoc}
      */

     public boolean allMatch(Predicate<T> p) {
         boolean allMatch = true;
         if (p == null) {
             throw new NullPointerException("Specified Predicate is null"); //error catching
         } else {
             for (T val : this) {
                 if (p.test(val) == false) { //does the test
                     allMatch = false;
                 } // if
             } //for
         } //else
         return allMatch;
     } // allMatch

     /**
      * Determines if any elements pass test in the predicate.
      *
      * <p>
      * {@inheritDoc}
      */

     public boolean anyMatch(Predicate<T> p) {
         boolean anyMatch = false;
         if (p == null) {
             throw new NullPointerException("Specified Predicate is null"); //error catching
         } else {
             for (T val : this) {
                 if (p.test(val)) { //does the test
                     anyMatch = true;
                 } // if
             } //for
         } //else
         return anyMatch;
     } //anyMatch

     /**
      * Clears the list of all elements.
      *
      * <p>
      * {@inheritDoc}
      */

     public void clear() {
         Node<T> newList = new Node<T>();
         genList = newList;
     } //clear

     /**
      * Determines if object is in list.
      *
      * <p>
      * {@inheritDoc}
      */

     public boolean contains(T o) {
         boolean contains = false;
         if (o == null) {
             throw new NullPointerException("Specified Object is null");
         } //error catching
         for (T val : this) {
             if (o.equals(val)) { //searches for specified object
                 contains = true;
             } //if
         } //for
         return contains;
     } //contains

     /**
      * Returns an array of no duplicates.
      *
      * <p>
      * {@inheritDoc}
      */

     public GenList<T> distinct() {
         GenList<T> newList = new LinkedGenList<T>();
         Node<T> currentNode = genList.getNext();
         for (int i = 0; i < size(); i++) { //goes through list in search of duplicates.
             if (newList.indexOf(currentNode.getStuff()) == -1) {
                 newList.add(currentNode.getStuff());
             } //if
             currentNode = currentNode.getNext();
         } //for
         return newList;
     } //distinct

     /**
      * Determines if each element passes the test in the predicate.
      *
      * <p>
      * {@inheritDoc}
      */

     public GenList<T> filter(Predicate<T> p) {
         if (p == null) {
             throw new NullPointerException("Specified Predicate is null");
         } //error catching
         GenList<T> newList = new LinkedGenList<T>();
         for (T val : this) { //goes through array testing each element.
             if (p.test(val)) {
                 newList.add(val);
             } //if
         } //fpr
         return newList;
     } //filter

     /**
      * Gets the specified element.
      *
      * <p>
      * {@inheritDoc}
      */

     public T get(int index) {
         T stuff = null;
         if (index < 0 || index >= size()) {
             throw new IndexOutOfBoundsException("Index out of range");
         } //error catching
         Node<T> currentNode = genList.getNext();
         for (int i = 0; i < index; i++) {
             currentNode = currentNode.getNext();
         } //goes through the array to get the element
         stuff = currentNode.getStuff();
         return stuff;
     } //get

     /**
      * This gets the specified Node.
      *
      * @param index This is the specified index.
      * @return returns the specified node.
      */

     public Node<T> getNode(int index) {
         Node<T> currentStuff;
         if (size() == 0) {
             currentStuff = genList;
         } else {
             currentStuff = genList.getNext();
             for (int i = 0; i < index - 1; i++) {
                 currentStuff = currentStuff.getNext();
             } //for
         } //else
         return currentStuff;
     } //getNode

     /**
      * Gets the index of the specified object if it is in the list.
      *
      * <p>
      * {@inheritDoc}
      */

     public int indexOf(T obj) {
         if (obj == null) {
             throw new NullPointerException("Specified Object is null");
         } //error catching
         for (int i = 0; i < size(); i++) {
             if (obj.equals(get(i))) {
                 return i;
             } //if
         } //for
         return -1; //-1 is if it doesn't exist
     } //indexOf

     /**
      * Determines if the list is empty.
      *
      * <p>
      * {@inheritDoc}
      */

     public boolean isEmpty() {
         if (size() == 0) {
             return true;
         } else {
             return false;
         } //else
     } //isEmpty

     /**
      * Iterates through the list.
      *
      * <p>
      * {@inheritDoc}
      */

     public Iterator<T> iterator() {
         Iterator<T> iterator = new LinkedGenListIterator<T>(genList);
         return iterator;
     } //iterator

     /**
      * Makes a string version of the list.
      *
      * <p>
      * {@inheritDoc}
      */

     public String makeString(String sep) {
         String string = "";
         if (size() != 0) {
             for (int i = 0; i < size() - 1; i++) {
                 string = string + get(i) + sep;
             } //for
             string = string + get(size() - 1);
         } //if
         return string;
     } //makeString

     /**
      * Returns a list with each element transformed a certain way.
      *
      * <p>
      * {@inheritDoc}
      */

     public <R> GenList<R> map(Function<T, R> f) {
         if (f == null) {
             throw new NullPointerException("Specified Function is null");
         } //error catching
         GenList<R> newList = new LinkedGenList<R>();
         for (T val : this) { //this goes through the list
             if (f.apply(val) == null) {
                 throw new NullPointerException("Result of the function is null");
             } //if
             newList.add(f.apply(val));
         } //for
         return newList;
     } //map

     /**
      * Returns the max value.
      *
      * <p>
      * {@inheritDoc}
      */

     public T max(Comparator<T> c) {
         if (c == null) {
             throw new NullPointerException("Specified Comparator is null");
         } //error catching
         T max = get(0);
         for (T val : this) {
             if (c.compare(max, val) < 0) {
                 max = val;
             } //if
         } //for
         return max;
     } //max

     /**
      * Returns the min value.
      *
      * <p>
      * {@inheritDoc}
      */

     public T min(Comparator<T> c) {
         if (c == null) {
             throw new NullPointerException("Specified Comparator is null");
         } //error catching
         T min = get(0);
         for (T val : this) {
             if (c.compare(min, val) > 0) {
                 min = val;
             } //if
         } //for
         return min;
     } //min

     /**
      * Applys some operations multiple times.
      *
      * <p>
      * {@inheritDoc}
      */

     public T reduce(T start, BinaryOperator<T> f) {
         if (f == null) {
             throw new NullPointerException("Specified Function is null");
         } //if
         T result = start;
         if (result == null) {
             throw new NullPointerException("Result of the function is null");
         } //if
         for (T val : this) { //goes through the list
             result = f.apply(result, val);
             if (result == null) {
                 throw new NullPointerException("Result of the function is null");
             } //if
         } //for
         return result;
     } //reduce

     /**
      * Removes the object at the specified index.
      *
      * <p>
      * {@inheritDoc}
      */

     public T remove(int index) {
         T stuff = null;
         if (index < 0 || index >= size()) {
             throw new IndexOutOfBoundsException("Index out of range");
         } //error catching
         Node<T> front = genList.getNext();
         if (index == 0) {
             stuff = front.getStuff();
             genList = front;
         } else {
             for (int i = 0; i < index - 1; i++) {
                 front = front.getNext();
             } //for
             stuff = front.getNext().getStuff();
             Node<T> back = front.getNext().getNext();
             front.setNext(back);
         } //else
         return stuff;
     } //remove

     /**
      * This reverses the list.
      *
      * <p>
      * {@inheritDoc}
      */

     public GenList<T> reverse() {
         GenList<T> newList = new LinkedGenList<T>();
         for (int i = size() - 1; i >= 0; i--) {
             newList.add(get(i));
         } //for
         return newList;
     } //reverse

     /**
      * This sets the specified index to the specified object.
      *
      * <p>
      * {@inheritDoc}
      */

     public T set(int index, T obj) {
         T stuff = null;
         if (obj == null) {
             throw new NullPointerException("Specified Object is null");
         } else if (index < 0 || index >= size()) {
             throw new IndexOutOfBoundsException("Index out of range");
         } //error catching
         Node<T> currentNode = getNode(index + 1);
         stuff = currentNode.getStuff();
         currentNode.setStuff(obj);
         return stuff;
     } //set

     /**
      * This determines the size.
      *
      * <p>
      * {@inheritDoc}
      */

     public int size() {
         int size = 0;
         Node<T> currentNode = genList.getNext();
         while (currentNode != null) {
             size++;
             if (size > Integer.MAX_VALUE) {
                 return Integer.MAX_VALUE;
             } //This probably won't happen but just in case.
             currentNode = currentNode.getNext();
         } //while
         return size;
     } //size

     /**
      * This splits the list from a start to end index.
      *
      * <p>
      * {@inheritDoc}
      */

     public GenList<T> splice(int fromIndex, int toIndex) {
         GenList<T> newList = new LinkedGenList<T>();
         if (fromIndex < 0 || toIndex > size() || fromIndex > toIndex) {
             throw new IndexOutOfBoundsException("Index out of range");
         } //error catching
         for (int i = fromIndex; i < toIndex; i++) {
             newList.add(get(i));
         } //for
         return newList;
     } //splice

      /**
      * This returns the array.
      *
      * <p>
      * {@inheritDoc}
      */

     public T[] toArray(IntFunction<T[]> gen) {
         T[] array = gen.apply(size());
         for (int i = 0; i < size(); i++) {
             array[i] = get(i);
         } //for
         return array;
     } //toArray

 } // LinkedGenList

