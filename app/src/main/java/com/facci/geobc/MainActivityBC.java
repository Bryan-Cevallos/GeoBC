package com.facci.geobc;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.List;
import java.util.jar.Manifest;

public class MainActivityBC extends AppCompatActivity {

    LocationManager locManager;

    private double latitud;
    private double longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_bc);

        //Inicializar LocManager
         locManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        //Obtner la lista de proveedores
        List<String> listaProviders = locManager.getAllProviders();

        //Obtner el primer proveedor de la lista
        LocationProvider provider  = locManager.getProvider(listaProviders.get(0));


    }

    public  void ActualizarLatlongClick (View v){

        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED  ){

        }

        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2*60*1000,10,locationListenerGPS);
    }

    private final LocationListener locationListenerGPS = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            longitud = location.getLongitude();
            latitud = location.getLatitude();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    EditText txtLongitud = (EditText) findViewById(R.id.txtLongitud);
                    EditText txtLatitud = (EditText) findViewById(R.id.txtLatitud);

                    txtLatitud.setText(latitud+"");
                    txtLongitud.setText(String.valueOf(longitud));
                }
            });

        }

        @Override
        public void onStatusChanged(String provider, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
}
