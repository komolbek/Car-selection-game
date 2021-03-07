package com.example.w1754980cargame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.w1754980cargame.BusinessLogic.CarsManager;

public class CarMakeIdentifyActivity extends AppCompatActivity {

    private Button backButton;
    private Button identifyButton;
    private Spinner spinner;
    private ImageView imageView;
    private TextView resultTextView;
    private TextView resultCarNameTextView;

    CarsManager carsManager;

    private String carNameInImage;
    private String carNameSelectedInSpinner;
    private Boolean isCarImageSet = false;
    private Boolean isCarSelected = false;

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
        setupResultTextView();
    }

    // MAIN LOGIC METHODS

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

                if (isCarImageSet) {
                    compareSelectedCarAndCarInImage();
                    return;
                }
            }
        });
    }

    private void setupCarImage() {
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
        this.isCarImageSet = true;
    }

    private void compareSelectedCarAndCarInImage() {
        Log.e("ERROR", this.carNameInImage + " ->>> " + this.carNameSelectedInSpinner);
        if (carNameSelectedInSpinner.equals(carNameInImage)) {
            Log.i("INFO", "Car name is correct");

            resultTextView.setText("CORRECT!");
            resultTextView.setTextColor(Color.GREEN);
            resultCarNameTextView.setText(spinner.getSelectedItem().toString().toUpperCase());
            resultTextView.setVisibility(View.VISIBLE);
            resultCarNameTextView.setVisibility(View.VISIBLE);

            isCarSelected = true;
        } else {
            Log.e("ERROR", "Car name is not correct");

            resultTextView.setText("NOT CORRECT!");
            resultTextView.setTextColor(Color.RED);
            resultCarNameTextView.setText(spinner.getSelectedItem().toString().toUpperCase());
            resultTextView.setVisibility(View.VISIBLE);
            resultCarNameTextView.setVisibility(View.VISIBLE);

            isCarSelected = false;
        }

        if (isCarSelected) {
            setupCarImage();
            resultTextView.setVisibility(View.INVISIBLE);
            resultCarNameTextView.setVisibility(View.INVISIBLE);
            isCarSelected = false;
        }
    }

//    SETUP UI ELEMENTS

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