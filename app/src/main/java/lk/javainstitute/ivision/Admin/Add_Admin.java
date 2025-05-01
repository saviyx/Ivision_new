package lk.javainstitute.ivision.Admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import lk.javainstitute.ivision.R;

public class Add_Admin extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add__admin, container, false);

        Button addAdminButton = view.findViewById(R.id.add_admin);
        addAdminButton.setOnClickListener(v -> {

            Fragment addAdminFragment = new New_Admin_Save();

            FragmentTransaction transaction = requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction();

            transaction.hide(Add_Admin.this);

            transaction.add(R.id.main_scrollview1,addAdminFragment);

            transaction.addToBackStack(null);
            transaction.commit();

        });

        return view;
    }
}