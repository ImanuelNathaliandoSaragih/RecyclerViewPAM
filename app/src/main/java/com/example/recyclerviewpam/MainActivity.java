package com.example.recyclerviewpam;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataAdapter dataAdapter;
    private List<DataModel> dataList;
    private Button addButton, clearButton;
    private DatabaseHelper databaseHelper;
    private EditText inputField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Initialize RecyclerView and list
        recyclerView = findViewById(R.id.recyclerView);
        inputField = findViewById(R.id.inputField);
        addButton = findViewById(R.id.addButton);
        clearButton = findViewById(R.id.clearButton);

        // Retrieve data from SQLite database
        dataList = databaseHelper.getAllData();

        // Set up RecyclerView with the adapter
        dataAdapter = new DataAdapter(dataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(dataAdapter);

        // Handle the Add Button click to insert data into the database and update RecyclerView
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem = inputField.getText().toString();

                if (!newItem.isEmpty()) {
                    // Add data to SQLite database
                    boolean isAdded = databaseHelper.addData(newItem);
                    if (isAdded) {
                        // Update list and RecyclerView
                        dataList.add(new DataModel(newItem));
                        dataAdapter.notifyItemInserted(dataList.size() - 1);
                        inputField.setText("");  // Clear the input field
                    } else {
                        Toast.makeText(MainActivity.this, "Error adding data", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter data", Toast.LENGTH_SHORT).show();
                }
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteAllData();  // Clear the database
                dataList.clear();  // Clear the list in the RecyclerView
                dataAdapter.notifyDataSetChanged();  // Notify the adapter about data changes
                Toast.makeText(MainActivity.this, "All data cleared", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
