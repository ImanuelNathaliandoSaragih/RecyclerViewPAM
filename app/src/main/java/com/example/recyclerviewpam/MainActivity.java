package com.example.recyclerviewpam;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataAdapter dataAdapter;
    private List<DataModel> dataList;
    private Button addButton;
    private int n = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView and list
        recyclerView = findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.addButton);
        dataList = new ArrayList<>();

        // Add initial static data
        dataList.add(new DataModel("Imanuel Nathaliando Saragih"));
        dataList.add(new DataModel("215150407111028"));
        dataList.add(new DataModel("Pemrograman Aplikasi Mobile - A"));

        // Set up RecyclerView with the adapter
        dataAdapter = new DataAdapter(dataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(dataAdapter);

        // Handle the Add Button click
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(n==0) {
                    dataAdapter.addData("Imanuel Nathaliando Saragih (Add Varian 1)");
                    n++;
                }else if (n==1) {
                    dataAdapter.addData("215150407111028 (Add Varian 2)");
                    n++;
                }else if (n==2) {
                    dataAdapter.addData("Pemrograman Aplikasi Mobile - A (Add Varian 3)");
                    n=0;
                }
            }
        });
    }
}
