package com.example.mandatoryassignment;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {
    MyRecyclerViewAdapter adapter;
    ArrayList<String> teachers = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // data to populate the RecyclerView with
        apicaller();
        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvAnimals);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, teachers);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


    }

    public void apicaller(){

        apicall api = new apicall();
        try {
          String a = api.execute().get();
          String ab[]=a.split(" ");
            teachers.addAll(Arrays.asList(ab));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, Course1.class);
        intent.putExtra("teacher", teachers.get(position));
        startActivity(intent);


    }




    public static class apicall extends AsyncTask<String, String, String> {
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


            String webapiadress = "http://10.149.88.167:8888/all";
            String reponse="";
            URL url;
            try {
                url = new URL(webapiadress);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();
                BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
                reponse = bf.readLine();
                reponse = reponse.replace(", ", " ");
                reponse = reponse.substring(1);
                reponse = reponse.substring(0, reponse.length() - 1);



            } catch (Exception e) {
                e.printStackTrace();


            }

            return reponse;
        }


    }
}











