package swp.ddp.report;

import java.util.Map;

public class Report<T> {
    String title;
    String comment;
    Map<Integer,T> initial;
    Map<Integer,T> result;
    Map<Integer,String[]> durations;
}
