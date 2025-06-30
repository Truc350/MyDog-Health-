package model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;

import java.io.IOException;

public class OpenAIService {
    private static final String OLLAMA_URL = "http://localhost:11434/api/generate"; // cổng mặc định
    private final OkHttpClient client;

    public OpenAIService() {
        client = new OkHttpClient();
    }

    public String ask(String prompt) throws IOException {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("model", "llama3"); // ← đổi thành tên model đã cài (llama3, gemma, mistral...)
        requestBody.addProperty("prompt", prompt);
        requestBody.addProperty("stream", false);

        Request request = new Request.Builder()
                .url(OLLAMA_URL)
                .post(RequestBody.create(
                        requestBody.toString(),
                        MediaType.get("application/json")))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return "⚠️ Lỗi từ Ollama: " + response.code();
            }

            String json = response.body().string();
            JsonObject obj = JsonParser.parseString(json).getAsJsonObject();
            return obj.get("response").getAsString().trim();
        }
    }
}
