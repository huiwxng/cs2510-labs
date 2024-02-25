import java.util.*;

public abstract class ShoppingItem implements IShoppingItem {
    private String name;
    private double rating;
    private List<String> reviews;

    public ShoppingItem(String name) {
        this.name = name;
        this.rating = 0.0;
        this.reviews = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public List<String> getReviews() {
        return reviews;
    }

    @Override
    public void addRating(int rating) {
        this.rating = this.rating + rating / 2;
    }

    @Override
    public void addReview(String review) {
        reviews.add(review);
    }
}