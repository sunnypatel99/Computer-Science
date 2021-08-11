package cs1302.list;

import cs1302.listadt.StringList;

/**
This is the LinkedStringList class which implements the StringList interface.
*/

public class LinkedStringList implements StringList {

    StringList.Node linkedList;

    /**
     * This is the default constructor for LinkedStringList.
     */
    public LinkedStringList() {
        linkedList = new StringList.Node();
    } //LinkedStringList

    /**
     * This is the copy constructor.
     * @param other this is the StringList to be coppied.
     */

    public LinkedStringList(StringList other) {
        linkedList = new StringList.Node();
        StringList.Node head = linkedList;
        for (int i = 0; i < other.size(); i++) {
            String sl = other.get(i);
            head.setNext(new StringList.Node(sl, null));
            head = head.getNext();
        } //for
    } // LinkedStringList

    /**
     * This adds the specified string at the specified position.
     *
     * <p>
     * {@inheritDoc}
     */

    public boolean add(int index, String s) {
        boolean add = false;
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index out of range");
        } else if (s.equals("")) {
            throw new IllegalArgumentException("Empty String");
        } else if (s == null) {
            throw new NullPointerException("String is equal to null");
        } else { // This does the error checking
            if (index == 0) {
                StringList.Node front = new StringList.Node();
                StringList.Node temp = new StringList.Node(s, linkedList.getNext());
                front.setNext(temp);
                linkedList = front;
            } else if (index == size()) {
                add(s);
            } else {
                StringList.Node front = linkedList.getNext();
                for (int i = 0; i < index - 1; i++) {
                     front = front.getNext();
                 }
                 StringList.Node back = new StringList.Node(s, front.getNext());
                 front.setNext(back);
             } //else
             add = true;
         } //else
         return add;
     } // add

     /**
      * This adds the specified strings at the specified position.
      *
      * <p>
      * {@inheritDoc}
      */

     public boolean add(int index, StringList sl) {
         boolean add = false;
         if (index < 0 || index >= size()) {
             throw new IndexOutOfBoundsException("Index out of range");
         } else if (sl == null) {
             throw new NullPointerException("StringList is null");
         } else { // This does the error checking
             if (index == size()) {
                 add(sl);
             } else {
                 StringList.Node front = linkedList.getNext();
                 for (int i = 0; i < index - 1; i++) {
                     front = front.getNext();
                 } //for
                 StringList.Node back = front.getNext();
                 for (int i = 0; i < sl.size(); i++) {
                     front.setNext(new StringList.Node(sl.get(i)));
                     front = front.getNext();
                 } //for
                 front.setNext(back);
                 add = true;
             } //else
         } //else
         return add;
     } // add

     /**
      * This adds the specified string to the end of the list.
      *
      * <p>
      * {@inheritDoc}
      */

     public boolean add(String s) {
         boolean add = false;
         if (s == null) {
             throw new NullPointerException("String is equal to null");
         } else if (s.equals("")) {
             throw new IllegalArgumentException("Empty String");
         } //This is the error checking part.
         if ((s != null) && (!s.equals(""))) {
             StringList.Node head = linkedList;
             while (head.getNext() != null) {
                 head = head.getNext();
             } //while
             head.setNext(new StringList.Node(s, null));
             add = true;
         } //if
         return add;
     }

     /**
      * This adds the strings in the list to the end of the list.
      *
      * <p>
      * {@inheritDoc}
      */

     public boolean add(StringList sl) {
         boolean add = false;
         if (sl == null) {
             throw new NullPointerException("StringList is null");
         } else { // This does the error checking part.
             StringList.Node current = getNode(size());
             for (int i = 0; i < sl.size(); i++) {
                 current.setNext(new StringList.Node(sl.get(i), null));
                 current = current.getNext();
             } //for
             add = true;
         } //else
         return add;
     } // add

     /**
      * Removes all the strings in this list.
      *
      * <p>
      * <p>
      * {@inheritDoc}
      */

     public void clear() {

         StringList.Node newLinkedList = new StringList.Node(); //Completely clears linkedList
         linkedList = newLinkedList;
     } //clear

     /**
      * Returns true if this list had the specified string.
      *
      * <p>
      * {@inheritDoc}
      */

     public boolean contains(String o) {
         boolean contains = false;
         if (o.equals("")) {
             throw new IllegalArgumentException("Empty string");
         } else if (o == null) {
             throw new NullPointerException("String is equal to null");
         } else { //This is the error checking part.
             for (int i = 0; i < size(); i++) {
                 if (o.equals(get(i))) {
                     contains = true;
                 } //if
             } //for
         } //else
         return contains;
     } //contains

     /**
      * Returns true if this list had the specified string ignoring case.
      *
      * <p>
      * {@inheritDoc}
      */

     public boolean containsIgnoreCase(String o) {
         boolean contains = false;
         if (o.equals("")) {
             throw new IllegalArgumentException("Empty string");
         } else if (o == null) {
             throw new NullPointerException("String is equal to null");
         } else {
             for (int i = 0; i < size(); i++) {
                 if (o.equalsIgnoreCase(get(i))) {
                     contains = true;
                 }
             }
         }
         return contains;
     } // containsIgnoreCase

     /**
     * Returns true if the list contains the specified substring.
     *
     * <p>
     * {@inheritDoc}
     */

     public boolean containsSubstring(String o) {
         boolean contains = false;
         if (o.equals("")) {
             throw new IllegalArgumentException("Empty string");
         } else if (o == null) {
             throw new NullPointerException("String is equal to null");
         } else { //This does the error checking part.
             for (int i = 0; i < size(); i++) {
                 if (get(i).contains(o)) {
                     contains = true;
                     break;
                 } //if
             } //for
             return contains;
         } //else
     } //containsSubstring

     /**
     * Returns a new StringList without any duplicates.
     *
     * <p>
     * {@inheritDoc}
     */

     public StringList distinct() {
         StringList list1 = new LinkedStringList();
         StringList.Node head = linkedList.getNext();
         for (int i = 0; i < size(); i++) {
             if (list1.indexOf(head.getStr()) == -1) {
                 list1.add(head.getStr());
             } //if
             head = head.getNext();
         } //for
         return list1;
     } //distinct

     /**
     * Returns the string at the specified position in the list.
     *
     * <p>
     * {@inheritDoc}
     */

     public String get(int index) {
         String string = "";
         if (index < 0 || index >= size()) {
             throw new IndexOutOfBoundsException("Index out of range");
         } else { //This does the error checking.
             StringList.Node head = linkedList.getNext();
             for (int i = 0; i < index; i++) {
                 head = head.getNext();
             } //for
             string = head.getStr();
         } //else
         return string;
     } //get

     /**
     * Returns the index of the first occurrence of the specified string or -1 if it doesn't exist.
     *
     * <p>
     * {@inheritDoc}
     */

     public int indexOf(String s) {
         int index = -1;
         if (s.equals("")) {
             throw new IllegalArgumentException("Empty string");
         } else if (s == null) {
             throw new NullPointerException("String is equal to null");
         } else { //This does the error checking.
             for (int i = 0; i < size(); i++) {
                 if (s.equals(get(i))) {
                     index = i;
                 } //if
             } //for
         } //else
         return index;
     } //indexOf

     /**
     * Returns the index of the first occurrence of the specified string
     * ignoring case or -1 if it doesn't exist.
     *
     * <p>
     * {@inheritDoc}
     */

     public int indexOfIgnoreCase(String s) {
         int index = -1;
         if (s.equals("")) {
             throw new IllegalArgumentException("Empty string");
         } else if (s == null) {
             throw new NullPointerException("String is equal to null");
         } else { //This does the error checking.
             for (int i = 0; i < size(); i++) {
                 if (s.equalsIgnoreCase(get(i))) {
                     index = i;
                 } //if
             } //for
         } //else
         return index;
     } // containsIgnoreCase

     /**
     * Returns true if this list containes no elements.
     *
     * <p>
     * {@inheritDoc}
     */

     public boolean isEmpty() {
         return (size() == 0);
     } //isEmpty

     /**
     * Returns the list as a string with a seperator in between each one.
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
     * Removes the string at the specified position in this list.
     *
     * <p>
     * {@inheritDoc}
     */

     public String remove(int index) {
         String string = "";
         if (index < 0 || index >= size()) {
             throw new IndexOutOfBoundsException("Index out of range");
         } else {
             StringList.Node front = linkedList.getNext();
             if (index == 0) {
                 string = front.getStr();
                 linkedList = front;
             } else {
                 for (int i = 0; i < index - 1; i++) {
                     front = front.getNext();
                 } //for
                 string = front.getNext().getStr();
                 StringList.Node back = front.getNext().getNext();
                 front.setNext(back);
             } //else
         } //else
         return string;
     } // remove

     /**
     * Returns the strings from the list in reverse order.
     *
     * <p>
     * {@inheritDoc}
     */

     public StringList reverse() {
         StringList newLinkedList = new LinkedStringList();
         for (int i = size() - 1; i >= 0; i--) {
             newLinkedList.add(get(i));
         } //for
         return newLinkedList;
     } //reverse

     /**
     * Replaces the string at the location with a specified string.
     *
     * <p>
     * {@inheritDoc}
     */

     public String set(int index, String s) {
         String string = "";
         if (index < 0 || index >= size()) {
             throw new IndexOutOfBoundsException("Index out of range");
         } else if (s.equals("")) {
             throw new IllegalArgumentException("Empty String");
         } else if (s == null) {
             throw new NullPointerException("String is equal to null");
         } else {
             StringList.Node current = getNode(index + 1);
             string = current.getStr();
             current.setStr(s);
         } //else
         return string;
         // return null;
     } //set

     /**
     * Returns the number of elements in the list that aren't null.
     *
     * <p>
     * {@inheritDoc}
     */

     public int size() {
         int size = 0;
         StringList.Node head = linkedList.getNext();
         while (head != null) {
             size++;
             head = head.getNext();
         } //while
         return size;
     } // size

     /**
     * Builds and returns a new StringList that contains the strings from this list
     * between the specified fromIndex, inclusive, and toIndex, exclusive.
     *
     * <p>
     * {@inheritDoc}
     */

     public StringList splice(int fromIndex, int toIndex) {
         StringList newLinkedList = new LinkedStringList();
         if (fromIndex < 0 || toIndex > size() || fromIndex > toIndex) {
             throw new IndexOutOfBoundsException("Index out of range");
         } else {
             for (int i = fromIndex; i < toIndex; i++) {
                 newLinkedList.add(get(i));
             } //for
         } //else
         return newLinkedList;
     } // splice

     /**
     * Returns a new array containing all of the strings in proper sequence.
     *
     * <p>
     * {@inheritDoc}
     */

     public String[] toArray() {
         String[] array = new String[size()];
         for (int i = 0; i < size(); i++) {
             array[i] = get(i);
         }
         return array;
     }

     /**
      * Returns the index of the node in a linked list.
      *
      * @param index the index of the node.
      * @return a reference to the node.
      */
     private StringList.Node getNode(int index) {
         StringList.Node current;
         if (size() == 0) {
             current = linkedList;
         } else {
             current = linkedList.getNext();
             for (int i = 0; i < index - 1; i++) {
                 current = current.getNext();
             } //for
         }
         return current;
     } // getNode
 } // LinkedStringList

