package lk.javainstitute.ivision.Admin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.Filter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

import lk.javainstitute.ivision.Alert;
import lk.javainstitute.ivision.Loading;
import lk.javainstitute.ivision.MainActivity;
import lk.javainstitute.ivision.R;
import lk.javainstitute.ivision.Validations;

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

            if (name.trim().isEmpty()){
                loading.stop();
                new Alert().showAlert(requireActivity(),"Opps..","Please Enter Your Name" );
            }else if (email.trim().isEmpty()) {
                loading.stop();
                new Alert().showAlert(requireActivity(),"Opps..","Please Enter Your Email" );
            } else if (!new Validations().isEmailValid(email)) {
                loading.stop();
                new Alert().showAlert(requireActivity(),"Opps..","Please Enter Valid Email" );
            } else if (mobile.trim().isEmpty()) {
                loading.stop();
                new Alert().showAlert(requireActivity(),"Opps..","Please Enter Your Password" );
            } else {
                firestore.collection("User")
                        .where(
                                Filter.and(
                                        Filter.equalTo("email",email),
                                        Filter.equalTo("mobile",mobile)
                                )
                        ).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                if (task.isSuccessful()){
                                    if (task.getResult().isEmpty()) {
                                        HashMap<String, Object> adminData = new HashMap<>();
                                        adminData.put("name", name);
                                        adminData.put("email", email);
                                        adminData.put("mobile", mobile);
                                        adminData.put("password", "admin123");
                                        adminData.put("type", "admin");
                                        adminData.put("verified", true);
                                        firestore.collection("User")
                                                .add(adminData)
                                                .addOnSuccessListener(aVoid -> {
                                                    loading.stop();
                                                    new Alert().showAlert(requireActivity(),"Success","Admin added successfully" );
                                                    adminName.setText("");
                                                    adminEmail.setText("");
                                                    adminMobile.setText("");
                                                })
                                                .addOnFailureListener(e -> {
                                                    loading.stop();
                                                    new Alert().showAlert(requireActivity(),"Error!","Something Went Wrong" );
                                                });
                                    }else {
                                        new Alert().showAlert(requireActivity(),"Error!","This email or mobile number registered as user" );

                                    }
                                }
                            }
                        });

            }


        });

        return view;


    }




}