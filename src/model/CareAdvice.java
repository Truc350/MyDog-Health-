package model;

/**
 * this is class represented instructive take after pet
 */

public class CareAdvice {
    private String diseaseName;
    private  String adviceText;

    public CareAdvice(String diseaseName, String adviceText) {
        this.diseaseName = diseaseName;
        this.adviceText = adviceText;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getAdviceText() {
        return adviceText;
    }

    public void setAdviceText(String adviceText) {
        this.adviceText = adviceText;
    }
}

