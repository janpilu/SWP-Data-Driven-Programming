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
        List<Report> reports = run(cache);
        printReport(reports);
    }

    public static List<Report> run(int cacheSize) {
        List<Report> reports = new ArrayList<>();

        Report report = benchmarkWithObjects("Liste von vielen Objekten mit kleiner Größe",cacheSize,10);
        reports.add(report);
        report = benchmarkWithObjects("Liste von wenigen Objekten mit großer Größe",cacheSize,50);
        reports.add(report);
        //report = benchmarkWithInts("Array von int",cacheSize);
        //reports.add(report);

        return reports;
    }

    public static Report benchmarkWithObjects(String title,int cacheSize, int arraySize){
        Report report = new Report(title);
        List<ComparableImpl> less = getComparableImplList(cacheSize,arraySize,true);
        List<ReportData> reportData= runAlgorithms(less);
        report.addReportData("Sortieren einer Liste (kleiner als CPU Cache)",reportData);
        List<ComparableImpl> greater = getComparableImplList(cacheSize,arraySize,false);
        reportData = runAlgorithms(greater);
        report.addReportData("Sortieren einer Liste (größer als CPU Cache)",reportData);
        return report;
    }

    public static Report benchmarkWithInts(String title,int cacheSize){
        Report report = new Report(title);
        int[] less = getIntArray(cacheSize,true);
        List<ReportData> reportData= runAlgorithmsInt(less);
        report.addReportData("Sortieren einer Liste (kleiner als CPU Cache)",reportData);
        int[] greater = getIntArray(cacheSize,false);
        reportData = runAlgorithmsInt(greater);
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

    public static List<ReportData> runAlgorithmsInt(int[] data){

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
                current.sortInt(data);
                long finish = System.nanoTime();
                long timeElapsed = finish - start;
                durations.add(timeElapsed);
            }
            long min = Collections.min(durations);
            long max = Collections.max(durations);
            long avg = durations.stream().collect(Collectors.summingLong(l->l))/durations.size();
            int size = data.length*4;
            currentRun = new ReportData(current.getName(),"",nanoToString(avg),nanoToString(min),nanoToString(max),bytesToString(size));
            reportData.add(currentRun);
        }
        return reportData;
    }

    public static String nanoToString(long nano){
        double seconds = (double)nano / 1000000000.0;
        int minutes = (int) (seconds/60)%60;
        int hours = (int) (seconds/(60*60));
        seconds = seconds%60;
        return hours+"h "+minutes+"m "+seconds+"s";
    }

    public static String bytesToString(int size){
        double mb = size/1000000.0;
        return mb+"MB";
    }

    private static List<ComparableImpl> getComparableImplList(int cacheSize,int arraySize,boolean lessThanCache) {
        List<ComparableImpl> values = new ArrayList<>();
        while (true){
            values.add(new ComparableImpl(arraySize));
            if(values.size()*values.get(0).getSizeBytes()>cacheSize){
                if(lessThanCache)
                    values.remove(values.size()-1);
                break;
            }
        }
        return values;
    }

    private static int[] getIntArray(int cacheSize, boolean lessThanCache) {
        int arraySize=0;
        while (arraySize*4<cacheSize){
            arraySize++;
        }
        if(lessThanCache)
            arraySize--;
        int[] values = new int[arraySize];
        for (int i= 0;i<arraySize;i++){
            values[i]=1;
        }
        return values;
    }

    private static void printReport(List<Report> reports) {
        MDReporter.printReport(reports);
    }
}
