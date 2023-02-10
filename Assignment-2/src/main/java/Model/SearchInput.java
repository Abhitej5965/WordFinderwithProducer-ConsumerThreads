package Model;

public class SearchInput {

    private String wordName;
    private String filePath;


    public SearchInput(String wordName, String filePath) {
        this.wordName = wordName;
        this.filePath = filePath;
    }

    public String getWordName() {
        return wordName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "SearchInput{" +
                "wordName='" + wordName + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
