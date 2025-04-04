package com.example.BMI.ui.recommendations;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import java.text.NumberFormat;
import com.example.BMI.R;

public class RecommendationsFragment extends Fragment {

    public static RecommendationsFragment newInstance() {
        return new RecommendationsFragment();
    }

    private static final NumberFormat numberFormat =
            NumberFormat.getNumberInstance();

    private EditText weightEditText;
    private EditText heightEditText;
    private TextView bmiTextView;
    private RecommendationsViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        bmiTextView = (TextView) getView().findViewById(R.id.bmiTextView);

        return inflater.inflate(R.layout.fragment_recommendations, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RecommendationsViewModel.class);
        bmiTextView = (TextView) getView().findViewById(R.id.bmiTextView);

        heightEditText =
                (EditText) getView().findViewById(R.id.heightEditText);
        heightEditText.addTextChangedListener(valueChangedTextWatcher);

        weightEditText =
                (EditText) getView().findViewById(R.id.weightEditText);
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