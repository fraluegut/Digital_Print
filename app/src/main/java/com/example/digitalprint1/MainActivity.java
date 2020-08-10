package com.example.digitalprint1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private EditText Excavador_name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private Button btnlink;
    private String url;
    private TextView mTextViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Excavador_name = (EditText)findViewById(R.id.Excavador_name);
        Password = (EditText)findViewById(R.id.Password);
        Info = (TextView)findViewById(R.id.Info);
        Login = (Button)findViewById(R.id.Login);
        mTextViewResult = (TextView)findViewById(R.id.text_view_result);




        Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                validate(Excavador_name.getText().toString(), Password.getText().toString());
            }
        });

        btnlink = (Button)findViewById(R.id.btnlink);

        //url="http://134.209.237.119:5000/";
//        url="http://127.0.0.1:5000/datos/?cuadro_letra=C&cuadro_numero=1&nivel_estrato=2&uuee=3";
        //url="http://192.168.1.116:5000/datos/?cuadro_letra=C&cuadro_numero=1&nivel_estrato=2&uuee=3";
//        String command = "curl --data  'cuadro_letra=9&cuadro_numero=2&nivel_estrato=9&uuee=9' http://localhost:5000/datos/get/";
//        Process process = Runtime.getRuntime().exec(command);

        btnlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Post", Toast.LENGTH_SHORT).show();
                OkHttpClient client = new OkHttpClient();


                String url = "http://192.168.1.116:5001/datos/";
//                String url = "https://reqres.in/api/users?page=2";

                Request request = new Request.Builder()
                        .url(url)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            final String myResponse = response.body().string();

                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "Post1", Toast.LENGTH_SHORT).show();
                                    mTextViewResult.setText(myResponse);
                                }
                            });
                        }
                        else {
                            Toast.makeText(MainActivity.this, "NO", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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



    private static final String USER_AGENT = "Mozilla/5.0";

    private static final String GET_URL = "https://localhost:9090/SpringMVCExample";

    private static final String POST_URL = "http://localhost:5001/datos/get/";

    private static final String POST_PARAMS = "cuadro_letra=a&cuadro_numero=a&nivel_estrato=a&uuee=a";

    public static void main(String[] args) throws IOException {


        sendPOST();
        System.out.println("POST DONE");
    }


    private static void sendPOST() throws IOException {
        URL obj = new URL(POST_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("POST request not worked");
        }
    }


}