package demo.com.roomdatabaseexample;


import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddUserFragment extends Fragment {

    EditText edittext_user_name, edittext_user_email, edittext_user_id, edittext_address;
    Button btn_save_user, btn_get_current_location;
    FusedLocationProviderClient client;
    String str_address = "";

    public AddUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_add_user, container, false);
        edittext_user_name = rootview.findViewById(R.id.edittext_user_name);
        edittext_user_email = rootview.findViewById(R.id.edittext_user_email);
        edittext_user_id = rootview.findViewById(R.id.edittext_user_id);
        edittext_address = rootview.findViewById(R.id.edittext_address);
        btn_save_user = rootview.findViewById(R.id.btn_save_user);
        btn_get_current_location = rootview.findViewById(R.id.btn_get_current_location);
        client = LocationServices.getFusedLocationProviderClient(getActivity());
        getLocationPermission();

        btn_get_current_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                client.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            // edittext_address.setText("Latitude " + String.valueOf(location.getLatitude()) + " " + "Longitude " + String.valueOf(location.getLongitude()));

                            str_address = getCurrentLocationAddress(location.getLatitude(), location.getLongitude());
                            edittext_address.setText(str_address);
                        }
                    }
                });
            }
        });


        btn_save_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int userid = Integer.parseInt(edittext_user_id.getText().toString());
                String str_userName = edittext_user_name.getText().toString();
                String str_emailID = edittext_user_email.getText().toString();

                User user = new User();
                user.setId(userid);
                user.setName(str_userName);
                user.setEmail(str_emailID);
                user.setAddress(str_address);

                MainActivity.myAppDatabase.myDao().addUser(user);
                Toast.makeText(getContext(), "User Added Successfully", Toast.LENGTH_SHORT).show();
                edittext_user_id.setText("");
                edittext_user_name.setText("");
                edittext_user_email.setText("");
                edittext_address.setText("");
            }
        });
        return rootview;
    }

    private String getCurrentLocationAddress(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder();

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("My Current location", strReturnedAddress.toString());
            } else {
                Log.w("My Current location", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Current location", "Cannot get Address!");
        }
        return strAdd;

    }

    private void getLocationPermission() {

        ActivityCompat.requestPermissions(getActivity(), new String[]{ACCESS_FINE_LOCATION}, 1);
    }

}
