/**
 * Your implementation of a LinkedStack. It should NOT be circular.
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
public class LinkedStack<T> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private LinkedNode<T> head;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the top of the stack.
     *
     * Must be O(1).
     *
     * @param data the data to add to the top of the stack
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void push(T data) {
        //Deals with invalid parameter
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data to top of stack!");
        }
        //Uses the constructor in LinkedNode
        LinkedNode<T> temp = new LinkedNode<>(data, head);
        //Head is equal to data at top since head is where pushing and popping happens
        head = temp;
        size++;
    }

    /**
     * Removes and returns the data from the top of the stack.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the top of the stack
     * @throws java.util.NoSuchElementException if the stack is empty
     */
    public T pop() {
        //Can't remove from empty stack so throws exception
        if (size == 0) {
            throw new java.util.NoSuchElementException("Cannot remove data from empty stack!");
        }
        //Head is set to either null or 2nd to last item due to LIFO structure
        T temp = peek();
        head = head.getNext();
        size--;
        return temp;
    }

    /**
     * Returns the data from the top of the stack without removing it.
     *
     * Must be O(1).
     *
     * @return the data from the top of the stack
     * @throws java.util.NoSuchElementException if the stack is empty
     */
    public T peek() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("The stack is empty therefore the top is nothing!");
        }
        return head.getData();
    }

    /**
     * Returns the head node of the stack.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the stack
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the size of the stack.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the stack
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
