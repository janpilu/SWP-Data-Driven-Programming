package swp.ddp.report;

public class ReportData {
    private String name;
    private String description;
    private String durationAvg;
    private String durationMin;
    private String durationMax;
    private String size;

    public ReportData(String name, String description, String durationAvg,String durationMin,String durationMax, String size) {
        this.name = name;
        this.description = description;
        this.durationAvg = durationAvg;
        this.durationMin = durationMin;
        this.durationMax = durationMax;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDurationAvg() {
        return durationAvg;
    }

    public void setDurationAvg(String durationAvg) {
        this.durationAvg = durationAvg;
    }

    public String getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(String durationMin) {
        this.durationMin = durationMin;
    }

    public String getDurationMax() {
        return durationMax;
    }

    public void setDurationMax(String durationMax) {
        this.durationMax = durationMax;
    }
}
