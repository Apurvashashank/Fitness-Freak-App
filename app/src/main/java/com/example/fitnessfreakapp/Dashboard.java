package com.example.fitnessfreakapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Dashboard extends AppCompatActivity {

    CardView cardstepCounter, cardWaterIntake, cardCaloriecal, cardProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        cardstepCounter = findViewById(R.id.cardStepCounter);
        cardWaterIntake = findViewById(R.id.cardWater);
        cardCaloriecal = findViewById(R.id.cardCalorie);
        cardProfile =  findViewById(R.id.cardProfile);
    }
        //To call stepCounter
    public void stepCounterPage(View view) {
        Intent intent = new Intent(Dashboard.this, StepCounter.class);
        startActivity(intent);
    }


    //To call waterIntake
    public void waterIntakePage(View view) {
        Intent intent = new Intent(Dashboard.this, WaterIntakeCal.class);
        startActivity(intent);
    }

    //To call calorieCounter
    public void timerPage(View view) {
        Intent intent = new Intent(Dashboard.this, Timer.class);
        startActivity(intent);
    }

    //To call profilePage
    public void profilePageRedirect(View view) {
        Dashboard.this.finish();
        System.exit(0);

    }

}