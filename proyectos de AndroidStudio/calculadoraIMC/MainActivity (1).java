package com.example.calculadoraimc;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText peso, altura;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        peso = findViewById(R.id.peso);
        altura = findViewById(R.id.altura);
        boton = findViewById(R.id.boton);


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pesoStr = peso.getText().toString();
                String alturaStr = altura.getText().toString();

                if (pesoStr.isEmpty() || alturaStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor ingresa peso y altura", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double peso = Double.parseDouble(pesoStr);
                    double altura = Double.parseDouble(alturaStr);



                    double imc = peso / (altura * altura);
                    String resultado = String.format("Tu IMC es: "+ imc);

                    Toast.makeText(MainActivity.this, resultado, Toast.LENGTH_LONG).show();

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Valores inválidos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
