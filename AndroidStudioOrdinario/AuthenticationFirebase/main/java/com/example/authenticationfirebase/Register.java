package com.example.authenticationfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

  TextInputEditText email,pwd;
  MaterialButton b_register;
  TextView login_now;
  FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        email=findViewById(R.id.r_email);
        pwd=findViewById(R.id.r_password);
        b_register=findViewById(R.id.r_btn_login);
        login_now=findViewById(R.id.login_now);
        login_now.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            String txt_email,txt_pwd;
          }
        });
      mAuth.createUserWithEmailAndPassword(txt_email, txt_pwd)
        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
              // Sign in success, update UI with the signed-in user's information

              Intent i=new Intent(getApplicationContext(),MainActivity.class);
              startActivity(i);
              finish();
            } else {
              // If sign in fails, display a message to the user.

              Toast.makeText(getApplicationContext(), "Authentication failed.",
                Toast.LENGTH_SHORT).show();

            }
          }
        });

    }
}
