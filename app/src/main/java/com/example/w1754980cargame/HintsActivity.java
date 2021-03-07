package com.example.w1754980cargame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.w1754980cargame.BusinessLogic.CarsManager;

public class HintsActivity extends AppCompatActivity {

    private Button backButton;

    private Button identifyButton;
    private ImageView imageView;
    private TextView resultTextView;
    private TextView resultCarNameTextView;

    CarsManager carsManager;

    private String carNameInImage;
    private String carNameSelectedInSpinner;
    private Boolean isCarImageSet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hints);

        setupButtons();
        setupButtonListeners();
    }

    // Private methods

    private void setupButtons() {
        backButton = findViewById(R.id.hintsBackButton);
    }

    private void setupButtonListeners() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}