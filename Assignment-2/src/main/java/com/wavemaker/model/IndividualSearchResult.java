package com.wavemaker.model;

import java.util.Objects;

public class IndividualSearchResult implements Comparable<IndividualSearchResult> {

    private String fileName;
    private int lineNumber;
    private int index;
    private String threadName;

    public IndividualSearchResult(String fileName, int lineNumber, int index, String threadName) {
        this.fileName = fileName;
        this.lineNumber = lineNumber;
        this.index = index;
        this.threadName = threadName;
    }

    public String getFileName() {
        return fileName;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getIndex() {
        return index;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndividualSearchResult that = (IndividualSearchResult) o;
        return Objects.equals(fileName, that.fileName) && lineNumber == that.lineNumber && index == that.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, lineNumber, index);
    }


    @Override
    public String toString() {
        return "IndividualSearchResult{" +
                "fileName='" + fileName + '\'' +
                ", lineNumber=" + lineNumber +
                ", index=" + index +
                '}' + "\n";
    }

    @Override
    public int compareTo(IndividualSearchResult o) {
        if (!this.fileName.equals(o.fileName)) {
            return this.fileName.compareTo(o.fileName);
        }
        if (this.lineNumber != o.lineNumber) {
            return Integer.compare(this.lineNumber, o.lineNumber);
        }
        return Integer.compare(this.index, o.index);
    }
}

