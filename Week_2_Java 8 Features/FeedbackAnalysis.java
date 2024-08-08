
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@FunctionalInterface
interface FeedbackFilter extends Predicate<Feedback> {
}

@FunctionalInterface
interface FeedbackProcessor extends Function<Feedback, String> {
}

class Feedback {

    private int feedbackId;
    private String customerName;
    private int rating;
    private String comments;

    public Feedback(int feedbackId, String customerName, int rating, String comments) {
        this.feedbackId = feedbackId;
        this.customerName = customerName;
        this.rating = rating;
        this.comments = comments;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getRating() {
        return rating;
    }

    public String getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "Feedback{" + "feedbackId=" + feedbackId + ", customerName='" + customerName + '\'' + ", rating=" + rating + ", comments='" + comments + '\'' + '}';
    }
}

public class FeedbackAnalysis {

    public static List<String> processFeedbacks(List<Feedback> feedbacks, FeedbackFilter filter, FeedbackProcessor processor) {
        return feedbacks.stream()
                .filter(filter)
                .map(processor)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Feedback> feedbacks = Arrays.asList(
                new Feedback(1, "Sagar", 5, "Excellent service"),
                new Feedback(2, "Jyotin", 3, "Average experience"),
                new Feedback(3, "Aditya", 2, "Not satisfied"),
                new Feedback(4, "Pradhyumn", 4, "Good support"),
                new Feedback(5, "Abhiraj", 1, "Very poor service")
        );

        // Define a FeedbackFilter for rating greater than or equal to 4
        FeedbackFilter positiveFilter = feedback -> feedback.getRating() >= 4;

        // Define a FeedbackProcessor to extract customer names and comments
        FeedbackProcessor feedbackProcessor = feedback -> "Customer: " + feedback.getCustomerName() + ", Comments: " + feedback.getComments();

        // Process feedbacks
        List<String> processedFeedbacks = processFeedbacks(feedbacks, positiveFilter, feedbackProcessor);

        // Print the processed feedbacks
        System.out.println("Processed Feedbacks:");
        processedFeedbacks.forEach(System.out::println);

        // Count positive and negative feedbacks
        long positiveCount = feedbacks.stream().filter(feedback -> feedback.getRating() >= 4).count();
        long negativeCount = feedbacks.stream().filter(feedback -> feedback.getRating() < 4).count();

        System.out.println("\nCount of Positive Feedbacks: " + positiveCount);
        System.out.println("Count of Negative Feedbacks: " + negativeCount);
    }
}
