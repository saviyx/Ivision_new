package lk.javainstitute.ivision;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class View_Appointment extends Fragment {

    private TextView tvDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view__appointment, container, false);

        // Initialize the TextView
        tvDate = view.findViewById(R.id.tvDate);

        // Get the arguments bundle
        Bundle args = getArguments();

        // Get the selected date from arguments
        if (args != null) {
            String selectedDate = args.getString("selected_date", "");

            // Set the selected date to the TextView
            if (!selectedDate.isEmpty()) {
                tvDate.setText(selectedDate);
            }
        }

        return view;
    }

}