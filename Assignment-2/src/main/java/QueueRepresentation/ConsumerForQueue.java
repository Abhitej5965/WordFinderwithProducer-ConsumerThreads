package QueueRepresentation;

import Model.SearchInput;
import Model.SearchResult;
import Service.WordFinderUtil;
import org.Main.MultiThreadWordSearch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ConsumerForQueue implements Runnable {
    private BlockingQueue<File> queueOfFilePaths;
    private WordFinderUtil wordFinderUtil = new WordFinderUtil();
    private SearchInput searchInput;
    private SearchResult searchResult;
    private boolean consumerFlag;

    private Logger logger = LogManager.getLogger(ConsumerForQueue.class);

    public ConsumerForQueue(BlockingQueue<File> queueOfFilePaths, SearchInput searchInput) {
        this.queueOfFilePaths = queueOfFilePaths;
        this.searchInput = searchInput;
    }

    public ConsumerForQueue(boolean consumerFlag) {
        this.consumerFlag = consumerFlag;
    }

    public void consume() {
        while (queueOfFilePaths.size() != 0) {
//        for(int i=0;i<size;i++) {
            try {
                searchInput.setFilePath(queueOfFilePaths.take().toString());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            searchResult = wordFinderUtil.searchWord(searchInput);
//        }
        }
    }


    @Override
    public void run() {
        logger.info("Consumer Thread -> " + Thread.currentThread().getName() + " started");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        consume();
        logger.info("Consumer Thread -> " + Thread.currentThread().getName() + " ended");
    }

    public SearchResult getSearchResult() {
        return searchResult;
    }
}
