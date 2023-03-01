package com.wavemaker.service;

import com.wavemaker.main.Main;
import com.wavemaker.model.IndividualSearchResult;
import com.wavemaker.model.SearchInput;
import com.wavemaker.model.SearchResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class WordFinderUtil {

    private static final Logger logger = LogManager.getLogger(Main.class);
    private int wordFoundInFiles = 0;
    private List<IndividualSearchResult> listOfIndividualSearchResults = Collections.synchronizedList(new ArrayList<>());
    private IndividualSearchResult individualSearchResult = null;
    private int flag = 0;

    public SearchResult searchWord(SearchInput searchInput, int lengthOfFilePath) {
        SearchResult searchResult = new SearchResult();
        String word = searchInput.getSearchKey();
        File individualFile = new File(searchInput.getFilePath());
        boolean flag1 = false;
        try {
            if (individualFile.isFile()) {
                int flag = 0;
                logger.info("Searching for word {} in the file {}", searchInput.getSearchKey(), searchInput.getFilePath());
                int wordLength = searchInput.getSearchKey().length();
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
                            flag1 = true;
                            String filePath = individualFile.getPath();
                            String actualPath = filePath.substring(lengthOfFilePath, filePath.length());
                            individualSearchResult = new IndividualSearchResult(actualPath, lineNumber, i, Thread.currentThread().getName());
                            listOfIndividualSearchResults.add(individualSearchResult);
                        }
                    }
                }
                Collections.sort(listOfIndividualSearchResults);
                if (flag!=0) {
                    wordFoundInFiles++;
                }
            } else if (individualFile.isDirectory()) {
                searchInput.setSearchKey(searchInput.getSearchKey());
                for (File subFile : individualFile.listFiles()) {
                    searchInput.setFilePath(subFile.getPath());
                    searchWord(searchInput, lengthOfFilePath);
                }
            }
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
}

