package org.Main;

import Model.IndividualSearchResult;
import Model.SearchInput;
import Model.SearchResult;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class WordSearchTest {

    public List<IndividualSearchResult> listingIndividualSearchResults() {
        IndividualSearchResult individualSearchResult1 = new IndividualSearchResult("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles0/file1.txt", 1, 47);
        IndividualSearchResult individualSearchResult2 = new IndividualSearchResult("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles0/file1.txt", 1, 95);
        IndividualSearchResult individualSearchResult3 = new IndividualSearchResult("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles0/file1.txt", 1, 209);
        IndividualSearchResult individualSearchResult4 = new IndividualSearchResult("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles0/file1.txt", 2, 10);
        IndividualSearchResult individualSearchResult5 = new IndividualSearchResult("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles0/file1.txt", 2, 105);
        IndividualSearchResult individualSearchResult6 = new IndividualSearchResult("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles0/file2.txt", 1, 37);
        IndividualSearchResult individualSearchResult7 = new IndividualSearchResult("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles0/file2.txt", 1, 271);
        List<IndividualSearchResult> listOfIndividualSearchResult = new ArrayList<>();
        listOfIndividualSearchResult.add(individualSearchResult1);
        listOfIndividualSearchResult.add(individualSearchResult2);
        listOfIndividualSearchResult.add(individualSearchResult3);
        listOfIndividualSearchResult.add(individualSearchResult4);
        listOfIndividualSearchResult.add(individualSearchResult5);
        listOfIndividualSearchResult.add(individualSearchResult6);
        listOfIndividualSearchResult.add(individualSearchResult7);
        return listOfIndividualSearchResult;
    }

    public List<IndividualSearchResult> listingIndividualSearchResultTest2() {
        IndividualSearchResult individualSearchResult1 = new IndividualSearchResult("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles/file1.txt", 1, 47);
        IndividualSearchResult individualSearchResult2 = new IndividualSearchResult("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles/file1.txt", 1, 95);
        IndividualSearchResult individualSearchResult3 = new IndividualSearchResult("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles/file1.txt", 1, 209);
        IndividualSearchResult individualSearchResult4 = new IndividualSearchResult("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles/file1.txt", 2, 10);
        IndividualSearchResult individualSearchResult5 = new IndividualSearchResult("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles/file1.txt", 2, 105);
        IndividualSearchResult individualSearchResult6 = new IndividualSearchResult("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles/file2.txt", 1, 37);
        IndividualSearchResult individualSearchResult7 = new IndividualSearchResult("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles/file2.txt", 1, 271);
        IndividualSearchResult individualSearchResult8 = new IndividualSearchResult("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles/test/file3.txt", 1, 47);
        IndividualSearchResult individualSearchResult9 = new IndividualSearchResult("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles/test/file3.txt", 1, 95);
        IndividualSearchResult individualSearchResult10 = new IndividualSearchResult("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles/test/file3.txt", 1, 209);
        IndividualSearchResult individualSearchResult11 = new IndividualSearchResult("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles/test/file3.txt", 2, 10);
        IndividualSearchResult individualSearchResult12 = new IndividualSearchResult("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles/test/file3.txt", 2, 105);
        IndividualSearchResult individualSearchResult13 = new IndividualSearchResult("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles/test/file4.txt", 1, 37);
        IndividualSearchResult individualSearchResult14 = new IndividualSearchResult("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles/test/file4.txt", 1, 271);
        List<IndividualSearchResult> listOfIndividualSearchResult = new ArrayList<>();
        listOfIndividualSearchResult.add(individualSearchResult1);
        listOfIndividualSearchResult.add(individualSearchResult2);
        listOfIndividualSearchResult.add(individualSearchResult3);
        listOfIndividualSearchResult.add(individualSearchResult4);
        listOfIndividualSearchResult.add(individualSearchResult5);
        listOfIndividualSearchResult.add(individualSearchResult6);
        listOfIndividualSearchResult.add(individualSearchResult7);
        listOfIndividualSearchResult.add(individualSearchResult8);
        listOfIndividualSearchResult.add(individualSearchResult9);
        listOfIndividualSearchResult.add(individualSearchResult10);
        listOfIndividualSearchResult.add(individualSearchResult11);
        listOfIndividualSearchResult.add(individualSearchResult12);
        listOfIndividualSearchResult.add(individualSearchResult13);
        listOfIndividualSearchResult.add(individualSearchResult14);
        return listOfIndividualSearchResult;
    }

    @Test
    public void test1MultiThreadWordFinder() {
        List<IndividualSearchResult> listForTestingMultiThread;
        listForTestingMultiThread = listingIndividualSearchResults();
        SearchResult searchResult = new SearchResult(2, listForTestingMultiThread);
        SearchInput searchInput = new SearchInput("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles0");
        int noOfThreads = 5;
        MultiThreadWordSearch multiThreadWordSearch = new MultiThreadWordSearch(searchInput, noOfThreads);
        Assert.assertEquals(searchResult.toString().trim(), multiThreadWordSearch.wordFinderInDirectory().toString().trim());
    }

    @Test
    public void test1SingleThreadWordFinder() {
        List<IndividualSearchResult> listForTestingSingleThread;
        listForTestingSingleThread = listingIndividualSearchResults();
        SearchResult searchResult = new SearchResult(2, listForTestingSingleThread);
        SearchInput searchInput = new SearchInput("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles0");
        SingleThreadWordSearch singleThreadWordSearch = new SingleThreadWordSearch(searchInput);
        Assert.assertEquals(searchResult.toString().trim(), singleThreadWordSearch.wordFinderInDirectory().toString().trim());
    }

    @Test
    public void test2MultiThreadWordFinder() {
        List<IndividualSearchResult> listForTestingMultiThread;
        listForTestingMultiThread = listingIndividualSearchResultTest2();
        SearchResult searchResult = new SearchResult(4, listForTestingMultiThread);
        SearchInput searchInput = new SearchInput("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles");
        int noOfThreads = 5;
        MultiThreadWordSearch multiThreadWordSearch = new MultiThreadWordSearch(searchInput, noOfThreads);
        Assert.assertEquals(searchResult.toString().trim(), multiThreadWordSearch.wordFinderInDirectory().toString().trim());
    }

    @Test
    public void test2SingleThreadWordFinder() {
        List<IndividualSearchResult> listForTestingSingleThread;
        listForTestingSingleThread = listingIndividualSearchResultTest2();
        SearchResult searchResult = new SearchResult(4, listForTestingSingleThread);
        SearchInput searchInput = new SearchInput("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles");
        SingleThreadWordSearch singleThreadWordSearch = new SingleThreadWordSearch(searchInput);
        Assert.assertEquals(searchResult.toString().trim(), singleThreadWordSearch.wordFinderInDirectory().toString().trim());
    }
}
