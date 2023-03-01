package com.wavemaker.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wavemaker.model.SearchInput;
import com.wavemaker.model.SearchResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.util.stream.Stream;

public class WordSearchTest {

    private WordSearch wordSearch;
    ObjectMapper objectMapper = new ObjectMapper();


    public WordSearchTest(WordSearch wordSearch) {
        this.wordSearch = wordSearch;
    }

    @ParameterizedTest
    @MethodSource("objectProvider")
    public void test(SearchInput searchInput, String filePath) {
        try {
            JsonNode jsonNode = objectMapper.readTree(new File(filePath));
            String expectedSearchResult = jsonNode.get("data").asText();
            SearchResult actualSearchResult = wordSearch.wordFinderInDirectory(searchInput);
            Assertions.assertEquals(expectedSearchResult, actualSearchResult.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Stream<Arguments> objectProvider() {
        return Stream.of(
                Arguments.of(new SearchInput("platform", "src/test/resources/testcase1/AllFolders"), "src/test/resources/testcase1/test1.json"),
                Arguments.of(new SearchInput("and", "src/test/resources/testcase2/AllFolders"), "src/test/resources/testcase2/test2.json")
        );
    }
}
