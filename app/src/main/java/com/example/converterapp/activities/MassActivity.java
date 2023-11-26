package com.example.converterapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.converterapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MassActivity extends AppCompatActivity {
    List<String> massUnitList;
    Map<String, Double> massConversionFactors;
    private EditText ed_value, ed_result;
    private TextView tvUnit1, tvUnit2;
    int unitIndex = 0;
    int unitIndex2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mass);

        ed_value = findViewById(R.id.ed_value);
        ed_result = findViewById(R.id.ed_result);
        tvUnit1 = findViewById(R.id.tvUnit1);
        tvUnit2 = findViewById(R.id.tvUnit2);

        findViewById(R.id.mass_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        massUnitList = new ArrayList<String>();
        massUnitList.add("KG");
        massUnitList.add("G");
        massUnitList.add("MG");
        massUnitList.add("LB");
        massUnitList.add("OZ");

        massConversionFactors = new HashMap<>();
        {
            // Define conversion factors for each unit
            massConversionFactors.put("KG", 1.0);
            massConversionFactors.put("G", 1000.0);
            massConversionFactors.put("MG", 1_000_000.0);
            massConversionFactors.put("LB", 2.20462);
            massConversionFactors.put("OZ", 35.274);
        }

        ed_value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) return;
                String sourceUnit = tvUnit1.getText().toString();
                String targetUnit = tvUnit2.getText().toString();
                double d = Double.parseDouble(s.toString());
                massConvert(d, sourceUnit, targetUnit);
            }
        });

        clicks();
    }

    private void clicks() {


        findViewById(R.id.up1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (unitIndex > 0) {
                    unitIndex--;
                    tvUnit1.setText(massUnitList.get(unitIndex));

                    String sourceUnit = tvUnit1.getText().toString();
                    String targetUnit = tvUnit2.getText().toString();
                    if (ed_value.getText().toString().isEmpty()) return;
                    double d = Double.parseDouble(ed_value.getText().toString());
                    massConvert(d, sourceUnit, targetUnit);
                }
            }
        });

        findViewById(R.id.down1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (unitIndex < massUnitList.size() - 1) {
                    unitIndex++;
                    tvUnit1.setText(massUnitList.get(unitIndex));

                    String sourceUnit = tvUnit1.getText().toString();
                    String targetUnit = tvUnit2.getText().toString();
                    if (ed_value.getText().toString().isEmpty()) return;
                    double d = Double.parseDouble(ed_value.getText().toString());
                    massConvert(d, sourceUnit, targetUnit);
                }
            }
        });

        findViewById(R.id.up2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (unitIndex2 > 0) {
                    unitIndex2--;
                    tvUnit2.setText(massUnitList.get(unitIndex2));

                    String sourceUnit = tvUnit1.getText().toString();
                    String targetUnit = tvUnit2.getText().toString();
                    if (ed_value.getText().toString().isEmpty()) return;
                    double d = Double.parseDouble(ed_value.getText().toString());
                    massConvert(d, sourceUnit, targetUnit);
                }
            }
        });

        findViewById(R.id.down2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (unitIndex2 < massUnitList.size() - 1) {
                    unitIndex2++;
                    tvUnit2.setText(massUnitList.get(unitIndex2));

                    String sourceUnit = tvUnit1.getText().toString();
                    String targetUnit = tvUnit2.getText().toString();
                    if (ed_value.getText().toString().isEmpty()) return;
                    double d = Double.parseDouble(ed_value.getText().toString());
                    massConvert(d, sourceUnit, targetUnit);
                }
            }
        });

        findViewById(R.id.reverseUnit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tv1 = tvUnit1.getText().toString();
                String tv2 = tvUnit2.getText().toString();
                tvUnit1.setText(tv2);
                tvUnit2.setText(tv1);

                String sourceUnit = tvUnit2.getText().toString();
                String targetUnit = tvUnit1.getText().toString();
                if (ed_value.getText().toString().isEmpty()) return;
                double d = Double.parseDouble(ed_value.getText().toString());
                massConvert(d, sourceUnit, targetUnit);
            }
        });

    }

    public double massConvert(double value, String sourceUnit, String targetUnit) {
        // Check if units are valid
        if (!massConversionFactors.containsKey(sourceUnit) || !massConversionFactors.containsKey(targetUnit)) {
            throw new IllegalArgumentException("Invalid units");
        }

        // Convert value to base unit (Kilograms)
        double valueInKilograms = value / massConversionFactors.get(sourceUnit);

        // Convert from base unit to target unit
        double convertedValue = valueInKilograms * massConversionFactors.get(targetUnit);

        ed_result.setText(String.valueOf(convertedValue));
        return convertedValue;
    }
}