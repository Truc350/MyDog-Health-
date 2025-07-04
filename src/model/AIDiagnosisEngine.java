package model;

import com.google.gson.*;
import okhttp3.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AIDiagnosisEngine {
    private static final String API_URL = "http://localhost:11434/api/generate"; // cổng mặc định
    private static final OkHttpClient client = new OkHttpClient();

    public static List<DiagnosisResult> analyzeSymptoms(String petId, String symptomText) {
        List<DiagnosisResult> results = new ArrayList<>();
        try {
            JsonObject json = new JsonObject();
            json.addProperty("symptoms", symptomText);

            RequestBody body = RequestBody.create(json.toString(), MediaType.parse("application/json"));
            Request request = new Request.Builder()
                    .url(API_URL)
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JsonArray jsonArray = JsonParser.parseString(responseBody).getAsJsonArray();
                for (JsonElement element : jsonArray) {
                    JsonObject obj = element.getAsJsonObject();
                    String disease = obj.has("disease") ? obj.get("disease").getAsString() : "Không xác định";
                    double probability = obj.has("probability") ? obj.get("probability").getAsDouble() : 0.0;
                    String note = obj.has("note") ? obj.get("note").getAsString() : "Không có ghi chú";
                    results.add(new DiagnosisResult(UUID.randomUUID().toString(), petId, disease, probability, note, LocalDateTime.now()));
                }
            } else {
                System.err.println("Lỗi API: " + response.code() + " - " + response.message());
                // Dữ liệu mẫu khi API lỗi
                results.add(new DiagnosisResult(UUID.randomUUID().toString(), petId, "Không xác định", 0.0, "Lỗi kết nối với API AI. Vui lòng thử lại sau.", LocalDateTime.now()));
            }
        } catch (IOException e) {
            System.err.println("Lỗi IO: " + e.getMessage());
            results.add(new DiagnosisResult(UUID.randomUUID().toString(), petId, "Không xác định", 0.0, "Lỗi hệ thống: " + e.getMessage(), LocalDateTime.now()));
        }
        return results;
    }
    }

