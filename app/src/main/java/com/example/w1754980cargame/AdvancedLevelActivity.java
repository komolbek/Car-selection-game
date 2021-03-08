package com.example.w1754980cargame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.w1754980cargame.BusinessLogic.CarsManager;

import java.util.ArrayList;
import java.util.Random;

public class AdvancedLevelActivity extends AppCompatActivity {

    private Button backButton;

    private Button identifyButton;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private TextView resultTextView;

    private EditText firstCarNameEditText;
    private EditText secondCarNameEditText;
    private EditText thirdCarNameEditText;

    private int attemptsCount = 3;

    CarsManager carsManager;
    ArrayList<String> carNamesList = new ArrayList<String>(3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_level);

        carsManager = new CarsManager(AdvancedLevelActivity.this);
        carsManager.createCars();

        setupButtons();
        setupTextViews();
        setupImageView();
        setupNextCarImage();
    }

    // MAIN LOGIC METHODS

    private void setupNextCarImage() {
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
    }

    private boolean checkIfInputsAreCorrect() {
        Log.i("INFO", "CHECK IF INPUTS ARE CORRECT");

        Log.i("INFO", "FISRT CAR NAME: " + carNamesList.get(0) + " AND INPUT TEXT: " + firstCarNameEditText.getText());
        Log.i("INFO", "SECOND CAR NAME: " + carNamesList.get(1) + " AND INPUT TEXT: " + secondCarNameEditText.getText());
        Log.i("INFO", "THIRD CAR NAME: " + carNamesList.get(2) + " AND INPUT TEXT: " + thirdCarNameEditText.getText());

        if ((carNamesList.contains(firstCarNameEditText.getText().toString())
                && carNamesList.contains(secondCarNameEditText.getText().toString())
                && carNamesList.contains(thirdCarNameEditText.getText().toString())))
        {
            Log.i("INFO", "ALL INPUTS PASSED");

            return true;
        } else {
            Log.i("INFO", "INPUTS ARE NOT PASSED");

            return false;
        }
    }

    private boolean checkIfAllTextFieldHaveText() {
        Log.i("INFO", "CHECK IF ALL TEXT FIELDS HAVE TEXT");

        Log.i("INFO", "FISRT CAR NAME: " + carNamesList.get(0) + " AND INPUT TEXT: " + firstCarNameEditText.getText());
        Log.i("INFO", "SECOND CAR NAME: " + carNamesList.get(1) + " AND INPUT TEXT: " + secondCarNameEditText.getText());
        Log.i("INFO", "THIRD CAR NAME: " + carNamesList.get(2) + " AND INPUT TEXT: " + thirdCarNameEditText.getText());

        if (firstCarNameEditText.length() > 0 && secondCarNameEditText.length() > 0 && thirdCarNameEditText.length() > 0) {
            firstCarNameEditText.setEnabled(false);
            secondCarNameEditText.setEnabled(false);
            thirdCarNameEditText.setEnabled(false);

            return true;
        } else {
            if (firstCarNameEditText.length() == 0) {
                firstCarNameEditText.setHighlightColor(Color.RED);
            } else {
                firstCarNameEditText.setEnabled(false);
            }

            if (secondCarNameEditText.length() == 0){
                secondCarNameEditText.setHighlightColor(Color.RED);
            } else {
                secondCarNameEditText.setEnabled(false);
            }

            if (thirdCarNameEditText.length() == 0) {
                thirdCarNameEditText.setHighlightColor(Color.RED);
            } else {
                thirdCarNameEditText.setEnabled(false);
            }

            return false;
        }
    }

    private void prepareViewToShowNextImages() {
        identifyButton.setText("SUBMIT");
        hideCorrectMessage();
        hideErrorText();
        this.carNamesList.clear();
        Log.i("INFO", "CARNAMES AFTER CLEAN: " + carNamesList.size());
        refreshTextFieldsHintText();
        setupNextCarImage();
    }

    private void refreshTextFieldsHintText() {
        firstCarNameEditText.setHint("Type first car name");
        secondCarNameEditText.setHint("Type second car name");
        thirdCarNameEditText.setHint("Type third car name");
    }

    private void showErrorText(String text) {
        resultTextView.setText(text);
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
                        if (attemptsCount > 0) {
                            attemptsCount -= 1;
                            showErrorText("WRONG! NEED TO FIX! ONLY " + attemptsCount + " ATTEMPTS LEFT");
                        } else {
                            // go next with wrong
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