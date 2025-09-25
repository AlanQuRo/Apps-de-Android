package com.example.app_parcial;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class reportarerror extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_reportarerror);

    Button btnRegresar = findViewById(R.id.btnRegresar);
    Button btnEnviar = findViewById(R.id.btnEnviar);
    EditText edtComentarios = findViewById(R.id.edtComentarios);


    btnRegresar.setOnClickListener(v -> {
      Intent intent = new Intent(reportarerror.this, AlmacenistaActivity.class);
      startActivity(intent);
      finish();
    });


    btnEnviar.setOnClickListener(v -> {
      String mensaje = edtComentarios.getText().toString().trim();
      if (mensaje.isEmpty()) {
        Toast.makeText(this, "Por favor escribe un comentario", Toast.LENGTH_SHORT).show();
      } else {
        Toast.makeText(this, "Reporte enviado, gracias por tus comentarios", Toast.LENGTH_LONG).show();
        edtComentarios.setText("");
      }
    });
  }
}
