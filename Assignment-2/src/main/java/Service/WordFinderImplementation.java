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

public class WordFinderImplementation implements WordFinder {
    private SearchResult searchResult = new SearchResult();
    private int wordFoundInFiles = 0;
    private List<IndividualSearchResult> listOfIndividualSearchResults = new ArrayList<>();
    private IndividualSearchResult individualSearchResult = null;
    private int flag = 0;

    @Override
    public synchronized SearchResult searchWord(SearchInput searchInput) {

        String word = searchInput.getWordName();
        try {
            String individualFile = searchInput.getFilePath();
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
                        individualSearchResult = new IndividualSearchResult(word, individualFile, lineNumber, i);
                        listOfIndividualSearchResults.add(individualSearchResult);
                    }

                }
            }

            if (flag > 0) {
                wordFoundInFiles++;
            }
            Collections.sort(listOfIndividualSearchResults, new Comparator<IndividualSearchResult>() {
                @Override
                public int compare(IndividualSearchResult o1, IndividualSearchResult o2) {
                    return o1.compareTo(o2);
                }
            });
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        searchResult.setTotalFiles(wordFoundInFiles);
        searchResult.setIndividualSearchResults(listOfIndividualSearchResults);
        return searchResult;
    }

    @Override
    public File[] takingFilesOneByOne(String filePath) {
        File directory = new File(filePath);
        File[] files = directory.listFiles();
        return files;
    }
}

