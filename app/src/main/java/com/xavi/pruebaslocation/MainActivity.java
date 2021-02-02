package com.xavi.pruebaslocation;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.security.Permission;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //Respuesta de pedir permiso
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //Valores de grantResults
        //Mientras usas la app: 0
        if (requestCode == 666) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //tENGO PERMISO!!
                obtenerUbicacion();
            }

        }
    }

    private void obtenerUbicacion() {
        LocationManager lc = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        /*Location localizacion = lc.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Log.d("POSICION lastknown", localizacion.getLatitude()+", "+localizacion.getLongitude());*/
        lc.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 50, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {C:\Users\xavie\DownloadsFEGBRNT*-+

                Log.d("POSICION cambiante", location.getLatitude()+", "+location.getLongitude());
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //List<String> proveedores = lc.getProviders(true);
        //Log.d("PROVEEDORES", proveedores.toString());
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
           String[] permisos={Manifest.permission.ACCESS_FINE_LOCATION};
            requestPermissions(permisos, 666);

        }
        else
        {
            obtenerUbicacion();
        }



    }
}