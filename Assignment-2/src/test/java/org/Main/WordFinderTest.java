package org.Main;

import Model.IndividualSearchResult;
import Model.SearchResult;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class WordFinderTest {

    @Test
    public void testWordFinder() throws InterruptedException {
        IndividualSearchResult individualSearchResult1 = new IndividualSearchResult("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles/file1.txt", 1, 47);
        IndividualSearchResult individualSearchResult2 = new IndividualSearchResult("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles/file1.txt", 1, 95);
        IndividualSearchResult individualSearchResult3 = new IndividualSearchResult("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles/file1.txt", 1, 209);
        IndividualSearchResult individualSearchResult4 = new IndividualSearchResult("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles/file1.txt", 2, 10);
        IndividualSearchResult individualSearchResult5 = new IndividualSearchResult("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles/file1.txt", 2, 105);
        IndividualSearchResult individualSearchResult6 = new IndividualSearchResult("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles/file1.txt", 3, 19);
        IndividualSearchResult individualSearchResult7 = new IndividualSearchResult("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles/file2.txt", 1, 37);
        IndividualSearchResult individualSearchResult8 = new IndividualSearchResult("platform", "/home/abhitejk_500313/Desktop/ImportantFolders/ImportantFiles/file2.txt", 1, 271);
        List<IndividualSearchResult> listOfIndividualSearchResult = new ArrayList<>();
        listOfIndividualSearchResult.add(individualSearchResult1);
        listOfIndividualSearchResult.add(individualSearchResult2);
        listOfIndividualSearchResult.add(individualSearchResult3);
        listOfIndividualSearchResult.add(individualSearchResult4);
        listOfIndividualSearchResult.add(individualSearchResult5);
        listOfIndividualSearchResult.add(individualSearchResult6);
        listOfIndividualSearchResult.add(individualSearchResult7);
        listOfIndividualSearchResult.add(individualSearchResult8);
        SearchResult searchResult = new SearchResult(2, listOfIndividualSearchResult);
        Assert.assertEquals(searchResult.toString().trim(), ThreadHandler.mainMethodForTesting(3).toString().trim());
    }
}
