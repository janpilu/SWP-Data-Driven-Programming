package swp.ddp.report;

import java.util.*;

public class MDReporter {
    public MDReporter(){
    }
    public void printReport(Report report){
        ArrayList<String> types = new ArrayList<>(report.getReportData().keySet().stream().toList());

        StringBuilder content = new StringBuilder();
        content.append("# ").append(report.getTitle()).append("+\n");
        content.append(report.getComment());
        content.append("\n");
        for(int i = 0;i<=types.size();i++){
            content.append("## ").append(types.get(i)).append("\n");
            ArrayList<ReportData> currentReports = new ArrayList<>(report.getReportData().get(types.get(i)));
            for (int j = 0;j<=currentReports.size();j++) {
                ReportData currentReport = currentReports.get(j);
                content.append("### ").append(currentReport.getName()).append("\n");
                content.append(currentReport.getDescription()).append("\n");
                content.append("* Dauer: ").append(currentReport.getDuration()).append("\n");
                content.append("* Datenmenge: ").append(currentReport.getSize()).append("\n");
            }

        }
    }
}
