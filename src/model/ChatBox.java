package model;

/**
 * Lớp đại diện cho chatbot AI tư vấn trước khi gặp bác sĩ.
 */

public class ChatBox {
    private  String question;
    private String responseType;// tra loi : khong, dung | van ban|lua chon

    public ChatBox(String question, String responseType) {
        this.question = question;
        this.responseType = responseType;
    }

}
