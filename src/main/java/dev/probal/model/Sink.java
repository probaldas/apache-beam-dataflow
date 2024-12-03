package dev.probal.model;

import java.io.Serializable;
import java.util.Objects;

public class Sink implements Serializable {

    private String successOutputFilePath;
    private String failureOutputFilePath;

    public Sink() {
    }

    public Sink(String successOutputFilePath, String failureOutputFilePath) {
        this.successOutputFilePath = successOutputFilePath;
        this.failureOutputFilePath = failureOutputFilePath;
    }

    public String getSuccessOutputFilePath() {
        return successOutputFilePath;
    }

    public void setSuccessOutputFilePath(String successOutputFilePath) {
        this.successOutputFilePath = successOutputFilePath;
    }

    public String getFailureOutputFilePath() {
        return failureOutputFilePath;
    }

    public void setFailureOutputFilePath(String failureOutputFilePath) {
        this.failureOutputFilePath = failureOutputFilePath;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Sink sink = (Sink) o;
        return Objects.equals(successOutputFilePath, sink.successOutputFilePath) && Objects.equals(failureOutputFilePath, sink.failureOutputFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(successOutputFilePath, failureOutputFilePath);
    }

    @Override
    public String toString() {
        return "Sink{" +
                "successOutputFilePath='" + successOutputFilePath + '\'' +
                ", failureOutputFilePath='" + failureOutputFilePath + '\'' +
                '}';
    }
}
