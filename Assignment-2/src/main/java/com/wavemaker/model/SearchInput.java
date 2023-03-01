package com.wavemaker.model;

public class SearchInput {

    private String searchKey;
    private String filePath;
    private int lengthOfFilePath;


    public SearchInput(String searchKey, String filePath) {
        this.searchKey = searchKey;
        this.filePath = filePath;
        this.lengthOfFilePath = filePath.length();
    }

    public String getSearchKey() {
        return searchKey;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getLengthOfFilePath() {
        return lengthOfFilePath;
    }

    public void setLengthOfFilePath(int lengthOfFilePath) {
        this.lengthOfFilePath = lengthOfFilePath;
    }

    @Override
    public String toString() {
        return "SearchInput{" +
                "searchKey='" + searchKey + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
