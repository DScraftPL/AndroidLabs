package com.example.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OcenyActivity extends MainActivity {

    private Button srednia;
    private RecyclerView recyclerView;
    private OcenyAdapter adapter;
    private List<OcenyDane> oceny;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_oceny);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);

        oceny = new ArrayList<>();
        String[] przedmioty = getResources().getStringArray(R.array.przedmioty);

        Bundle paczka = getIntent().getExtras();
        int liczbaOcen = paczka.getInt("oceny");
        for (int i = 0; i < liczbaOcen; i++) {
            oceny.add(new OcenyDane(przedmioty[i]));
        }

        adapter = new OcenyAdapter(oceny);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        srednia = findViewById(R.id.srednia);

        srednia.setOnClickListener((v) -> {
            Intent intencja = new Intent(this, Lab2Activity.class);
            Bundle paczka2 = new Bundle();
            double suma = 0;
            for (int i = 0; i < liczbaOcen; i++){
                suma += oceny.get(i).getCurrentOcena().getValue();
            }
            paczka2.putDouble("srednia", suma/liczbaOcen);
            intencja.putExtras(paczka2);
            startActivity(intencja);
        });
    }
}