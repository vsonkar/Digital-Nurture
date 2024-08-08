
import java.util.Map;
import java.util.concurrent.*;

public class WebCrawler {

    public static void main(String[] args) {
        int numberOfThreads = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        ConcurrentHashMap<String, String> crawledData = new ConcurrentHashMap<>();

        String[] urls = {"www.geeksforgeeks.com", "www.gitam.edu", "www.onlinegdb.com"};

        for (String url : urls) {
            executorService.submit(new CrawlerTask(url, crawledData));
        }

        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }

        System.out.println("Crawled Data:");
        for (Map.Entry<String, String> entry : crawledData.entrySet()) {
            System.out.println("URL: " + entry.getKey() + " | Content: " + entry.getValue());
        }
    }
}

class CrawlerTask implements Runnable {

    private final String url;
    private final ConcurrentHashMap<String, String> crawledData;

    public CrawlerTask(String url, ConcurrentHashMap<String, String> crawledData) {
        this.url = url;
        this.crawledData = crawledData;
    }

    @Override
    public void run() {
        try {
            String content = retrievePage(url);

            crawledData.put(url, content);
            System.out.println(Thread.currentThread().getName() + " completed crawling: " + url);
        } catch (Exception e) {
            System.err.println("Error crawling " + url + ": " + e.getMessage());
        }
    }

    private String retrievePage(String url) throws InterruptedException {
        Thread.sleep(1000);
        return "Content from " + url;
    }
}
