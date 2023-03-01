package com.wavemaker.queue;

import com.wavemaker.model.SearchInput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class

ProducerForQueue implements Runnable {


    private BlockingQueue<File> queueOfFilePaths;
    private SearchInput searchInput;
    private Logger logger = LogManager.getLogger(ProducerForQueue.class);

    public ProducerForQueue(BlockingQueue<File> queueOfFilePaths, SearchInput searchInput) {
        this.queueOfFilePaths = queueOfFilePaths;
        this.searchInput = searchInput;
    }

    @Override
    public void run() {
        logger.info("Producer Thread started");
        File file = new File(searchInput.getFilePath());
        logger.info("Producer Thread started pushing files into queue");
        addFilesToQueue(file);
        logger.info("Producer Thread stopped pushing files into queue, as all the files are pushed into queue");
        logger.info("Producer Thread ended");
    }

    private void addFilesToQueue(File requiredFile) {
        if (requiredFile.isFile()) {
            try {
                queueOfFilePaths.put(requiredFile);
                logger.info("Producer Thread pushed file path " + requiredFile + " into queue");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else if (requiredFile.isDirectory()) {
            for (File subfile : requiredFile.listFiles()) {
                addFilesToQueue(subfile);
            }
        }
    }
}
