import java.util.*;

public interface IShoppingItem {
    String getName();
    double getRating();
    List<String> getReviews();
    void addRating(int rating);
    void addReview(String review);
}
