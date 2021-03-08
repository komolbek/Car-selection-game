package com.example.w1754980cargame;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.w1754980cargame.BusinessLogic.CarsManager;

public class HintsActivity extends BaseActivity { // OOP. Inheritance

    private ImageView imageView;
    private TextView resultCarNameTextView;
    private EditText carCharacterAddTextField;
    private TextView dashedTextView;
    private int attemptsCount = 3;

    String carNameStringOfDisplayedImage;
    StringBuilder dashedText = new StringBuilder("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hints);

        carsManager = new CarsManager(HintsActivity.this);
        carsManager.createCars();
        carsLeft = carsManager.getCars().size();

        setupButtons();
        setupImageView();
        setupTextViews();
        setupNextCarImage();
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

            int resID = getResources().getIdentifier(
                    carName,
                    "drawable",
                    getPackageName()
            );
            imageView.setImageResource(resID);
            carNameStringOfDisplayedImage = carName;

            for (int i = 0; i < carName.length(); i++) {
                dashedText.append("-");
            }
            Log.e("ERROR", dashedText.toString());
            dashedTextView.setText(dashedText.toString());
            dashedTextView.setTextColor(Color.BLACK);
        } else {
            showFinishedText("GAME IS FINISHED");
            return;
        }
    }

    private boolean openDashedCharacterIfMatchesWithUserInput() { // FIXME: maybe change method name
        // https://stackoverflow.com/questions/4531396/get-value-of-a-edit-text-field
        Character userInputCharacter =  carCharacterAddTextField.getText().toString().toLowerCase().charAt(0);

        if (carNameStringOfDisplayedImage.contains(userInputCharacter.toString())) {
            for (int i = 0; i < carNameStringOfDisplayedImage.length(); i++) {
                Log.i("INFO", "CAR NAME: " + carNameStringOfDisplayedImage + " <---> INPUT: " + userInputCharacter);

                if (carNameStringOfDisplayedImage.charAt(i) == userInputCharacter) {
                    Log.i("INFO", "MATCHED!!! INPUT: " + userInputCharacter + " <---> with CARNAME: " + carNameStringOfDisplayedImage);

                    //https://stackoverflow.com/questions/6952363/replace-a-character-at-a-specific-index-in-a-string
                    dashedText.setCharAt(i, userInputCharacter);
                    dashedTextView.setText(dashedText);
                    carCharacterAddTextField.setText("");
                }
            }

            return true;
        } else {
            return false;
        }
    }

    private boolean checkIfFinishedGuessingCarName() {
        if (dashedText.toString().contains("-")) {
            return false;
        } else {
            return true;
        }
    }

    private boolean checkIfInputContainsChar() {
        if (carCharacterAddTextField.getText().length() > 0) {
            return true;
        } else {
            return  false;
        }
    }

    private void prepareViewToShowNextImage() {
        carCharacterAddTextField.setEnabled(true);
        dashedText.setLength(0);
        attemptsCount = 3;
        identifyButton.setText("IDENTIFY");
        hideCorrectMessage();
        setupNextCarImage();
    }

    // UI Setup mathods

    private void setupButtons() {
        identifyButton = findViewById(R.id.hintsIdentifyButton);
        identifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("INFO", "BUTTON CLICKED");

                hideErrorText();

                if (!checkIfFinishedGuessingCarName()) {
                    if (checkIfInputContainsChar()) {

                        if (openDashedCharacterIfMatchesWithUserInput()) {

                        } else {
                            Log.e("ERROR", "CAR NAME DOES NOT CONTAIN THIS INPUT LETTER");

                            if (attemptsCount > 0 && attemptsCount != 0) {
                                attemptsCount -= 1;
                                showErrorText("TRY ANOTHER LETTER! ONLY " + attemptsCount + " ATTEMPTS LEFT");
                                carCharacterAddTextField.setText("");
                            } else {
                                if (identifyButton.getText().equals("NEXT")) {
                                    prepareViewToShowNextImage();
                                } else {
                                    carCharacterAddTextField.setEnabled(false);
                                    identifyButton.setText("NEXT");
                                    showErrorText("WRONG! NO ATTEMPTS LEFT. PRESS NEXT");
                                }
                            }
                        }

                        if (checkIfFinishedGuessingCarName()) {
                            if (identifyButton.getText().equals("NEXT")) {
                                prepareViewToShowNextImage();
                            } else {
                                showCorrectMessage();
                                identifyButton.setText("NEXT");
                                carCharacterAddTextField.setEnabled(false);
                            }
                        }
                    } else {
                        showErrorText("PLEASE ADD  INPUT LETTER THAT CAR MAKE CONTAINS");
                    }
                } else {
                    prepareViewToShowNextImage();
                }
            }
        });

        backButton = findViewById(R.id.hintsBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupTextViews() {
        resultTextView = findViewById(R.id.hintsResultTextView);
        resultTextView.setVisibility(View.INVISIBLE);
        resultCarNameTextView = findViewById(R.id.hintsResultCarTextView);
        resultCarNameTextView.setVisibility(View.INVISIBLE);
        carCharacterAddTextField = findViewById(R.id.carMakeLetterEditText);
        carCharacterAddTextField.setFilters(new InputFilter[] {new InputFilter.LengthFilter(1)});
        dashedTextView = findViewById(R.id.hintsTextView);
    }

    private void setupImageView() {
        imageView = (ImageView) findViewById(R.id.hintsCarImageView);
    }
}