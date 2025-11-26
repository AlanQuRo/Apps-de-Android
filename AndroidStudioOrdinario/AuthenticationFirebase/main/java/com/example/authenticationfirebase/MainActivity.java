package com.example.authenticationfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
  Button Logout;
  TextView txt_main;

  FirebaseAuth mAuth;
  FirebaseUser user;

  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mAuth=FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();

      Logout=findViewById(R.id.logout);
      txt_main=findViewById(R.id.Token_id);
      if (user==null){
        Intent i=new Intent(getApplicationContext(),login.class);
        startActivity(i);
        finish();
      }else{
        txt_main.setText(user.getEmail());
      }

      Logout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          FirebaseAuth.getInstance().signOut();
          Intent i= new Intent()
        }
      });

    }
}
