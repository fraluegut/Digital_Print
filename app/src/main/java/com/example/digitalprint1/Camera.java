package com.example.digitalprint1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class Camera extends AppCompatActivity {

    private Button botonInicio_camera;
    private Button boton_take_photo;
    private final String CARPETA_RAIZ="misImagenesPrueba/";
    private final String RUTA_IMAGEN=CARPETA_RAIZ+"misFotos";

    final int COD_SELECCIONA=10;
    final int COD_FOTO=20;
    ImageView imagen;
    String path;
    Button botonCargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imagen= (ImageView) findViewById(R.id.imagen);
        botonCargar= (Button) findViewById(R.id.btnCargarImg);
        if (validaPermisos()){
            botonCargar.setEnabled(true);
        }else{
            botonCargar.setEnabled(false);
        }

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

        boton_take_photo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){cargarImagen();
            }

        });
    }

    private boolean validaPermisos() {
        if(Build.VERSION.SDK_INT< Build.VERSION_CODES.M){
            return true;
        }
        if((checkSelfPermission(CAMERA)== PackageManager.PERMISSION_GRANTED)&&
                (checkSelfPermission(WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED)){
        return false;
    }

        if((shouldShowRequestPermissionRationale(CAMERA))||
                (shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE))) {
            cargarDialogoRecomendacion();
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        
        if(requestCode==100){
            if(grantResults.length==2 && grantResults[0]==PackageManager.PERMISSION_GRANTED &&
                    grantResults[1]==PackageManager.PERMISSION_GRANTED){
                botonCargar.setEnabled(true);
            }else{
                solicitarPermisosManual();
            }
        }
    }

    private void solicitarPermisosManual() {
        final CharSequence[] opciones={"si", "no"};
        final AlertDialog.Builder alertOpciones=new AlertDialog.Builder(Camera.this);
        alertOpciones.setTitle("¿Desea configurar los permisos de forma manual?");
        alertOpciones.setItems(opciones, (DialogInterface.OnClickListener) (dialogInterface, i) -> {
            if (opciones[i].equals("si")){
                Intent intent=new Intent();
                intent.setAction((Settings.ACTION_APPLICATION_DETAILS_SETTINGS));
                Uri uri=Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);

            } else{
                Toast.makeText(getApplicationContext(), "Los permisos no fueron aceptados", Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
            }
        });
        alertOpciones.show();

    }

    private void cargarDialogoRecomendacion() {
        AlertDialog.Builder dialogo=new AlertDialog.Builder(Camera.this);
        dialogo.setTitle("Permisos Desactivados");
        dialogo.setMessage("Debe aceptar los permisos para el correcto funcionamiento de la App");

        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener()  {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,CAMERA},100);
                }
            }

        });
        dialogo.show();
    }

    private void cargarImagen() {

        final CharSequence[] opciones={"Tomar Foto", "Cargar Imagen", "Cancelar"};
        final AlertDialog.Builder alertOpciones=new AlertDialog.Builder(Camera.this);
        alertOpciones.setTitle("Seleccione una Opción");
        alertOpciones.setItems(opciones, (DialogInterface.OnClickListener) (dialogInterface, i) -> {
            if (opciones[i].equals("Tomar Foto")){
                Toast.makeText(getApplication(), "TOMAR FOTO", Toast.LENGTH_SHORT).show();
                tomarFotografia();
            } else{
                if (opciones[i].equals("Cargar Imagen")){
                    Intent intent=new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/");
                    startActivityForResult(intent.createChooser(intent, "Seleccione la Aplicación"),COD_SELECCIONA);
                    Toast.makeText(getApplication(), "TOMAR FOTO", Toast.LENGTH_SHORT).show();
                }else{
                    dialogInterface.dismiss();
                }
            }
        });
        alertOpciones.show();

    }

    private void tomarFotografia() {
        File fileImagen=new File(Environment.getExternalStorageDirectory(), RUTA_IMAGEN);
        boolean isCreada=fileImagen.exists();
        String nombreImagen="";
        if(isCreada==false){
            isCreada=fileImagen.mkdirs();
        }
        if(isCreada==true){
            nombreImagen=(System.currentTimeMillis()/1000)+".jpg";
        }

        path=Environment.getExternalStorageDirectory()+File.separator+RUTA_IMAGEN+File.separator+nombreImagen;

        File imagen=new File(path);

        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagen));
        startActivityForResult(intent, COD_FOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){

            switch (requestCode){
                case COD_SELECCIONA:
                    Uri miPath=data.getData();
                    imagen.setImageURI(miPath);
                    break;
                case COD_FOTO:
                    MediaScannerConnection.scanFile(this, new String[]{path}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                        @Override
                        public void onScanCompleted(String s, Uri uri) {
                                Log.i("Ruta de almacenamiento", "Path: "+path);

                        }
                    });

                            Bitmap bitmap= BitmapFactory.decodeFile(path);
                            imagen.setImageBitmap(bitmap);
                            break;
            }

            Uri path=data.getData();
            imagen.setImageURI(path);
        }
    }
}