package com.example.pizzaapp2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AddPizzaActivity extends AppCompatActivity {
    private Spinner pizzaSizesDropdown;
    private String[] sizes = new String[]{"Small", "Medium", "Large"};
    private ArrayAdapter pizzaSizesAdapter;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_pizza_activity);
        Intent intent = getIntent();
        pizzaSizesDropdown = findViewById(R.id.pizzaSizes);

        ArrayAdapter<String> pizzaSizesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sizes);
        pizzaSizesDropdown.setAdapter(pizzaSizesAdapter);
    }
}
