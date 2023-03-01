package com.wavemaker.main;

import com.wavemaker.model.SearchInput;
import com.wavemaker.model.SearchResult;
import com.wavemaker.service.MultiThreadWordSearch;
import com.wavemaker.service.SimpleWordSearch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;


public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the word you want to search: ");
        String searchableWord = scanner.nextLine();
        logger.info("The word user searched for: " + searchableWord);
        SearchInput searchInput = new SearchInput(searchableWord, "src/test/resources/testcase1/AllFolders");
        int noOfThreads;
        SearchResult searchResult;
        System.out.println("Enter the 1 to run this application with multi thread." + "\n" + "Enter 2 to run this application with single threads " + "\n" + "\n" + "Enter your choice");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                logger.info("Case " + choice + " is choosed i.e., multiThreading is chosen");
                System.out.println("Enter the number of threads you want to consume: ");
                noOfThreads = scanner.nextInt();
                logger.info("User selected " + noOfThreads + "-Number of Consumer Threads");
                MultiThreadWordSearch multiThreadWordSearch = new MultiThreadWordSearch(noOfThreads);
                searchResult = multiThreadWordSearch.wordFinderInDirectory(searchInput);
                System.out.println(searchResult);
                logger.info(searchResult);
                System.out.println("Total Results->" + searchResult.getIndividualSearchResults().size());
                break;
            case 2:
                logger.info("Case " + choice + " is chosed i.e., Single Thread is chosen");
                SimpleWordSearch simpleWordSearch = new SimpleWordSearch();
                searchResult = simpleWordSearch.wordFinderInDirectory(searchInput);
                System.out.println(searchResult);
                logger.info(searchResult);
                System.out.println("Total Results->" + searchResult.getIndividualSearchResults().size());
                break;
            default:
                System.out.println("Please enter 1 or 2");
                logger.info("User has chosen the case other than case-1 or case-2");
        }
    }
}