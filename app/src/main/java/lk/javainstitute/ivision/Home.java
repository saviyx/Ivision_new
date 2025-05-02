package lk.javainstitute.ivision;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity {
    private DrawerLayout drawerLayout;
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
        // Initialize DrawerLayout
        drawerLayout = findViewById(R.id.main);

        // Set up NavigationView
        NavigationView navigationView = findViewById(R.id.nav_view);
        setupNavigationView(navigationView);
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
        // Set up more icon click listener to open navigation drawer
        ImageView moreIcon = findViewById(R.id.more);
        moreIcon.setOnClickListener(v -> openNavigationDrawer());
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
    private void openNavigationDrawer() {
        if (drawerLayout != null) {
            drawerLayout.openDrawer(GravityCompat.END); // Open from the right side
        }
    }

    private void setupNavigationView(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_profile) {
                // Navigate to profile settings
                loadFragment(new Profile_Settings_Fragment());
                drawerLayout.closeDrawers();
                return true;
            }
            else if (itemId == R.id.nav_logout) {
                // Handle logout
                logout();
                return true;
            }

            drawerLayout.closeDrawers();
            return false;
        });
    }

    private void logout() {
        // Clear user session/preferences
        // For example:
        // SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        // preferences.edit().clear().apply();

        // Show logout message
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();

        // Navigate to login screen
        Intent loginIntent = new Intent(this, MainActivity.class);
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        // Close drawer if open when back is pressed
        if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }
}