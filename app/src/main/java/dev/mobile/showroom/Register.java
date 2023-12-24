package dev.mobile.showroom;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class Register extends AppCompatActivity {
    Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        signin = findViewById(R.id.register);
        List<String> genre = Arrays.asList("Male","Female");
        final Spinner gendre = findViewById(R.id.gend);
        ArrayAdapter adapterr = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item,genre);
        adapterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gendre.setAdapter(adapterr);
        List<String> states = Arrays.asList("Tunisia","Algérie","Libya","Marroc");
        final Spinner spinner = findViewById(R.id.pays);
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item,states);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this,Listevoitures.class);
                startActivity(intent);
            }
        });
    }
}
