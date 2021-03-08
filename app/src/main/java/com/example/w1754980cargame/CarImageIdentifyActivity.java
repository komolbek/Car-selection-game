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

import java.util.ArrayList;
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

    ArrayList<ImageView> imageViewsList = new ArrayList<ImageView>(3);
    ArrayList<String> carNamesList = new ArrayList<String>(3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_image_identify);

        carsManager = new CarsManager(CarImageIdentifyActivity.this);
        carsManager.createCars();

        setupImageView();
        setupTextViews();
        setupListeners();
        setupNextCarImage();
    }

    // MAIN LOGIC METHODS

    @Nullable
    private Integer getCorrespondingCarNameIndexFromClickedImage(ImageView v) {
        int selectedImageViewID = v.getId();

        for (int i = 0; i < imageViewsList.size(); i++) {

            if (imageViewsList.get(i).getId() == selectedImageViewID) {
                Log.i("INFO", "TAG OF MATCHED IMAGEVIEW IS: " + imageViewsList.get(i).getId());

                return i;
            }
        }

        return  null;
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

        carNamesList.add(carName1);
        carNamesList.add(carName2);
        carNamesList.add(carName3);

        // https://stackoverflow.com/questions/21726033/picking-a-random-item-from-an-array-of-strings-in-java/21726085
        carNameToGuess = carNamesList.get(new Random().nextInt(carNamesList.size()));
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

        imageViewsList.add(imageView1);
        imageViewsList.add(imageView2);
        imageViewsList.add(imageView3);
    }

    private void prepareViewToShowNextImages() {
        identifyButton.setText("IDENTIFY");
        hideCorrectMessage();
        hideErrorText();
        carNameToGuess = "";
        this.carNamesList.clear();
        Log.i("INFO", "CARNAMES AFTER CLEAN: " + carNamesList.size());
        this.imageViewsList.clear();
        Log.i("INFO", "IMAGEVIEWS AFTER CLEAN: " + imageViewsList.size());

        imageView1.setEnabled(true);
        imageView2.setEnabled(true);
        imageView3.setEnabled(true);

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

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("INFO", "IMAGEVIEW 1 CLICKED");

                int selectedCarNameIndex = getCorrespondingCarNameIndexFromClickedImage(imageView1);
                String selectedCarName = carNamesList.get(selectedCarNameIndex);
                Log.i("INFO", "MATCHED!!! RETURNED CAR NAME IS: " + selectedCarName);

                if (selectedCarName != null) {
                    if (selectedCarName == carNameToGuess) {
                        showCorrectMessage();
                    } else {
                        showErrorText();
                    }
                }
                imageView1.setEnabled(false);
                identifyButton.setText("NEXT");
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("INFO", "IMAGEVIEW 2 CLICKED");

                int selectedCarNameIndex = getCorrespondingCarNameIndexFromClickedImage(imageView2);
                String selectedCarName = carNamesList.get(selectedCarNameIndex);
                Log.i("INFO", "MATCHED!!! RETURNED CAR NAME IS: " + selectedCarName);

                if (selectedCarName != null) {
                    if (selectedCarName == carNameToGuess) {
                        showCorrectMessage();
                    } else {
                        showErrorText();
                    }
                }
                imageView2.setEnabled(false);
                identifyButton.setText("NEXT");
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("INFO", "IMAGEVIEW 3 CLICKED");

                int selectedCarNameIndex = getCorrespondingCarNameIndexFromClickedImage(imageView3);
                String selectedCarName = carNamesList.get(selectedCarNameIndex);
                Log.i("INFO", "MATCHED!!! RETURNED CAR NAME IS: " + selectedCarName);

                if (selectedCarName != null) {
                    if (selectedCarName == carNameToGuess) {
                        showCorrectMessage();
                    } else {
                        showErrorText();
                    }
                }
                imageView3.setEnabled(false);
                identifyButton.setText("NEXT");
            }
        });


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