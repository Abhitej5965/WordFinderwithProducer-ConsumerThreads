package QueueRepresentation;

import Model.SearchInput;
import Model.SearchResult;
import Service.WordFinderImplementation;

import java.io.File;
import java.util.Stack;
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
//    public void run() {
//        files = wordFinderImplementation.takingFilesOneByOne(searchInput.getFilePath());
//        capacityOfQueue = files.length;
//        for (File individualFiles : files) {
//            if (individualFiles.isFile()) {
//                producerFlag = false;
//                try {
//                    queueOfFilePaths.put(individualFiles);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            } else if (individualFiles.isDirectory()) {
//                requiredMethod(individualFiles);
//            }
//        }
//        producerFlag = true;
//}
//    public void run() {
//        files = wordFinderImplementation.takingFilesOneByOne(searchInput.getFilePath());
//        capacityOfQueue = files.length;
//        Stack<File> stack = new Stack<>();
//        for (File individualFiles : files) {
//            stack.push(individualFiles);
//        }
//
//        while (!stack.isEmpty()) {
//            File individualFiles = stack.pop();
//            if (individualFiles.isFile()) {
//                producerFlag = false;
//                try {
//                    queueOfFilePaths.put(individualFiles);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            } else if (individualFiles.isDirectory()) {
//                File[] subFiles = individualFiles.listFiles();
//                for (File subFile : subFiles) {
//                    stack.push(subFile);
//                }
//            }
//        }
//        producerFlag = true;
//    }
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
                processDirectory(individualFiles);
            }
        }
        producerFlag = true;
    }

    private void processDirectory(File directory) {
        File[] subFiles = wordFinderImplementation.takingFilesOneByOne(directory.getPath());
        for (File subFile : subFiles) {
            if (subFile.isFile()) {
                try {
                    queueOfFilePaths.put(subFile);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else if (subFile.isDirectory()) {
                processDirectory(subFile);
            }
        }
    }
}
