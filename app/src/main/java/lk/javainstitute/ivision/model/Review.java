package lk.javainstitute.ivision.model;

import com.google.firebase.Timestamp;

public class Review {
    private String id;
    private String userId;
    private String userName;
    private float rating;
    private String reviewText;
    private Timestamp timestamp;

    public Review() {}

    public Review(String userId, String userName, String reviewText, float rating) {
        this.userId = userId;
        this.userName = userName;
        this.reviewText = reviewText;
        this.rating = rating;
        this.timestamp = Timestamp.now();
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}