package swp.ddp.algorithms;

import java.util.List;

public class BubbleSort<T extends Comparable<T>> implements SortingAlgorithm<T>{

    @Override
    public void sort(List<T> items) {
        int n = items.size();
        T temp;
        for(int i=0; i < n; i++){
            for(int j=1; j < (n-i); j++){
                if(items.get(j-1).compareTo(items.get(j))>0){
                    temp = items.get(j-1);
                    items.set(j-1, items.get(j));
                    items.set(j, temp);
                }
            }
        }
    }

    @Override
    public void sortInt(int[] items) {
        int n = items.length;
        int temp = 0;
        for(int i=0; i < n; i++){
            for(int j=1; j < (n-i); j++){
                if(items[j-1] > items[j]){
                    temp = items[j-1];
                    items[j-1] = items[j];
                    items[j] = temp;
                }

            }
        }
    }

    @Override
    public String getName() {
        return "Bubble Sort";
    }
}
