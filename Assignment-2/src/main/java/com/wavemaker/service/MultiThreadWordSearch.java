package com.wavemaker.service;

import com.wavemaker.model.SearchInput;
import com.wavemaker.model.SearchResult;
import com.wavemaker.queue.ConsumerForQueue;
import com.wavemaker.queue.ProducerForQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MultiThreadWordSearch implements WordSearch {
    private int noOfThreads;
    private final Logger logger = LogManager.getLogger(MultiThreadWordSearch.class);
    Thread mainThread;

    private int lengthOfFilePath;

    public MultiThreadWordSearch(int noOfThreads) {
        this.noOfThreads = noOfThreads;
    }

    @Override
    public SearchResult wordFinderInDirectory(SearchInput searchInput) {
        lengthOfFilePath = searchInput.getFilePath().length();
        mainThread = Thread.currentThread();
        BlockingQueue<File> queue = new ArrayBlockingQueue<>(20);
        ProducerForQueue producerForQueue = new ProducerForQueue(queue, searchInput);
        Thread thread1 = new Thread(producerForQueue);
        ConsumerForQueue consumerForQueue = new ConsumerForQueue(queue, searchInput, lengthOfFilePath, thread1);
        thread1.start();
        Thread[] consumerThreads = new Thread[noOfThreads];
        for (int threadIndex = 0; threadIndex < noOfThreads; threadIndex++) {
            consumerThreads[threadIndex] = new Thread(consumerForQueue);
            consumerThreads[threadIndex].start();
        }
        for (Thread consumer : consumerThreads) {
            try {
                consumer.join(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        SearchResult searchResult = consumerForQueue.getSearchResult();
        return searchResult;
    }
}
