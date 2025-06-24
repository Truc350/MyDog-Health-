package model;

/**
 * this is class represented result from AI
 */
public class Diagnosis {
    private String diseaseName;// ten benh
    private double probability; // phan tram kha nag mac benh
    private String adviceText;// cho loi khuyen de cham soc thu cung



    public Diagnosis(String diseaseName, double probability, String adviceText) {
        this.diseaseName = diseaseName;
        this.probability = probability;
        this.adviceText = adviceText;
    }
    public String getAdviceText() {
        return adviceText;
    }

    public void setAdviceText(String adviceText) {
        this.adviceText = adviceText;
    }
    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }
}
