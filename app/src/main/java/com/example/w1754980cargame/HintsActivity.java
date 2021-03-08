package com.example.w1754980cargame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.w1754980cargame.BusinessLogic.CarsManager;

public class HintsActivity extends AppCompatActivity {

    private Button backButton;

    private Button identifyButton;
    private ImageView imageView;
    private TextView resultTextView;
    private TextView resultCarNameTextView;
    private EditText hintsCarLetterEditText;
    private TextView hintsFinalTextView;

    CarsManager carsManager;
    String shownCarName;
    StringBuilder hiddenText = new StringBuilder("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hints);

        carsManager = new CarsManager(HintsActivity.this);
        carsManager.createCars();

        setupButtons();
        setupImageView();
        setupTextViews();
        setupCarImage();
    }

    // MAIN LOGIC METHODS

    private void setupCarImage() {
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
        shownCarName = carName;

        for (int i = 0; i < carName.length(); i++) {
            hiddenText.append("-");
        }
        Log.e("ERROR", hiddenText.toString());
        hintsFinalTextView.setText(hiddenText.toString());
        hintsFinalTextView.setTextColor(Color.BLACK);
    }

    private void checkIfCarNameContainsInputChar() {
        Character inputChar =  hintsCarLetterEditText.getText().toString().toLowerCase().charAt(0);

        if (shownCarName.contains(inputChar.toString())) {
            for (int i = 0; i < shownCarName.length(); i++) {
                Log.i("INFO", "CAR NAME: " + shownCarName + " <---> INPUT: " + inputChar);

                if (shownCarName.charAt(i) == inputChar) {
                    Log.i("INFO", "MATCHED!!! INPUT: " + inputChar + " <---> with CARNAME: " + shownCarName);

                    hiddenText.setCharAt(i, inputChar);
                    hintsFinalTextView.setText(hiddenText);
                    hintsCarLetterEditText.setText("");
                }
            }
        } else {
            Log.e("ERROR", "CAR NAME DOES NOT CONTAIN THIS INPUT LETTER");

            showErrorText("TRY ANOTHER LETTER!");
            hintsCarLetterEditText.setText("");
        }
    }

    private boolean checkIfFinishedGuessing() {
        if (hiddenText.toString().contains("-")) {
            return false;
        } else {
            return true;
        }
    }

    private boolean checkIfInputContainsChar() {
        if (hintsCarLetterEditText.getText().length() > 0) {
            return true;
        } else {
            return  false;
        }
    }

    private void prepareViewToShowNextImage() {
        hintsCarLetterEditText.setEnabled(true);
        hiddenText.setLength(0);
        hideCorrectMessage();
        setupCarImage();
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
        resultCarNameTextView.setText(shownCarName);
        resultTextView.setVisibility(View.VISIBLE);
        resultCarNameTextView.setVisibility(View.VISIBLE);
    }

    private void hideCorrectMessage() {
        resultTextView.setVisibility(View.INVISIBLE);
        resultCarNameTextView.setVisibility(View.INVISIBLE);
    }

    // UI Setup mathods

    private void setupTextViews() {
        resultTextView = findViewById(R.id.hintsResultTextView);
        resultTextView.setVisibility(View.INVISIBLE);
        resultCarNameTextView = findViewById(R.id.hintsResultCarTextView);
        resultCarNameTextView.setVisibility(View.INVISIBLE);
        hintsCarLetterEditText = findViewById(R.id.carMakeLetterEditText);
        hintsCarLetterEditText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(1)});
        hintsFinalTextView = findViewById(R.id.hintsTextView);
    }

    private void setupImageView() {
        imageView = (ImageView) findViewById(R.id.hintsCarImageView);
    }

    private void setupButtons() {
        backButton = findViewById(R.id.hintsBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        identifyButton = findViewById(R.id.hintsIdentifyButton);
        identifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("INFO", "BUTTON CLICKED");

                hideErrorText();

                if (!checkIfFinishedGuessing()) {
                    if (checkIfInputContainsChar()) {

                        checkIfCarNameContainsInputChar();

                        if (checkIfFinishedGuessing()) {
                            if (identifyButton.getText().equals("NEXT")) {
                                prepareViewToShowNextImage();
                            } else {
                                showCorrectMessage();
                                identifyButton.setText("NEXT");
                                hintsCarLetterEditText.setEnabled(false);
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
    }
}