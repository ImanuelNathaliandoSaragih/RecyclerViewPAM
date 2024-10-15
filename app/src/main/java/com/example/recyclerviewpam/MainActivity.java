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

        databaseHelper = new DatabaseHelper(this);

        recyclerView = findViewById(R.id.recyclerView);
        inputField = findViewById(R.id.inputField);
        addButton = findViewById(R.id.addButton);
        clearButton = findViewById(R.id.clearButton);

        dataList = databaseHelper.getAllData();

        dataAdapter = new DataAdapter(dataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(dataAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem = inputField.getText().toString();

                if (!newItem.isEmpty()) {
                    boolean isAdded = databaseHelper.addData(newItem);
                    if (isAdded) {
                        dataList.add(new DataModel(newItem));
                        dataAdapter.notifyItemInserted(dataList.size() - 1);
                        inputField.setText("");
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
                databaseHelper.deleteAllData();
                dataList.clear();
                dataAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "All data cleared", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
