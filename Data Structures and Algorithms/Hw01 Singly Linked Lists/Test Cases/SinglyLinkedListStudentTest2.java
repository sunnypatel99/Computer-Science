import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * JUnit Test class to test the HW01 SinglyLinkedList file!
 * This is gonna be used to further test our SinglyLinkedList.java file for ANY edge cases we missed!
 * @author Menelik Gebremariam
 * @version 1.0
 */
public class SinglyLinkedListStudentTest2 {
    private SinglyLinkedList<Integer> list;
    private static final int TIMEOUT = 300;
    // Adding to empty list, removal from list with one element. CHECK THOSE!

    // When doing homeworks.
    // FOCUS ON EFFICIENCY
    @Before
    public void setUp() {
        list = new SinglyLinkedList<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, list.size());
        assertNull(list.getHead());
        assertNull(list.getTail());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexEmpty() {
        list.addAtIndex(0, 5);
        assertSame(list.getHead(), list.getTail()); // checks edge case of adding one to linked list with tail.
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex() {
        list.addAtIndex(0, 1);
        list.addAtIndex(1, 2);
        list.addAtIndex(0, 3);
        list.addAtIndex(2, 4);
        list.addAtIndex(list.size() - 1, 5);
        list.addAtIndex(list.size() - 2, 6);
        //should be [3, 1, 4, 6, 5, 2]

        assertEquals(6, list.size());
        // First entry check
        SinglyLinkedListNode<Integer> cur = list.getHead();
        assertNotNull(cur);
        assertNotNull(cur.getNext());
        Integer val = 3;
        assertEquals(val, cur.getData());
        // Second entry check
        cur = cur.getNext();
        assertNotNull(cur);
        assertNotNull(cur.getNext());
        val = 1;
        assertEquals(val, cur.getData());
        // Third entry check
        cur = cur.getNext();
        assertNotNull(cur);
        assertNotNull(cur.getNext());
        val = 4;
        assertEquals(val, cur.getData());
        // Fourth entry check
        cur = cur.getNext();
        assertNotNull(cur);
        assertNotNull(cur.getNext());
        val = 6;
        assertEquals(val, cur.getData());
        // Fifth entry check
        cur = cur.getNext();
        assertNotNull(cur);
        assertNotNull(cur.getNext());
        val = 5;
        assertEquals(val, cur.getData());
        // Sixth entry check
        cur = cur.getNext();
        assertNotNull(cur);
        assertNull(cur.getNext());
        val = 2;
        assertEquals(val, cur.getData());
        assertSame(list.getTail(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFront() {
        SinglyLinkedListNode<Integer> head = list.getHead();
        SinglyLinkedListNode<Integer> tail = list.getTail();
        // Tests to make sure that when list is empty, both head and tail are null.
        assertNull(head);
        assertNull(tail);

        // Block below tests to make sure head and tail are properly updated when adding to front of list.
        list.addToFront(10);
        head = list.getHead();
        tail = list.getTail();
        assertSame(list.getHead(), head);
        assertSame(list.getTail(), tail);
        assertSame(head, tail);
        list.addToFront(20);
        head = list.getHead();
        tail = list.getTail();
        assertSame(list.getHead(), head);
        assertSame(list.getTail(), tail);
        list.addToFront(30);
        head = list.getHead();
        tail = list.getTail();
        assertSame(list.getHead(), head);
        assertSame(list.getTail(), tail);
        list.addToFront(40);
        head = list.getHead();
        tail = list.getTail();
        assertSame(list.getHead(), head);
        assertSame(list.getTail(), tail);
        list.addToFront(50);
        head = list.getHead();
        tail = list.getTail();
        assertSame(list.getHead(), head);
        assertSame(list.getTail(), tail);
        list.addToFront(60);
        head = list.getHead();
        tail = list.getTail();
        assertSame(list.getHead(), head);
        assertSame(list.getTail(), tail);
        list.addToFront(70);
        head = list.getHead();
        tail = list.getTail();
        assertSame(list.getHead(), head);
        assertSame(list.getTail(), tail);

        assertEquals(7, list.size());
        // End of block
        // List is [70, 60, 50, 40, 30, 20, 10]

        // Block checks that entries were entered in correctly and are connected correctly
        SinglyLinkedListNode<Integer> cur = list.getHead();
        Integer val = 70;
        assertNotNull(cur);
        assertNotNull(cur.getNext());
        assertEquals(val, cur.getData());
        assertSame(list.getHead(), cur);

        cur = cur.getNext();
        val = 60;
        assertNotNull(cur);
        assertNotNull(cur.getNext());
        assertEquals(val, cur.getData());

        cur = cur.getNext();
        val = 50;
        assertNotNull(cur);
        assertNotNull(cur.getNext());
        assertEquals(val, cur.getData());

        cur = cur.getNext();
        val = 40;
        assertNotNull(cur);
        assertNotNull(cur.getNext());
        assertEquals(val, cur.getData());

        cur = cur.getNext();
        val = 30;
        assertNotNull(cur);
        assertNotNull(cur.getNext());
        assertEquals(val, cur.getData());

        cur = cur.getNext();
        val = 20;
        assertNotNull(cur);
        assertNotNull(cur.getNext());
        assertEquals(val, cur.getData());

        cur = cur.getNext();
        val = 10;
        assertNotNull(cur);
        assertNull(cur.getNext());
        assertEquals(val, cur.getData());
        assertSame(list.getTail(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBack() {
        SinglyLinkedListNode<Integer> head = list.getHead();
        SinglyLinkedListNode<Integer> tail = list.getTail();
        // Tests to make sure that when list is empty, both head and tail are null.
        assertNull(head);
        assertNull(tail);

        // Block below tests to make sure head and tail are properly updated when adding to front of list.
        list.addToBack(10);
        head = list.getHead();
        tail = list.getTail();
        assertSame(list.getHead(), head);
        assertSame(list.getTail(), tail);
        assertSame(head, tail);
        list.addToBack(20);
        head = list.getHead();
        tail = list.getTail();
        assertSame(list.getHead(), head);
        assertSame(list.getTail(), tail);
        list.addToBack(30);
        head = list.getHead();
        tail = list.getTail();
        assertSame(list.getHead(), head);
        assertSame(list.getTail(), tail);
        list.addToBack(40);
        head = list.getHead();
        tail = list.getTail();
        assertSame(list.getHead(), head);
        assertSame(list.getTail(), tail);
        list.addToBack(50);
        head = list.getHead();
        tail = list.getTail();
        assertSame(list.getHead(), head);
        assertSame(list.getTail(), tail);
        list.addToBack(60);
        head = list.getHead();
        tail = list.getTail();
        assertSame(list.getHead(), head);
        assertSame(list.getTail(), tail);
        list.addToBack(70);
        head = list.getHead();
        tail = list.getTail();
        assertSame(list.getHead(), head);
        assertSame(list.getTail(), tail);
        assertEquals(7, list.size());
        // End of block
        // List is [10,20,30,40,50,60,70]

        // Block checks that entries were entered in correctly and are connected correctly
        SinglyLinkedListNode<Integer> cur = list.getHead();
        Integer val = 10;
        assertNotNull(cur);
        assertNotNull(cur.getNext());
        assertEquals(val, cur.getData());
        assertSame(list.getHead(), cur);

        cur = cur.getNext();
        val = 20;
        assertNotNull(cur);
        assertNotNull(cur.getNext());
        assertEquals(val, cur.getData());

        cur = cur.getNext();
        val = 30;
        assertNotNull(cur);
        assertNotNull(cur.getNext());
        assertEquals(val, cur.getData());

        cur = cur.getNext();
        val = 40;
        assertNotNull(cur);
        assertNotNull(cur.getNext());
        assertEquals(val, cur.getData());

        cur = cur.getNext();
        val = 50;
        assertNotNull(cur);
        assertNotNull(cur.getNext());
        assertEquals(val, cur.getData());

        cur = cur.getNext();
        val = 60;
        assertNotNull(cur);
        assertNotNull(cur.getNext());
        assertEquals(val, cur.getData());

        cur = cur.getNext();
        val = 70;
        assertNotNull(cur);
        assertNull(cur.getNext());
        assertEquals(val, cur.getData());
        assertSame(list.getTail(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndex() {
        Integer val2 = 10;
        list.addAtIndex(list.size(), 5);
        list.addAtIndex(1, 10);
        list.addAtIndex(0, 15);
        list.addAtIndex(2, 20);
        list.addAtIndex(1, 25);
        assertEquals(5, list.size());
        //should be [15,25,5,20,10]

        assertSame(val2, list.removeAtIndex(list.size() - 1));
        // [15,25,5,20]

        assertEquals(4, list.size());

        SinglyLinkedListNode<Integer> cur = list.getHead();
        assertNotNull(cur);
        assertNotNull(cur.getNext());
        Integer val = 15;
        assertEquals(val, cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertNotNull(cur.getNext());
        val = 25;
        assertEquals(val, cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertNotNull(cur.getNext());
        val = 5;
        assertEquals(val, cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertNull(cur.getNext());
        val = 20;
        assertEquals(val, cur.getData());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveListSize1() { // Fixed a edge case! Removing from list of size 1
        // This tests with the method removeAtIndex.
        // Since removeAtIndex calls to both removeFromFront and removeFromBack, they should also work
        // with this edge case!
        list.addToFront(2);
        SinglyLinkedListNode<Integer> cur = list.getHead();
        assertSame(list.getHead(), cur);
        assertSame(list.getTail(), list.getHead());

        list.removeAtIndex(0);
        assertSame(null, list.getHead());
        assertSame(null, list.getTail());
        Integer val = 2;
        assertEquals(val, cur.getData());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFront() {
        // Checks the edge case of the list being of size 1.
        list.addAtIndex(0, 3);
        SinglyLinkedListNode<Integer> temp = list.getHead();
        assertSame(list.getHead(), temp);
        assertSame(list.getTail(), list.getHead());

        Integer val = 3;
        assertEquals(val, list.removeFromFront());
        assertNull(list.getTail());
        assertNull(list.getHead());

        // Generic tests of removing from front.
        list.addAtIndex(0, 1);   // 1
        list.addAtIndex(1, 2);   // 1, 2
        list.addAtIndex(2, 3);   // 1, 2, 3
        list.addAtIndex(3, 4);   // 1, 2, 3, 4
        list.addAtIndex(4, 5);   // 1, 2, 3, 4, 5
        list.addAtIndex(5, 6);   // 1, 2, 3, 4, 5, 6
        assertEquals(6, list.size());

        assertSame(1, list.removeFromFront());
        assertEquals(5, list.size());


        SinglyLinkedListNode<Integer> cur = list.getHead();
        assertNotNull(cur);
        Integer val2 = 2;
        assertEquals(val2, cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        val2 = 3;
        assertEquals(val2, cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        val2 = 4;
        assertEquals(val2, cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        val2 = 5;
        assertEquals(val2, cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        val2 = 6;
        assertEquals(val2, cur.getData());
        assertSame(list.getTail(), cur);

        cur = cur.getNext();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBack() {
        // Checks the edge case of the list being of size 1.
        list.addAtIndex(0, 3);
        SinglyLinkedListNode<Integer> temp = list.getHead();
        assertSame(list.getHead(), temp);
        assertSame(list.getTail(), list.getHead());

        Integer val = 3;
        assertEquals(val, list.removeFromBack());
        assertNull(list.getTail());
        assertNull(list.getHead());

        // Generic tests of removing from back.
        list.addAtIndex(0, 1);   // 1
        list.addAtIndex(1, 2);   // 1, 2
        list.addAtIndex(2, 3);   // 1, 2, 3
        list.addAtIndex(3, 4);   // 1, 2, 3, 4
        list.addAtIndex(4, 5);   // 1, 2, 3, 4, 5
        list.addAtIndex(5, 6);   // 1, 2, 3, 4, 5, 6
        assertEquals(6, list.size());

        assertSame(6, list.removeFromBack());
        assertEquals(5, list.size());


        SinglyLinkedListNode<Integer> cur = list.getHead();
        assertNotNull(cur);
        Integer val2 = 1;
        assertEquals(val2, cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        val2 = 2;
        assertEquals(val2, cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        val2 = 3;
        assertEquals(val2, cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        val2 = 4;
        assertEquals(val2, cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        val2 = 5;
        assertEquals(val2, cur.getData());
        assertSame(list.getTail(), cur);

        cur = cur.getNext();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        list.addAtIndex(0, 6);
        list.addAtIndex(1, 12);
        list.addAtIndex(2, 18);
        list.addAtIndex(list.size(), 36);
        assertEquals(4, list.size());

        Integer val = 6;
        assertEquals(val, list.get(0));
        val = 12;
        assertEquals(val, list.get(1));
        val = 18;
        assertEquals(val, list.get(2));
        val = 36;
        assertEquals(val, list.get(3));

        list.clear();
        //assertNull(list.get(0)); Throws correct exception!
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.addAtIndex(0, 7);
        assertEquals(1, list.size());

        Integer val = 7;
        assertEquals(val, list.get(0));
        assertFalse(list.isEmpty());

        assertEquals(val, list.removeFromFront());
        assertTrue(list.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        list.addAtIndex(0, 4);
        list.addAtIndex(0, 7);
        list.addAtIndex(0, 8);
        list.addAtIndex(0, 2);
        assertEquals(4, list.size());

        list.clear();
        assertEquals(0, list.size());
        assertNull(list.getHead());
        assertNull(list.getTail());
    }

    @Test(timeout = TIMEOUT)
    public void testToArray() {
        list.addAtIndex(0, 9);   // 9
        list.addAtIndex(1, 8);   // 9, 8
        list.addAtIndex(2, 7);   // 9, 8, 7
        list.addAtIndex(3, 6);   // 9, 8, 7, 6
        list.addAtIndex(4, 5);   // 9, 8, 7, 6, 5
        assertEquals(5, list.size());

        Integer[] expected = new Integer[5];
        expected[0] = 9;
        expected[1] = 8;
        expected[2] = 7;
        expected[3] = 6;
        expected[4] = 5;
        assertArrayEquals(expected, list.toArray());

        Integer val = 8;
        assertEquals(val, list.removeAtIndex(1));
        assertEquals(4, list.size());
        Integer[] expected2 = new Integer[4];
        expected2[0] = 9;
        expected2[1] = 7;
        expected2[2] = 6;
        expected2[3] = 5;
        assertArrayEquals(expected2, list.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testLastOccurrence() {
        list.addAtIndex(0, 10);
        list.addAtIndex(1, 20);
        list.addAtIndex(2, 20);
        list.addAtIndex(3, 40);
        list.addAtIndex(4, 20);
        list.addAtIndex(5, 50);
        assertEquals(6, list.size());

        Integer val = 20;
        assertSame(val, list.removeLastOccurrence(20));
        assertEquals(5, list.size());

        SinglyLinkedListNode<Integer> cur = list.getHead();
        assertNotNull(cur);
        assertNotNull(cur.getNext());
        val = 10;
        assertEquals(val, cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertNotNull(cur.getNext());
        val = 20;
        assertEquals(val, cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertNotNull(cur.getNext());
        val = 20;
        assertEquals(val, cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertNotNull(cur.getNext());
        val = 40;
        assertEquals(val, cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertNull(cur.getNext());
        val = 50;
        assertEquals(val, cur.getData());
        assertSame(list.getTail(), cur);

        list.clear();

        list.addAtIndex(0, 10);
        list.addAtIndex(1, 20);
        list.addAtIndex(2, 20);
        list.addAtIndex(3, 40);
        list.addAtIndex(4, 20);
        assertEquals(5, list.size());

        val = 20;
        assertSame(val, list.removeLastOccurrence(20));
        assertEquals(4, list.size());

        cur = list.getHead();
        assertNotNull(cur);
        assertNotNull(cur.getNext());
        val = 10;
        assertEquals(val, cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertNotNull(cur.getNext());
        val = 20;
        assertEquals(val, cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertNotNull(cur.getNext());
        val = 20;
        assertEquals(val, cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertNull(cur.getNext());
        val = 40;
        assertEquals(val, cur.getData());
        assertSame(list.getTail(), cur);
    }
}