package com.wavemaker.service;

import com.wavemaker.model.SearchInput;
import com.wavemaker.model.SearchResult;

public interface WordSearch {
    public SearchResult wordFinderInDirectory(SearchInput searchInput) throws InterruptedException;
}
