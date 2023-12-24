package dev.mobile.showroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    private EditText login,pw;
    private Button connect;
    private TextView textregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login= (EditText) findViewById(R.id.log);
        pw= (EditText) findViewById(R.id.pass);
        connect= (Button) findViewById(R.id.connect);
        textregister = findViewById(R.id.register);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((login.getText().toString()).equals("test")) && ((pw.getText().toString()).equals("iset"))) {
                    Toast.makeText(MainActivity.this, "bien login et mot de passe", Toast.LENGTH_LONG).show();
                    goTonextActivity();
                }
                else {
                    Toast.makeText(MainActivity.this, "vérifier vos donnée s'il vous plait ", Toast.LENGTH_LONG).show();
                }

            }
        });
        textregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }

        });
    }
    private void goTonextActivity()
    {
        Intent intent = new Intent(this,Listevoitures.class);
        startActivity(intent);
    }

}