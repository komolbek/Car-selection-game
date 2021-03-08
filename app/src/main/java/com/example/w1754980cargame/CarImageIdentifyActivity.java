package com.example.w1754980cargame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.w1754980cargame.BusinessLogic.CarsManager;

import java.util.Random;

public class CarImageIdentifyActivity extends AppCompatActivity {

    private Button backButton;

    private Button identifyButton;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private TextView resultTextView;
    private TextView carNameToGuessTextView;

    private String carNameToGuess;

    CarsManager carsManager;
    String[] carNames = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_image_identify);

        carsManager = new CarsManager(CarImageIdentifyActivity.this);
        carsManager.createCars();

        setupButtons();
        setupImageView();
        setupTextViews();
    }

    // MAIN LOGIC METHODS

    private void setupNextCarImage() {
        String carName1 = carsManager.getRandomCar().getName();
        String carName2 = carsManager.getRandomCar().getName();
        String carName3 = carsManager.getRandomCar().getName();

        carNames[0] = carName1; carNames[1] = carName2; carNames[2] = carName3;
        carNameToGuess = carNames[new Random().nextInt(carNames.length)];
        carNameToGuessTextView.setText(carNameToGuess);

        if ((carName1 == null) && (carName2 == null) && (carName3 == null)) {
            Log.e("ERROR", "FINISHED!!! NO CARS LEFT IN TO USE");
        }

        int imageFromResources_1 = getResources().getIdentifier(
                carName1,
                "drawable",
                getPackageName()
        );
        imageView1.setImageResource(imageFromResources_1);

        int imageFromResources_2 = getResources().getIdentifier(
                carName2,
                "drawable",
                getPackageName()
        );
        imageView2.setImageResource(imageFromResources_2);

        int imageFromResources_3 = getResources().getIdentifier(
                carName3,
                "drawable",
                getPackageName()
        );
        imageView3.setImageResource(imageFromResources_3);
        
        
    }

    // UI Setup mathods

    private void setupButtons() {
        backButton = findViewById(R.id.carImageBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        identifyButton = findViewById(R.id.carImageIdentifyButton);
        identifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("INFO", "BUTTON CLICKED");

            }
        });
    }

    private void setupTextViews() {
        resultTextView = findViewById(R.id.carImageResultTextView);
        resultTextView.setVisibility(View.INVISIBLE);
        carNameToGuessTextView = findViewById(R.id.carImageCarNameToGuessTextView);
    }

    private void setupImageView() {
        imageView1 = (ImageView) findViewById(R.id.carImageCarImageView1);
        imageView2 = (ImageView) findViewById(R.id.carImageCarImageView1);
        imageView3 = (ImageView) findViewById(R.id.carImageCarImageView1);
    }
}