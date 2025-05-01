package lk.javainstitute.ivision;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView mapButton = findViewById(R.id.map);

        mapButton.setOnClickListener(v -> {
            Fragment mapFragment = new MapFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_scrollview, mapFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });


        // Load initial fragment
        loadFragment(new Home_Fragment());

        // Set up appointment button click listener
        ImageView appointment = findViewById(R.id.appoinment);
        appointment.setOnClickListener(v -> {
            // Create new appointment fragment
            Fragment appointmentFragment = new View_Appointment();

            // Get fragment manager from this activity
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();

            // Replace current fragment with appointment fragment
            transaction.replace(R.id.main_scrollview, appointmentFragment);

            // Add to back stack so user can navigate back
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        });

        // Set up home button click listener
        ImageView home = findViewById(R.id.home);
        home.setOnClickListener(v -> {
            // Create new home fragment
            Fragment homeFragment = new Home_Fragment();

            // Get fragment manager from this activity
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();

            // Replace current fragment with home fragment
            transaction.replace(R.id.main_scrollview, homeFragment);

            // Add to back stack so user can navigate back
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        });

        // Set up call button click listener
        ImageView callButton = findViewById(R.id.call); // Assuming the call ImageView has id 'call'
        callButton.setOnClickListener(v -> {
            // Open the dial pad
            Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0374320678"));
            startActivity(dialIntent);
        });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_scrollview, fragment).commit();
            return true;
        }
        return false;
    }
}