package com.example.calculadorapropina;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText cuenta1;
    RadioGroup grupoPropina;
    RadioButton opcion5, opcion10, opcion15;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cuenta1 = findViewById(R.id.cuenta1);
        grupoPropina = findViewById(R.id.grupoPropina);
        opcion5 = findViewById(R.id.opcion5);
        opcion10 = findViewById(R.id.opcion10);
        opcion15 = findViewById(R.id.opcion15);
        boton = findViewById(R.id.boton);


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cantidadStr = cuenta1.getText().toString();

                if (cantidadStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor ingresa la cantidad", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double cantidad = Double.parseDouble(cantidadStr);
                    double porcentaje = 0;


                    int opcionSeleccionada = grupoPropina.getCheckedRadioButtonId();
                    if (opcionSeleccionada == R.id.opcion5) {
                        porcentaje = 0.05;
                    } else if (opcionSeleccionada == R.id.opcion10) {
                        porcentaje = 0.10;
                    } else if (opcionSeleccionada == R.id.opcion15) {
                        porcentaje = 0.15;
                    } else {
                        Toast.makeText(MainActivity.this, "Selecciona un porcentaje de propina", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    double total = cantidad + (cantidad * porcentaje);
                    String resultado = String.format("Total con propina: $%.2f", total);

                    Toast.makeText(MainActivity.this, resultado, Toast.LENGTH_LONG).show();

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Valor inválido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
