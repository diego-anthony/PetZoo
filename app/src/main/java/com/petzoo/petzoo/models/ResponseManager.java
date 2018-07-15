package com.petzoo.petzoo.models;

public class ResponseManager {
    private boolean isSuccess;
    private String message;
    private Object result;

    public ResponseManager(boolean isSuccess, Object result) {
        this.isSuccess = isSuccess;
        this.result = result;
    }

    public ResponseManager(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
