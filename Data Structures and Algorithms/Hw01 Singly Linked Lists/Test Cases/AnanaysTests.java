import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * These tests will run an ArrayList and your SLL in parallel.
 *
 * These tests emulate a random order of operations of the SLL on an ArrayList
 * and compares the results in parallel. The tests are based on the principle
 * that the resulting ArrayList and SLL will be the same. The tests are in no way
 * a conclusive method to decide if your program works correctly or not.
 *
 * @author Ananay Gupta
 * @version 1.0
 */
public class AnanaysTests {

    // Increase or decrease number of random operations
    private static final int NUM_RANDOM_OPS = 99999;

    // Check list equality after each random operation
    private static final boolean CHECK_AFTER_EACH_ROP = true;

    // Check length of Item String
    // Making this small and setting a higher number of ops will
    // repeat elements more frequently
    private static final int ITEM_LENGTH = 2;

    // Dump both Lists for a side by side comparison if an error occurs
    private static final boolean DEBUG_PRINT = true;

    // DO NOT CHANGE IF YOU DON'T KNOW WHAT THESE ARE DOING
    // Force repetitions of a random element to test removeLastOccurance
    private static final int LAST_OCCURANCE_FORCE_MULTIPLICITIES = 5;

    @Test
    public void testRandomOps() {
        System.out.printf("%n----------[Random Operation Testing]----------%n");
        List<String> internal = new ArrayList<String>();
        SinglyLinkedList<String> answer = new SinglyLinkedList<String>();
        boolean stop = false;
        outer: for (int i = 1, op = (int) (3 * Math.random()); !stop && i <= NUM_RANDOM_OPS; i++, op = (int) (15 * Math.random())) {
            String item = null;
            int index = -1;
            String internalRet = null;
            String answerRet = null;
            switch (op) {
                case 0:
                    index = (int) (answer.size() * Math.random());
                    item = randomString();
                    internal.add(index, item);
                    answer.addAtIndex(index, item);
                    break;
                case 1:
                    item = randomString();
                    internal.add(0, item);
                    answer.addToFront(item);
                    break;
                case 2:
                    item = randomString();
                    internal.add(internal.size(), item);
                    answer.addToBack(item);
                    break;
                case 3:
                    if (internal.size() < 1) {
                        i--;
                        continue outer;
                    }
                    index = (int) (answer.size() * Math.random());
                    internalRet = internal.remove(index);
                    answerRet = answer.removeAtIndex(index);
                    if (!internalRet.equals(answerRet)) {
                        System.out.printf("Test Failed [Op %5d, OPCODE %3s]: Not Equal internal(%15s) answer(%15s)%n", i, op, internalRet, answerRet);
                        stop = true;
                    }
                    break;
                case 4:
                    if (internal.size() < 1) {
                        i--;
                        continue outer;
                    }
                    internalRet = internal.remove(0);
                    answerRet = answer.removeFromFront();
                    if (!internalRet.equals(answerRet)) {
                        System.out.printf("Test Failed [Op %5d, OPCODE %3s]: Not Equal internal(%15s) answer(%15s)%n", i, op, internalRet, answerRet);
                        stop = true;
                    }
                    break;
                case 5:
                    if (internal.size() < 1) {
                        i--;
                        continue outer;
                    }
                    internalRet = internal.remove(internal.size()-1);
                    answerRet = answer.removeFromBack();
                    if (!internalRet.equals(answerRet)) {
                        System.out.printf("Test Failed [Op %5d, OPCODE %3s]: Not Equal internal(%15s) answer(%15s)%n", i, op, internalRet, answerRet);
                        stop = true;
                    }
                    break;
                case 6:
                    if (internal.size() < 1) {
                        i--;
                        continue outer;
                    }
                    index = (int) (internal.size() * Math.random());
                    internalRet = internal.get(index);
                    answerRet = answer.get(index);
                    if (!internalRet.equals(answerRet)) {
                        System.out.printf("Test Failed [Op %5d, OPCODE %3s]: Not Equal internal(%15s) answer(%15s)%n", i, op, internalRet, answerRet);
                        stop = true;
                    }
                    break;
                case 7:
                    if (!internal.isEmpty() == answer.isEmpty()) {
                        System.out.printf("Test Failed [Op %5d, OPCPDE %2s]: List Emptiness conflict%n");
                        stop = true;
                    }
                    break;
                case 8:
                    internal.clear();
                    answer.clear();
                    break;
                case 9:
                    String rand = "";
                    if (LAST_OCCURANCE_FORCE_MULTIPLICITIES > 0) {
                        rand = randomString();
                        for (int j = 0; j < LAST_OCCURANCE_FORCE_MULTIPLICITIES; j++){
                            index = (int) (internal.size() * Math.random());
                            internal.add(index, rand);
                            answer.addAtIndex(index, rand);
                        }
                    } else {
                        if (internal.size() < 1) {
                            i--;
                            continue outer;
                        }
                        rand = internal.get((int) (internal.size() * Math.random()));
                    }
                    internal.remove(internal.lastIndexOf(rand));
                    answer.removeLastOccurrence(rand);
                    break;
                case 10:
                    if (internal.size() < 1) {
                        i--;
                        continue outer;
                    }
                    if (!(java.util.Arrays.equals(internal.toArray(), answer.toArray()))) {
                        System.out.printf("Test Failed [Op %5d, OPCODE %3s]: Array representation not equal%n");
                        stop = true;
                    }
                    break;
                case 11:
                    if (internal.size() < 1) {
                        i--;
                        continue outer;
                    }
                    if (!(internal.get(0).equals(answer.getHead().getData()))) {
                        System.out.printf("Test Failed [Op %5d, OPCODE %3s]: Head Node data are not equal%n");
                        stop = true;
                    }
                    break;
                case 12:
                    if (internal.size() < 1) {
                        i--;
                        continue outer;
                    }
                    if (!(internal.get(internal.size() - 1).equals(answer.getTail().getData()))) {
                        System.out.printf("Test Failed [Op %5d, OPCODE %3s]: Tail Node data are not equal%n");
                        stop = true;
                    }
                    break;
                case 13:
                    if (internal.size() != answer.size()) {
                        System.out.printf("Test Failed [Op %5d, OPCODE %3s]: List Sizes are not equal%n");
                        stop = true;
                    }
                    break;

            }
            if (CHECK_AFTER_EACH_ROP) {
                boolean eq = equals(internal, answer);
                System.out.printf("Op %5d] OPCODE:%3s, STATUS: %8s%n", i, op, eq ? "FINE" : "ERROR");
                if (!eq) {
                    if (DEBUG_PRINT) {
                        debugPrint(internal, answer);
                    }
                    assertEquals(eq, true);
                }
            }
        }
        if (!CHECK_AFTER_EACH_ROP || stop) {
            boolean eq = equals(internal, answer);
            System.out.printf("[FINAL] STATUS: %s%n", eq ? "FINE" : "ERROR");
            if (!eq) {
                if (DEBUG_PRINT) {
                    debugPrint(internal, answer);
                }
                assertEquals(eq, true);
            }
        }
        System.out.printf("----------[PASSED ALL %5d RANDOM OPERATION TESTS]-----------", NUM_RANDOM_OPS);
        assertEquals(true, true);
    }
    static String randomString() {
        int size = 1 + (int) (Math.random() * (ITEM_LENGTH - 1));
        String ret = "";
        for (int i = 0; i < size; i++) {
            ret += (char) (97 + 26 * Math.random());
        }
        return ret;
    }
    static <T extends Comparable> boolean equals(List<T> internalImpl, SinglyLinkedList<T> answer) {
        if (internalImpl.size() != answer.size()) {
            System.out.printf("--->[EQUALITY CHECK] FAILED TO CHECK SIZE %5s %5s%n", internalImpl.size(), answer.size());
            return false;
        }
        Iterator internalIter = internalImpl.iterator();
        SinglyLinkedListNode<T> answerIter = answer.getHead();
        while (internalIter.hasNext() && answerIter != null) {
            if (!internalIter.next().equals(answerIter.getData())) {
                System.out.printf("--->[EQUALITY CHECK] LIST NODES DO NOT MATCH%n");
                return false;
            }
            answerIter = answerIter.getNext();
        }
        if ((internalIter.hasNext() && answerIter == null)
                || (!internalIter.hasNext() && answerIter != null)) {
            System.out.printf("--->[EQUALITY CHECK] ONE LIST TERMINATED BEFORE OTHER (same size registered)%n");
            return false;
        }
        return true;
    }

    static <T> void debugPrint(List<T> internal, SinglyLinkedList<T> answer) {
        System.out.printf("|-------[DUMPING DEBUG DATA]-------|%n");
        Iterator internalIter = internal.iterator();
        SinglyLinkedListNode<T> answerIter = answer.getHead();
        int max = (int) Math.max(internal.size(), answer.size());
        for (int i = 0; i < max; i++) {
            System.out.printf("%5s] %15s, %15s%n",
                    i,
                    internalIter.hasNext() ? internalIter.next() : "",
                    answerIter != null ? answerIter.getData() : ""
            );
            answerIter = answerIter == null ? answerIter : answerIter.getNext();
        }
        System.out.printf("|---------[END DUMP]---------|%n");
    }
}