package com.vamsisangam.androidexamples.location;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.vamsisangam.androidexamples.R;

import java.util.List;
import java.util.Locale;

public class LocationActivity extends Activity {
    EditText lat, lng, altitude, speed, accuracy, status;
    TextView messages;
    LocationManager locationManager;
    Switch providerSwitch;
    String locationProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        init();
        activateListener();
    }

    private void init() {
        lat = (EditText) findViewById(R.id.lat);
        lng = (EditText) findViewById(R.id.lng);
        status = (EditText) findViewById(R.id.status);
        altitude = (EditText) findViewById(R.id.altitude);
        speed = (EditText) findViewById(R.id.speed);
        accuracy = (EditText) findViewById(R.id.accuracy);
        messages = (TextView) findViewById(R.id.messages);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        providerSwitch = (Switch) findViewById(R.id.provider);
        locationProvider= LocationManager.GPS_PROVIDER;

        providerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    locationProvider = LocationManager.GPS_PROVIDER;
                } else {
                    locationProvider = LocationManager.NETWORK_PROVIDER;
                }

                activateListener();
            }
        });
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            if (location != null) {
                final String valLat = Double.toString(location.getLatitude());
                final String valLng = Double.toString(location.getLongitude());
                final String valAltitude = Double.toString(location.getAltitude());
                final String valSpeed = Double.toString(location.getSpeed());
                final String valAccuracy = Double.toString(location.getAccuracy());

                lat.post(new Runnable() {
                    @Override
                    public void run() {
                        lat.setText(valLat);
                        lng.setText(valLng);
                        altitude.setText(valAltitude);
                        speed.setText(valSpeed);
                        accuracy.setText(valAccuracy);
                        messages.append(getAddress(location.getLatitude(), location.getLongitude()));
                    }
                });
            }
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
            StringBuilder str = new StringBuilder(s + " - ");

            switch (i) {
                case LocationProvider.AVAILABLE:
                    str.append(" Available");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    str.append(" Out of service");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    str.append(" Temporarily unavailable");
                    break;
            }

            final String output = str.toString();

            status.post(new Runnable() {
                @Override
                public void run() {
                    status.setText(output);
                }
            });
        }

        @Override
        public void onProviderEnabled(String s) {
            messages.post(new Runnable() {
                @Override
                public void run() {
                    messages.append("Location provider, + " + locationProvider + ", enabled.\n");
                }
            });
        }

        @Override
        public void onProviderDisabled(String s) {
            messages.post(new Runnable() {
                @Override
                public void run() {
                    messages.append("Location provider, + " + locationProvider + ", disabled.\n");
                }
            });
        }

        private String getAddress(double lat, double lng) {
            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> addresses = null;

            try {
                addresses = geocoder.getFromLocation(lat, lng, 1);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);

                String text = String.format("%s, %s, %s, %s\n",
                        address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "NA",
                        address.getSubLocality(),
                        address.getLocality(),
                        address.getCountryName());

                return text;
            }

            return null;
        }
    };

    private void activateListener() {
        try {
            locationManager.requestLocationUpdates(locationProvider, 500, 0, locationListener);
        } catch (SecurityException ex) {
            ex.printStackTrace();
        }
    }
}
