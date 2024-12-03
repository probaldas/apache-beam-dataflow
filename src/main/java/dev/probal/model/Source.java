package dev.probal.model;

import java.io.Serializable;
import java.util.Objects;

public class Source implements Serializable {

    private String inputFilePath;

    public Source() {
    }

    public Source(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public String getInputFilePath() {
        return inputFilePath;
    }

    public void setInputFilePath(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Source source = (Source) o;
        return Objects.equals(inputFilePath, source.inputFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(inputFilePath);
    }

    @Override
    public String toString() {
        return "Source{" +
                "inputFilePath='" + inputFilePath + '\'' +
                '}';
    }
}
