package org.Main;

import Model.IndividualSearchResult;
import Model.SearchInput;
import Model.SearchResult;
import QueueRepresentation.ConsumerForQueue;
import QueueRepresentation.ProducerForQueue;
import Service.WordSearch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MultiThreadWordSearch implements WordSearch {
    static List<IndividualSearchResult> listOfSearchResult = Collections.synchronizedList(new ArrayList<>());
    private SearchResult searchResult;
    private SearchInput searchInput;
    private int noOfThreads;
    private Logger logger = LogManager.getLogger(MultiThreadWordSearch.class);

    public MultiThreadWordSearch(SearchInput searchInput, int noOfThreads) {
        this.searchInput = searchInput;
        this.noOfThreads = noOfThreads;
    }

    @Override
    public SearchResult wordFinderInDirectory() {
        BlockingQueue<File> queue = new ArrayBlockingQueue<>(20);
        ProducerForQueue producerForQueue = new ProducerForQueue(queue, searchInput);
        ConsumerForQueue consumerForQueue = new ConsumerForQueue(queue, searchInput);
        Thread thread1 = new Thread(producerForQueue);
        thread1.start();
        Thread[] consumerThreads = new Thread[noOfThreads];
        for (int threadIndex = 0; threadIndex < noOfThreads; threadIndex++) {
            consumerThreads[threadIndex] = new Thread(consumerForQueue);
            consumerThreads[threadIndex].start();
        }
        for (int threadIndex = 0; threadIndex < noOfThreads; threadIndex++) {
            try {
                consumerThreads[threadIndex].sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        searchResult = consumerForQueue.getSearchResult();
        List<IndividualSearchResult> individualSearchResultList = Collections.synchronizedList(new ArrayList<>());
        individualSearchResultList = searchResult.getIndividualSearchResults();
        Collections.sort(individualSearchResultList, new Comparator<IndividualSearchResult>() {
            @Override
            public int compare(IndividualSearchResult o1, IndividualSearchResult o2) {
                return o1.compareTo(o2);
            }
        });
        searchResult.setIndividualSearchResults(individualSearchResultList);
        return searchResult;
    }

}
