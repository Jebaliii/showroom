package dev.mobile.showroom;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link update#newInstance} factory method to
 * create an instance of this fragment.
 */
public class update extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText CarId, updatedMarque, updatedModele, updatedPrice, updatedmethode;
    private Button btnUpdate;

    public update() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment update.
     */
    // TODO: Rename and change types and number of parameters
    public static update newInstance(String param1, String param2) {
        update fragment = new update();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_update, container, false);

        CarId = v.findViewById(R.id.Car_id);
        updatedMarque = v.findViewById(R.id.updated_marque);
        updatedModele = v.findViewById(R.id.updated_modele);
        updatedPrice= v.findViewById(R.id.updated_price);
        updatedmethode = v.findViewById(R.id.updated_methode);
        btnUpdate = v.findViewById(R.id.btnUpdateCar);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser();
            }
        });

        return v;
    }

    private void updateUser() {
        String id = CarId.getText().toString().trim();
        String marque = updatedMarque.getText().toString().trim();
        String modele = updatedModele.getText().toString().trim();
        String price = updatedPrice.getText().toString().trim();
        String paiement = updatedmethode.getText().toString().trim();

        // Ensure ID is not empty
        if (id.isEmpty())
        {
            Toast.makeText(getActivity(), "Please enter a Car ID", Toast.LENGTH_SHORT).show();
            return;
        }

        String URL = getArguments().getString("url", "");
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();

        CarApi api = retrofit.create(CarApi.class);
        Call<Car> updateCarCall = api.updateCar(Integer.parseInt(id), marque, modele, price, paiement);

        updateCarCall.enqueue(new Callback<Car>()
        {
            @Override
            public void onResponse(Response<Car> response, Retrofit retrofit)
            {
                if (response.isSuccess())
                {
                    Toast.makeText(getActivity(), "Car updated", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "Update failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t)
            {
                Toast.makeText(getActivity(), "Update failed: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}