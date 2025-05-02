package lk.javainstitute.ivision;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import lk.javainstitute.ivision.model.Review;

public class ReviewDialogueBox extends DialogFragment {
    private RatingBar ratingBar;
    private Button button;
    private EditText editTextReview;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private CollectionReference reviewCollection;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.review_dialogue_box, container, false);

        // Initialize UI components
        ratingBar = view.findViewById(R.id.ratingBar);
        button = view.findViewById(R.id.button);
        editTextReview = view.findViewById(R.id.editTextReview);

        // Initialize Firebase components
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        reviewCollection = db.collection("Review");

        // Style the rating bar
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP);

        // Set button click listener
        button.setOnClickListener(v -> submitReview(editTextReview, ratingBar));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Make dialog width match parent and height wrap content
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
        }
    }

    private void submitReview(EditText editTextReview, RatingBar ratingBar) {
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            Toast.makeText(requireContext(), "Please sign in to submit a review", Toast.LENGTH_SHORT).show();
            return;
        }

        String reviewText = editTextReview.getText().toString().trim();
        float rating = ratingBar.getRating();

        // Validate input
        if (reviewText.isEmpty()) {
            editTextReview.setError("Please write a review");
            return;
        }

        if (rating == 0) {
            Toast.makeText(requireContext(), "Please select a rating", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get user display name or fallback to email
        String userName = currentUser.getDisplayName();
        if (userName == null || userName.isEmpty()) {
            userName = currentUser.getEmail();
            if (userName != null) {
                userName = userName.split("@")[0]; // Use email prefix if no display name
            } else {
                userName = "Anonymous";
            }
        }

        // Create review with user data
        Review review = new Review(
                currentUser.getUid(),
                userName,
                reviewText,
                rating
        );

        reviewCollection.add(review)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(requireContext(), "Review submitted successfully", Toast.LENGTH_SHORT).show();
                    ratingBar.setRating(0);
                    editTextReview.setText("");
                    dismiss(); // Close the dialog after successful submission
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(requireContext(), "Failed to submit review: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("Firestore", "Error submitting review", e);
                });
    }
}