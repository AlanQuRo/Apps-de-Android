package com.example.mapas;

import org.osmdroid.config.Configuration;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MainActivity extends AppCompatActivity {
    private MapView map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
      Configuration.getInstance().setUserAgentValue(getPackageName());
        setContentView(R.layout.activity_main);
        map=findViewById(R.id.map);
        map.setMultiTouchControls(true);

      GeoPoint startPoint = new GeoPoint(19.4326,-99.1332);
      map.getController().setZoom(12.0);
      map.getController().setCenter(startPoint);


      Marker marker=new Marker(map);
      marker.setPosition(startPoint);
      marker.setAnchor(Marker.ANCHOR_CENTER,Marker.ANCHOR_BOTTOM);
      marker.setTitle("CDMX");
      map.getOverlays().add(marker);

    }
}
