package com.example.BMI.ui.notifications;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.example.BMI.R;
import com.example.BMI.databinding.FragmentNotificationsBinding;
import com.example.BMI.services.CalculationsService;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    private static final NumberFormat numberFormat =
            NumberFormat.getNumberInstance();

    private EditText weightEditText;
    private EditText heightEditText;

    private EditText AgeEditText;
    private TextView bmiTextView;

    private Switch genderSwitch;
    Spinner mySpinner;

    String selected = "";
    HashMap<String, Double> activityLevelMultipliers = new HashMap<>();

    public static double calories = 0;

    private Boolean isWoman = true;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        activityLevelMultipliers.putAll(Map.of("1) Little or no exercise", 1.2, "2) Light exercise/sports 1–3 days/week", 1.375, "3) Moderate exercise/sports 3–5 days/week", 1.55, "4) Hard exercise/sports 6–7 days/week", 1.725, "5) Very hard exercise/physical job, or 2× training/day", 1.9));
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bmiTextView = (TextView) getView().findViewById(R.id.bmiTextView);
        bmiTextView = (TextView) getView().findViewById(R.id.bmiTextView);

        heightEditText =
                (EditText) getView().findViewById(R.id.heightEditText);
        heightEditText.addTextChangedListener(valueChangedTextWatcher);

        weightEditText =
                (EditText) getView().findViewById(R.id.weightEditText);
        weightEditText.addTextChangedListener(valueChangedTextWatcher);

        AgeEditText = (EditText) getView().findViewById(R.id.AgeEditText);
        AgeEditText.addTextChangedListener(valueChangedTextWatcher);

        genderSwitch = (Switch) getView().findViewById(R.id.switchGender);
        genderSwitch.setOnCheckedChangeListener((new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isWoman = isChecked;
                calculate();
            }
        }));

        mySpinner = getView().findViewById(R.id.my_spinner);


        String[] items = Arrays.stream(activityLevelMultipliers.keySet().toArray(new String[0])).sorted().toArray(String[]::new);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, items
        );
        mySpinner.setSelection(0);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mySpinner.setAdapter(adapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected = parent.getItemAtPosition(position).toString();
                calculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void calculate() {
        String heightString = heightEditText.getText().toString();
        String weightString = weightEditText.getText().toString();
        String ageString = AgeEditText.getText().toString();
        if (!heightString.isEmpty() && !weightString.isEmpty() && !ageString.isEmpty())
        {
            double height = Double.parseDouble(heightString);
            double weight = Double.parseDouble(weightString);
            int age = Integer.parseInt(ageString);
            double bmi = CalculationsService.getBenedictHarrisBasalMetabolicRate(isWoman, weight, height, age);
            var multiplier = activityLevelMultipliers.get(selected) != null ? activityLevelMultipliers.get(selected) : 1;
            calories = bmi * multiplier;
            bmiTextView.setText(numberFormat.format(calories));
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}