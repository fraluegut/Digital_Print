package com.example.digitalprint1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class Camera extends AppCompatActivity {

    private Button botonInicio_camera;
    private Button boton_take_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);


        botonInicio_camera = (Button)findViewById(R.id.botonInicio_camera);
        boton_take_photo = (Button)findViewById(R.id.boton_take_photo);

        // Texto Día y Hora
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        Date rightnow = Calendar.getInstance().getTime();
        TextView CurrentTime2 = findViewById(R.id.currentTime3);
        CurrentTime2.setText(rightnow.toString());

        botonInicio_camera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent go_inicio = new Intent(Camera.this, MainActivity.class);
                startActivity(go_inicio);
            }
        });

//        boton_take_photo.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                Intent intent = new Intent(Location.this, Location.class);
//                startActivity(intent);
//            }
//        });
    }

    private void cargarImagen() {

        final CharSequence[] opciones={"Tomar Foto", "Cargar Imagen", "Cancelar"};
        final AlertDialog.Builder alertOpciones=new AlertDialog.Builder(Camera.this);
        alertOpciones.setTitle("Seleccione una Opción");
        alertOpciones.setItems(opciones, (DialogInterface.OnClickListener) (dialogInterface, i) -> {
            if (opciones[i].equals("Tomar Foto")){
                Toast.makeText(getApplication(), "TOMAR FOTO", Toast.LENGTH_SHORT).show();
            } else{
                if (opciones[i].equals("Cargar Imagen")){
                    Toast.makeText(getApplication(), "TOMAR FOTO", Toast.LENGTH_SHORT).show();
                }else{
                    dialogInterface.dismiss();
                }
            }
        });
        alertOpciones.show();

    }
}