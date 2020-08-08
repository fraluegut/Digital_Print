package com.example.digitalprint1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class Location extends AppCompatActivity {

    private Button nextButton2;
    private Button botonInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        nextButton2 = (Button)findViewById(R.id.nextButton2);
        botonInicio = (Button)findViewById(R.id.botonInicio);

        // Texto Día y Hora
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        Date rightnow = Calendar.getInstance().getTime();
        TextView CurrentTime2 = findViewById(R.id.currentTime2);
        CurrentTime2.setText(rightnow.toString());

    botonInicio.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){
            Intent go_inicio = new Intent(Location.this, MainActivity.class);
            startActivity(go_inicio);
        }
    });

    nextButton2.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){
            Intent intent = new Intent(Location.this, Location.class);
            startActivity(intent);
        }
    });
    }

}