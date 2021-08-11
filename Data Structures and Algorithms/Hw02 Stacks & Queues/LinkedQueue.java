/**
 * Your implementation of a LinkedQueue. It should NOT be circular.
 *
 * @author SUNNY PATEL
 * @version 1.0
 * @userid spatel725
 * @GTID 903466059
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class LinkedQueue<T> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the back of the queue.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the queue
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void enqueue(T data) {
        //Deals with invalid parameter
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data to back of queue!");
        }
        //To achieve O(1) we add at tail
        if (size == 0) {
            head = new LinkedNode<>(data);
            tail = head;
        } else {
            LinkedNode<T> newTail = new LinkedNode<>(data);
            tail.setNext(newTail);
            tail = newTail;
        }
        size++;
    }

    /**
     * Removes and returns the data from the front of the queue.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    public T dequeue() {
        //Cannot remove anything from empty queue so throws exception
        if (size == 0) {
            throw new java.util.NoSuchElementException("Cannot remove from empty queue!");
        }
        //To achieve O(1) we remove at head
        T item = head.getData();
        size--;
        if (size == 0) {
            head = null;
            tail = null;
        } else {
            LinkedNode<T> newHead = head.getNext();
            head.setNext(null);
            head = newHead;
        }
        return item;
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
        return head.getData();
    }

    /**
     * Returns the head node of the queue.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the queue
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }

    /**
     * Returns the tail node of the queue.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the queue
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
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
