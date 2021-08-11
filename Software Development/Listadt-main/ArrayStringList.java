 package cs1302.list;

 import cs1302.listadt.StringList;

 /**
 This is the ArrayStringList class which implements the StringList interface.
 */

 public class ArrayStringList implements StringList {

     private String [] array; //This is the main array for this class to which everything is done

     /**
     * This is the default constructor for arrays.
     */

     public ArrayStringList() {
         array = new String[5];
     } //ArrayStringList Constructor

     /**
     * This is the copy constructor.
     * @param other this is the array to be coppied.
     */

     public ArrayStringList(StringList other) {
         array = new String[other.size()];
         for (int i = 0; i < array.length; i++) {
             array[i] = other.get(i);
         }
     } //ArrayStringList Copy Constructor

     /**
     * This adds the specified string at the specified position.
     *
     * <p>
     * {@inheritDoc}
     */

     public boolean add(int index, String s) {
         boolean add = false;
         if (index < 0 || index > array.length) {
             throw new IndexOutOfBoundsException("Index out of range");
         } else if (s.equals("")) {
             throw new IllegalArgumentException("Empty String");
         } else if (s == null) {
             throw new NullPointerException("String is equal to null");
         } else { //This checks for exceptions above.
             int size;
             if (size() != 0) {
                 size = size();
             } else {
                 size = 1;
             } //This is for initial size when you run this for the very first time.
             String [] newArray = new String[size + 2];
             int counter = 0;
             for (int i = 0; i < index; i++) {
                 if (array[i] != null) {
                     newArray[i] = array[i];
                     counter++;
                 } //if
             } //for
             newArray[counter] = s;
             for (int i = counter + 1; i < array.length; i++) {
                 if (array[i] != null) {
                     newArray[i] = array[i];
                 } //if
             } //for
             int counter2 = 0;
             for (int i = 0; i < newArray.length; i++) {
                 if (newArray[i] != null) {
                     counter2++;
                 }
             }
             String[]  finalArray = new String[counter2];
             for (int i = 0; i < finalArray.length; i++) {
                 finalArray[i] = newArray[i];
             }
             array = finalArray; //Sets main array to new array.
             add = true;
         } //else
         return add;
     } //add

     /**
     * This adds the specified strings at the specified position.
     *
     * <p>
     * {@inheritDoc}
     */

     public boolean add(int index, StringList sl) {
         boolean add = false;
         if (index < 0 || index >= array.length) {
             throw new IndexOutOfBoundsException("Index out of range");
         } else if (sl == null) {
             throw new NullPointerException("StringList is null");
         } else { //This is the error checking part for exceptions.
             String [] newArray = new String[sl.size() + size()];
             int counter = 0;
             for (int i = 0; i < index; i++) {
                 newArray[i] = array[i];
                 counter++;
             } //for

             for (int i = 0; i < sl.size(); i++) {
                 newArray[counter] = sl.get(i);
                 counter ++;
             } //for

             for (int i = index; i < size(); i++) {
                 newArray[counter] = array[i];
                 counter ++;
             } //for

             array = newArray; //Sets main array to new array.
             add = true;
         } //else
         return add;
     } //add

     /**
     * This adds the specified string to the end of the list.
     *
     * <p>
     * {@inheritDoc}
     */

     public boolean add(String s) {
         add(size(), s);
         return true;
     } //add

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
         } else { //This is the error checking part for exceptions.
             String[] list1 = new String[this.size() + sl.size()];
             int i = 0;
             for (int n = 0; n < size(); n++) {
                 list1[n] = array[n];
                 i++;
             }
             for (int n = 0; n < sl.size(); n++) {
                 list1[i] = sl.get(n);
                 i++;
             }
             array = list1; //This sets main array to modified array.
             add = true;
         }
         return add;
     } //add

     /**
     * Removes all the strings in this list.
     *
     * <p>
     * {@inheritDoc}
     */

     public void clear() {
         String [] newArray = new String[5]; //Completely clears array and sets it to initial length
         array = newArray;
     } //clear

     /**
     * Returns true is this list has the specified string.
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
         } else { //Error checking part.
             for (int i = 0; i < size(); i++) {
                 if (array[i].equals(o)) {
                     contains = true;
                 } //if
             } //for
         } //else
         return contains;
     } //contains

     /**
     * Returns true if the list has the specified string ignoring case.
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
         } else { //Error checking part.
             for (int i = 0; i < size(); i++) {
                 if (array[i].equalsIgnoreCase(o)) {
                     contains = true;
                 } //if
             } //for
         } //else
         return contains;
     } //containsIgnoreCase

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
         } else { //Error checking part.
             for (int i = 0; i < size(); i++) {
                 if (array[i].contains(o)) {
                     contains = true;
                 } //if
             } //for
         } //else
         return contains;
     } //containsSubstring

     /**
     * Returns a new StringList without any duplicates.
     *
     * <p>
     * {@inheritDoc}
     */

     public StringList distinct() {
         StringList newArray = new ArrayStringList();
         for (int i = 0; i < size(); i++) {
             if (newArray.indexOf(array[i]) == -1) {
                 newArray.add(array[i]);
             } //if
         } //for
         return newArray;
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
         } else { //Error checking part.
             string = array[index];
         }
         return string;
     } //get

     /**
     * Returns the index of the first occurrence of the specified string or -1 if it doesn't exist.
     *
     * <p>
     * {@inheritDoc}
     */

     public int indexOf(String s) {
         if (s.equals("")) {
             throw new IllegalArgumentException("Empty string");
         } else if (s == null) {
             throw new NullPointerException("String is equal to null");
         } else { //Error checking part.
             int index = 0;
             for (int i = 0; i < size(); i++) {
                 if (s.equals(array[i])) {
                     return index;
                 } //if
                 index++;
             } //for
         } //else
         return -1;
     } //indexOf

     /**
     * Returns the index of the first occurrence of the specified string
     * ignoring case or -1 if it doesn't exist.
     *
     * <p>
     * {@inheritDoc}
     */

     public int indexOfIgnoreCase(String s) {
         if (s.equals("")) {
             throw new IllegalArgumentException("Empty string");
         } else if (s == null) {
             throw new NullPointerException("String is equal to null");
         } else { //Error checking part.
             int index = 0;
             for (int i = 0; i < size(); i++) {
                 if (s.equalsIgnoreCase(array[i])) {
                     return index;
                 } //if
                 index++;
             } //for
         } //else
         return -1;

     } //indexOfIgnoreCase

     /**
     * Returns true if this list containes no elements.
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
     * Returns the list as a string with a seperator in between each one.
     *
     * <p>
     * {@inheritDoc}
     */

     public String makeString(String sep) {
         String makeString = "";
         if ( size() != 0) {
             for (int i = 0; i < size() - 1; i++) {
                 makeString = makeString + array[i] + sep;
             }
             makeString += array[array.length - 1];
         }
         return makeString;
     } //makeString

     /**
     * Removes the string at the specified position in this list.
     *
     * <p>
     * {@inheritDoc}
     */

     public String remove(int index) {
         String remove = "";
         if (index < 0 || index >= size()) {
             throw new IndexOutOfBoundsException("Index out of range");
         } else { //Error checking part.
             remove = array[index];
             String[] newArray = new String[size() - 1];
             for (int i = 0; i < index; i++) {
                 newArray[i] = array[i];
             } //for
             for (int i = index + 1; i < array.length; i++) {
                 newArray[i - 1] = array[i];
             } //for
             array = newArray;
         } //else
         return remove;
     } //remove

     /**
     * Returns the strings from the list in reverse order.
     *
     * <p>
     * {@inheritDoc}
     */

     public StringList reverse() {
         StringList newArray = new ArrayStringList();
         for (int i = size() - 1; i >= 0; i--) {
             newArray.add(array[i]);
         }
         return newArray;
     } //reverse

     /**
     * Replaces the string at the location with a specified string.
     *
     * <p>
     * {@inheritDoc}
     */

     public String set(int index, String s) {
         String set = "";
         if (index < 0 || index >= size()) {
             throw new IndexOutOfBoundsException("Index out of range");
         } else if (s.equals("")) {
             throw new IllegalArgumentException("Empty String");
         } else if (s == null) {
             throw new NullPointerException("String is equal to null");
         } else { //Error checking part.
             set = array[index];
             array[index] = s;
         } //else
         return set;
     } //set

     /**
     * Returns the number of elements in the list that aren't null.
     *
     * <p>
     * {@inheritDoc}
     */

     public int size() {
         int counter = 0;
         for (int i = 0; i < array.length; i++) {
             if (array[i] != null) {
                 counter++;
             }
         }
         return counter;
     } //size

     /**
     * Builds and returns a new StringList that contains the strings from this list
     * between the specified fromIndex, inclusive, and toIndex, exclusive.
     *
     * <p>
     * {@inheritDoc}
     */

     public StringList splice(int fromIndex, int toIndex) {
         StringList newArray = new ArrayStringList();
         if (fromIndex < 0 || toIndex > size() || fromIndex > toIndex) {
             throw new IndexOutOfBoundsException("Index out of range");
         } else {
             for (int i = fromIndex; i < toIndex; i++) {
                 newArray.add(array[i]);
             } //for
         } //else
         return newArray;
     } //splice

     /**
     * Returns a new array containing all of the strings in proper sequence.
     *
     * <p>
     * {@inheritDoc}
     */

     public String[] toArray() {
         String[] toArray = new String[size()];
         for (int i = 0; i < array.length; i++) {
             if (array[i] != null) {
                 toArray[i] = array[i];
             }
         } //for
         return toArray;
     } //toArray

 } //ArrayStringList

