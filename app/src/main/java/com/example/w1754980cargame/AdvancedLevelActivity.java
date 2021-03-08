package com.example.w1754980cargame;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.w1754980cargame.BusinessLogic.CarsManager;

import java.util.ArrayList;

public class AdvancedLevelActivity extends BaseActivity { // OOP. Inheritance.

    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;

    private EditText firstCarNameEditText;
    private EditText secondCarNameEditText;
    private EditText thirdCarNameEditText;

    private int attemptsCount = 3;
    private TextView scoreTextView;
    private int score = 0; // FIXME: need to fix

    ArrayList<String> carNamesList = new ArrayList<String>(3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_level);

        carsManager = new CarsManager(AdvancedLevelActivity.this);
        carsManager.createCars();
        carsLeft = carsManager.getCars().size();

        setupButtons();
        setupTextViews();
        setupImageView();
        setupNextCarImage();
    }

    // MAIN LOGIC METHODS

    private void setupNextCarImage() {
        if (carsLeft != 0) {
            carsLeft -= 3;

            String carName1 = carsManager.getRandomCar().getName();
            String carName2 = carsManager.getRandomCar().getName();
            String carName3 = carsManager.getRandomCar().getName();
            carNamesList.add(carName1);
            carNamesList.add(carName2);
            carNamesList.add(carName3);

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
        } else {
            showFinishedText("GAME IS FINISHED");
            return;
        }
    }

    private boolean checkIfInputsAreCorrect() {
        Log.i("INFO", "CHECK IF INPUTS ARE CORRECT");

        Log.i("INFO", "FISRT CAR NAME: " + carNamesList.get(0) + " AND INPUT TEXT: " + firstCarNameEditText.getText());
        Log.i("INFO", "SECOND CAR NAME: " + carNamesList.get(1) + " AND INPUT TEXT: " + secondCarNameEditText.getText());
        Log.i("INFO", "THIRD CAR NAME: " + carNamesList.get(2) + " AND INPUT TEXT: " + thirdCarNameEditText.getText());

//        st.replaceAll("\\s+","") // TODO: to avoid all 'extra space' from user input. stackoverflow.com

        if ((carNamesList.contains(firstCarNameEditText.getText().toString().toLowerCase())
                && carNamesList.contains(secondCarNameEditText.getText().toString().toLowerCase())
                && carNamesList.contains(thirdCarNameEditText.getText().toString().toLowerCase())))
        {
            Log.i("INFO", "ALL INPUTS PASSED");

            firstCarNameEditText.setEnabled(false);
            secondCarNameEditText.setEnabled(false);
            thirdCarNameEditText.setEnabled(false);

            return true;
        } else {
            Log.i("INFO", "INPUTS ARE NOT PASSED");

            if (!carNamesList.contains(firstCarNameEditText.getText().toString().toLowerCase())) {
                firstCarNameEditText.setHighlightColor(Color.RED);
                score += 3;
                scoreTextView.setText("Score: " + score);
            } else {
                if (firstCarNameEditText.isEnabled()) {
                    score += 1;
                    scoreTextView.setText("Score: " + score);
                }
                firstCarNameEditText.setEnabled(false);
            }

            if (!carNamesList.contains(secondCarNameEditText.getText().toString().toLowerCase())) {
                secondCarNameEditText.setHighlightColor(Color.RED);
            } else {
                if (secondCarNameEditText.isEnabled()) {
                    score += 1;
                    scoreTextView.setText("Score: " + score);
                }
                secondCarNameEditText.setEnabled(false);
            }

            if (!carNamesList.contains(thirdCarNameEditText.getText().toString().toLowerCase())) {
                thirdCarNameEditText.setHighlightColor(Color.RED);
            } else {
                if (thirdCarNameEditText.isEnabled()) {
                    score += 1;
                    scoreTextView.setText("Score: " + score);
                }
                thirdCarNameEditText.setEnabled(false);
            }

            return false;
        }
    }

    private boolean checkIfAllTextFieldHaveText() {
        Log.i("INFO", "CHECK IF ALL TEXT FIELDS HAVE TEXT");

        Log.i("INFO", "FISRT CAR NAME: " + carNamesList.get(0) + " AND INPUT TEXT: " + firstCarNameEditText.getText());
        Log.i("INFO", "SECOND CAR NAME: " + carNamesList.get(1) + " AND INPUT TEXT: " + secondCarNameEditText.getText());
        Log.i("INFO", "THIRD CAR NAME: " + carNamesList.get(2) + " AND INPUT TEXT: " + thirdCarNameEditText.getText());

        if (firstCarNameEditText.length() > 0 && secondCarNameEditText.length() > 0 && thirdCarNameEditText.length() > 0) {

            return true;
        } else {

            return false;
        }
    }

    private void prepareViewToShowNextImages() {
        identifyButton.setText("SUBMIT");
        hideCorrectMessage();
        hideErrorText();
        this.carNamesList.clear();
        attemptsCount = 3;
        Log.i("INFO", "CARNAMES AFTER CLEAN: " + carNamesList.size());
        refreshTextFieldsTexts();
        setupNextCarImage();
    }

    private void refreshTextFieldsTexts() {
        firstCarNameEditText.setEnabled(true);
        secondCarNameEditText.setEnabled(true);
        thirdCarNameEditText.setEnabled(true);
        firstCarNameEditText.setHint("Type first car name");
        secondCarNameEditText.setHint("Type second car name");
        thirdCarNameEditText.setHint("Type third car name");
        firstCarNameEditText.setText("");
        secondCarNameEditText.setText("");
        thirdCarNameEditText.setText("");
    }

