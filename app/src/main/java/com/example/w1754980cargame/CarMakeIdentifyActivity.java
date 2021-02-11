package com.example.w1754980cargame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.w1754980cargame.BusinessLogic.CarsManager;

public class CarMakeIdentifyActivity extends AppCompatActivity {

    private Button backButton;
    private Button identifyButton;
    private Spinner spinner;
    private ImageView imageView;

    CarsManager carsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_make_identify);

        carsManager = new CarsManager(CarMakeIdentifyActivity.this);
        carsManager.createCars();

        setupButtons();
        setupButtonListeners();
        setupSpinner();
        setupImageView();
        setupCarImage();
    }

    // Private methods

    private void setupButtons() {
        identifyButton = (Button) findViewById(R.id.carMakeIdentifyButton);
        backButton = findViewById(R.id.carIdentifyBackButton);
    }

    private void setupButtonListeners() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        identifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupCarImage();
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
        String carName = carsManager.getRandomCar().getName();
        Log.e("INFO", carName);

        if (carName == null) {
            Log.e("ERROR", "FINISHED");
        }

        int resID = getResources().getIdentifier(
                carName,
                "drawable",
                getPackageName()
        );
        imageView.setImageResource(resID);
    }


}