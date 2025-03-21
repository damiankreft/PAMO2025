package com.example.BMI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable; // for EditText event handling
import android.text.TextWatcher; // EditText listener
import android.widget.EditText; // for bill amount input
import android.widget.TextView; // for displaying text

import java.text.NumberFormat; // for currency formatting

public class MainActivity extends AppCompatActivity {

    private static final NumberFormat numberFormat =
            NumberFormat.getNumberInstance();

    private EditText weightEditText;
    private EditText heightEditText;
    private TextView bmiTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bmiTextView = (TextView) findViewById(R.id.bmiTextView);
        bmiTextView = (TextView) findViewById(R.id.bmiTextView);

        heightEditText =
                (EditText) findViewById(R.id.heightEditText);
        heightEditText.addTextChangedListener(valueChangedTextWatcher);

        weightEditText =
                (EditText) findViewById(R.id.weightEditText);
        weightEditText.addTextChangedListener(valueChangedTextWatcher);
    }

    private void calculate() {
        String heightString = heightEditText.getText().toString();
        String weightString = weightEditText.getText().toString();
        if (!heightString.isEmpty() && !weightString.isEmpty())
        {
            double height = Double.parseDouble(heightString);
            double weight = Double.parseDouble(weightString);
            double bmi = weight / Math.pow(height, 2);
            bmiTextView.setText(String.format(getResources().getString(R.string.bmi_output), numberFormat.format(bmi), getBmiStatus(bmi)));
        }
    }

    private final TextWatcher valueChangedTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {
            calculate();
        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) { }
    };

    private String getBmiStatus(double bmi) {
        String status = "";

        if(bmi < 18.5) {
            status = "underweight";
        } else if (bmi >= 18.5 & bmi < 24.99) {
            status = "healthy";
        } else if (bmi >= 25 & bmi < 29.99) {
            status = "overweight";
        } else if (bmi >= 30) {
            status = "obesity";
        }

        return status;
    }
}