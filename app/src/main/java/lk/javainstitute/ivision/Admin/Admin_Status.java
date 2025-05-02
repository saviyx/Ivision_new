package lk.javainstitute.ivision.Admin;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import lk.javainstitute.ivision.Alert;
import lk.javainstitute.ivision.MainActivity;
import lk.javainstitute.ivision.R;

public class Admin_Status extends Fragment {

    private TextInputEditText patientMobileEditText;
    private TextView patientNameTextView, patientEmailTextView;
    private Spinner statusSpinner;
    private String currentPatientId = "";

    public Admin_Status() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin__status, container, false);


        patientMobileEditText = view.findViewById(R.id.patient_mobile);
        patientNameTextView = view.findViewById(R.id.textView44);
        patientEmailTextView = view.findViewById(R.id.textView46);
        statusSpinner = view.findViewById(R.id.spinner);
        Button addButton = view.findViewById(R.id.button2);
        Button updateButton = view.findViewById(R.id.button3);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.spinner_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(adapter);

        addButton.setOnClickListener(v -> searchPatientByMobile());
        updateButton.setOnClickListener(v -> insertStatus());


        return view;
    }

    private void searchPatientByMobile() {
        String mobileNumber = patientMobileEditText.getText().toString().trim();
        Log.i("Admin_Status", "Searching for mobile: " + mobileNumber);

        if (mobileNumber.isEmpty()) {
            Toast.makeText(getContext(), "Please enter mobile number", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseFirestore.getInstance().collection("User")
                .whereEqualTo("mobileNumber", mobileNumber)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (queryDocumentSnapshots.isEmpty()) {
                        new Alert().showAlert(requireActivity(),"Opps..","Patient not found" );

                        clearFields();
                        return;
                    }

                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        currentPatientId = document.getId();
                        String patientName = document.getString("Name");
                        String patientEmail = document.getString("email");
                        String currentStatus = document.getString("status");


                        patientNameTextView.setText(patientName != null ? patientName : "");
                        patientEmailTextView.setText(patientEmail != null ? patientEmail : "");


                        if (currentStatus != null) {
                            ArrayAdapter adapter = (ArrayAdapter) statusSpinner.getAdapter();
                            int position = adapter.getPosition(currentStatus);
                            if (position >= 0) {
                                statusSpinner.setSelection(position);
                            }
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("Admin_Status", "Error searching patient", e);
                });
    }

    private void insertStatus() {

        if (currentPatientId.isEmpty()) {
            new Alert().showAlert(requireActivity(),"Opps..","Please search for a patient first" );

            return;
        }


        String selectedStatus = statusSpinner.getSelectedItem().toString();


        FirebaseFirestore.getInstance().collection("User")
                .document(currentPatientId)
                .update("glass_status", selectedStatus)
                .addOnSuccessListener(aVoid -> {
                    new Alert().showAlert(requireActivity(),"Success","Status updated successfully" );

                    clearFields();
                })
                .addOnFailureListener(e -> {
                    new Alert().showAlert(requireActivity(),"Opps..","Failed to update status" );
                });
    }

    private void clearFields() {
        patientNameTextView.setText("");
        patientEmailTextView.setText("");
        statusSpinner.setSelection(0);
        currentPatientId = "";
    }
}