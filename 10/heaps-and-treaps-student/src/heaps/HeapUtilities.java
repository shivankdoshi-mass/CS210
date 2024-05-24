package heaps;

import java.util.Arrays;
import java.util.Random;

public class HeapUtilities {
    static boolean isHeap(double[] a, int i) {
        if (i >= a.length) return true;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        boolean heap = true;
        if (left < a.length) heap &= (a[i] >= a[left]) && isHeap(a, left);
        if (right < a.length) heap &= (a[i] >= a[right]) && isHeap(a, right);
        return heap;
    }

    static void siftDown(double[] a, int i, int n) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if (left < n && a[left] > a[largest]) {
            largest = left;
        }
        if (right < n && a[right] > a[largest]) {
            largest = right;
        }
        
        if (largest != i) {
            double swap = a[i];
            a[i] = a[largest];
            a[largest] = swap;
            siftDown(a, largest, n);
        }
    }

    static void heapify(double[] a) {
        for (int i = a.length / 2 - 1; i >= 0; i--) {
            siftDown(a, i, a.length);
        }
    }

    static void heapSort(double[] a) {
        heapify(a);
        for (int n = a.length; n > 0; n--) {
            double temp = a[0];
            a[0] = a[n - 1];
            a[n - 1] = temp;
            siftDown(a, 0, n - 1);
        }
    }

    public static void main(String[] args) {
        Random r = new Random(0);
        int length = 15;
        double[] l = new double[length];
        for (int i = 0; i < length; i++) {
            l[i] = r.nextInt(20);
        }
        System.out.println("Original array: " + Arrays.toString(l));
        heapify(l);
        System.out.println("After heapify: " + Arrays.toString(l));
        heapSort(l);
        System.out.println("After heapsort: " + Arrays.toString(l));
    }
}
