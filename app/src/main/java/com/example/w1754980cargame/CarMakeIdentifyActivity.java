package com.example.w1754980cargame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.w1754980cargame.BusinessLogic.CarsManager;

public class CarMakeIdentifyActivity extends AppCompatActivity {

    private Button backButton;
    private Spinner spinner;
    private ImageView imageView;
    private Resources res = getResources();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_make_identify);

        setupButtons();
        setupButtonListeners();
        setupSpinner();
        setupImageView();
        setupCarImage();
    }

    // Private methods

    private void setupButtons() {
        backButton = findViewById(R.id.carIdentifyBackButton);
    }

    private void setupButtonListeners() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupSpinner() {
        spinner = (Spinner) findViewById(R.id.carIdentifySpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                CarMakeIdentifyActivity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.names)
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void setupImageView() {
        imageView = (ImageView) findViewById(R.id.carIdentifyImageView);
    }

    private void setupCarImage() {
        CarsManager carsManager = new CarsManager(CarMakeIdentifyActivity.this);
        carsManager.createCars();

//        int resID = res.getIdentifier(
//                carsManager.getRandomCar().getName() ,
//                "drawable", getPackageName()
//        );
//        imageView.setImageResource(resID);
    }
}