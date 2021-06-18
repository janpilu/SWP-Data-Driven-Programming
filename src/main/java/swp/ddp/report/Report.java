package swp.ddp.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Report {

    String title;
    String comment;
    Map<String ,List<ReportData>> reportData; // <algorithmName,<name/duration/description>>

    public Report() {
        reportData=new TreeMap<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Map<String, List<ReportData>> getReportData() {
        return reportData;
    }

    public void setReportData(Map<String, List<ReportData>> reportData) {
        this.reportData = reportData;
    }

    public void addReportData(String type,List<ReportData> reportData) {
        this.reportData.put(type,reportData);
    }
}
