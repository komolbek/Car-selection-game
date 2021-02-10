package com.example.w1754980cargame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button carMakeIndentifyButton;
    private Button hintsButton;
    private Button carMakeImageButton;
    private Button advancedLevelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupButtons();
        setupButtonListeners();
    }

//    Private methods

    private void setupButtons() {
        carMakeIndentifyButton = (Button) findViewById(R.id.carMakeIdentifyButton);
        hintsButton = (Button) findViewById(R.id.hintsButton);
        carMakeImageButton = (Button) findViewById(R.id.carMakeImageButton);
        advancedLevelButton = (Button) findViewById(R.id.advancedLevelButton);
    }

    private void setupButtonListeners() {
        carMakeIndentifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCarMakeIdentifyActivity();
            }
        });

        hintsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHintsActivity();
            }
        });

        carMakeImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCarMakeImageActivity();
            }
        });

        advancedLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdvancedLevelActivity();
            }
        });
    }

    private void openCarMakeIdentifyActivity() {
        Intent intent = new Intent(this, CarMakeIdentifyActivity.class);
        startActivity(intent);
    }

    private void openHintsActivity() {
        Intent intent = new Intent(this, HintsActivity.class);
        startActivity(intent);
    }

    private void openCarMakeImageActivity() {
        Intent intent = new Intent(this, CarImageIdentifyActivity.class);
        startActivity(intent);
    }

    private void openAdvancedLevelActivity() {
        Intent intent = new Intent(this, AdvancedLevelActivity.class);
        startActivity(intent);
    }
}