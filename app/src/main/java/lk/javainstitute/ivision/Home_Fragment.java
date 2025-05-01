package lk.javainstitute.ivision;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Home_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_fragment, container, false);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("USERPREF", getContext().MODE_PRIVATE);
        String userId = sharedPreferences.getString("Logged_userId", "");

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        firestore.collection("User").document(userId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                TextView name = view.findViewById(R.id.user_name);
                name.setText(documentSnapshot.getString("name"));

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                new Alert().showAlert(getContext(),"Opps","Somthing Went Wrong." );
            }
        });

        LinearLayout appointmentCard = view.findViewById(R.id.apponment);

        appointmentCard.setOnClickListener(v -> {
            Fragment appoinmentFragment = new Appoinment_Fragment();


            FragmentTransaction transaction = requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction() ;

            // Hide this fragment
            transaction.hide(Home_Fragment.this);

            // Add the detail fragment to your activity container (NOT the linear layout)
            // Make sure R.id.mainFragmentContainer is in your activity layout
            transaction.add(R.id.main_scrollview, appoinmentFragment);

            // Add to back stack so pressing BACK will remove detail and unhide this
            transaction.addToBackStack(null);

            // Commit
            transaction.commit();

        });

        LinearLayout glassesStatusCard = view.findViewById(R.id.glasses_status);

        glassesStatusCard.setOnClickListener(v -> {
            Fragment glassesStatusFragment = new Glass_Fragment();


            FragmentTransaction transaction = requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction();

            // Hide this fragment
            transaction.hide(Home_Fragment.this);

            // Add the detail fragment to your activity container (NOT the linear layout)
            // Make sure R.id.mainFragmentContainer is in your activity layout
            transaction.add(R.id.main_scrollview, glassesStatusFragment);

            // Add to back stack so pressing BACK will remove detail and unhide this
            transaction.addToBackStack(null);

            // Commit
            transaction.commit();

        });



        LinearLayout eyeTestCard = view.findViewById(R.id.eye_test);

        eyeTestCard.setOnClickListener(v -> {
            Fragment eyeTestFragment = new Eye_Test_Fragment();


            FragmentTransaction transaction = requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction();

            // Hide this fragment
            transaction.hide(Home_Fragment.this);

            // Add the detail fragment to your activity container (NOT the linear layout)
            // Make sure R.id.mainFragmentContainer is in your activity layout
            transaction.add(R.id.main_scrollview, eyeTestFragment);

            // Add to back stack so pressing BACK will remove detail and unhide this
            transaction.addToBackStack(null);

            // Commit
            transaction.commit();


        });




        LinearLayout eyeCareCard = view.findViewById(R.id.eye_care);

        eyeCareCard.setOnClickListener(v -> {
            Fragment eyeCareFragment = new Eye_Care_Fragment();


            FragmentTransaction transaction = requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction();

            // Hide this fragment
            transaction.hide(Home_Fragment.this);

            // Add the detail fragment to your activity container (NOT the linear layout)
            // Make sure R.id.mainFragmentContainer is in your activity layout
            transaction.add(R.id.main_scrollview, eyeCareFragment);

            // Add to back stack so pressing BACK will remove detail and unhide this
            transaction.addToBackStack(null);

            // Commit
            transaction.commit();

        });




        LinearLayout appointmentHistoryCard = view.findViewById(R.id.appoiment_history);

        appointmentHistoryCard.setOnClickListener(v -> {
            Fragment appoinmenHistoryFragment = new Appoinmen_History_Fragment();


            FragmentTransaction transaction = requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction();

            // Hide this fragment
            transaction.hide(Home_Fragment.this);

            // Add the detail fragment to your activity container (NOT the linear layout)
            // Make sure R.id.mainFragmentContainer is in your activity layout
            transaction.add(R.id.main_scrollview, appoinmenHistoryFragment);

            // Add to back stack so pressing BACK will remove detail and unhide this
            transaction.addToBackStack(null);

            // Commit
            transaction.commit();

        });




        LinearLayout reviewCard = view.findViewById(R.id.review);

        reviewCard.setOnClickListener(v -> {
            Fragment reviewFragment = new Review_Fragment();


            FragmentTransaction transaction = requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction();

            // Hide this fragment
            transaction.hide(Home_Fragment.this);

            // Add the detail fragment to your activity container (NOT the linear layout)
            // Make sure R.id.mainFragmentContainer is in your activity layout
            transaction.add(R.id.main_scrollview, reviewFragment);

            // Add to back stack so pressing BACK will remove detail and unhide this
            transaction.addToBackStack(null);

            // Commit
            transaction.commit();

        });



        // Inflate the layout for this fragment
        return view;
    }



}
