package com.example.newsx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.newsx.Models.DatabaseHelper;
import com.example.newsx.Models.Headline;

import java.util.List;

public class SeeLater extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_later);
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(SeeLater.this);
        List<Headline> temp = databaseHelper.headlineDao().getall();
        RecyclerView recyclerView = findViewById(R.id.SeeLater);
        AdapterSeeLater adapterSeeLater = new AdapterSeeLater(SeeLater.this, temp);
        recyclerView.setAdapter(adapterSeeLater);
        recyclerView.setLayoutManager(new LinearLayoutManager(SeeLater.this));
    }
}