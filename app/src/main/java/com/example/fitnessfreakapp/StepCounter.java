package com.example.fitnessfreakapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import java.text.DecimalFormat;

public class StepCounter extends AppCompatActivity {

    private TextView step_counter;
    private double MagnitudePrevious = 0;
    private Integer stepCount = 0;
    private TextView onkm,oncal,onmeter;
    private Button stepstometer, resetbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_step_counter);

        step_counter = findViewById(R.id.txt_stepcounted);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        stepstometer = findViewById(R.id.btnconvertstep);
        resetbtn = findViewById(R.id.btnreset);

        stepstometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenConversion();
            }
        });
        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenReset();

            }
        });
        SensorEventListener stepDetector = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent!= null){
                    float x_acceleration = sensorEvent.values[0];
                    float y_acceleration = sensorEvent.values[1];
                    float z_acceleration = sensorEvent.values[2];

                    double Magnitude = Math.sqrt(x_acceleration*x_acceleration + y_acceleration*y_acceleration + z_acceleration*z_acceleration);
                    double MagnitudeDelta = Magnitude - MagnitudePrevious;
                    MagnitudePrevious = Magnitude;

                    if (MagnitudeDelta > 6){
                        stepCount++;
                    }
                    step_counter.setText(stepCount.toString());
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };
        sensorManager.registerListener(stepDetector, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void OpenConversion(){
        onmeter= findViewById(R.id.txt_meter);
        onkm= findViewById(R.id.txt_km);
        oncal= findViewById(R.id.txt_cal);
        double convert=Double.valueOf(step_counter.getText().toString());
        double resultonmeter=convert*0.762;
        double resultonkm=convert/1312.33;
        double cal=resultonkm/55;
        DecimalFormat formatVal=new DecimalFormat("##.####");
        onmeter.setText(formatVal.format(resultonmeter)+ " Meters ");
        onkm.setText(formatVal.format(resultonkm)+ " Kilometers ");
        oncal.setText(formatVal.format(cal)+ " Calories Burned ");
    }
    public void OpenReset(){
        oncal.setText("");
        onkm.setText("");
        onmeter.setText("");
    }

    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putInt("stepCount", stepCount);
        editor.apply();
    }

    protected void onStop() {
        super.onStop();

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putInt("stepCount", stepCount);
        editor.apply();
    }

    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        stepCount = sharedPreferences.getInt("stepCount", 0);
    }

}