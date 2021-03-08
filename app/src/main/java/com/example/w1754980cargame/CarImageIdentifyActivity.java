package com.example.w1754980cargame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.w1754980cargame.BusinessLogic.CarsManager;
import com.example.w1754980cargame.Models.Car;

import java.util.HashMap;
import java.util.Map;
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
    HashMap<String, ImageView> carNamesMap = new HashMap<String, ImageView>(3);
    String[] carNames = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_image_identify);

        carsManager = new CarsManager(CarImageIdentifyActivity.this);
        carsManager.createCars();

        setupListeners();
        setupImageView();
        setupTextViews();
        setupNextCarImage();
    }

    // MAIN LOGIC METHODS
    @Nullable
    private String getCorrespondingCarNameFromClickedImage(View v) {
        for (Map.Entry<String, ImageView> carNameMapObject : carNamesMap.entrySet()) {
            ImageView tempImageView = (ImageView) carNameMapObject.getValue();

            if (tempImageView.equals(tempImageView)) {
                return carNameMapObject.getKey();
            }
        }
        return null;
    }

    private void showErrorText() {
        resultTextView.setText("SORRY BUT THIS IS WRONG");
        resultTextView.setTextColor(Color.RED);
        resultTextView.setVisibility(View.VISIBLE);
    }

    private void hideErrorText() {
        resultTextView.setVisibility(View.INVISIBLE);
    }

    private void showCorrectMessage() {
        resultTextView.setText("CORRECT! CLICK 'NEXT' FOR NEXT CHALLENGE");
        resultTextView.setTextColor(Color.GREEN);
        resultTextView.setVisibility(View.VISIBLE);
    }

    private void hideCorrectMessage() {
        resultTextView.setVisibility(View.INVISIBLE);
    }

    private void setupNextCarImage() {
        String carName1 = carsManager.getRandomCar().getName();
        String carName2 = carsManager.getRandomCar().getName();
        String carName3 = carsManager.getRandomCar().getName();

        carNamesMap.put(carName1, imageView1);
        carNamesMap.put(carName2, imageView2);
        carNamesMap.put(carName3, imageView3);

        carNames[0] = carName1; carNames[1] = carName2; carNames[2] = carName3;
        // https://stackoverflow.com/questions/21726033/picking-a-random-item-from-an-array-of-strings-in-java/21726085
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

    private void prepareViewToShowNextImages() {
        identifyButton.setText("IDENTIFY");
        hideCorrectMessage();
        hideErrorText();
        setupNextCarImage();
    }

    // UI Setup mathods

    private void setupListeners() {
        backButton = findViewById(R.id.carImageBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView[] imageViews = new ImageView[3];
        imageViews[0] = imageView1; imageViews[1] = imageView2; imageViews[2] = imageView3;

        for (ImageView imageView : imageViews) {

            //https://stackoverflow.com/questions/7326601/android-imageview-on-click
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String selectedCarName = getCorrespondingCarNameFromClickedImage(v);
                    if (selectedCarName == carNameToGuess) {
                        showCorrectMessage();
                    } else {
                        showErrorText();
                    }
                    identifyButton.setText("NEXT");
                }
            });
        }

        identifyButton = findViewById(R.id.carImageIdentifyButton);
        identifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("INFO", "BUTTON CLICKED");

                if (identifyButton.getText() == "NEXT") {
                    prepareViewToShowNextImages();
                }
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
        imageView2 = (ImageView) findViewById(R.id.carImageCarImageView2);
        imageView3 = (ImageView) findViewById(R.id.carImageCarImageView3);
    }
}