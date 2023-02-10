package QueueRepresentation;

import Model.SearchInput;
import Model.SearchResult;
import Service.WordFinderImplementation;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class ProducerForQueue implements Runnable {


    private BlockingQueue<File> queueOfFilePaths;
    private int capacityOfQueue;

    private WordFinderImplementation wordFinderImplementation = new WordFinderImplementation();
    private SearchInput searchInput;
    private SearchResult searchResult;
    private File[] files;
    Thread t1 = new Thread(new ConsumerForQueue());
    private boolean producerFlag;

    public ProducerForQueue(BlockingQueue<File> queueOfFilePaths, SearchInput searchInput) {
        this.queueOfFilePaths = queueOfFilePaths;
        this.searchInput = searchInput;

    }

    ConsumerForQueue consumerForQueue = new ConsumerForQueue(producerFlag);

    @Override
    public void run() {
        files = wordFinderImplementation.takingFilesOneByOne(searchInput.getFilePath());
        capacityOfQueue = files.length;
        for (File individualFiles : files) {
            if (individualFiles.isFile()) {
                producerFlag = false;
                try {
                    queueOfFilePaths.put(individualFiles);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else if (individualFiles.isDirectory()) {
//                try {
//                    t1.join();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
                searchInput.setWordName(searchInput.getWordName());
                searchInput.setFilePath(individualFiles.getPath());
                producerFlag = true;
                run();
            }
        }
        producerFlag = true;
    }

}
