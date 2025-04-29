package lk.javainstitute.ivision;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Home_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_fragment, container, false);

        LinearLayout appointmentCard = view.findViewById(R.id.apponment);

        appointmentCard.setOnClickListener(v -> {
            Fragment appoinmentFragment = new Appoinment_Fragment();


            FragmentTransaction transaction = requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction();

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
            Fragment glassesStatusFragment = new Glasses_Status_Fragment();


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