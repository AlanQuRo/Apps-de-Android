package com.example.authenticationfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {


  MaterialButton btn_login;
  TextInputEditText email,pwd;
  TextView register_now;
  FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_login=findViewById(R.id.btn_login);
        email=findViewById(R.id.email);
        pwd=findViewById(R.id.password);
        register_now=findViewById(R.id.register_now);
        mAuth=FirebaseAuth.getInstance();

        btn_login.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            String txt_email,txt_pwd;
            txt_email=email.getText().toString();
            txt_pwd=pwd.getText().toString();
            if(TextUtils.isEmpty(txt_email)){
              Toast.makeText(login.this,"Escriba su correo",Toast.LENGTH_SHORT).show();
              return;
              if(TextUtils.isEmpty(txt_pwd)){
                Toast.makeText(pwd.this,"Escriba su contraseña",Toast.LENGTH_SHORT).show();
                return;
              }
              mAuth.signInWithEmailAndPassword(txt_email, txt_pwd)
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


      });

    }
}
