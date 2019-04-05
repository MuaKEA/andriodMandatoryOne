package com.example.mandatoryassignment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class userRegister extends AppCompatActivity implements View.OnClickListener  {
    Button back;
    Button register;
     EditText username;
     EditText password;
    EditText conformpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        startup();

        back.setOnClickListener(this);
        register.setOnClickListener(this);

    }

    private void startup() {
        back = findViewById(R.id.back_button);
        register = findViewById(R.id.register_button);
        username = findViewById(R.id.username_txt);
        password = findViewById(R.id.password_txt);
        conformpassword = findViewById(R.id.conform_txt);

    }


    @Override
    public void onClick(View v) {
        Intent Loginclassactivity = new Intent(this, Login.class);

        switch (v.getId()) {

            case R.id.back_button:
                startActivity(Loginclassactivity);
                break;

            case R.id.register_button:
                try {
                    if(username_passwordchecker()){

                        Loginclassactivity.putExtra("username",username.getText().toString());
                        Loginclassactivity.putExtra("password",password.getText().toString());
                        startActivity(Loginclassactivity);
                    }else
                        break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        }



         public boolean username_passwordchecker() throws ExecutionException, InterruptedException {
             SaveUser user= new SaveUser();

             if (!password.getText().toString().equals(conformpassword.getText().toString())) {
                 Toast.makeText(this, "Passwords dont match, try again!!  ",
                         Toast.LENGTH_LONG).show();
                     return false;

             } else if (!username.getText().toString().contains("@")) {
                     Toast.makeText(this, "@ missing,",
                             Toast.LENGTH_LONG).show();
                     return false;

             }else if(user.execute().get().equals("403")){
                 Toast.makeText(this, "username is already taken!!!",
                         Toast.LENGTH_LONG).show();
                     return false;
             }

                     return true;

             }



        public class SaveUser extends AsyncTask<String, String, String> {


            String username2 = username.getText().toString();
            String password2 = password.getText().toString();


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
                String webapiadress = "http://10.149.88.167:8888//saveLogin?" + "username=" + username2 + "&password=" + password2;

                Log.d("username-->", username2);
                Log.d("username-->", password2);
                String reponse = "";
                URL url;
                try {
                    url = new URL(webapiadress);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    con.connect();
                    reponse = String.valueOf(con.getResponseCode());
                    Log.d("Server response", reponse);


                } catch (Exception e) {
                    e.printStackTrace();


                }
                return reponse;
            }
        }

}