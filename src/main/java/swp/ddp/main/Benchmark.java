package swp.ddp.main;

import swp.ddp.algorithms.BubbleSort;
import swp.ddp.algorithms.MergeSort;
import swp.ddp.algorithms.SelectionSort;
import swp.ddp.algorithms.SortingAlgorithm;
import swp.ddp.report.Report;
import swp.ddp.report.ReportData;

import java.util.ArrayList;
import java.util.List;

public class Benchmark<T extends Comparable<T>> {
    public static void main(String[] args) {
        Report report = run();
        printReport(report);
    }

    public Report run(int cacheSize) {
        Report report = new Report();

        List<SortingAlgorithm<T>> algorithms = new ArrayList<>();
        algorithms.add(new BubbleSort<>());
        algorithms.add(new MergeSort<>());
        algorithms.add(new SelectionSort<>());

        for(int i = 0; i<algorithms.size();i++){
            SortingAlgorithm<T> current =algorithms.get(i);
            List<ReportData> reportData = runAlgorithm(current,cacheSize);
            report.addReportData(current.getName(),reportData);
        }
        return report;
    }

    public List<ReportData> runAlgorithm(SortingAlgorithm algorithm, int cacheSize){
        List<Integer> less = getLessThanCache(cacheSize);
        List<Integer> equal = getEqualToCache(cacheSize);
        List<Integer> greater = getGreaterThanCache(cacheSize);

    }

    private List<Integer> getGreaterThanCache(int cacheSize) {
        List<Integer> values = new ArrayList<>();
        values.to
    }

    private List<Integer> getEqualToCache(int cacheSize) {
    }

    private List<Integer> getLessThanCache(int cacheSize) {

    }

    private void printReport(Report report) {
    }
}
