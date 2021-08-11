/**
 * Your implementation of an ArrayQueue.
 *
 * @author SUNNY PATEL
 * @version 1.0
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class ArrayQueue<T> {

    /*
     * The initial capacity of the ArrayQueue.
     *
     * DO NOT MODIFY THIS VARIABLE.
     */
    public static final int INITIAL_CAPACITY = 9;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int front;
    private int size;

    /**
     * Constructs a new ArrayQueue with a backing array with capacity
     * INITIAL_CAPACITY.
     */
    public ArrayQueue() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        front = 0;
        size = 0;
    }

    /**
     * Adds the data to the back of the queue.
     * Remember that the queue MUST behave circularly.
     *
     * If sufficient space is not available in the backing array, resize it to
     * double the current length. When resizing, copy elements to the
     * beginning of the new array. Do not forget to reset front to index 0 of
     * the new array.
     *
     * Must be amortized O(1).
     *
     * @param data the data to add to the back of the queue
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void enqueue(T data) {
        //Deals with invalid parameter
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data to back of queue!");
        }
        //Resizes to double the length and implements circular nature of array to add at back of array
        //Mod formulas taken from recitation
        if (size == backingArray.length) {
            T[] tempArr = (T[]) new Object[backingArray.length * 2];
            for (int i = 0; i < backingArray.length; i++) {
                tempArr[i] = backingArray[(front + i) % backingArray.length];
            }
            backingArray = tempArr;
            front = 0;
        }
        backingArray[((size++) + front) % backingArray.length] = data;
    }

    /**
     * Removes and returns the data from the front of the queue.
     *
     * Do not shrink the backing array.
     *
     * Replace any spots that you dequeue from with null.
     *
     * If the queue becomes empty as a result of this call, do not reset
     * front to 0.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    public T dequeue() {
        //Can't remove from empty queue so throws exception
        if (size == 0) {
            throw new java.util.NoSuchElementException("Cannot remove from empty queue!");
        }
        //removing happens at front to achieve O(1)
        T temp = backingArray[front];
        backingArray[front] = null;
        size--;
        if (size == 0) {
            front = 0;
        } else {
            front = (front + 1) % backingArray.length;
        }
        return temp;
    }

    /**
     * Returns the data from the front of the queue without removing it.
     *
     * Must be O(1).
     *
     * @return the data located at the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    public T peek() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("Queue is empty therefore top is nothing!");
        }
        return backingArray[front];
    }

    /**
     * Returns the backing array of the queue.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the queue
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the queue.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the queue
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
