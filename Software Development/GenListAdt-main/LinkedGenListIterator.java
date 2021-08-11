package cs1302.genlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is the Iterator for LinkedGenList objects.
 */

public class LinkedGenListIterator<T> implements Iterator<T> {
    private Node<T> node;

    /**
     * Default constructor which sets {@code node} to list the iterator will go through.
     *
     * @param newNode This is the list the iterator will go through.
     */
    public LinkedGenListIterator(Node<T> newNode) {
        node = newNode;
    } // LinkedGenListIterator default constructor

    /**
     * Returns true if there are more elements.
     *
     * <p>
     * {@inheritDoc}
     */

    public boolean hasNext() {
        if (node.getNext() == null) {
            return false;
        } else {
            return true;
        }
    } //hasNext

    /**
     * Returns the next element.
     *
     * <p>
     * {@inheritDoc}
     */
    public T next() {
        if (hasNext() == false) {
            throw new NoSuchElementException("There are no more elements");
        } else {
            T next = node.getNext().getStuff();
            node = node.getNext();
            return next;
        } //else
    } //next

} //LinkedGenListIterator


