package model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;

import java.io.IOException;
import java.time.Duration;

public class OpenAIService {
    private static final String OLLAMA_URL = "http://localhost:11434/api/generate"; // cổng mặc định
    private final OkHttpClient client;

    public OpenAIService() {
//        client = new OkHttpClient();
        client = new OkHttpClient.Builder()
                .callTimeout(Duration.ofSeconds(20))
                .connectTimeout(Duration.ofSeconds(10))
                .readTimeout(Duration.ofSeconds(15))
                .build();
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

            ResponseBody body = response.body();
            if (body == null) {
                return "❌ Phản hồi từ AI bị rỗng (null).";
            }

            String json = body.string();
            JsonObject obj = JsonParser.parseString(json).getAsJsonObject();
            if (!obj.has("response")) {
                return "❌ Phản hồi từ AI không hợp lệ.";
            }
            return obj.get("response").getAsString().trim();
        }
    }
}
