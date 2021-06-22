package swp.ddp.main;

import swp.ddp.ComparableImpl;
import swp.ddp.algorithms.BubbleSort;
import swp.ddp.algorithms.MergeSort;
import swp.ddp.algorithms.SelectionSort;
import swp.ddp.algorithms.SortingAlgorithm;
import swp.ddp.report.MDReporter;
import swp.ddp.report.Report;
import swp.ddp.report.ReportData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Benchmark {
    public static void main(String[] args) {
        System.out.println("Geben Sie ihre CPU Cache Größe in Byte an");
        Scanner s = new Scanner(System.in);
        int cache = Integer.parseInt(s.next());
        Report report = run(cache);
        printReport(report);
    }

    public static Report run(int cacheSize) {
        Report report = new Report();

        List<ComparableImpl> less = getLessThanCache(cacheSize);
        List<ReportData> reportData= runAlgorithms(less);
        report.addReportData("Sortieren einer Liste (kleiner als CPU Cache)",reportData);
        List<ComparableImpl> greater = getGreaterThanCache(cacheSize);
        reportData = runAlgorithms(greater);
        report.addReportData("Sortieren einer Liste (größer als CPU Cache)",reportData);
        return report;
    }

    public static List<ReportData> runAlgorithms(List<ComparableImpl> data){

        List<ReportData> reportData = new ArrayList<>();
        List<SortingAlgorithm<ComparableImpl>> algorithms = new ArrayList<>();
        algorithms.add(new BubbleSort<>());
        algorithms.add(new MergeSort<>());
        algorithms.add(new SelectionSort<>());

        for(int i = 0; i<algorithms.size();i++){
            ReportData currentRun;
            SortingAlgorithm<ComparableImpl> current =algorithms.get(i);
            ArrayList<Long> durations = new ArrayList<>();
            for(int j =0;j<30;j++){
                long start = System.nanoTime();
                current.sort(data);
                long finish = System.nanoTime();
                long timeElapsed = finish - start;
                durations.add(timeElapsed);
            }
            long min = Collections.min(durations);
            long max = Collections.max(durations);
            long avg = durations.stream().collect(Collectors.summingLong(l->l))/durations.size();
            int size = data.stream().collect(Collectors.summingInt(s->s.getSizeBytes()));
            currentRun = new ReportData(current.getName(),"",nanoToString(avg),nanoToString(min),nanoToString(max),bytesToString(size));
            reportData.add(currentRun);
        }
        return reportData;
    }

    public static String nanoToString(long nano){
        double seconds = (double)nano / 1000000000.0;
        int minutes = (int) (seconds/60)%60;
        int hours = (int) (seconds/(60*60));
        return hours+"h "+minutes+"m "+seconds+"s";
    }

    public static String bytesToString(int size){
        double mb = size/1000000.0;
        return mb+"MB";
    }

    private static List<ComparableImpl> getGreaterThanCache(int cacheSize) {
        List<ComparableImpl> values = new ArrayList<>();
        boolean smaller=true;
        while (smaller){
            values.add(new ComparableImpl(10));
            if(values.size()*values.get(0).getSizeBytes()>cacheSize){
                break;
            }
        }
        return values;
    }

    private static List<ComparableImpl> getLessThanCache(int cacheSize) {
        List<ComparableImpl> values = new ArrayList<>();
        boolean smaller=true;
        while (smaller){
            values.add(new ComparableImpl(100));
            if(values.size()*values.get(0).getSizeBytes()>cacheSize){
                values.remove(values.size()-1);
                break;
            }
        }
        return values;
    }

    private static List<ComparableImpl> getEvenLessThanCache(int cacheSize) {
        List<ComparableImpl> values = new ArrayList<>();
        boolean smaller=true;
        while (smaller){
            values.add(new ComparableImpl(100));
            if(values.size()*values.get(0).getSizeBytes()>cacheSize){
                values.remove(values.size()-1);
                break;
            }
        }
        return values;
    }

    private static void printReport(Report report) {
        MDReporter.printReport(report);
    }
}
