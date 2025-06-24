package model;

/**
 * this is class represented instructive take after pet
 */

public class CareAdvice {
    private String diseaseName;//tenh benh
    private String advice;// goi y cham soc
    private String dangerSigns; // Dấu hiệu nguy hiểm
    private String extraNotes;  // Ghi chú

    public CareAdvice(String diseaseName, String advice, String dangerSigns, String extraNotes) {
        this.diseaseName = diseaseName;
        this.advice = advice;
        this.dangerSigns = dangerSigns;
        this.extraNotes = extraNotes;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getDangerSigns() {
        return dangerSigns;
    }

    public void setDangerSigns(String dangerSigns) {
        this.dangerSigns = dangerSigns;
    }

    public String getExtraNotes() {
        return extraNotes;
    }

    public void setExtraNotes(String extraNotes) {
        this.extraNotes = extraNotes;
    }
}

