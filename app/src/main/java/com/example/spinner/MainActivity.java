package com.example.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner countriesSpinner;
    private Spinner citiesSpinner;
    private Spinner houseNumberSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        countriesSpinner = findViewById(R.id.countriesSpinner);
        citiesSpinner = findViewById(R.id.citiesSpinner);
        houseNumberSpinner = findViewById(R.id.houseNumberSpinner);
        initHousNumbersSpinner();
        initcountriesSpinner();
    }

    private void initHousNumbersSpinner() {
        Integer[] houseNumbers = new Integer[50];
        for (int i = 1; i <= 50; i++) {
            houseNumbers[i - 1] = i;
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, houseNumbers);
        houseNumberSpinner.setAdapter(adapter);
    }

    private void initcountriesSpinner(){
        ArrayAdapter<CharSequence> adapterCountries = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_item);
        adapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countriesSpinner.setAdapter(adapterCountries);

        countriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] countries = getResources().getStringArray(R.array.countries);
                initSpinnerCities(countries[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initSpinnerCities(String country){
        ArrayAdapter<CharSequence> adapter = null;
        switch (country) {
            case "Россия":
                adapter = ArrayAdapter.createFromResource(this, R.array.r_cities, android.R.layout.simple_spinner_item);
                break;
            case "Украина":
                adapter = ArrayAdapter.createFromResource(this, R.array.u_cities, android.R.layout.simple_spinner_item);
                break;
            case "Белоруссия":
                adapter = ArrayAdapter.createFromResource(this, R.array.b_cities, android.R.layout.simple_spinner_item);
                break;
        }
        if (adapter != null) {
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            citiesSpinner.setAdapter(adapter);
        }
    }

    public void btn(View view){
        Toast.makeText(MainActivity.this
                ,countriesSpinner.getSelectedItem().toString()
                        + " "
                        + citiesSpinner.getSelectedItem().toString()
                        + " "
                        + houseNumberSpinner.getSelectedItem().toString()
                ,Toast.LENGTH_LONG)
                .show();
    }
}