package com.example.app_parcial;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

  private int usuarioSeleccionado;


  private final String USER_ALM = "alan";
  private final String ALM_PASS = "1234";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    TextView txtTitulo = findViewById(R.id.txtLoginTitulo);
    EditText edtUsuario = findViewById(R.id.edtUsuario);
    EditText edtPassword = findViewById(R.id.edtPassword);
    Button btnLogin = findViewById(R.id.btnLogin);

    usuarioSeleccionado = getIntent().getIntExtra("usuario", 0);


    if (usuarioSeleccionado == 1) {
      txtTitulo.setText("Login Usuario Almacenista");
    } else if (usuarioSeleccionado == 2) {
      txtTitulo.setText("Login Usuario Inspector");
    }

    btnLogin.setOnClickListener(v -> {
      String user = edtUsuario.getText().toString().trim();
      String pass = edtPassword.getText().toString().trim();

      if (user.isEmpty() || pass.isEmpty()) {
        Toast.makeText(this, "Por favor ingresa usuario y contraseña", Toast.LENGTH_SHORT).show();
        return;
      }

      if (usuarioSeleccionado == 1) {
        if (user.equals(USER_ALM) && pass.equals(ALM_PASS)) {

          Intent intent = new Intent(LoginActivity.this, AlmacenistaActivity.class);
          startActivity(intent);
          finish();
        } else {
          Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
        if (user.equals(USER_ALM) && pass.equals(ALM_PASS)) {
          Intent intent = new Intent(LoginActivity.this, AlmacenistaActivity.class);
          intent.putExtra("nombreUsuario", user);
          startActivity(intent);
          finish();
        }

      }
    });
  }
}
