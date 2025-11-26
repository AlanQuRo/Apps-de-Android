package com.example.app_parcial;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {


  private static final int SPLASH_DURATION = 2000;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    // Handler para retrasar la ejecución
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {

        Intent intent = new Intent(Splash.this, seleccionusuario.class);
        startActivity(intent);
        finish();
      }
    }, SPLASH_DURATION);
  }
}
