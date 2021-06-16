package swp.ddp.algorithms;

import java.util.List;

public interface SortingAlgorithm<T extends Comparable<T>> {
    void sort(List<T> items);
    void sortInt(int[] items);
}
