package service;

import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentResponse;

import java.util.ArrayList;
import java.util.List;

public class GeminiAIService {

    private final Client client;
    private final String modelName = "gemini-2.5-flash-lite"; // hoặc "gemini-1.5-pro"
    private final List<String> history = new ArrayList<>();
    private static GeminiAIService instance;

    public GeminiAIService(String apiKey) {
        // Tạo client Gemini với API key
        this.client = Client.builder()
                .apiKey(apiKey)
                .build();
    }

    public static GeminiAIService getInstance(String apiKey) {
        if (instance == null) {
            instance = new GeminiAIService(apiKey);
        }
        return instance;
    }


    /**
     * Gửi prompt đến Gemini và nhận text trả về
     */
    public String generateText(String prompt) {
        try {
            // Lưu user message vào history
            history.add(prompt);

            // Gọi Gemini API với toàn bộ history
            GenerateContentResponse response = client.models.generateContent(modelName, prompt, null);

            // Lấy text chính
            String reply = response.text();

            // Lưu phản hồi AI vào history
            history.add(reply);

            return reply;
        } catch (Exception e) {
            e.printStackTrace();
            return "❌ Lỗi khi gọi Gemini API: " + e.getMessage();
        }
    }

    /**
     * Xoá toàn bộ lịch sử hội thoại (reset cuộc trò chuyện)
     */
    public void clearHistory() {
        history.clear();
    }

}
