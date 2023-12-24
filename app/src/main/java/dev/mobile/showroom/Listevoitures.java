package dev.mobile.showroom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
public class Listevoitures extends AppCompatActivity implements CarListAdapter.OnItemClickListener {

    RecyclerView recyclerViewCar;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listevoitures);

        recyclerViewCar = findViewById(R.id.recyclerview);
        add = findViewById(R.id.add);

        // Create a Retrofit instance
        CarApi carApi = RetrofitClient.getClient().create(CarApi.class);

        // Make a network request to fetch car data
        Call<List<Car>> call = carApi.getCar();
        call.enqueue(new Callback<List<Car>>() {
            @Override
            public void onResponse(Response<List<Car>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    List<Car> carList = response.body();

                    CarListAdapter carAdapter = new CarListAdapter(Listevoitures.this, carList, Listevoitures.this);
                    recyclerViewCar.setAdapter(carAdapter);

                    // Set up RecyclerView with GridLayoutManager
                    int spanCount = 2; // Number of columns in the grid
                    recyclerViewCar.setLayoutManager(new GridLayoutManager(Listevoitures.this, spanCount));

                    // Pass the activity as the listener

                } else {
                    // Handle error
                    Toast.makeText(Listevoitures.this, "Failed to fetch car data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(Listevoitures.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Listevoitures.this, Admin.class);
                startActivity(intent);
                Toast.makeText(Listevoitures.this, "Add button clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(Car car) {
        // Handle the click event, e.g., navigate to the detail activity
        Intent intent = new Intent(Listevoitures.this, Detailvoiture.class);
        intent.putExtra("carmarque", car.getMarque()); // Assuming you have a getId() method in your Car class
        startActivity(intent);
    }
}