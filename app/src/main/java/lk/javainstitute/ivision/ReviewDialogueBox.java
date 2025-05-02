package lk.javainstitute.ivision;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReviewDialogueBox extends AppCompatActivity {
    private RatingBar ratingBar;
    private Button button;
    private TextView editTextReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_dialogue_box);

        ratingBar = findViewById(R.id.ratingBar);
        button = findViewById(R.id.button);
        editTextReview = findViewById(R.id.editTextReview);

        button.setOnClickListener(view -> {

            editTextReview.setText("You Rated : " + ratingBar.getRating());
            LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
            stars.getDrawable(2).setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP);
        });
    }
}
