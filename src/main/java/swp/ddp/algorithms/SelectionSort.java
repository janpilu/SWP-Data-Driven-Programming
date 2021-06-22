package swp.ddp.algorithms;

import java.util.List;

public class SelectionSort<T extends Comparable<T>> implements SortingAlgorithm<T> {
    @Override
    public void sort(List<T> items) {

        sortOfList(items);

    }

    @Override
    public void sortInt(int[] items) {

        sortIntArray(items);

    }

    @Override
    public String getName() {
        return "Selection Sort";
    }

    void sortOfList(List<T> items) {
        int n = items.size();

        // One by one move boundary of unsorted sublist
        for (int i = 0; i < n -1; i++){
            // Find the minimum element in unsorted list
            int min_idx = i;
            for (int j = i+1; j < n; j++) {
                if (items.get(j).compareTo(items.get(min_idx)) < 0) {
                    min_idx = j;
                }
            }
            // Swap the found minimum element with the first
            // element
            T temp = items.get(min_idx);
            items.set(min_idx, items.get(i));
            items.set(i,temp);
        }
    }

    void sortIntArray(int items[]) {
        int n = items.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (items[j] < items[min_idx]) {
                    min_idx = j;
                }
            }
            // Swap the found minimum element with the first
            // element
            int temp = items[min_idx];
            items[min_idx] = items[i];
            items[i] = temp;
        }
    }
}
