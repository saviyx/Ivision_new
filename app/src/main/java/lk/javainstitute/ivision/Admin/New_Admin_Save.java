package lk.javainstitute.ivision.Admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.Firebase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import lk.javainstitute.ivision.Alert;
import lk.javainstitute.ivision.Loading;
import lk.javainstitute.ivision.R;

public class New_Admin_Save extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_new__admin__save, container, false);

        EditText adminName = view.findViewById(R.id.a_name);
        EditText adminEmail = view.findViewById(R.id.a_email);
        EditText adminMobile = view.findViewById(R.id.a_mobile);
        Button addAdminButton = view.findViewById(R.id.add_new_admin);

        addAdminButton.setOnClickListener(View -> {

            FirebaseFirestore firestore = FirebaseFirestore.getInstance();

            Loading loading = new Loading();

            String name = adminName.getText().toString();
            String email = adminEmail.getText().toString();
            String mobile = adminMobile.getText().toString();

            HashMap<String, Object> adminData = new HashMap<>();
            adminData.put("name", name);
            adminData.put("email", email);
            adminData.put("mobile", mobile);
            adminData.put("password", "admin123");
            adminData.put("type", "admin");
            adminData.put("verified", "true");

            firestore.collection("user")
                    .add(adminData)
                    .addOnSuccessListener(aVoid -> {
                        loading.stop();
                    })
                    .addOnFailureListener(e -> {
                        loading.stop();
                    });

        });

        return view;


    }
}