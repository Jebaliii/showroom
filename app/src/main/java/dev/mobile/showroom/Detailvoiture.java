package dev.mobile.showroom;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class Detailvoiture extends AppCompatActivity implements CarListAdapter.OnItemClickListener {

    private static final String TAG = Detailvoiture.class.getSimpleName();

    private TextView marqv, modv, prixv, optionsv;
    private Button btnTotal;

    // Assuming you have a CarApi and RetrofitClient set up
    private CarApi carApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailvoiture);

        // Assuming you have a Retrofit instance created
        carApi = RetrofitClient.getClient().create(CarApi.class);

        btnTotal = findViewById(R.id.tot);
        marqv = findViewById(R.id.marque);
        modv = findViewById(R.id.modele);
        prixv = findViewById(R.id.price);
        optionsv = findViewById(R.id.options);

        Intent intent = getIntent();
        String marque = intent.getStringExtra("carmarque");

        // Use Retrofit to get car details from the database
        Call<Car> call = carApi.getCarDetails(marque);
        call.enqueue(new Callback<Car>() {
            @Override
            public void onResponse(Response<Car> response, Retrofit retrofit) {
                handleCarDetailsResponse(response);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "Failed to fetch car details", t);
                showErrorMessage("Failed to fetch car details. Please check your network connection.");
            }
        });

        btnTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAndShowTotal();
            }
        });
    }

    // Helper method to handle the response when fetching car details
    private void handleCarDetailsResponse(Response<Car> response) {
        if (response.isSuccess()) {
            Car car = response.body();
            if (car != null) {
                updateUI(car);
            } else {
                Log.e(TAG, "Received a null Car object");
                showErrorMessage("Failed to fetch car details. The response is empty or does not match the expected format.");
            }
        } else {
            Log.e(TAG, "API call not successful. Code: " + response.code());
            showErrorMessage("Failed to fetch car details. Please check your network connection.");
        }
    }

    // Helper method to update UI with car details
    private void updateUI(Car car) {
        marqv.setText("Marque: " + car.getMarque());
        modv.setText("Modèle: " + car.getModel());
        prixv.setText("Prix: " + car.getPrix());
    }

    // Helper method to calculate and show the total
    private void calculateAndShowTotal() {
        int sum = 0;
        if (((CheckBox) findViewById(R.id.airbag)).isChecked()) sum += 10_000;
        if (((CheckBox) findViewById(R.id.clim)).isChecked()) sum += 17_500;
        if (((CheckBox) findViewById(R.id.régulateur)).isChecked()) sum += 5_000;
        if (((CheckBox) findViewById(R.id.jante)).isChecked()) sum += 22_000;
        if (((CheckBox) findViewById(R.id.toit)).isChecked()) sum += 25_000;
        if (((CheckBox) findViewById(R.id.sallon)).isChecked()) sum += 35_000;
        if (((CheckBox) findViewById(R.id.Nitro)).isChecked()) sum += 50_000;

        showTotalDialog(sum);
    }

    // Helper method to show a dialog with the total
    private void showTotalDialog(int total) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(" La Somme Total est de  : ");
        dialog.setMessage(String.valueOf(total));
        dialog.setPositiveButton("  Buy  ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Intent intent = new Intent(Detailvoiture.this, Payement.class);
                startActivity(intent);
            }
        }).setNegativeButton(" Cancel ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    // Helper method to show an error message
    private void showErrorMessage(String message) {
        Toast.makeText(Detailvoiture.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(Car car) {
        // Handle item click if needed
    }
}