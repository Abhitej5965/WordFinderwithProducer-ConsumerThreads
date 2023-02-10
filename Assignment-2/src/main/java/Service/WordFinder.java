package Service;

import Model.SearchInput;
import Model.SearchResult;

import java.io.File;

public interface WordFinder {
    public  SearchResult searchWord(SearchInput searchInput);
    public File[] takingFilesOneByOne(String filePath);
}
