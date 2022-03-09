package com.example.exception;

import java.util.Date;

public class ErrorDetails {
    private Date timestmp;
    private String error;
    private String description;

    public ErrorDetails() {
    }

    public ErrorDetails(Date timestmp, String error, String description) {
        this.timestmp = timestmp;
        this.error = error;
        this.description = description;
    }

    public Date getTimestmp() {
        return timestmp;
    }

    public void setTimestmp(Date timestmp) {
        this.timestmp = timestmp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ErrorDetails{" +
                "timestmp=" + timestmp +
                ", error='" + error + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
