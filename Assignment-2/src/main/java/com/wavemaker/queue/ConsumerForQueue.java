package com.wavemaker.queue;

import com.wavemaker.model.SearchInput;
import com.wavemaker.model.SearchResult;
import com.wavemaker.service.WordFinderUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class ConsumerForQueue implements Runnable {
    private BlockingQueue<File> queueOfFilePaths;
    private WordFinderUtil wordFinderUtil = new WordFinderUtil();
    private SearchInput searchInput;
    private SearchResult searchResult;
    private int lengthOfFilePath;
    private Thread producerThread;
    private Logger logger = LogManager.getLogger(ConsumerForQueue.class);

    public ConsumerForQueue(BlockingQueue<File> queueOfFilePaths, SearchInput searchInput, int lengthOfFilePath, Thread producerThread) {
        this.queueOfFilePaths = queueOfFilePaths;
        this.searchInput = searchInput;
        this.lengthOfFilePath = lengthOfFilePath;
        this.producerThread = producerThread;
    }


    @Override
    public void run() {
        logger.info("Consumer Thread -> " + Thread.currentThread().getName() + " started");
        consume();
        logger.info("Consumer Thread -> " + Thread.currentThread().getName() + " ended");
    }

    private void consume() {
        if (queueOfFilePaths.size() == 0) {
            try {
                producerThread.join(10);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        SearchInput searchInputToConsume;
        while (queueOfFilePaths.size() != 0) {
            try {
                searchInputToConsume = new SearchInput(searchInput.getSearchKey(), queueOfFilePaths.take().toString());
                logger.info(Thread.currentThread().getName() + " picked file path " + searchInputToConsume.getFilePath() + " from queue");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            searchResult = wordFinderUtil.searchWord(searchInputToConsume, lengthOfFilePath);
        }
    }

    public SearchResult getSearchResult() {
        return searchResult;
    }
}
