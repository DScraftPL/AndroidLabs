package com.example.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Lab2Activity extends MainActivity {

    Button przycisk, czyZdane;

    TextView sredniaDisplay;

    EditText firstName, lastName, oceny;

    boolean[] flags = new boolean[3];

    protected void checkAllTrue() {
        boolean allTrue = true;

        for (boolean flag : flags) {
            if (!flag) {
                allTrue = false;
                break;
            }
        }

        if (allTrue) {
            przycisk.setVisibility(View.VISIBLE);
        } else {
            przycisk.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        firstName = findViewById(R.id.FirstName);
        lastName = findViewById(R.id.LastName);
        oceny = findViewById(R.id.Oceny);
        przycisk = findViewById(R.id.przycisk);
        przycisk.setVisibility(View.INVISIBLE);

        firstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().isEmpty()) {
                    flags[0] = false;
                } else {
                    flags[0] = true;
                }
                checkAllTrue();
            }
        });
        lastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().isEmpty()) {
                    flags[1] = false;
                } else {
                    flags[1] = true;
                }
                checkAllTrue();
            }
        });
        oceny.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().isEmpty()) {
                    flags[2] = false;
                } else {
                    int number = Integer.parseInt(s.toString());
                    if (number >= 5 && number <= 15) {
                        flags[2] = true;
                    } else {
                        flags[2] = false;
                    }
                }
                checkAllTrue();
            }
        });

        oceny.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                String text = oceny.getText().toString();
                try {
                    int number = Integer.parseInt(text);
                    if (number >= 5 && number <= 15) {
                        oceny.setError(null);
                        flags[2] = true;
                    } else {
                        oceny.setError("Wartość musi być między 5 a 15");
                        flags[2] = false;
                        Toast.makeText(getApplicationContext(), "Wartość musi być między 5 a 15", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    oceny.setError("pole nie moze byc puste");
                }
            }
        });

        firstName.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                String text = firstName.getText().toString();
                if (text.isEmpty()) {
                    firstName.setError("Wartość nie może być pusta");
                    Toast.makeText(getApplicationContext(), "Pole nie moze być puste", Toast.LENGTH_SHORT).show();
                } else {
                    firstName.setError(null);
                }
            }
        });

        lastName.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                String text = lastName.getText().toString();
                if (text.isEmpty()) {
                    lastName.setError("Wartość nie może być pusta");
                    Toast.makeText(getApplicationContext(), "Pole nie moze być puste", Toast.LENGTH_SHORT).show();
                } else {
                    lastName.setError(null);
                }
            }
        });
        przycisk.setOnClickListener((v) -> {
            Intent intencja = new Intent(this, OcenyActivity.class);
            Bundle paczka = new Bundle();
            paczka.putInt("oceny", Integer.parseInt(oceny.getText().toString()));
            intencja.putExtras(paczka);
            startActivity(intencja);
        });

        czyZdane = findViewById(R.id.czyZdane);
        sredniaDisplay = findViewById(R.id.sredniaDisplay);
        Bundle paczka2 = getIntent().getExtras();
        if (paczka2 != null) {
            String buttonText = "";
            double srednia = paczka2.getDouble("srednia");
            String text = String.format("%.2f", srednia);
            sredniaDisplay.setText(text);
            sredniaDisplay.setVisibility(View.VISIBLE);
            if(srednia >= 3.0){
                buttonText = "Super :)";
                czyZdane.setOnClickListener((v) -> {
                    Toast.makeText(getApplicationContext(), "Gratulacje! Otrzymujesz zaliczenie!", Toast.LENGTH_SHORT).show();
                    finishAffinity();
                });
            } else {
                buttonText = "Tym razem mi nie poszło";
                czyZdane.setOnClickListener((v) -> {
                    Toast.makeText(getApplicationContext(), "Wysyłam podanie o zaliczenie warunkowe", Toast.LENGTH_SHORT).show();
                    finishAffinity();
                });
            }
            czyZdane.setText(buttonText);
            czyZdane.setVisibility(View.VISIBLE);
        }
    }
}