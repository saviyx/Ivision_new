package lk.javainstitute.ivision;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    public MapFragment() {
        super(R.layout.fragment_map); // Create this layout
    }

    @Override
    public void onViewCreated(@NonNull android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LatLng kurunegala = new LatLng(7.4863, 80.3629);

        googleMap.addMarker(new MarkerOptions()
                .position(kurunegala)
                .title("Kurunegala - Tap for directions"));

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kurunegala, 15));

        googleMap.setOnMarkerClickListener(marker -> {
            Uri gmmIntentUri = Uri.parse("google.navigation:q=Kurunegala+Sri+Lanka");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
            return true;
        });
    }
}
