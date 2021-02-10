package com.example.w1754980cargame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HintsActivity extends AppCompatActivity {

    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_make_identify);

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