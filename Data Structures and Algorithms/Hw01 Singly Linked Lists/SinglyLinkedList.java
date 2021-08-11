import java.util.NoSuchElementException;

/**
 * Your implementation of a non-circular SinglyLinkedList with a tail pointer.
 *
 * @author Sunny Patel
 * @version 1.0
 * @userid spatel725
 * @GTID 903466059
 */
public class SinglyLinkedList<T> {

    // Do not add new instance variables or modify existing ones.
    private SinglyLinkedListNode<T> head;
    private SinglyLinkedListNode<T> tail;
    private int size;

    // Do not add a constructor.

    /**
     * Adds the element to the specified index.
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index to add the new element
     * @param data  the data to add
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        // This if block deals with invalid parameters
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index must be greater than 0!");
        } else if (index > size) {
            throw new IndexOutOfBoundsException("Index is greater than size!");
        } else if (data == null) {
            throw new IllegalArgumentException("Data to be added is null!");
        } else {
            SinglyLinkedListNode<T> newNodeData = new SinglyLinkedListNode<T>(data);
            // If list is empty head and tail are the same and size is increased by 1
            if (isEmpty()) {
                head = newNodeData;
                tail = newNodeData;
                size++;
            // Index 0 means add to front
            } else if (index == 0) {
                addToFront(data);
            // Index == size means add to back
            } else if (index == size) {
                addToBack(data);
            } else {
                SinglyLinkedListNode<T> curr = head;
                // This gets the node to the left of the index
                for (int i = 1; i < index; i++) {
                    curr = curr.getNext();
                }
                // Below is data's next node which is also the node at the current index
                SinglyLinkedListNode<T> futureNextNode = curr.getNext();
                // Node left of index is set to point to data
                curr.setNext(newNodeData);
                // data is set to point to node which was previously at data's index
                newNodeData.setNext(futureNextNode);
                // Sets tail to data if data's next is null
                if (futureNextNode == null) {
                    tail = newNodeData;
                }
                // Data has been added to the list therefore the size increases
                size++;
            }
        }
    }

    /**
     * Adds the element to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        // Deals with invalid parameter
        if (data == null) {
            throw new IllegalArgumentException("Data to be added is null!");
        }
        // If empty, the data is the only thing in the list
        if (isEmpty()) {
            SinglyLinkedListNode<T> newNodeData = new SinglyLinkedListNode<T>(data);
            head = newNodeData;
            tail = newNodeData;
            size++;
        // Else head is now equal to data node
        } else {
            SinglyLinkedListNode<T> newHeadData = new SinglyLinkedListNode<T>(data, head);
            head = newHeadData;
            size++;
        }
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        // Deals with invalid parameter
        if (data == null) {
            throw new IllegalArgumentException("Data to be added is null!");
        } else {
            // If empty, data is now only thing in list so head and tail are equal
            if (isEmpty()) {
                SinglyLinkedListNode<T> newNodeData = new SinglyLinkedListNode<T>(data);
                head = newNodeData;
                tail = newNodeData;
                size++;
            // Else tail is now pointing to data node and tail is equal to new data node
            } else {
                SinglyLinkedListNode<T> newTailData = new SinglyLinkedListNode<T>(data);
                tail.setNext(newTailData);
                tail = newTailData;
                size++;
            }
        }
    }

    /**
     * Removes and returns the element at the specified index.
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other
     * cases.
     *
     * @param index the index of the element to remove
     * @return the data that was removed
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        // Deals with invalid parameter
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index must be greater than 0!");
        } else if (index >= size) {
            throw new IndexOutOfBoundsException("Index is greater than size!");
        } else if (isEmpty()) {
            throw new IndexOutOfBoundsException("List size is 0 therefore cannot remove!");
        } else {
            // 0 index means remove first element in list
            if (index == 0) {
                return removeFromFront();
            // This case would mean remove from the back
            } else if (index - 1 == size - 1) {
                removeFromBack();
            } else {
                SinglyLinkedListNode<T> curr = head;
                SinglyLinkedListNode<T> nodeToBeRemoved = null;
                SinglyLinkedListNode<T> nextNode = null;
                // Gets node before index
                for (int i = 0; i < index - 1; i++) {
                    curr = curr.getNext();
                }
                // Current node's next would be the one to be removed
                if (curr.getNext() != null) {
                    nodeToBeRemoved = curr.getNext();
                }
                // This gets node to be removed next
                if (nodeToBeRemoved != null) {
                    nextNode = nodeToBeRemoved.getNext();
                }
                // Sets node next before index to node after index
                curr.setNext(nextNode);
                // If node after index is null then tail is set to current node
                if (nextNode == null) {
                    tail = curr;
                }
                size--;
                return nodeToBeRemoved.getData();
            }
            return null;
        }
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        // Can't remove if list is empty
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot remove from empty list!");
        // List becomes empty if only thing in list is removed
        } else if (size == 1) {
            T headData = head.getData();
            head = null;
            tail = null;
            size--;
            return headData;
        // Head is now set to removed node's next
        } else {
            T headData = head.getData();
            if (head.getNext() != null) {
                SinglyLinkedListNode<T> newHead = head.getNext();
                head = newHead;
            }
            size--;
            return headData;
        }
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        // Can't remove from list if it is empty
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot remove from empty list!");
        // List becomes empty if only thing in list is removed
        } else if (size == 1) {
            T headData = head.getData();
            head = null;
            tail = null;
            size--;
            return headData;
        // Head and tail would be same if one item is removed from a list with 2 items
        } else if (size == 2) {
            T headData = tail.getData();
            head.setNext(null);
            tail = head;
            size--;
            return headData;
        // If last item is removed then item before last is set to null
        // Tail is now the 2nd to last item in list before removal
        } else {
            T headData = null;
            SinglyLinkedListNode<T> curr = head;
            for (int i = 0; i < size - 2; i++) {
                curr = curr.getNext();
            }
            headData = curr.getNext().getData();
            curr.setNext(null);
            tail = curr;
            size--;
            return headData;
        }
    }

    /**
     * Returns the element at the specified index.
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        // Deals with invalid parameter
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index must be greater than 0!");
        } else if (index >= size) {
            throw new IndexOutOfBoundsException("Index is greater than size!");
        } else {
            // Size being 1 or index being 0 can be retrieved using head of list
            if (size == 1 || index == 0) {
                return head.getData();
            }
            // Gets data at index
            SinglyLinkedListNode<T> curr = head;
            for (int i = 0; i < index; i++) {
                if (curr != null && curr.getNext() != null) {
                    curr = curr.getNext();
                }
            }
            if (curr != null) {
                return curr.getData();
            }
        }
        return null;
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return (size == 0 && head == null && tail == null);
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(n).
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        // Deals with invalid parameter
        if (data == null) {
            throw new IllegalArgumentException("Data to be removed is Null!");
        } else {
            // If list is empty then can't remove anything
            if (isEmpty()) {
                throw new NoSuchElementException("Data to be removed is not found in list!");
            // List is empty if only thing in list is removed
            } else if (size == 1) {
                if (head.getData().equals(data)) {
                    head = null;
                    tail = null;
                    size--;
                    return data;
                } else {
                    throw new NoSuchElementException("Data to be removed is not found in list!");
                }
            } else {
                SinglyLinkedListNode<T> curr = head;
                SinglyLinkedListNode<T> temp = null;
                int index = 0;
                // Find's last occurrence node and index of last occurrence
                // Then uses removeAtIndex() to remove last occurrence at index of last occurrence
                for (int i = 0; i < size; i++) {
                    if (curr.getData().equals(data)) {
                        temp = curr;
                        curr = curr.getNext();
                        index = i;
                    } else {
                        curr = curr.getNext();
                    }
                }
                // Deals with if data to be removed is not found in list
                if (temp == null) {
                    throw new NoSuchElementException("Data to be removed is not found in list!");
                } else {
                    return removeAtIndex(index);
                }
            }
        }
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return the array of length size holding all of the data (not the
     * nodes) in the list in the same order
     */
    public T[] toArray() {
        T[] arrayRep = (T[]) new Object[size];
        SinglyLinkedListNode<T> curr = head;
        for (int i = 0; i < size; i++) {
            arrayRep[i] = curr.getData();
            curr = curr.getNext();
        }
        return arrayRep;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public SinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the list
     */
    public SinglyLinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }
}
