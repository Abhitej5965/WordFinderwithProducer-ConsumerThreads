package com.wavemaker.service;

import com.wavemaker.model.IndividualSearchResult;
import com.wavemaker.model.SearchInput;
import com.wavemaker.model.SearchResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

public class SimpleWordSearch implements WordSearch {
    private Logger logger = LogManager.getLogger(SimpleWordSearch.class);

    @Override
    public SearchResult wordFinderInDirectory(SearchInput searchInput) {
        int lengthOfFilePath = searchInput.getFilePath().length();
        logger.info("Single Thread Word Search Operation Started");
        WordFinderUtil wordFinderUtil = new WordFinderUtil();
        SearchResult searchResult = wordFinderUtil.searchWord(searchInput, lengthOfFilePath);
        List<IndividualSearchResult> listOfIndividualSearchResults = searchResult.getIndividualSearchResults();
        Collections.sort(listOfIndividualSearchResults);
        searchResult.setIndividualSearchResults(listOfIndividualSearchResults);
        logger.info("Single Thread Word Search Operation ended");
        return searchResult;
    }
}

