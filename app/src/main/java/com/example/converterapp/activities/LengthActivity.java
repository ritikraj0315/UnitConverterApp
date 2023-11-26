package com.example.converterapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.converterapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LengthActivity extends AppCompatActivity {
    List<String> unitList;
    Map<String, Double> conversionFactors;
    private EditText ed_value, ed_result;
    private TextView tvUnit1, tvUnit2;
    int unitIndex = 0;
    int unitIndex2 = 0; //hh

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);

        ed_value = findViewById(R.id.ed_value);
        ed_result = findViewById(R.id.ed_result);
        tvUnit1 = findViewById(R.id.tvUnit1);
        tvUnit2 = findViewById(R.id.tvUnit2);

        findViewById(R.id.length_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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
                convert(d, sourceUnit, targetUnit);
            }
        });

        unitList = new ArrayList<String>();
        unitList.add("M");
        unitList.add("KM");
        unitList.add("CM");
        unitList.add("MM");
        unitList.add("IN");
        unitList.add("FT");
        unitList.add("YD");
        unitList.add("MI");

        conversionFactors = new HashMap<>();
        {
            // Define conversion factors for each unit
            conversionFactors.put("M", 1.0);
            conversionFactors.put("KM", 0.001);
            conversionFactors.put("CM", 100.0);
            conversionFactors.put("MM", 1000.0);
            conversionFactors.put("IN", 39.3701);
            conversionFactors.put("FT", 3.28084);
            conversionFactors.put("YD", 1.09361);
            conversionFactors.put("MI", 0.000621371);
        }

        clicks();
    }

    private void clicks() {


        findViewById(R.id.up1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (unitIndex > 0) {
                    unitIndex--;
                    tvUnit1.setText(unitList.get(unitIndex));

                    String sourceUnit = tvUnit1.getText().toString();
                    String targetUnit = tvUnit2.getText().toString();
                    if (ed_value.getText().toString().isEmpty()) return;
                    double d = Double.parseDouble(ed_value.getText().toString());
                    convert(d, sourceUnit, targetUnit);
                }
            }
        });

        findViewById(R.id.down1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (unitIndex < unitList.size() - 1) {
                    unitIndex++;
                    tvUnit1.setText(unitList.get(unitIndex));

                    String sourceUnit = tvUnit1.getText().toString();
                    String targetUnit = tvUnit2.getText().toString();
                    if (ed_value.getText().toString().isEmpty()) return;
                    double d = Double.parseDouble(ed_value.getText().toString());
                    convert(d, sourceUnit, targetUnit);
                }
            }
        });

        findViewById(R.id.up2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (unitIndex2 > 0) {
                    unitIndex2--;
                    tvUnit2.setText(unitList.get(unitIndex2));

                    String sourceUnit = tvUnit1.getText().toString();
                    String targetUnit = tvUnit2.getText().toString();
                    if (ed_value.getText().toString().isEmpty()) return;
                    double d = Double.parseDouble(ed_value.getText().toString());
                    convert(d, sourceUnit, targetUnit);
                }
            }
        });

        findViewById(R.id.down2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (unitIndex2 < unitList.size() - 1) {
                    unitIndex2++;
                    tvUnit2.setText(unitList.get(unitIndex2));

                    String sourceUnit = tvUnit1.getText().toString();
                    String targetUnit = tvUnit2.getText().toString();
                    if (ed_value.getText().toString().isEmpty()) return;
                    double d = Double.parseDouble(ed_value.getText().toString());
                    convert(d, sourceUnit, targetUnit);
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
                convert(d, sourceUnit, targetUnit);
            }
        });

    }

    public double convert(double value, String sourceUnit, String targetUnit) {
        // Check if units are valid
        if (!conversionFactors.containsKey(sourceUnit) || !conversionFactors.containsKey(targetUnit)) {
            throw new IllegalArgumentException("Invalid units");
        }

        // Convert value to base unit (Meters)
        double valueInMeters = value / conversionFactors.get(sourceUnit);

        // Convert from base unit to target unit
        double convertedValue = valueInMeters * conversionFactors.get(targetUnit);

        ed_result.setText(String.valueOf(convertedValue));
        return convertedValue;
    }

}