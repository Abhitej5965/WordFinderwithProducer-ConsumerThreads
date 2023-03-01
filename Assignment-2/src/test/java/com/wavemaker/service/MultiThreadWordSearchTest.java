package com.wavemaker.service;

import java.io.IOException;

public class MultiThreadWordSearchTest extends WordSearchTest {

    public MultiThreadWordSearchTest() {
        super(new MultiThreadWordSearch(20));
    }

}
