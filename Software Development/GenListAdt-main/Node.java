package cs1302.genlist;

/**
 * This class is one Node in the linkedGenList.
 */
public class Node<T> {
    private T stuff;
    private Node<T> next;

    /**
     * This is the default constructor which sets {@code stuff} and {@code next} to null.
     */
    public Node() {
        stuff = null;
        next = null;
    } //Node Default Constructor

    /**
     * This creates a node by setting {@code stuff} to newStuff with {@code next} to null.
     * @param newStuff this is what is getting stored in stuff.
     */
    public Node(T newStuff) {
        stuff = newStuff;
        next = null;
    } //Node newStuff

    /**
     * This creates a node by setting {@code stuff} to newStuff with {@code next} to nextNode.
     * @param newStuff this is what is getting stored in stuff.
     * @param nextNode this is describing the next node after the current one.
     */
    public Node(T newStuff, Node<T> nextNode) {
        stuff = newStuff;
        next = nextNode;
    } //Node stuff nextNode

    /**
     * Returns what the next node is.
     * @return next this is the next node.
     */
    public Node<T> getNext() {
        return next;
    } //getNext

    /**
     * This sets {@code next} to the specified Node.
     * @param nextNode This is describing the next node after the current one.
     */
    public void setNext(Node<T> nextNode) {
        next = nextNode;
    } //setNext

    /**
     * This returns the stuff in the current Node.
     * @return the stuff in the current Node.
     */
    public T getStuff() {
        return stuff;
    } //getStuff

    /**
     * Set's the current Node's stuff to newStuff.
     * @param newStuff stuff to be in the Node.
     */
    public void setStuff(T newStuff) {
        stuff = newStuff;
    } //setStuff
} //Node
