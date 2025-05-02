//package lk.javainstitute.ivision;
//
//import android.graphics.Color;
//import android.graphics.PorterDuff;
//import android.graphics.drawable.LayerDrawable;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.RatingBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.firebase.Firebase;
//import com.google.firebase.firestore.CollectionReference;
//import com.google.firebase.firestore.FirebaseFirestore;
//
//import lk.javainstitute.ivision.model.Review;
//
//public class ReviewDialogueBox extends AppCompatActivity {
//    private RatingBar ratingBar;
//    private Button button;
//    private TextView editTextReview;
//    private FirebaseFirestore db;
//
//    private CollectionReference reviCollection;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.review_dialogue_box);
//
//        ratingBar = findViewById(R.id.ratingBar);
//        button = findViewById(R.id.button);
//        editTextReview = findViewById(R.id.editTextReview);
//
//        db=FirebaseFirestore.getInstance();
//        reviCollection=db.collection("Review");
//
//
//        button.setOnClickListener(view -> {
//
//            editTextReview.setText("You Rated : " + ratingBar.getRating());
//            LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
//            stars.getDrawable(2).setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP);
//        });
//    }
//
//    private void submitReview(EditText editTextReview, RatingBar ratingBar){
//        String reviewText=editTextReview.getText().toString();
//        float rating=ratingBar.getRating();
//
//        Review review=new Review(reviewText,rating);
//        reviCollection.add(review).addOnSuccessListener(documentReference -> {
//            Toast.makeText(this, "Review submitted successfully", Toast.LENGTH_SHORT).show();
//            ratingBar.setRating(0);
//            reviewText.setText("");
//        }).addOnFailureListener(e -> {
//            Toast.makeText(this, "Failed to submit review", Toast.LENGTH_SHORT).show();
//        });
//
//        }
//    }
//}
