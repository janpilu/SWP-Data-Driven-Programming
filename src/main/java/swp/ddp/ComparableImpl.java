package swp.ddp;

public class ComparableImpl implements Comparable<ComparableImpl>{
    private int key;
    private double[] numbers;

    public ComparableImpl(){
        key = (int) (Math.random()*100);
        numbers = new double[10];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Math.random()*10;
        }
    }

    public ComparableImpl(int arraySize){
        key = (int) (Math.random()*100);
        numbers = new double[arraySize];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Math.random()*10;
        }
    }

    @Override
    public int compareTo(ComparableImpl c) {
        if(key>c.getKey()){
            return 1;
        } else if(key<c.getKey()){
            return -1;
        } else {
            return 0;
        }
    }

    public int getSizeBytes(){
        int size = 4;//int
        for (int i = 0; i < numbers.length; i++) {
            size+=8;//double
        }
        return size;
    }

    public int getKey() {
        return key;
    }
}
