package org.Main;

import Model.SearchInput;
import Model.SearchResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;;

import java.util.Scanner;


public class Main {

    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        SearchInput searchInput = new SearchInput("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles");
        int noOfThreads;
        SearchResult searchResult;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the 0 to run this application with multi thread." + "\n" + "Enter 1 to run this application with single threads " + "\n" + "\n" + "Enter your choice");
        int choice = scanner.nextInt();
        switch (choice) {
            case 0:
                logger.info("Case " + choice + " is choosed i.e., multiThreading is choosen");
                System.out.println("Enter the number of threads you want to consume: ");
                noOfThreads = scanner.nextInt();
                logger.info("User selected " + noOfThreads + "-Number of Consumer Threads");
                MultiThreadWordSearch multiThreadWordSearch = new MultiThreadWordSearch(searchInput, noOfThreads);
                searchResult = multiThreadWordSearch.wordFinderInDirectory();
                System.out.println(searchResult);
                break;
            case 1:
                logger.info("Case " + choice + " is choosed i.e., Single Thread is choosen");
                SingleThreadWordSearch singleThreadWordSearch = new SingleThreadWordSearch(searchInput);
                searchResult = singleThreadWordSearch.wordFinderInDirectory();
                System.out.println(searchResult);
                break;
            default:
                System.out.println("Please enter 0 or 1");
                logger.info("User has choosen the case other than case-0 or case-1");
        }


    }
}