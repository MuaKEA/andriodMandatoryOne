package com.example.mandatoryassignment;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Course1 extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener, View.OnClickListener {
    RatingBar StarRating1;
    RatingBar StarRating2;
    RatingBar StarRating3;
    RatingBar StarRating4;
    RatingBar StarRating5;
    RatingBar StarRating6;
    Button sendbutton;
    TextView StarRatingtxt1;
    TextView StarRatingtxt2;
    TextView StarRatingtxt3;
    TextView StarRatingtxt4;
    TextView StarRatingtxt5;
    TextView StarRatingtxt6;
    Float score=0F;
    String teachersname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course1);
        setup();


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle!=null) {

            teachersname=bundle.getString("teacher");
            Log.d("teacher name ", teachersname);
        }
        sendbutton.setOnClickListener(this);
        StarRating1.setOnRatingBarChangeListener(this);
        StarRating2.setOnRatingBarChangeListener(this);
        StarRating3.setOnRatingBarChangeListener(this);
        StarRating4.setOnRatingBarChangeListener(this);
        StarRating5.setOnRatingBarChangeListener(this);
        StarRating6.setOnRatingBarChangeListener(this);


    }

    public void setup(){
        StarRatingtxt1=findViewById(R.id.T1);
        StarRatingtxt2=findViewById(R.id.T2);
        StarRatingtxt3=findViewById(R.id.T3);
        StarRatingtxt4=findViewById(R.id.T4);
        StarRatingtxt5=findViewById(R.id.T5);
        StarRatingtxt6=findViewById(R.id.T6);

        StarRating1=findViewById(R.id.ratingBar1);
        StarRating2=findViewById(R.id.ratingBar2);
        StarRating3=findViewById(R.id.ratingBar3);
        StarRating4=findViewById(R.id.ratingBar4);
        StarRating5=findViewById(R.id.ratingBar5);
        StarRating6=findViewById(R.id.ratingBar6);

        sendbutton=findViewById(R.id.sendbutton);

    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser)   {
         switch (ratingBar.getId()){

            case R.id.ratingBar1:

                StarRatingtxt1.setText(String.valueOf(rating *10 ));
                Log.d("step size =", score.toString());
                break;

            case R.id.ratingBar2:
                StarRatingtxt2.setText(String.valueOf(rating *10 ));
                break;

            case R.id.ratingBar3:

                StarRatingtxt3.setText(String.valueOf(rating *10 ));
                break;

            case R.id.ratingBar4:

                StarRatingtxt4.setText(String.valueOf(rating *10 ));
                break;

            case R.id.ratingBar5:
                StarRatingtxt5.setText(String.valueOf(rating * 10));
                break;

                case R.id.ratingBar6:
                    StarRatingtxt6.setText(String.valueOf(rating * 10));
                break;
        }


    }

    @Override
    public void onClick(View v) {
    sendmail();


    }
    private void sendmail() {
        String[] TO = {"altair2400@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);


        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "student rating");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "a student has rated: " + averagescore());

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Course1.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

     public String averagescore() {
        score=StarRating1.getRating();
        score=score + StarRating1.getRating();
        score=score + StarRating2.getRating();
        score=score + StarRating3.getRating();
        score=score + StarRating4.getRating();
        score=score + StarRating5.getRating();
        score=score + StarRating6.getRating();

        Float rating = score * 10;
        rating=rating / 6;

        Log.d("ratings = ", rating.toString());

        Log.d("this is the rating-->", rating.toString());
        if (rating > 90) {
          return "score A ";


        } else if (rating > 80 && rating < 90) {
          return "score B ";

        } else if (rating > 70 && rating < 80) {
            return "score C";

        } else if (rating > 60 && rating < 70) {
            return "score D ";

        } else if (rating > 50 && rating < 60) {
            return "score E";

        } else if (rating < 50) {
            return "GET A NEW JOB";
        }

      return null;
    }


}

