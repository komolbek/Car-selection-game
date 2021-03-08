package com.example.w1754980cargame;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.w1754980cargame.BusinessLogic.CarsManager;

public class BaseActivity extends Activity { //// OOP. Parent abstract class for other activities

    protected Button backButton;
    protected TextView resultTextView;
    protected Button identifyButton;
    protected CarsManager carsManager;
    protected int carsLeft;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected void showErrorText(String text) {
        resultTextView.setText(text);
        resultTextView.setTextColor(Color.RED);
        resultTextView.setVisibility(View.VISIBLE);
    }

    protected void hideErrorText() {
        resultTextView.setVisibility(View.INVISIBLE);
    }

    protected void showFinishedText(String text) {
        resultTextView.setText(text);
        resultTextView.setTextColor(Color.BLUE);
        resultTextView.setVisibility(View.VISIBLE);
    }

    protected void showCorrectMessage() {
        resultTextView.setText("CORRECT! CLICK 'NEXT' FOR NEXT CHALLENGE");
        resultTextView.setTextColor(Color.GREEN);
        resultTextView.setVisibility(View.VISIBLE);
    }

    protected void hideCorrectMessage() {
        resultTextView.setVisibility(View.INVISIBLE);
    }
}
