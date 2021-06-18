package swp.ddp;

public class ComparableImpl implements Comparable<ComparableImpl>{
    private int key;
    private boolean state;
    private double[] numbers;

    public ComparableImpl(){
        key = (int) (Math.random()*100);
        state = Math.random() < 0.5;
        numbers = new double[]{Math.random(),Math.random()*10,Math.random()*100};
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

    public int getKey() {
        return key;
    }
}
