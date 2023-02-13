package QueueRepresentation;

import Model.SearchInput;
import Model.SearchResult;
import Service.WordFinderImplementation;
import org.Main.ThreadHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ConsumerForQueue implements Runnable {
    private BlockingQueue<File> queueOfFilePaths;
    private WordFinderImplementation wordFinderImplementation = new WordFinderImplementation();
    private SearchInput searchInput;
    private SearchResult searchResult;
    List<SearchResult> listOfSearchResult=new ArrayList<>();
    private boolean consumerFlag;

    public ConsumerForQueue(BlockingQueue<File> queueOfFilePaths, SearchInput searchInput) {
        this.queueOfFilePaths = queueOfFilePaths;
        this.searchInput = searchInput;
    }

    public ConsumerForQueue() {

    }

    public ConsumerForQueue(boolean consumerFlag) {
        this.consumerFlag = consumerFlag;
    }

    public void consume(){
        int size = queueOfFilePaths.size();
        while (!consumerFlag && size != 0) {
            for (int index = 0; index < size; index++) {
                searchInput.setWordName(searchInput.getWordName());
                try {
                    searchInput.setFilePath(queueOfFilePaths.take().toString());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                searchResult = wordFinderImplementation.searchWord(searchInput);
                listOfSearchResult.add(searchResult);
            }
            synchronized (this){
//                System.out.println("List--->>>> "+listOfSearchResult);

//                System.out.println(searchResult);
            }
        }
    }


    @Override
    public void run() {
//        int size = queueOfFilePaths.size();
//        while (!consumerFlag && size != 0) {
//            for (int index = 0; index < size; index++) {
//                searchInput.setWordName(searchInput.getWordName());
//                try {
//                    searchInput.setFilePath(queueOfFilePaths.take().toString());
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                searchResult = wordFinderImplementation.searchWord(searchInput);
//            }
//            synchronized (this) {
//                System.out.println(searchResult);
//            }
//        }
        consume();
    }

    public List<SearchResult> getListOfSearchResult() {
        return this.listOfSearchResult;
    }
}
