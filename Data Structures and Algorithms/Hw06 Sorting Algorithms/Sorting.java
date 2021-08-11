import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Sunny Patel
 * @version 1.0
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class Sorting {

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        //Follows pseudocode from Introduction to Insertion Sort Video
        if (arr == null) {
            throw new IllegalArgumentException("Array is null! Cannot sort null array!");
        } else if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null!");
        } else {
            for (int i = 1; i < arr.length; i++) {
                T item = arr[i];
                int j = i - 1;
                while (j > -1 && comparator.compare(item, arr[j]) < 0) {
                    arr[j + 1] = arr[j];
                    j--;
                }
                arr[j + 1] = item;
            } //for
        } //else
    } //insertionSort

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: If two data are equal when merging, think about which subarray
     * you should pull from first
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        //Follows pseudocode from 6-24-21 lecture
        if (arr == null) {
            throw new IllegalArgumentException("Array is null! Cannot sort null array!");
        } else if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null!");
        } else if (arr.length > 1) {
            int length = arr.length;
            int midIndex = (int) length / 2;
            T[] leftArray = (T[]) new Object[midIndex];
            T[] rightArray = (T[]) new Object[length - midIndex];
            for (int i = 0; i < midIndex; i++) {
                leftArray[i] = arr[i];
            } //for
            for (int i = midIndex; i < length; i++) {
                rightArray[i - midIndex] = arr[i];
            } //for
            mergeSort(leftArray, comparator);
            mergeSort(rightArray, comparator);
            int leftIndex = 0;
            int rightIndex = 0;
            int currentIndex = 0;
            while (leftIndex < midIndex && rightIndex < length - midIndex) {
                if (comparator.compare(leftArray[leftIndex], rightArray[rightIndex]) <= 0) {
                    arr[currentIndex] = leftArray[leftIndex];
                    leftIndex++;
                } else {
                    arr[currentIndex] = rightArray[rightIndex];
                    rightIndex++;
                } //else
                currentIndex++;
            } //while
            while (leftIndex < midIndex) {
                arr[currentIndex] = leftArray[leftIndex];
                leftIndex++;
                currentIndex++;
            } //while
            while (rightIndex < length - midIndex) {
                arr[currentIndex] = rightArray[rightIndex];
                rightIndex++;
                currentIndex++;
            } //while
        } //else if
    } //mergeSort

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(kn)
     *
     * And a best case running time of:
     * O(kn)
     *
     * You are allowed to make an initial O(n) passthrough of the array to
     * determine the number of iterations you need.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * @param arr the array to be sorted
     * @throws java.lang.IllegalArgumentException if the array is null
     */
    public static void lsdRadixSort(int[] arr) {
        //Follows pseudocode from Sorting: LSD Radix Sort module video
        if (arr == null) {
            throw new IllegalArgumentException("Array is null! Cannot sort null array!");
        } else {
            LinkedList<Integer>[] buckets = new LinkedList[19];
            for (int i = 0; i < 19; i++) {
                buckets[i] = new LinkedList<>();
            } //for
            int div = 1;
            boolean breaker = true;
            while (breaker) {
                breaker = false;
                for (int item : arr) {
                    int bucket = item / div;
                    if (bucket / 10 != 0) {
                        breaker = true;
                    } //if
                    if (buckets[bucket % 10 + 9] == null) {
                        buckets[bucket % 10 + 9] = new LinkedList<>();
                    } //if
                    buckets[bucket % 10 + 9].add(item);
                } //for
                int idx = 0;
                for (int i = 0; i < buckets.length; i++) {
                    if (buckets[i] != null) {
                        for (int num : buckets[i]) {
                            arr[idx++] = num;
                        } //for
                        buckets[i].clear();
                    } //if
                } //for
                div = div * 10;
            } //while
        } //else
    } //lsdRadixSort

    /**
     * Implement kth select.
     *
     * Use the provided random object to select your pivots. For example if you
     * need a pivot between a (inclusive) and b (exclusive) where b > a, use
     * the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * If your recursion uses an inclusive b instead of an exclusive one,
     * the formula changes by adding 1 to the nextInt() call:
     *
     * int pivotIndex = rand.nextInt(b - a + 1) + a;
     *
     * It should be:
     * in-place
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * @param <T>        data type to sort
     * @param k          the index to retrieve data from + 1 (due to
     *                   0-indexing) if the array was sorted; the 'k' in "kth
     *                   select"; e.g. if k == 1, return the smallest element
     *                   in the array
     * @param arr        the array that should be modified after the method
     *                   is finished executing as needed
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @return the kth smallest element
     * @throws java.lang.IllegalArgumentException if the array or comparator
     *                                            or rand is null or k is not
     *                                            in the range of 1 to arr
     *                                            .length
     */
    public static <T> T kthSelect(int k, T[] arr, Comparator<T> comparator,
                                  Random rand) {
        //Follows Quicksort pseudocode from 6-24-21 lecture except this is one sided
        if (arr == null) {
            throw new IllegalArgumentException("Array is null! Cannot sort null array!");
        } //if
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null!");
        } //if
        if (rand == null) {
            throw new IllegalArgumentException("Rand cannot be null!");
        } //if
        if (k < 1 || k > arr.length) {
            throw new IllegalArgumentException("K is not in range [1,arr.length]!");
        } //if
        return kthSelectHelper(k, arr, comparator, rand, 0, arr.length);
    } //kthSelect

    /**
     * Helper method for kthSelect method
     *
     * @param <T>        data type to sort
     * @param k          the index to retrieve data from + 1 (due to
     *                   0-indexing) if the array was sorted; the 'k' in "kth
     *                   select"; e.g. if k == 1, return the smallest element
     *                   in the array
     * @param arr        the array that should be modified after the method
     *                   is finished executing as needed
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @param left       index to start sorting from
     * @param right      index to sort to
     * @return           the kth smallest element
     */
    private static <T> T kthSelectHelper(int k, T[] arr, Comparator<T> comparator, Random rand, int left, int right) {
        //Follows Quicksort pseudocode from 6-24-21 lecture except this is one sided
        int pivot = rand.nextInt(right - left) + left;
        swap(arr, left, pivot);
        int leftIdx = left + 1;
        int rightIdx = right - 1;
        while (leftIdx <= rightIdx) {
            while (leftIdx <= right && rightIdx >= leftIdx && comparator.compare(arr[leftIdx], arr[left]) <= 0) {
                leftIdx++;
            }  //while
            while (rightIdx > left && rightIdx >= leftIdx && comparator.compare(arr[rightIdx], arr[left]) >= 0) {
                rightIdx--;
            } //while
            if (rightIdx > leftIdx) {
                swap(arr, leftIdx++, rightIdx--);
            } //if
        } //while
        swap(arr, left, rightIdx);
        if (rightIdx < k - 1) {
            return kthSelectHelper(k, arr, comparator, rand, leftIdx, right);
        } //if
        if (rightIdx == k - 1) {
            return arr[rightIdx];
        } //if
        if (rightIdx > k - 1) {
            return kthSelectHelper(k, arr, comparator, rand, left, rightIdx);
        } //if
        return null;
    } //kthSelectHelper

    /**
     * Helper method that swaps array elements.
     *
     * @param <T> data type to sort
     * @param arr array in which elements are swapped
     * @param idx1 index of first item
     * @param idx2 index of second item
     */
    private static <T> void swap(T[] arr, int idx1, int idx2) {
        T temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    } //swap
} //Sorting
