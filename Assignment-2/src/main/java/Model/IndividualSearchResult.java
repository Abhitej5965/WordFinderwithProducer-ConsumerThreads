package Model;

public class IndividualSearchResult implements Comparable<IndividualSearchResult> {

    private String word;
    private String fileName;
    private int lineNumber;
    private int index;

    private String threadName;

    public IndividualSearchResult() {
    }

    public IndividualSearchResult(String word, String fileName, int lineNumber, int index) {
        this.word = word;
        this.fileName = fileName;
        this.lineNumber = lineNumber;
        this.index = index;
    }

    public String getWord() {
        return word;
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

    public void setWord(String word) {
        this.word = word;
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
    public String toString() {
        return
                "\n{" + "word='" + word + "'" +
                        ", fileName='" + fileName + "'" +
                        ", lineNumber=" + lineNumber +
                        ", position=" + index  + "}\n";
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

