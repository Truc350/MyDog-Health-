package model;

import service.GeminiAIService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AIDiagnosisEngine {

    private static boolean USE_GEMINI = true; // 👉 đổi sang true khi muốn dùng AI thật
    private static GeminiAIService geminiService;

    // 👉 Khởi tạo 1 lần (singleton)
    static {
        String apiKey = System.getenv("GEMINI_API_KEY"); // Lấy từ biến môi trường
        if (apiKey != null && !apiKey.isEmpty()) {
            geminiService = new GeminiAIService(apiKey);
        }
    }

    /**
     * 👉 API chính, code cũ (DogInforPanel, CheckSymptomsPanel, ...) vẫn gọi được
     */
    public static List<DiagnosisResult> analyzeSymptoms(String petId, String symptomText) {
        if (USE_GEMINI && geminiService != null) {
            return analyzeSymptomsGemini(petId, symptomText);
        } else {
            return analyzeSymptomsMock(petId, symptomText);
        }
    }

    /**
     * 👉 Phương thức mock offline để test giao diện nhanh
     */
    public static List<DiagnosisResult> analyzeSymptomsMock(String petId, String symptomText) {
        List<DiagnosisResult> results = new ArrayList<>();

        // ✅ Tạo list triệu chứng demo (dùng symptomText làm "name")
        List<Symptom> symptoms = new ArrayList<>();
        symptoms.add(new Symptom(
                -1,                            // id tạm
                symptomText,                   // name
                "Không rõ",                    // location
                "Triệu chứng nhập từ người dùng", // description
                LocalDate.now().toString(),               // dateNoticed
                "Trung bình",                  // severity
                null                           // imagePath
        ));

        // ✅ CareAdvice cho kết quả 1
        List<CareAdvice> advices1 = new ArrayList<>();
        advices1.add(new CareAdvice(
                "Viêm da dị ứng",
                "Giữ vệ sinh vùng da, dùng thuốc bôi theo chỉ định",
                "Nếu vùng da bị loét, chảy máu hoặc thú cưng bỏ ăn → cần đưa đi thú y",
                null
        ));

        DiagnosisResult r1 = new DiagnosisResult(
                1,
                symptoms,
                "Có khả năng thú cưng bị viêm da dị ứng (xác suất khoảng 75%).",
                advices1
        );

        // ✅ CareAdvice cho kết quả 2
        List<CareAdvice> advices2 = new ArrayList<>();
        advices2.add(new CareAdvice(
                "Nhiễm giun",
                "Khuyến nghị tẩy giun định kỳ bằng thuốc thú y",
                "Nếu nôn ra giun, tiêu chảy kéo dài → cần khám ngay",
                null
        ));

        DiagnosisResult r2 = new DiagnosisResult(
                2,
                symptoms,
                "Có khả năng thú cưng bị nhiễm giun đường ruột (xác suất khoảng 25%).",
                advices2
        );

        results.add(r1);
        results.add(r2);

        return results;
    }

    /**
     * 👉 Phương thức gọi Gemini API (viết sau khi có API Key)
     */
    public static List<DiagnosisResult> analyzeSymptomsGemini(String petId, String symptomText) {
        List<DiagnosisResult> results = new ArrayList<>();

        try {
            // Prompt: ép AI chỉ trả JSON
            String prompt = "Bạn là bác sĩ thú y. Hãy phân tích triệu chứng của thú cưng: \""
                    + symptomText + "\". "
                    + "Trả về DUY NHẤT một JSON đúng chuẩn với cấu trúc:\n"
                    + "{\n"
                    + "  \"diagnosis\": \"string - chẩn đoán ngắn gọn\",\n"
                    + "  \"advices\": [\n"
                    + "    { \"title\": \"Tên bệnh hoặc vấn đề\", "
                    + "      \"care\": \"Hướng dẫn chăm sóc\", "
                    + "      \"warning\": \"Khi nào cần đi thú y\" }\n"
                    + "  ]\n"
                    + "}";

            String aiResponse = geminiService.generateText(prompt);

            // Debug: in ra console để xem AI trả về cái gì
            System.out.println("🔎 Raw Gemini response:");
            System.out.println(aiResponse);

            // 👉 Làm sạch response: bỏ phần ```json ... ```
            String cleaned = aiResponse
                    .replaceAll("(?s)```json", "")
                    .replaceAll("(?s)```", "")
                    .trim();

//            System.out.println("✅ Cleaned response:");
            System.out.println(cleaned);

            // 👉 Parse JSON
            org.json.JSONObject json = new org.json.JSONObject(cleaned);
            String diagnosisText = json.optString("diagnosis", "Không rõ");

            // Tạo symptom
            List<Symptom> symptoms = new ArrayList<>();
            symptoms.add(new Symptom(
                    -1, symptomText, "Không rõ",
                    "Triệu chứng nhập từ người dùng",
                    LocalDate.now().toString(),
                    "Không rõ",
                    null
            ));

            // Parse advices
            List<CareAdvice> advices = new ArrayList<>();
            if (json.has("advices")) {
                org.json.JSONArray arr = json.getJSONArray("advices");
                for (int i = 0; i < arr.length(); i++) {
                    org.json.JSONObject obj = arr.getJSONObject(i);
                    advices.add(new CareAdvice(
                            obj.optString("title", "Không rõ"),
                            obj.optString("care", "Không có hướng dẫn"),
                            obj.optString("warning", "Không có cảnh báo"),
                            null
                    ));
                }
            }

            // Tạo kết quả
            DiagnosisResult r = new DiagnosisResult(
                    1, symptoms,
                    diagnosisText,
                    advices
            );
            results.add(r);

        } catch (Exception e) {
            e.printStackTrace();
            return analyzeSymptomsMock(petId, symptomText); // fallback
        }

        return results;
    }



}
