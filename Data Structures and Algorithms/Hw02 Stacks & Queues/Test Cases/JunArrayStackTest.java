import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class JunArrayStackTest {
    private static final int TIMEOUT = 200;
    private ArrayStack<String> array;

    @Before
    public void setup() {
        array = new ArrayStack<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, array.size());
        assertArrayEquals(new Object[ArrayStack.INITIAL_CAPACITY],
                array.getBackingArray());
    }

    @Test (timeout = TIMEOUT)
    public void push() {
        array.push("0a");   // 0a
        array.push("1a");   // 0a, 1a
        array.push("2a");   // 0a, 1a, 2a
        array.push("3a");   // 0a, 1a, 2a, 3a
        array.push("4a");   // 0a, 1a, 2a, 3a, 4a

        assertEquals("4a", array.peek());
        assertEquals(5, array.size());

        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, array.getBackingArray());

        array.push("5a");
        array.push("6a");
        array.push("7a");
        array.push("8a");
        array.push("9a");

        assertEquals(10, array.size());

        assertEquals("9a", array.peek());
    }

    @Test (timeout = TIMEOUT)
    public void pop() {
        array.push("0a");   // 0a
        array.push("1a");   // 0a, 1a
        array.push("2a");   // 0a, 1a, 2a
        array.push("3a");   // 0a, 1a, 2a, 3a
        array.push("4a");   // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, array.size());

        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, array.getBackingArray());

        array.push("5a");
        array.push("6a");
        array.push("7a");
        array.push("8a");
        array.push("9a");

        assertEquals(10, array.size());

        assertEquals("9a", array.pop());
        assertEquals(9, array.size());

        assertEquals("8a", array.pop());
        assertEquals(8, array.size());

        assertEquals("7a", array.pop());
        assertEquals(7, array.size());

        assertEquals("6a", array.pop());
        assertEquals(6, array.size());

        array.push("new one");
        array.push("new two");

        assertEquals("new two", array.peek());
        assertEquals(8, array.size());
    }

    @Test (timeout = TIMEOUT)
    public void peek() {
        array.push("0a");   // 0a
        array.push("1a");   // 0a, 1a
        array.push("2a");   // 0a, 1a, 2a
        array.push("3a");   // 0a, 1a, 2a, 3a
        array.push("4a");   // 0a, 1a, 2a, 3a, 4a

        assertEquals("4a", array.peek());
    }
}