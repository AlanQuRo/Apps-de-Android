package com.example.app_parcial;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class AlmacenistaActivity extends AppCompatActivity {

  private DrawerLayout drawerLayout;
  private NavigationView navigationView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_almacenista);

    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    drawerLayout = findViewById(R.id.drawerLayout);
    navigationView = findViewById(R.id.navigationView);

    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
      this, drawerLayout, toolbar,
      R.string.navigation_drawer_open,
      R.string.navigation_drawer_close
    );
    drawerLayout.addDrawerListener(toggle);
    toggle.syncState();

    String nombreUsuario = getIntent().getStringExtra("nombreUsuario");
    TextView tvBienvenida = findViewById(R.id.tvBienvenida);
    tvBienvenida.setText("Bienvenido, " + nombreUsuario + ", manos a la obra!");

    Button btnOpciones1 = findViewById(R.id.btnOpciones1);
    Button btnOpciones2 = findViewById(R.id.btnOpciones2);

    btnOpciones1.setOnClickListener(v -> {
      PopupMenu popup = new PopupMenu(AlmacenistaActivity.this, btnOpciones1);
      popup.getMenuInflater().inflate(R.menu.inventariado, popup.getMenu());

      popup.setOnMenuItemClickListener(item -> {
        int id = item.getItemId();
        if (id == R.id.op1_inventario) {
          Toast.makeText(this, "Inventario seleccionado", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.op1_entradas) {
          Toast.makeText(this, "Entradas seleccionadas", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.op1_salidas) {
          Toast.makeText(this, "Salidas seleccionadas", Toast.LENGTH_SHORT).show();
        }
        return true;
      });
      popup.show();
    });


    btnOpciones2.setOnClickListener(v -> {
      PopupMenu popup = new PopupMenu(AlmacenistaActivity.this, btnOpciones2);
      popup.getMenuInflater().inflate(R.menu.reportesalm, popup.getMenu());

      popup.setOnMenuItemClickListener(item -> {
        int id = item.getItemId();
        if (id == R.id.op2_diario) {
          Toast.makeText(this, "Reporte Diario seleccionado", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.op2_mensual) {
          Toast.makeText(this, "Reporte Mensual seleccionado", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.op2_anual) {
          Toast.makeText(this, "Reporte Anual seleccionado", Toast.LENGTH_SHORT).show();
        }
        return true;
      });
      popup.show();
    });

    navigationView.setNavigationItemSelectedListener(item -> {
      int id = item.getItemId();

      if (id == R.id.nav_home) {
      } else if (id == R.id.nav_settings) {
      } else if (id == R.id.nav_report) {

          Intent intent = new Intent(AlmacenistaActivity.this, reportarerror.class);
          startActivity(intent);

      } else if (id == R.id.nav_logout) {
        Intent intent = new Intent(AlmacenistaActivity.this, seleccionusuario.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
      }

      drawerLayout.closeDrawers();
      return true;
    });
  }
}
