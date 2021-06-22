package swp.ddp.algorithms;

import java.util.ArrayList;
import java.util.List;

public class MergeSort<T extends Comparable<T>> implements SortingAlgorithm<T> {
    @Override
    public void sort(List<T> items) {

        sortOfList(items, 0, items.size() - 1);

    }

    @Override
    public void sortInt(int[] items) {

        sortIntArray(items, 0, items.length - 1);

    }

    @Override
    public String getName() {
        return "Merge Sort";
    }

    // Merges two sublists.
    // First sublist is from index [l..m]
    // Second sublist is from index [m+1..r]
    void mergeOfList(List<T> items, int l, int m, int r) {
        // Find sizes of two sublists to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp Lists */
        List<T> Left = new ArrayList<T>();
        List<T> Right = new ArrayList<T>();

        /*Copy data to temp Lists*/
        for (int i = 0; i < n1; ++i)
            Left.add(items.get(l+1));
        for (int j = 0; j < n2; ++j)
            Right.add(items.get(m+1+j));

        /* Merge the temp Lists */

        // Initial indexes of first and second sublist
        int i = 0, j = 0;

        // Initial index of merged sublist
        int k = l;
        while (i < n1 && j < n2) {

            if (Left.get(i).compareTo(Right.get(j))<=0) {
                items.set(k,Left.get(i));
                i++;
            } else {
                items.set(k, Right.get(i));
                j++;
            }
            k++;
        }

        /* Copy remaining elements of Left list if any */
        while (i < n1) {
            items.set(k, Left.get(i));
            i++;
            k++;
        }

        /* Copy remaining elements of Right list if any */
        while (j < n2) {
            items.set(k,Right.get(j));
            j++;
            k++;
        }
    }

    // Main function that sorts Lisz from [l..r] using
    // mergeOfList()
    void sortOfList(List<T> items, int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            sortOfList(items, l, m);
            sortOfList(items, m + 1, r);

            // Merge the sorted halves
            mergeOfList(items, l, m, r);
        }
    }

    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    void mergeOfIntArray(int arr[], int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        List<T> Left = new ArrayList<T>();
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // mergeofIntArray()
    void sortIntArray(int arr[], int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            sortIntArray(arr, l, m);
            sortIntArray(arr, m + 1, r);

            // Merge the sorted halves
            mergeOfIntArray(arr, l, m, r);
        }
    }
}