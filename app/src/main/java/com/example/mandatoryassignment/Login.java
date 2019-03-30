package com.example.mandatoryassignment;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class Login extends AppCompatActivity implements View.OnClickListener {
    static EditText Email;
    static EditText Password;
    CheckBox remember_Checkbox;
    Button Login;
    Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        startup();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Email.setText(extras.getString("username"));
            Password.setText(extras.getString("password"));
        }

       Login.setOnClickListener(this);
        Register.setOnClickListener(this);
    }

    private void startup() {
        Email = findViewById(R.id.E_mail);
        Password = findViewById(R.id.Pass_word);
        remember_Checkbox = findViewById(R.id.Remember_me);
        Login = findViewById(R.id.Sign_in);
        Register = findViewById(R.id.Register);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.Sign_in:
                Log.d("onclick -->", "sign in button");
                Passwordchecker passwordchecker = new Passwordchecker();
                try {
                    loginchecker(passwordchecker.execute().get());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                break;

            case R.id.Register:
              Intent intent= new Intent(this,userRegister.class);
              startActivity(intent);

                break;
        }
    }

       public void loginchecker (String serverresponse ){
           Intent intent = new Intent(this, MainActivity.class);
            Log.d("method passwordchecker",serverresponse);
           if(serverresponse.equals(String.valueOf(202))){
               startActivity(intent);

           }else
               Toast.makeText(this, "Wrong password",
                       Toast.LENGTH_LONG).show();
        }






    public static class Passwordchecker extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }


        @Override
        protected String doInBackground(String... strings) {

            String webapiadress = "http://10.149.88.167:8888/loginvalidation?" +"username=" + Email.getText().toString()+ "&password=" + Password.getText().toString();
            String reponse="";
            URL url;
            try {
                url = new URL(webapiadress);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();
                reponse=String.valueOf(con.getResponseCode());
                Log.d("Server response",reponse);



            } catch (Exception e) {
                e.printStackTrace();


            }

            return reponse;
        }


    }
}