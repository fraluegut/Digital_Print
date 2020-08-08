package com.example.digitalprint1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText Excavador_name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private Button btnlink;
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Excavador_name = (EditText)findViewById(R.id.Excavador_name);
        Password = (EditText)findViewById(R.id.Password);
        Info = (TextView)findViewById(R.id.Info);
        Login = (Button)findViewById(R.id.Login);

        Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                validate(Excavador_name.getText().toString(), Password.getText().toString());
            }
        });

        btnlink = (Button)findViewById(R.id.btnlink);

        //url="http://134.209.237.119:5000/";
//        url="http://127.0.0.1:5000/datos/?cuadro_letra=C&cuadro_numero=1&nivel_estrato=2&uuee=3";
        url="http://192.168.1.116:5000/datos/?cuadro_letra=C&cuadro_numero=1&nivel_estrato=2&uuee=3";
        btnlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    private void validate(String userName, String userPassword){
        if((userName.equals("Admin")) && (userPassword.equals("1234"))){
            Intent intent = new Intent(MainActivity.this, Cuadro_Nivel.class);
            startActivity(intent);
        }else{
            counter--;

            Info.setText("Número de intentos restantes: " + counter);
            if(counter == 0){
                Login.setEnabled(false);
            }
        }

    }
}