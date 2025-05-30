package lk.javainstitute.ivision;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class Review_Fragment extends Fragment {

    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_review, container, false);

        button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popUpReviewDialogueBox();
            }
        });

        return view;
    }

    private void popUpReviewDialogueBox() {
        DialogFragment reviewDialogueBox = new ReviewDialogueBox();
        reviewDialogueBox.show(getChildFragmentManager(), "ReviewDialogueBox");
    }
}