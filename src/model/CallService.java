package model;

public class CallService {
    private String idCall,callType, startTime, endTime;

    public CallService(String idCall, String callType, String startTime, String endTime) {
        this.idCall = idCall;
        this.callType = callType;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getIdCall() {
        return idCall;
    }

    public void setIdCall(String idCall) {
        this.idCall = idCall;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
