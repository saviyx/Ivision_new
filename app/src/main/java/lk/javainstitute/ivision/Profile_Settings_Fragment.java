package lk.javainstitute.ivision;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class Profile_Settings_Fragment extends Fragment {

    private EditText editName, editEmail, editPhone;
    private Button btnSaveProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile__settings_, container, false);

        // Initialize UI elements
        editName = view.findViewById(R.id.edit_name);
        editEmail = view.findViewById(R.id.edit_email);
        editPhone = view.findViewById(R.id.edit_phone);
        btnSaveProfile = view.findViewById(R.id.btn_save_profile);

        // Load existing user profile data
        loadUserProfileData();

        // Set click listener for save button
        btnSaveProfile.setOnClickListener(v -> saveProfile());

        return view;
    }

    private void loadUserProfileData() {
        // Here you would load the user's current profile data
        // For example, from SharedPreferences or your database
        // For now, we'll just set placeholder text

        // Example:
        // SharedPreferences preferences = getActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        // editName.setText(preferences.getString("user_name", ""));
        // editEmail.setText(preferences.getString("user_email", ""));
        // editPhone.setText(preferences.getString("user_phone", ""));

        editName.setText("John Doe");
        editEmail.setText("john.doe@example.com");
        editPhone.setText("1234567890");
    }

    private void saveProfile() {
        // Get values from EditText fields
        String name = editName.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String phone = editPhone.getText().toString().trim();

        // Validate inputs
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save profile data
        // Example:
        // SharedPreferences preferences = getActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        // preferences.edit()
        //     .putString("user_name", name)
        //     .putString("user_email", email)
        //     .putString("user_phone", phone)
        //     .apply();

        // Show success message
        Toast.makeText(getContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show();
    }
}