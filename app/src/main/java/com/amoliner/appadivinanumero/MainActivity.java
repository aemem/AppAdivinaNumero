package com.amoliner.appadivinanumero;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView enunciado;
    TextView salida;
    TextView adivina;
    EditText entrada;
    Button botonAdivinar;
    Button botonReiniciar;
    int numero;
    int numEntrada;
    int intentos = 0;
    TextView textIntentos;


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

        numero = randomNum();
        enunciado = findViewById(R.id.enunciado);
        salida = findViewById(R.id.salida);
        entrada = findViewById(R.id.entrada);
        adivina = findViewById(R.id.adivina);
        botonAdivinar = findViewById(R.id.botonAdivinar);
        botonReiniciar = findViewById(R.id.botonReiniciar);
        botonReiniciar.setVisibility(View.GONE);
        textIntentos = findViewById(R.id.intentos);
        textIntentos.setVisibility(View.GONE);


        botonAdivinar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoEntrada = entrada.getText().toString();
                numEntrada = Integer.parseInt(textoEntrada);
                intentos++;
                textIntentos.setVisibility(View.VISIBLE);
                textIntentos.setText("Intentos: " + intentos);
                comprobarNumero(numEntrada);
                entrada.setText("");
            }
        });

        botonReiniciar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                intentos = 0;
                numero = randomNum();
                enunciado.setVisibility(View.VISIBLE);
                adivina.setVisibility(View.VISIBLE);
                entrada.setVisibility(View.VISIBLE);
                entrada.setText("");
                salida.setText("");
                botonAdivinar.setVisibility(View.VISIBLE);
                botonReiniciar.setVisibility(View.GONE);
                textIntentos.setVisibility(View.GONE);
            }
        });
    }

    public int randomNum() {
        int num = (int) (Math.random() * 100) + 1;
        return num;
    }

    public void comprobarNumero(int numEntrada) {
        if (intentos < 10 && numEntrada == numero) {
            salida.setText("¡Correcto! El número que había pensado era: " + numero);
            enunciado.setVisibility(View.GONE);
            entrada.setVisibility(View.GONE);
            botonAdivinar.setVisibility(View.GONE);
            adivina.setVisibility(View.GONE);
            botonReiniciar.setVisibility(View.VISIBLE);
        } else if (intentos < 10 && numEntrada > numero) {
            salida.setText("¡Incorrecto! El numero que he pensado es menor que " + numEntrada);
        } else if (intentos < 10) {
            salida.setText("¡Incorrecto! El numero que he pensado es mayor que " + numEntrada);
        } else {
            salida.setText("Te has quedado sin intentos, el número era " + numero);
            enunciado.setVisibility(View.GONE);
            entrada.setVisibility(View.GONE);
            botonAdivinar.setVisibility(View.GONE);
            adivina.setVisibility(View.GONE);
            botonReiniciar.setVisibility(View.VISIBLE);
        }
    }

}