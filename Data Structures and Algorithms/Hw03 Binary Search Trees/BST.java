import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;
import java.util.NoSuchElementException;
/**
 * Your implementation of a BST.
 *
 * @author Sunny Patel
 * @version 1.0
 * @userid spatel725
 * @GTID 903466059
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     * This constructor should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize the BST with the data in the
     * Collection. The elements should be added to the BST in the order in 
     * which they appear in the Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for-loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public BST(Collection<T> data) {
        //Deals with invalid parameter
        if (data == null || data.contains(null)) {
            throw new IllegalArgumentException("Data or any element in data cannot be null!");
        }
        size = 0;
        //Uses add method to add data to the tree
        for (T item : data) {
            add(item);
        }
    }

    /**
     * Adds the data to the tree.
     *
     * The data becomes a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        //Deals with invalid parameter
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data into tree!");
        } else {
            addHelp(data, root);
        }
    }

    /**
     * This is the helper method for add(T data)
     *
     * @param data the data to add
     * @param root the root of the tree
     */
    private void addHelp(T data, BSTNode<T> root) {
        //If nothing in tree then data is first thing in tree
        //which is also now the root
        if (this.root == null) {
            this.root = new BSTNode<>(data);
            size++;
        } else {
            //If data is bigger than root then traverse right
            if (data.compareTo(root.getData()) > 0) {
                if (root.getRight() == null) {
                    root.setRight(new BSTNode<>(data));
                    size++;
                } else {
                    addHelp(data, root.getRight());
                }
            //If data is smaller than root then traverse left
            } else if (data.compareTo(root.getData()) < 0) {
                if (root.getLeft() == null) {
                    root.setLeft(new BSTNode<>(data));
                    size++;
                } else {
                    addHelp(data, root.getLeft());
                }
            }
        }
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the predecessor to
     * replace the data. You MUST use recursion to find and remove the
     * predecessor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        //Deals with invalid parameter
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null!");
        }
        BSTNode<T> removedItem = new BSTNode<>(null);
        root = removeHelper(data, root, removedItem);
        size--;
        return removedItem.getData();
    }

    /**
     * Helper method for remove(T data)
     *
     * @param data data to be removed from tree
     * @param curr current node
     * @param removedItem node to be removed
     * @return parent node of node to be removed
     */
    private BSTNode<T> removeHelper(T data, BSTNode<T> curr, BSTNode<T> removedItem) {
        //Deals with invalid parameter for current node
        if (curr == null) {
            throw new NoSuchElementException("Data is not in the tree!");
        }
        //If data is bigger than current traverse right
        if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(removeHelper(data, curr.getRight(), removedItem));
        //If data is smaller than current traverse left
        } else if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(removeHelper(data, curr.getLeft(), removedItem));
        } else {
            removedItem.setData(curr.getData());
            if (curr.getLeft() != null && curr.getRight() != null) {
                BSTNode<T> temp = new BSTNode<>(null);
                //This depends on how many children there are on the right and left
                //which makes recursion useful for traversing
                curr.setLeft(predecessorHelper(curr.getLeft(), temp));
                curr.setData(temp.getData());
            } else {
                if (curr.getLeft() != null) {
                    return curr.getLeft();
                } else {
                    return curr.getRight();
                }
            }
        }
        return curr;
    }

    /**
     * Helper method for remove(T data)
     * This helper method finds the predecessor node
     *
     * @param curr current node
     * @param child child of a node to be removed
     * @return predecessor node of a node to be removed
     */
    private BSTNode<T> predecessorHelper(BSTNode<T> curr, BSTNode<T> child) {
        if (curr.getRight() == null) {
            child.setData(curr.getData());
            return curr.getLeft();
        }
        curr.setRight(predecessorHelper(curr.getRight(), child));
        return curr;
    }
    /**
     * Returns the data from the tree matching the given parameter.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null!");
        }
        return getHelper(root, data);
    }

    /**
     *
     * @param root current root node to inspect
     * @param data the data to search for
     * @return data in tree equal to the parameter
     */
    private T getHelper(BSTNode<T> root, T data) {
        //Deals with invalid root parameter
        //Then proceeds with recursion
        if (root == null) {
            throw new NoSuchElementException("Data is not in the tree!");
        //If data is larger then go right
        } else if (data.compareTo(root.getData()) > 0) {
            return getHelper(root.getRight(), data);
        //If data is smaller then go left
        } else if (data.compareTo(root.getData()) < 0) {
            return getHelper(root.getLeft(), data);
        } else {
            return root.getData();
        }
    }
    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        //Deals with invalid parameter
        if (data == null) {
            throw new IllegalArgumentException("Data passed in is null!");
        } else {
            return containsHelper(data, root);
        }
    }

    /**
     *  Helper method for Contains(T data)
     *
     * @param data data to search for
     * @param curr current node to inspect
     * @return true if the parameter is contained within the tree, false
     * otherwise
     */
    private boolean containsHelper(T data, BSTNode<T> curr) {
        //If equals then data is in tree
        if (curr != null) {
            if (curr.getData().equals(data)) {
                return true;
            //If data is larger then go right
            } else if (data.compareTo(curr.getData()) > 0) {
                return containsHelper(data, curr.getRight());
            //If data is smaller then go left
            } else if (data.compareTo(curr.getData()) < 0) {
                return containsHelper(data, curr.getLeft());
            }
        }
        return false;
    }
    /**
     * Generate a pre-order traversal of the tree.
     *
     * Must be O(n).
     *
     * @return the preorder traversal of the tree
     */
    public List<T> preorder() {
        List<T> poList = new ArrayList<>();
        preorderHelper(root, poList);
        return poList;
    }

    /**
     * Helper method for preorder()
     *
     * @param curr current root of the tree
     * @param poList list that stores preorder traversal
     */
    private void preorderHelper(BSTNode<T> curr, List<T> poList) {
        if (curr == null) {
            return;
        } else {
            poList.add(curr.getData());
            preorderHelper(curr.getLeft(), poList);
            preorderHelper(curr.getRight(), poList);
        }
    }

    /**
     * Generate an in-order traversal of the tree.
     *
     * Must be O(n).
     *
     * @return the inorder traversal of the tree
     */
    public List<T> inorder() {
        List<T> ioList = new ArrayList<>();
        inorderHelper(ioList, root);
        return ioList;
    }

    /**
     *  Helper method for inorder()
     *
     * @param ioList list that stores inorder traversal
     * @param curr current root of the tree
     */
    private void inorderHelper(List<T> ioList, BSTNode<T> curr) {
        //Deals with invalid curr parameter
        if (curr == null) {
            return;
        } else {
            inorderHelper(ioList, curr.getLeft());
            ioList.add(curr.getData());
            inorderHelper(ioList, curr.getRight());
        }
    }

    /**
     * Generate a post-order traversal of the tree.
     *
     * Must be O(n).
     *
     * @return the postorder traversal of the tree
     */
    public List<T> postorder() {
        List<T> poList = new ArrayList<>();
        postorderHelper(poList, root);
        return poList;
    }

    /**
     * Helper method for postorder()
     *
     * @param list list that stores postorder traversal
     * @param curr current root of the tree
     */
    private void postorderHelper(List<T> list, BSTNode<T> curr) {
        //Deals with invalid curr parameter
        if (curr == null) {
            return;
        } else {
            postorderHelper(list, curr.getLeft());
            postorderHelper(list, curr.getRight());
            list.add(curr.getData());
        }
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * This does not need to be done recursively.
     *
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use. You may import java.util.Queue as well as an implmenting
     * class.
     *
     * Must be O(n).
     *
     * @return the level order traversal of the tree
     */
    public List<T> levelorder() {
        List<T> loList = new ArrayList();
        Queue<BSTNode<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BSTNode<T> temp = queue.poll();
            if (temp == null) {
                return loList;
            }
            loList.add(temp.getData());
            if (temp.getLeft() != null) {
                queue.add(temp.getLeft());
            }
            if (temp.getRight() != null) {
                queue.add(temp.getRight());
            }
        }
        return loList;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     *
     * Must be O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if (root == null) {
            return -1;
        } else {
            return heightHelper(root) - 1;
        }
    }

    /**
     *
     * @param curr current node to find the height from
     * @return height
     */
    private int heightHelper(BSTNode<T> curr) {
        if (curr == null) {
            return 0;
        } else {
            if (heightHelper(curr.getLeft()) > heightHelper(curr.getRight())) {
                return heightHelper(curr.getLeft()) + 1;
            } else {
                return heightHelper(curr.getRight()) + 1;
            }
        }
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Finds the path between two elements in the tree, specifically the path
     * from data1 to data2, inclusive of both.
     *
     * To do this, you must first find the deepest common ancestor of both data
     * and add it to the list. Then traverse to data1 while adding its ancestors
     * to the front of the list. Finally, traverse to data2 while adding its
     * ancestors to the back of the list. Please note that there is no
     * relationship between the data parameters in that they may not belong
     * to the same branch. You will most likely have to split off and
     * traverse the tree for each piece of data.
     * *
     * You may only use 1 list instance to complete this method. Think about
     * what type of list to use since you will have to add to the front and
     * back of the list.
     *
     * This method only needs to traverse to the deepest common ancestor once.
     * From that node, go to each data in one traversal each. Failure to do
     * so will result in a penalty.
     *
     * If both data1 and data2 are equal and in the tree, the list should be
     * of size 1 and contain the element from the tree equal to data1 and data2.
     *
     * Hint: How can we use the order property of the BST to locate the deepest
     * common ancestor?
     *
     * Ex:
     * Given the following BST composed of Integers
     *              50
     *          /        \
     *        25         75
     *      /    \
     *     12    37
     *    /  \    \
     *   11   15   40
     *  /
     * 10
     * findPathBetween(10, 40) should return the list [10, 11, 12, 25, 37, 40]
     * findPathBetween(50, 37) should return the list [50, 25, 37]
     * findPathBetween(75, 75) should return the list [75]
     *
     * Must be O(log n) for a balanced tree and O(n) for worst case.
     *
     * @param data1 the data to start the path from
     * @param data2 the data to end the path on
     * @return the unique path between the two elements
     * @throws java.lang.IllegalArgumentException if either data1 or data2 is
     *                                            null
     * @throws java.util.NoSuchElementException   if data1 or data2 is not in
     *                                            the tree
     */
    public List<T> findPathBetween(T data1, T data2) {
        LinkedList<T> list = new LinkedList<T>();
        //Deals with invalid parameters
        if (data1 == null || data2 == null) {
            throw new IllegalArgumentException("Cannot find path between null data.");
        } else if (data1 != null && data2 != null) {
            //Try catch deals with if data can't be found in tree after traversing tree
            try {
                return compareHelp(data1, data2, root, list);
            } catch (Exception e) {
                throw new java.util.NoSuchElementException("Data not in the tree");
            }
            //If data is null to begin with then there no path between null data
        } else {
            throw new java.util.NoSuchElementException("Data not in the tree.");
        }
    }

    /**
     * Helper method for findPathBetween(T data1, T data2)
     * Overall this method will try to find the ancestor
     *
     * @param data1 path start
     * @param data2 path end
     * @param curr current node
     * @param list path is in this list
     * @return path list
     */
    private LinkedList<T> compareHelp(T data1, T data2, BSTNode<T> curr, LinkedList<T> list) {
        //Uses compareTo to see how data points relate to current root
        int data1Com = data1.compareTo(curr.getData());
        int data2Com = data2.compareTo(curr.getData());
        //This deals with the case if root is between the start and end otherwise
        //all points will just be down one branch
        if (data1Com <= 0 && data2Com >= 0 || data1Com >= 0 && data2Com <= 0) {
            list.addFirst(curr.getData());
            compareStartHelp(data1, curr, list);
            compareEndHelp(data2, curr, list);
            return list;
        //This will be a branch on the right side if start and end are larger than root
        } else if (data1Com > 0 && data2Com > 0) {
            compareHelp(data1, data2, curr.getRight(), list);
            return list;
        } else {
            //This will be a branch on the left side if start and end are less than root
            compareHelp(data1, data2, curr.getLeft(), list);
            return list;
        }
    }

    /**
     * Helper method for findPathBetween(T data1, Tdata2)
     * Given the ancestor this method will traverse to data1 while adding
     * its ancestors to the front.
     *
     * @param data1 path start
     * @param curr current node
     * @param list path list
     */
    private void compareStartHelp(T data1, BSTNode<T> curr, LinkedList<T> list) {
        int data1Com = data1.compareTo(curr.getData());
        //If smaller traverse left
        if (data1Com < 0) {
            list.addFirst(curr.getLeft().getData());
            compareStartHelp(data1, curr.getLeft(), list);
        //If smaller traverse right
        } else if (data1Com > 0) {
            list.addFirst((curr.getRight().getData()));
            compareStartHelp(data1, curr.getRight(), list);
        //Essentially the base case do nothing
        } else {
            return;
        }
    }

    /**
     * Helper method for findPathBetween(T data1, T data2)
     * Given the ancestor this method will traverse to data2 while adding
     * its ancestors to the back.
     *
     * @param data2 path end
     * @param curr current node
     * @param list path list
     */
    private void compareEndHelp(T data2, BSTNode<T> curr, LinkedList<T> list) {
        int data2Com = data2.compareTo(curr.getData());
        //If larger traverse right
        if (data2Com > 0) {
            list.addLast(curr.getRight().getData());
            compareEndHelp(data2, curr.getRight(), list);
        //If smaller traverse left
        } else if (data2Com < 0) {
            list.addLast((curr.getLeft().getData()));
            compareEndHelp(data2, curr.getLeft(), list);
        //Essentially the base case do nothing
        } else {
            return;
        }
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
