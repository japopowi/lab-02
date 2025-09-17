package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button addButton;
    Button delButton;
    Button confirmButton;
    EditText newCity;

    //no city has been selected so starts as -1
    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.listy_toolbar);
        setSupportActionBar(toolbar);

        cityList = findViewById(R.id.city_list);
        addButton = findViewById(R.id.add_button);
        delButton = findViewById(R.id.del_button);
        confirmButton = findViewById(R.id.confirm_button);
        newCity = findViewById(R.id.new_city);

        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter= new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        // clicking on city to be deleted
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
                cityList.setItemChecked(position, true);
            }
        });

        // adding a city
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newCity.setVisibility(View.VISIBLE);
                confirmButton.setVisibility(View.VISIBLE);
                newCity.requestFocus();
            }
        });

        // confirm when finished typing new city name
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enterCity = newCity.getText().toString().trim();
                if (!enterCity.isEmpty()) {
                    dataList.add(enterCity);
                    cityAdapter.notifyDataSetChanged();
                    newCity.setText("");
                    newCity.setVisibility(View.GONE);
                    confirmButton.setVisibility(View.GONE);
                }
            }
        });

        // delete city that was clicked on
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition != -1) {
                    dataList.remove(selectedPosition);
                    cityAdapter.notifyDataSetChanged();
                    cityList.clearChoices();
                    selectedPosition = -1;
                }
            }
        });
    }
}
