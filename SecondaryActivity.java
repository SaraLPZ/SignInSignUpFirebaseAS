package com.example.pantallaregistroylogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondaryActivity extends AppCompatActivity {
    private TextView tvDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        tvDatos=findViewById(R.id.tvDatos);

        if (getIntent() == null || getIntent().getStringExtra("email") == null){
            return;
        }
        tvDatos.setText("Bienvenido" + getIntent().getStringExtra("email"));

    }
}