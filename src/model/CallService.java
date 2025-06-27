package model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CallService {

    public enum CallType {
        CHAT, AUDIO, VIDEO
    }

    private String idCall;
    private CallType callType;
    LocalDateTime startTime, endTime;
    private String doctorName;

    // Format thời gian để in/log
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Danh sách tĩnh để lưu tất cả các cuộc gọi
    private static List<CallService> callHistory = new ArrayList<>();

    public CallService(CallType callType) {
        this.idCall = UUID.randomUUID().toString();
        this.callType = callType;
    }

    public CallService(String idCall, CallType callType, LocalDateTime startTime, LocalDateTime endTime) {
        this.idCall = idCall;
        this.callType = callType;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Ghi lại cuộc gọi vào danh sách
    public void record() {
        callHistory.add(this);
    }

    // Lấy danh sách lịch sử
    public static List<CallService> getCallHistory() {
        return callHistory;
    }

    public String getIdCall() {
        return idCall;
    }

    public void setIdCall(String idCall) {
        this.idCall = idCall;
    }

    public CallType getCallType() {
        return callType;
    }

    public void setCallType(CallType callType) {
        this.callType = callType;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    // Bắt đầu cuộc gọi
    public void startCall() {
        this.startTime = LocalDateTime.now();
        System.out.println("📞 Cuộc gọi " + callType + " bắt đầu lúc " + formatter.format(startTime));
    }

    // Kết thúc cuộc gọi
    public void endCall() {
        this.endTime = LocalDateTime.now();
        System.out.println("📴 Cuộc gọi kết thúc lúc " + formatter.format(endTime));
        logCallDetails();
    }

    // Tính thời lượng
    public Duration getCallDuration() {
        if (startTime != null && endTime != null) {
            return Duration.between(startTime, endTime);
        }
        return Duration.ZERO;
    }

    // Hiển thị thời lượng dễ hiểu
    public String getDurationAsString() {
        Duration duration = getCallDuration();
        long minutes = duration.toMinutes();
        long seconds = duration.getSeconds() % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public void setDoctorName(String name) {
        this.doctorName = name;
    }

    // Ghi log cuộc gọi (console, có thể mở rộng ghi file/DB)
    public void logCallDetails() {
        System.out.println("📝 Thông tin cuộc gọi:");
        System.out.println("➡️ Mã cuộc gọi: " + idCall);
        System.out.println("➡️ Loại: " + callType);
        System.out.println("➡️ Bắt đầu: " + formatter.format(startTime));
        System.out.println("➡️ Kết thúc: " + formatter.format(endTime));
        System.out.println("⏱️ Thời lượng: " + getDurationAsString());
    }


}
