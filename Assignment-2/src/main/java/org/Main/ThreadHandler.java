package org.Main;

import Model.SearchInput;
import Model.SearchResult;
import QueueRepresentation.ConsumerForQueue;
import QueueRepresentation.ProducerForQueue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadHandler {
    static List<SearchResult> listOfSearchResult = new ArrayList<>();
    private static SearchResult searchResult;

    public static void setListOfSearchResult(List<SearchResult> listOfSearchResult) {
        ThreadHandler.listOfSearchResult = listOfSearchResult;
    }

    public static SearchResult mainMethodForTesting(int noOfThreadsYouWant) throws InterruptedException {
        SearchInput searchInput = new SearchInput("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles");
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter the number of threads you want to consume: ");
        int noOfThreads = noOfThreadsYouWant;
        BlockingQueue<File> queue = new ArrayBlockingQueue<>(10);
        ProducerForQueue producerForQueue = new ProducerForQueue(queue, searchInput);
        ConsumerForQueue consumerForQueue = new ConsumerForQueue(queue, searchInput);
        Thread thread1 = new Thread(producerForQueue);
        thread1.start();
        Thread[] consumerThreads = new Thread[noOfThreads];
        for (int threadIndex = 0; threadIndex < noOfThreads; threadIndex++) {
            consumerThreads[threadIndex] = new Thread(consumerForQueue);
            consumerThreads[threadIndex].start();
//             listOfSearchResult.add(consumerForQueue.getSearchResult());
//            listOfSearchResult = consumerForQueue.getListOfSearchResult();
        }
        for (int threadIndex = 0; threadIndex < noOfThreads; threadIndex++) {
            consumerThreads[threadIndex].sleep(100);
        }
        listOfSearchResult=consumerForQueue.getListOfSearchResult();
//        System.out.println("List------------------->>>>>>>>"+listOfSearchResult);
        return listOfSearchResult.get(0);
    }
}
