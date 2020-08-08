package com.example.digitalprint1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Cuadro_Nivel extends AppCompatActivity {

    private Spinner spinnerCuadroLetra;
    private Spinner spinnerCuadroNumber;
    private Spinner spinnerNivelEstrato;
    private Spinner spinnerUUEE;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuadro_nivel);

        nextButton = (Button)findViewById(R.id.nextButton);

        // Texto Día y Hora
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        Date rightnow = Calendar.getInstance().getTime();
        TextView CurrentTime = findViewById(R.id.CurrentTime);
        CurrentTime.setText(rightnow.toString());

        // Spinner de Cuadro-Letra
        spinnerCuadroLetra = (Spinner) findViewById(R.id.spinnerCuadroLetra);
        ArrayList<String> elementos_letras = new ArrayList<String>();

        elementos_letras.add("J");
        elementos_letras.add("K");
        elementos_letras.add("L");
        elementos_letras.add("M");
        elementos_letras.add("N");
        elementos_letras.add("O");
        elementos_letras.add("P");
        elementos_letras.add("Q");
        ArrayAdapter adp = new ArrayAdapter(Cuadro_Nivel.this, android.R.layout.simple_spinner_dropdown_item, elementos_letras);
        spinnerCuadroLetra.setAdapter(adp);
        spinnerCuadroLetra.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String letra_escogida= (String) spinnerCuadroLetra.getAdapter().getItem(position);

                Toast.makeText(Cuadro_Nivel.this, "Seleccionaste: " + letra_escogida, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        // Spinner de Cuadro-Number
        spinnerCuadroNumber = (Spinner) findViewById(R.id.spinnerCuadroNumber);
        ArrayList<String> elementos_numeros = new ArrayList<String>();

        elementos_numeros.add("7");
        elementos_numeros.add("8");
        elementos_numeros.add("9");
        elementos_numeros.add("10");
        elementos_numeros.add("11");
        elementos_numeros.add("12");
        elementos_numeros.add("13");
        elementos_numeros.add("14");
        elementos_numeros.add("15");
        elementos_numeros.add("16");
        elementos_numeros.add("17");
        elementos_numeros.add("18");
        ArrayAdapter adp1 = new ArrayAdapter(Cuadro_Nivel.this, android.R.layout.simple_spinner_dropdown_item, elementos_numeros);
        spinnerCuadroNumber.setAdapter(adp1);
        spinnerCuadroNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String numero_escogido= (String) spinnerCuadroNumber.getAdapter().getItem(position);

                Toast.makeText(Cuadro_Nivel.this, "Seleccionaste: " + numero_escogido, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        // Spinner de Nivel Estrato
        spinnerNivelEstrato = (Spinner) findViewById(R.id.spinnerNivelEstrato);
        ArrayList<String> elementos_estratos = new ArrayList<String>();

        elementos_estratos.add("1");
        elementos_estratos.add("2");
        elementos_estratos.add("3");
        elementos_estratos.add("4");
        elementos_estratos.add("5");
        elementos_estratos.add("6");
        elementos_estratos.add("7");
        elementos_estratos.add("8");
        elementos_estratos.add("9");
        elementos_estratos.add("10");
        elementos_estratos.add("11");
        elementos_estratos.add("12");
        elementos_estratos.add("13");
        elementos_estratos.add("14");
        ArrayAdapter adp2 = new ArrayAdapter(Cuadro_Nivel.this, android.R.layout.simple_spinner_dropdown_item, elementos_estratos);
        spinnerNivelEstrato.setAdapter(adp1);
        spinnerNivelEstrato.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String estrato_escogido= (String) spinnerNivelEstrato.getAdapter().getItem(position);

                Toast.makeText(Cuadro_Nivel.this, "Seleccionaste: " + estrato_escogido, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        // Spinner de Cuadro-Number
        spinnerUUEE = (Spinner) findViewById(R.id.spinnerUUEE);
        ArrayList<String> elementos_UUEE = new ArrayList<String>();

        elementos_UUEE.add("7");
        elementos_UUEE.add("8");
        elementos_UUEE.add("9");
        elementos_UUEE.add("10");
        elementos_UUEE.add("11");
        elementos_UUEE.add("12");
        elementos_UUEE.add("13");
        elementos_UUEE.add("14");
        elementos_UUEE.add("15");
        elementos_UUEE.add("16");
        elementos_UUEE.add("17");
        elementos_UUEE.add("18");
        ArrayAdapter adp3 = new ArrayAdapter(Cuadro_Nivel.this, android.R.layout.simple_spinner_dropdown_item, elementos_UUEE);
        spinnerUUEE.setAdapter(adp3);
        spinnerUUEE.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String uuee_escogida= (String) spinnerUUEE.getAdapter().getItem(position);

                Toast.makeText(Cuadro_Nivel.this, "Seleccionaste: " + uuee_escogida, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Cuadro_Nivel.this, Location.class);
                startActivity(intent);
            }
        });


    }
}