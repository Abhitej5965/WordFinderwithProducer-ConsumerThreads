package org.Main;

import Model.IndividualSearchResult;
import Model.SearchInput;
import Model.SearchResult;
import Service.WordFinderUtil;
import Service.WordSearch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SingleThreadWordSearch implements WordSearch {
    private SearchInput searchInput;
    List<IndividualSearchResult> listOfIndividualSearchResults = new ArrayList<>();
    private int wordFoundInFiles = 0;
    private SearchResult searchResult = new SearchResult();
    private Logger logger= LogManager.getLogger(SingleThreadWordSearch.class);

    public SingleThreadWordSearch(SearchInput searchInput) {
        this.searchInput = searchInput;
    }

    @Override
    public SearchResult wordFinderInDirectory() {
        logger.info("Single Thread Word Search Operation Started");
        WordFinderUtil wordFinderUtil = new WordFinderUtil();
        searchResult = wordFinderUtil.searchWord(searchInput);
        listOfIndividualSearchResults = searchResult.getIndividualSearchResults();
        Collections.sort(listOfIndividualSearchResults, new Comparator<IndividualSearchResult>() {
            @Override
            public int compare(IndividualSearchResult o1, IndividualSearchResult o2) {
                return o1.compareTo(o2);
            }
        });
        searchResult.setIndividualSearchResults(listOfIndividualSearchResults);
        logger.info("Single Thread Word Search Operation ended");
        return searchResult;
    }
}

