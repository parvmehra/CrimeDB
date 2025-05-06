package models;



public class Case {
    private int caseId;
    private String title;
    private String description;
    private String region;
    private String status;
    private String suspect;
    private String urgency;
    private String date;

    //with case id to find a specific case
    public Case(int caseid, String title, String description, String region, String status, String suspect, String urgency, String date) {
        this.caseId = caseid;
        this.title = title;
        this.description = description;
        this.region = region;
        this.status = status;
        this.suspect = suspect;
        this.urgency = urgency;
        this.date = date;
    }

    //withoout the case id for adding a new case
    public Case(String title, String description, String region, String status, String suspect, String urgency, String date) {

        this.title = title;
        this.description = description;
        this.region = region;
        this.status = status;
        this.suspect = suspect;
        this.urgency = urgency;
        this.date = date;
    }

    // Getters and setters
    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSuspect() {
        return suspect;
    }

    public void setSuspect(String suspect) {
        this.suspect = suspect;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
