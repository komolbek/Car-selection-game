package com.example.w1754980cargame;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.w1754980cargame.BusinessLogic.CarsManager;

public class CarMakeIdentifyActivity extends BaseActivity { // OOP. Inheritance

    private Spinner spinner;
    private ImageView imageView;
    private TextView resultCarNameTextView;

    private String carNameInImage;
    private String carNameSelectedInSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_make_identify);

        carsManager = new CarsManager(CarMakeIdentifyActivity.this);
        carsManager.createCars();
        carsLeft = carsManager.getCars().size();

        setupButtons();
        setupButtonListeners();
        setupSpinner();
        setupImageView();
        setupNextCarImage();
        setupResultTextView();
    }

    // MAIN LOGIC METHODS

    private void setupNextCarImage() {
        if (carsLeft != 0) {
            carsLeft -= 1;

            String carName = carsManager.getRandomCar().getName();
            Log.i("INFO", carName);

            if (carName == null) {
                Log.e("ERROR", "FINISHED");
            }

            this.carNameInImage = carName;

            int resID = getResources().getIdentifier(
                    carName,
                    "drawable",
                    getPackageName()
            );
            imageView.setImageResource(resID);
        } else {
            showFinishedText("Game IS FINISHED");
            return;
        }
    }

    private boolean checkIfSelectedCarAndCarInImageMatches() {
        Log.e("ERROR", this.carNameInImage + " ->>> " + this.carNameSelectedInSpinner);
        if (carNameSelectedInSpinner.equals(carNameInImage)) {
            Log.i("INFO", "Car name is correct");

            resultCarNameTextView.setText(spinner.getSelectedItem().toString().toUpperCase());
            resultCarNameTextView.setVisibility(View.VISIBLE);

            return true;
        } else {
            Log.e("ERROR", "Car name is not correct");

            return false;
        }
    }

    private void prepareViewForNextCarImage() {
        identifyButton.setText("Identify");
        resultCarNameTextView.setText("");
        resultCarNameTextView.setVisibility(View.INVISIBLE);
        hideCorrectMessage();
        setupNextCarImage();
    }

//    SETUP UI ELEMENTS

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
                Log.i("INFO", spinner.getSelectedItem().toString());
                carNameSelectedInSpinner = spinner.getSelectedItem().toString().toLowerCase();

                if (checkIfSelectedCarAndCarInImageMatches()) {
                    showCorrectMessage();

                    if (identifyButton.getText().equals("NEXT")) {
                        prepareViewForNextCarImage();
                    } else {
                        identifyButton.setText("NEXT");
                    }
                } else {
                    showErrorText("WRONG! TRY AGAIN!");
                }
            }
        });
    }

    private void setupButtons() {
        identifyButton = (Button) findViewById(R.id.carMakeIdentifyButton);
        backButton = findViewById(R.id.carIdentifyBackButton);
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

    private void setupResultTextView() {
        resultTextView = findViewById(R.id.carMakeResultTextView);
        resultTextView.setVisibility(View.INVISIBLE);
        resultCarNameTextView = findViewById(R.id.resultCarNameTextView);
        resultCarNameTextView.setVisibility(View.INVISIBLE);
    }
}