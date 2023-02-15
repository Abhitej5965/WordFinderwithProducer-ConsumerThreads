package Model;

import java.util.List;

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
    public String toString() {
        return "SearchResult{" +
                "totalFiles=" + totalFiles +
                ", individualSearchResults=" + individualSearchResults +
                '}';
    }
}
