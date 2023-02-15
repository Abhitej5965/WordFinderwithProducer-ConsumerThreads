package QueueRepresentation;

import Model.SearchInput;
import Service.WordFinderUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class ProducerForQueue implements Runnable {


    private BlockingQueue<File> queueOfFilePaths;

    private WordFinderUtil wordFinderUtil = new WordFinderUtil();
    private SearchInput searchInput;
    private File file;

    private Logger logger= LogManager.getLogger(ProducerForQueue.class);
    private boolean producerFlag;

    public ProducerForQueue(BlockingQueue<File> queueOfFilePaths, SearchInput searchInput) {
        this.queueOfFilePaths = queueOfFilePaths;
        this.searchInput = searchInput;

    }

    ConsumerForQueue consumerForQueue = new ConsumerForQueue(producerFlag);


    @Override
    public void run() {
        logger.info("Producer Thread started");
        file = new File(searchInput.getFilePath());
        logger.info("Producer Thread started pushing files into queue");
        produce(file);
        logger.info("Producer Thread stopped pushing files into queue, as all the files are pushed into queue");
        producerFlag = true;
        logger.info("Producer Thread ended");
    }

    public void produce(File requiredFile) {
        if (requiredFile.isFile()) {
            producerFlag = false;
            try {
                queueOfFilePaths.put(requiredFile);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else if (requiredFile.isDirectory()) {
            for (File subfile : requiredFile.listFiles()) {
                produce(subfile);
            }
        }
    }
}
