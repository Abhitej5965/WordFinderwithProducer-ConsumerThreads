package org.Main;

import Model.SearchInput;
import QueueRepresentation.ConsumerForQueue;
import QueueRepresentation.ProducerForQueue;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class Main {

    public static void main(String[] args) {
        SearchInput searchInput = new SearchInput("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of threads you want to consume: ");
        int noOfThreads = scanner.nextInt();
        BlockingQueue<File> queue = new ArrayBlockingQueue<>(10);
        ProducerForQueue producerForQueue = new ProducerForQueue(queue, searchInput);
        ConsumerForQueue consumerForQueue = new ConsumerForQueue(queue, searchInput);
        Thread thread1 = new Thread(producerForQueue);
        thread1.start();
        Thread[] consumerThreads = new Thread[noOfThreads];
        for (int threadIndex = 0; threadIndex < noOfThreads; threadIndex++) {
            consumerThreads[threadIndex] = new Thread(consumerForQueue);
            consumerThreads[threadIndex].start();
        }
    }
}