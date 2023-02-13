package org.Main;

import Model.SearchResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) throws InterruptedException {
        ThreadHandler threadHandler = new ThreadHandler();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of threads you want to consume: ");
        int noOfThreads = scanner.nextInt();
        SearchResult searchResult=new SearchResult();
        searchResult=ThreadHandler.mainMethodForTesting(noOfThreads);
        System.out.println(searchResult);

    }

}