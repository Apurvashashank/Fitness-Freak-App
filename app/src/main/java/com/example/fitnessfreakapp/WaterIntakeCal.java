package com.example.fitnessfreakapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import static java.lang.String.valueOf;

public class WaterIntakeCal extends AppCompatActivity {
    int gender,pregsta;
    TextView Gender,txt_age,txt_weight,txt_duration,tv_water,tv_fluid;
    RadioButton rb_notpregnant,rb_pregnant,rb_lactating,rb_male,rb_female;
    Button bt_calculate;
    EditText txt_years,txt_kg,txt_hours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //This Line will hide the status bar from the screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_water_intake_cal);


        //Declares
        Gender = findViewById(R.id.txt_Gender);
        bt_calculate =findViewById(R.id.btn_CalWater);

        rb_male = findViewById(R.id.radioButtonMale);
        rb_female = findViewById(R.id.radioButtonFemale);

        txt_age = findViewById(R.id.txt_age);
        txt_years = findViewById(R.id.editTextNumber_years);

        txt_weight =findViewById(R.id.txt_weight);
        txt_kg = findViewById(R.id.editTextNumber_kg);

        txt_duration = findViewById(R.id.txt_duration);
        txt_hours = findViewById(R.id.editTextNumber_hours);

        rb_notpregnant = findViewById(R.id.rb_NotPregnant);
        rb_pregnant = findViewById(R.id.rb_Pregnant);
        rb_lactating = findViewById(R.id.rb_lactating);

        tv_water = findViewById(R.id.textView_water);
        tv_fluid = findViewById(R.id.textView_fluid);


        RadioGroup radioGRP_gender = findViewById(R.id.radioGrp_Gender);
        radioGRP_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId1) {
                if(checkedId1 == R.id.radioButtonMale)
                    gender = 1;
                else if(checkedId1 == R.id.radioButtonFemale)
                    gender = 0;
            }
        });
        RadioGroup radioGRP_pregsta = findViewById(R.id.radioGrp_Pregsta);
        radioGRP_pregsta.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId2) {
                if (checkedId2 == R.id.rb_NotPregnant)
                    pregsta = 0;
                else if(checkedId2 == R.id.rb_Pregnant)
                    pregsta = 1;
                else if(checkedId2 == R.id.rb_lactating)
                    pregsta = 2;
            }
        });
    }

    private Boolean validateWeight() {
        String val = txt_years.getText().toString();

        if (val.isEmpty()) {
            txt_years.setError("Field cannot be empty");
            return false;
        } else {
            txt_years.setError(null);
            return true;
        }
    }


    private Boolean validateAge() {
        String val = txt_kg.getText().toString();

        if (val.isEmpty()) {
            txt_kg.setError("Field cannot be empty");
            return false;
        } else {
            txt_kg.setError(null);
            return true;
        }
    }


    private Boolean validateDuration() {
        String val = txt_hours.getText().toString();

        if (val.isEmpty()) {
            txt_hours.setError("Field cannot be empty");
            return false;
        } else {
            txt_hours.setError(null);
            return true;
        }
    }

    public void calculate(View v) {

        if (!validateWeight() | !validateAge() | !validateDuration() ) {
            Toast.makeText(getApplicationContext(), "Enter correct details", Toast.LENGTH_SHORT).show();
            return;
        }


        Float age = Float.parseFloat(txt_years.getText().toString());
        Float weight = Float.parseFloat(txt_kg.getText().toString());
        Float hours = Float.parseFloat(txt_hours.getText().toString());

        Double water = ((weight / 30) + (hours * 0.7));
        Double fluid = weight / 30.0;
        DecimalFormat formatVal = new DecimalFormat("##.##");

        while (age > 0) {
            if (age <= 0.5) {
                water = 0.70;
                fluid = 0.70;
                tv_water.setText("Milk Required: " + valueOf(water)+"litres per day");
                tv_fluid.setText("Fluid Required: " + valueOf(fluid) +"litres per day");
                break;
            } else if ((age > 0.5) && (age <= 1)) {
                water = 0.80;
                fluid = 0.80;
                tv_water.setText("Water Required: " + valueOf(water)+"litres per day");
                tv_fluid.setText("Fluid Required: " + valueOf(fluid)+"litres per day");
                break;
            } else if ((age > 1) && (age <= 8)) {
                water += 0.50;
                fluid += 0.30;
                tv_water.setText("Water Required: " + valueOf(formatVal.format(water))+"litres per day");
                tv_fluid.setText("Fluid Required: " + valueOf(formatVal.format(fluid))+"litres per day");
                break;
            } else if ((age > 8) && (age <= 13)) {
                if (gender == 1) {
                    water += 0.50;
                    fluid += 0.20;
                    tv_water.setText("Water Required: " + valueOf(formatVal.format(water))+"litres per day");
                    tv_fluid.setText("Fluid Required: " + valueOf(formatVal.format(fluid))+"litres per day");
                    break;
                }
                else if (gender == 0) {
                    if( pregsta == 0) {
                        tv_water.setText("Water Required: " + valueOf(formatVal.format(water))+"litres per day");
                        tv_fluid.setText("Fluid Required: " + valueOf(formatVal.format(fluid))+"litres per day");
                        break;
                    }
                    else if ( pregsta == 1) {
                        water += 0.50;
                        tv_water.setText("Water Required: " + valueOf(formatVal.format(water))+"litres per day");
                        tv_fluid.setText("Fluid Required: " + valueOf(formatVal.format(fluid))+"litres per day");
                        break;
                    }
                    else if (pregsta == 2) {
                        water += 0.80;
                        tv_water.setText("Water Required: " + valueOf(formatVal.format(water))+"litres per day");
                        tv_fluid.setText("Fluid Required: " + valueOf(formatVal.format(fluid))+"litres per day");
                        break;
                    }
                }

            }
            else if ((age >= 13)) {
                if (gender == 1) {
                    water += 1;
                    tv_water.setText("Water Required: " + valueOf(formatVal.format(water))+"litres per day");
                    tv_fluid.setText("Fluid Required: " + valueOf(formatVal.format(fluid))+"litres per day");
                    break;
                }
                else if (gender == 0) {
                    if( pregsta == 0) {
                        tv_water.setText("Water Required: " + valueOf(formatVal.format(water))+"litres per day");
                        tv_fluid.setText("Fluid Required: " + valueOf(formatVal.format(fluid))+"litres per day");
                        break;
                    }
                    else if (pregsta == 1) {
                        water += 0.30;
                        tv_water.setText("Water Required: " + valueOf(formatVal.format(water))+"litres per day");
                        tv_fluid.setText("Fluid Required: " + valueOf(formatVal.format(fluid))+"litres per day");
                        break;
                    }
                    else if (pregsta == 2) {
                        water += 0.60;
                        tv_water.setText("Water Required: " + valueOf(formatVal.format(water))+"litres per day");
                        tv_fluid.setText("Fluid Required: " + valueOf(formatVal.format(fluid))+"litres per day");
                        break;
                    }
                }
            }

        }

    }
}