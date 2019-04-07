package com.example.mandatoryassignment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Menu extends AppCompatActivity implements View.OnClickListener {
Button teacherratebutton;
Button googlemapsbtn;
Button signout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setup();
        teacherratebutton.setOnClickListener(this);
        googlemapsbtn.setOnClickListener(this);
        signout.setOnClickListener(this);

    }

    private void setup() {
    teacherratebutton=findViewById(R.id.Rate_btn);
    googlemapsbtn=findViewById(R.id.location_btn);
    signout=findViewById(R.id.signoutmenu_btn);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Rate_btn:
                Intent teacherRateactivity= new Intent(this,Course1.class);
                 startActivity(teacherRateactivity);
                break;

            case R.id.location_btn:
                Intent userlocationactiviry= new Intent(this,userlocation.class);
                 startActivity(userlocationactiviry);
                break;

            case R.id.signoutmenu_btn:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Menu.this);
                alertDialog.setTitle("Logout"); // Sets title for your alertbox
                alertDialog.setMessage("Are you sure you want to Logout ?"); // Message to be displayed on alertbox


                /* When positive (yes/ok) is clicked */
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                        pref.edit().clear().apply();

                        Toast.makeText(Menu.this,"Successfully Logged Out", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });

                /* When negative (No/cancel) button is clicked*/
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();
                break;
        }



        }
}

