package swp.ddp.report;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MDReporter {

    public static void printReport(List<Report> reports){
        try {
            FileWriter reporter = new FileWriter("report.md");
            reporter.write("");
            for(int r = 0;r<reports.size();r++) {
                Report report= reports.get(r);

                ArrayList<String> types = new ArrayList<>(report.getReportData().keySet().stream().toList());

                StringBuilder content = new StringBuilder();
                content.append("### ").append(report.getTitle()).append("+\n");
                content.append(report.getComment());
                content.append("\n");
                for (int i = 0; i < types.size(); i++) {
                    content.append("#### ").append(types.get(i)).append("\n");
                    ArrayList<ReportData> currentReports = new ArrayList<>(report.getReportData().get(types.get(i)));
                    for (int j = 0; j < currentReports.size(); j++) {

                        ReportData currentReport = currentReports.get(j);
                        content.append("##### ").append(currentReport.getName()).append("\n");
                        content.append(currentReport.getDescription()).append("\n");
                        content.append("* Dauer: ").append("\n");
                        content.append("* Durchschnitt: ").append(currentReport.getDurationAvg()).append("\n");
                        content.append("* Min: ").append(currentReport.getDurationMin()).append("\n");
                        content.append("* Max: ").append(currentReport.getDurationMax()).append("\n");
                        content.append("* Datenmenge: ").append(currentReport.getSize()).append("\n");
                    }
                }
                reporter.append(content.toString());
            }
            reporter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printReport(Report report){
        try {
            FileWriter reporter = new FileWriter(report.getTitle()+"report.md");
            reporter.write("");

            ArrayList<String> types = new ArrayList<>(report.getReportData().keySet().stream().toList());

            StringBuilder content = new StringBuilder();
            content.append("### ").append(report.getTitle()).append("+\n");
            content.append(report.getComment());
            content.append("\n");
            for (int i = 0; i < types.size(); i++) {
                content.append("#### ").append(types.get(i)).append("\n");
                ArrayList<ReportData> currentReports = new ArrayList<>(report.getReportData().get(types.get(i)));
                for (int j = 0; j < currentReports.size(); j++) {

                    ReportData currentReport = currentReports.get(j);
                    content.append("##### ").append(currentReport.getName()).append("\n");
                    content.append(currentReport.getDescription()).append("\n");
                    content.append("* Dauer: ").append("\n");
                    content.append("* Durchschnitt: ").append(currentReport.getDurationAvg()).append("\n");
                    content.append("* Min: ").append(currentReport.getDurationMin()).append("\n");
                    content.append("* Max: ").append(currentReport.getDurationMax()).append("\n");
                    content.append("* Datenmenge: ").append(currentReport.getSize()).append("\n");
                    content.append("* Anzahl der Elemente: ").append(currentReport.getArraySize()).append("\n");
                    content.append("* Größe der Elemente: ").append(currentReport.getElementSize()).append("\n");

                }
            }
            reporter.append(content.toString());
            reporter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
