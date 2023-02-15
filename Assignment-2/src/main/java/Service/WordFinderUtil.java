package Service;

import Model.IndividualSearchResult;
import Model.SearchInput;
import Model.SearchResult;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WordFinderUtil {
    private SearchResult searchResult = new SearchResult();
    private int wordFoundInFiles = 0;
    private List<IndividualSearchResult> listOfIndividualSearchResults = Collections.synchronizedList(new ArrayList<>());
    private IndividualSearchResult individualSearchResult = null;
    private int flag = 0;

    public SearchResult searchWord(SearchInput searchInput) {

        String word = searchInput.getWordName();
        File individualFile = new File(searchInput.getFilePath());
        try {
            if (individualFile.isFile()) {
                int wordLength = searchInput.getWordName().length();
                int lineNumber = 0;
                BufferedReader br = new BufferedReader(new FileReader(individualFile));

                String line;
                while ((line = br.readLine()) != null) {
                    lineNumber++;
                    int lineLength = line.length();
                    for (int i = 0; i < lineLength - wordLength + 1; i++) {

                        int j = 0;
                        while (j < wordLength && line.toLowerCase().charAt(i + j) == word.toLowerCase().charAt(j)) {
                            j++;
                        }
                        if (j == wordLength) {
                            flag++;
                            individualSearchResult = new IndividualSearchResult(word, individualFile.getPath(), lineNumber, i);
                            listOfIndividualSearchResults.add(individualSearchResult);
                        }

                    }
                }

                if (flag > 0) {
                    wordFoundInFiles++;
                }
            } else if (individualFile.isDirectory()) {
                searchInput.setWordName(searchInput.getWordName());
                for (File subFile : individualFile.listFiles()) {
                    searchInput.setFilePath(subFile.getPath());
                    searchWord(searchInput);
                }
            }
//            Collections.sort(listOfIndividualSearchResults, new Comparator<IndividualSearchResult>() {
//                @Override
//                public int compare(IndividualSearchResult o1, IndividualSearchResult o2) {
//                    return o1.compareTo(o2);
//                }
//            });
        } catch (
                IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (
                NullPointerException e) {
            System.out.println(e.getMessage());
        }
        searchResult.setTotalFiles(wordFoundInFiles);
        searchResult.setIndividualSearchResults(listOfIndividualSearchResults);
        return searchResult;
    }

    public File[] takingFilesOneByOne(String filePath) {
        File directory = new File(filePath);
        File[] files = directory.listFiles();
        return files;
    }
}

