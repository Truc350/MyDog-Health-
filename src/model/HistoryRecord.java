package model;

import java.time.LocalDate;
import java.util.Date;

public class HistoryRecord {
    private String recordId;
    private Date date;
    private  String notes;

    public HistoryRecord(String recordId, Date date, String notes) {
        this.recordId = recordId;
        this.date = date;
        this.notes = notes;
    }
    public String detail(){
        return "recordId:"+recordId+" date:"+date+" notes:"+notes;
    }

    public String getRecordId() {
        return recordId;
    }

    public Date getDate() {
        return date;
    }

    public String getNotes() {
        return notes;
    }
}
