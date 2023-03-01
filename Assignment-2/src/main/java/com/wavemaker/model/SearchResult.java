package com.wavemaker.model;

import java.util.List;
import java.util.Objects;

public class SearchResult {
    private int totalFiles;
    private List<IndividualSearchResult> individualSearchResults;


    public SearchResult() {
    }

    public SearchResult(int totalFiles, List<IndividualSearchResult> individualSearchResults) {
        this.totalFiles = totalFiles;
        this.individualSearchResults = individualSearchResults;
    }

    public int getTotalFiles() {
        return totalFiles;
    }

    public List<IndividualSearchResult> getIndividualSearchResults() {
        return individualSearchResults;
    }

    public void setTotalFiles(int totalFiles) {
        this.totalFiles = totalFiles;
    }

    public void setIndividualSearchResults(List<IndividualSearchResult> individualSearchResults) {
        this.individualSearchResults = individualSearchResults;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchResult that = (SearchResult) o;
        return totalFiles == that.totalFiles && individualSearchResults.equals(that.individualSearchResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalFiles, individualSearchResults);
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "totalFiles=" + totalFiles +
                ", individualSearchResults=" + individualSearchResults +
                '}';
    }
}