// UI Setup mathods

    private void setupButtons() {
        identifyButton = findViewById(R.id.advancedLvlIdentifyButton);
        identifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("INFO", "BUTTON CLICKED");

                if (checkIfAllTextFieldHaveText()) {
                    if (checkIfInputsAreCorrect()) {
                        if (identifyButton.getText() == "NEXT") {
                            prepareViewToShowNextImages();
                        } else {
                            identifyButton.setText("NEXT");
                            showCorrectMessage();
                        }
                    } else {
                        if (attemptsCount > 0 && attemptsCount != 0) {
                            attemptsCount -= 1;
                            showErrorText("WRONG! NEED TO FIX! ONLY " + attemptsCount + " ATTEMPTS LEFT");
                        } else {
                            if (identifyButton.getText() == "NEXT") {
                                prepareViewToShowNextImages();
                            } else {
                                identifyButton.setText("NEXT");
                                firstCarNameEditText.setEnabled(false);
                                secondCarNameEditText.setEnabled(false);
                                thirdCarNameEditText.setEnabled(false);
                                showErrorText("WRONG! NO ATTEMPTS LEFT. PRESS NEXT");
                            }
                        }
                    }
                } else {
                    showErrorText("PLEASE FILL ALL TEXT FIELDs BEFORE SUBMIT");
                }
            }
        });

        backButton = findViewById(R.id.advancedLevelBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupTextViews() {
        scoreTextView = findViewById(R.id.advancedLvlPointsTextView);
        scoreTextView.setText("Score: " + score);
        resultTextView = findViewById(R.id.advancedLvlResultTextView);
        resultTextView.setVisibility(View.INVISIBLE);
        firstCarNameEditText = findViewById(R.id.advancedLvlEditText1);
        secondCarNameEditText = findViewById(R.id.advancedLvlEditText2);
        thirdCarNameEditText = findViewById(R.id.advancedLvlEditText3);
    }

    private void setupImageView() {
        imageView1 = (ImageView) findViewById(R.id.advancedLvlCarImageView1);
        imageView2 = (ImageView) findViewById(R.id.advancedLvlImageView2);
        imageView3 = (ImageView) findViewById(R.id.advancedLvlImageView3);
    }
}