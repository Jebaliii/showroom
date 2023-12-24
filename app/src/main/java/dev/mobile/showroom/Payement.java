package dev.mobile.showroom;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class Payement extends AppCompatActivity {
    Button btnBuy;
    EditText mail , nom,prenom;
    CheckBox agree;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payement);
        btnBuy=findViewById(R.id.Buy);
        agree=findViewById(R.id.Agree);
        mail = findViewById(R.id.Adresse_mail);
        nom = findViewById(R.id.Nom);
        prenom = findViewById(R.id.Prenom);


        List<String> states = Arrays.asList("Tunisia","Algérie","Libya","Marroc","France","Italie");
        final Spinner spinner = findViewById(R.id.pays);
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item,states);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        btnBuy.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {


                if((agree.isChecked() && ((mail.getText().toString()).equals("jbalidhia07@gmail.com")) && ((nom.getText().toString()).equals("Jebali")) && ((prenom.getText().toString().equals("Dhiaa")))))
                {
                    AlertDialog.Builder Dialog;
                    Dialog = new AlertDialog.Builder(Payement.this);
                    Dialog.setTitle(" Votre demande est ");
                    Dialog.setMessage(" en cours de l'exécution ");
                    Dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Payement.this , Listevoitures.class);
                            startActivity(intent);
                        }
                    });
                    Dialog.show();                }
                else
                {
                    Toast.makeText(Payement.this, "vérifier vos donnée s'il vous plait ", Toast.LENGTH_LONG).show();
                    AlertDialog.Builder Dialog;
                    Dialog = new AlertDialog.Builder(Payement.this);
                    Dialog.setTitle(" Error ");
                    Dialog.setMessage(" you must accpet the rules !!! ");
                    Dialog.show();
                }


            }



        });}}