package lk.javainstitute.ivision;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Appoinment_Fragment extends Fragment {

    private TextView tvDate;
    private ImageButton btnCalendar;
    private Button btnAppointment;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;

    public Appoinment_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize calendar and date format
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_appointment_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views
        tvDate = view.findViewById(R.id.tvDate);
        btnCalendar = view.findViewById(R.id.btnCalendar);
        btnAppointment = view.findViewById(R.id.btnAppointment);

        // Set current date to TextView initially
        updateDateDisplay();

        // Set click listener for calendar button
        btnCalendar.setOnClickListener(v -> showDatePickerDialog());

        // Set click listener for appointment button
        btnAppointment.setOnClickListener(v -> {
            // Get the selected date from the TextView
            String selectedDate = tvDate.getText().toString();

            // Show confirmation message
            Toast.makeText(requireContext(),
                    "Appointment scheduled for " + selectedDate,
                    Toast.LENGTH_LONG).show();

            // Create new View_Appointment fragment
            Fragment viewAppointment = new View_Appointment();

            // Create bundle and add the selected date
            Bundle args = new Bundle();
            args.putString("selected_date", selectedDate);

            // Set arguments to the fragment
            viewAppointment.setArguments(args);

            FragmentTransaction transaction = requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction();

            // Hide this fragment
            transaction.hide(Appoinment_Fragment.this);

            // Add the detail fragment to your activity container
            transaction.add(R.id.main_scrollview, viewAppointment);

            // Add to back stack so pressing BACK will remove detail and unhide this
            transaction.addToBackStack(null);

            // Commit
            transaction.commit();

            // Here you would typically save the appointment data
            // to a database or send it to a server
        });
    }

    /**
     * Shows the date picker dialog
     */
    private void showDatePickerDialog() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create date picker dialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Update calendar with selected date
                    calendar.set(Calendar.YEAR, selectedYear);
                    calendar.set(Calendar.MONTH, selectedMonth);
                    calendar.set(Calendar.DAY_OF_MONTH, selectedDay);

                    // Update the TextView with the selected date
                    updateDateDisplay();
                },
                year, month, day);

        // Set minimum date to today to prevent past dates selection
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        // Show the dialog
        datePickerDialog.show();
    }

    /**
     * Updates the date TextView with the current date from calendar
     */
    private void updateDateDisplay() {
        String formattedDate = dateFormat.format(calendar.getTime());
        tvDate.setText(formattedDate);
    }
}