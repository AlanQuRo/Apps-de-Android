package com.example.app_parcial;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class seleccionusuario extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_seleccionusuario);

    Button btnUsuario1 = findViewById(R.id.btnUsuario1);
    Button btnUsuario2 = findViewById(R.id.btnUsuario2);


    btnUsuario1.setOnClickListener(v -> {
      Intent intent = new Intent(seleccionusuario.this, LoginActivity.class);
      intent.putExtra("usuario", 1);
      startActivity(intent);
    });


    btnUsuario2.setOnClickListener(v -> {
      Intent intent = new Intent(seleccionusuario.this, LoginActivity.class);
      intent.putExtra("usuario", 2);
      startActivity(intent);
    });
  }
}
