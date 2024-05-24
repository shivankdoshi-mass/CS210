package sorting;

import java.util.Random;

public class SortingExercises {

    /**
     * Swap the values at a[i] and a[j].
     */
    static void swap(double[] a, int i, int j) {
        double t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * Perform an in-place insertion sort on the array a.
     * The array will be in ascending order (least-to-greatest) after sorting.
     */
    static void insertionSort(double[] a) {
        for (int i = 1; i < a.length; i++) {
            double key = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    /**
     * Perform an in-place insertion sort of the range start (inclusive) 
     * through end (exclusive) on array a.
     * The array will be in ascending order (least-to-greatest) after sorting.
     */
    static void insertionSort(double[] a, int start, int end) {
        for (int i = start + 1; i < end; i++) {
            double key = a[i];
            int j = i - 1;
            while (j >= start && a[j] > key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    /**
     * Perform a destructive mergesort on the array a.
     * The array will be in ascending order (least-to-greatest) after sorting; the original
     * values will be overwritten.
     * Additional array space will be allocated by this method.
     * 
     * For efficiency, this method will *insertion sort* any array of length less than 10.
     */
    public static void mergeSort(double[] a) {
        if (a.length < 10) {
            insertionSort(a);
        } else {
            double[] aux = new double[a.length];
            mergeSort(a, aux, 0, a.length);
        }
    }

    static void mergeSort(double[] a, double[] aux, int low, int high) {
        if (high - low < 10) {
            insertionSort(a, low, high);
            return;
        }
        int mid = low + (high - low) / 2;
        mergeSort(a, aux, low, mid);
        mergeSort(a, aux, mid, high);
        merge(a, aux, low, mid, high);
    }

    static void merge(double[] a, double[] aux, int low, int mid, int high) {
        for (int k = low; k < high; k++) {
            aux[k] = a[k];
        }
        int i = low, j = mid;
        for (int k = low; k < high; k++) {
            if (i >= mid) a[k] = aux[j++];
            else if (j >= high) a[k] = aux[i++];
            else if (aux[j] < aux[i]) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    /**
     * Perform an in-place quicksort on the array a.
     */
    static void quickSort(double[] a) {
        quickSort(a, 0, a.length);
    }

    /**
     * Perform an in-place quicksort on the range i (inclusive) to j 
     * (exclusive) of the array a.
     * 
     * For efficiency, this method will *insertion sort* any range of 
     * length less than 10.
     */
    static void quickSort(double[] a, int i, int j) {
        if (j - i < 10) {
            insertionSort(a, i, j);
            return;
        }
        int p = partition(a, i, j);
        quickSort(a, i, p);
        quickSort(a, p + 1, j);
    }

    /**
     * Perform an in-place partition on the range i (inclusive) to j 
     * (exclusive) of the array a, returning the index of the pivot
     * after partitioning.
     * 
     * To cut down on worst case choices, this method will chose the 
     * pivot value at random from among the values in the range.
     * 
     * @return the index of the pivot after the partition
     */
    static int partition(double[] a, int i, int j) {
        int pivotIndex = i + new Random().nextInt(j - i);
        swap(a, i, pivotIndex);
        double pivot = a[i];
        int left = i + 1;
        int right = j - 1;
        while (left <= right) {
            while (left <= right && a[left] <= pivot) left++;
            while (left <= right && a[right] > pivot) right--;
            if (left < right) {
                swap(a, left, right);
            }
        }
        swap(a, i, right);
        return right;
    }
}
