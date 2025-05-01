package lk.javainstitute.ivision.Admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import lk.javainstitute.ivision.Appoinment_Fragment;
import lk.javainstitute.ivision.Eye_Test_Fragment;
import lk.javainstitute.ivision.Glass_Fragment;
import lk.javainstitute.ivision.Home_Fragment;
import lk.javainstitute.ivision.R;
import lk.javainstitute.ivision.Review_Fragment;


public class Admin_Home_Fragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_admin__home_, container, false);

        LinearLayout appointmentCard = view.findViewById(R.id.apponment1);

        appointmentCard.setOnClickListener(v -> {
            Fragment appoinmentFragment = new Admin_appointment();


            FragmentTransaction transaction = requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction() ;

            // Hide this fragment
            transaction.hide(Admin_Home_Fragment.this);

            // Add the detail fragment to your activity container (NOT the linear layout)
            // Make sure R.id.mainFragmentContainer is in your activity layout
            transaction.add(R.id.main_scrollview1, appoinmentFragment);

            // Add to back stack so pressing BACK will remove detail and unhide this
            transaction.addToBackStack(null);

            // Commit
            transaction.commit();

        });

        LinearLayout glassesStatusCard = view.findViewById(R.id.glasses_status1);

        glassesStatusCard.setOnClickListener(v -> {
            Fragment glassesStatusFragment = new Admin_Status();


            FragmentTransaction transaction = requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction();

            // Hide this fragment
            transaction.hide(Admin_Home_Fragment.this);

            // Add the detail fragment to your activity container (NOT the linear layout)
            // Make sure R.id.mainFragmentContainer is in your activity layout
            transaction.add(R.id.main_scrollview1, glassesStatusFragment);

            // Add to back stack so pressing BACK will remove detail and unhide this
            transaction.addToBackStack(null);

            // Commit
            transaction.commit();

        });

        LinearLayout reportsCard = view.findViewById(R.id.reports);

        reportsCard.setOnClickListener(v -> {
            Fragment reportFragment = new Admin_Report();


            FragmentTransaction transaction = requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction();

            // Hide this fragment
            transaction.hide(Admin_Home_Fragment.this);

            // Add the detail fragment to your activity container (NOT the linear layout)
            // Make sure R.id.mainFragmentContainer is in your activity layout
            transaction.add(R.id.main_scrollview1, reportFragment);

            // Add to back stack so pressing BACK will remove detail and unhide this
            transaction.addToBackStack(null);

            // Commit
            transaction.commit();


        });

        LinearLayout paymentsCard = view.findViewById(R.id.payment);
        paymentsCard.setOnClickListener(v ->{
            Fragment paymentFragment = new Payment_History_Fragment();

            FragmentTransaction transaction = requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction();

            transaction.hide(Admin_Home_Fragment.this);

            transaction.add(R.id.main_scrollview1,paymentFragment);

            transaction.addToBackStack(null);
            transaction.commit();

        });

        LinearLayout addAdminCard = view.findViewById(R.id.add_admin);

        addAdminCard.setOnClickListener(v -> {
            Fragment addAdminFragment = new Add_Admin();


            FragmentTransaction transaction = requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction();

            // Hide this fragment
            transaction.hide(Admin_Home_Fragment.this);

            // Add the detail fragment to your activity container (NOT the linear layout)
            // Make sure R.id.mainFragmentContainer is in your activity layout
            transaction.add(R.id.main_scrollview1, addAdminFragment);

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
            transaction.hide(Admin_Home_Fragment.this);

            // Add the detail fragment to your activity container (NOT the linear layout)
            // Make sure R.id.mainFragmentContainer is in your activity layout
            transaction.add(R.id.main_scrollview1, reviewFragment);

            // Add to back stack so pressing BACK will remove detail and unhide this
            transaction.addToBackStack(null);

            // Commit
            transaction.commit();


        });



        // Inflate the layout for this fragment
        return view;
    }
}