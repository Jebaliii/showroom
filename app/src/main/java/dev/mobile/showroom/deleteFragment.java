package dev.mobile.showroom;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link deleteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class deleteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText CarIdToDelete;
    private Button btnDeleteCar;
    public deleteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment deleteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static deleteFragment newInstance(String param1, String param2) {
        deleteFragment fragment = new deleteFragment();
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
        View v = inflater.inflate(R.layout.fragment_delete, container, false);

        CarIdToDelete = v.findViewById(R.id.Car_id_to_delete);
        btnDeleteCar = v.findViewById(R.id.btnDeleteCar);

        btnDeleteCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCar();
            }
        });

        return v;
    }

    private void deleteCar() {
        String id = CarIdToDelete.getText().toString().trim();

        // Ensure ID is not empty
        if (id.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter a Car ID", Toast.LENGTH_SHORT).show();
            return;
        }

        // Retrieve URL from arguments
        String URL = getArguments().getString("url", "");
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();

        CarApi api = retrofit.create(CarApi.class);
        Call<Car> deleteCarCall = api.deleteCar(Integer.parseInt(id));

        deleteCarCall.enqueue(new Callback<Car>() {
            @Override
            public void onResponse(Response<Car> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Toast.makeText(getActivity(), "Car deleted", Toast.LENGTH_LONG).show();
                    // You might want to clear the ID field or perform any other UI update here
                } else {
                    Toast.makeText(getActivity(), "Delete failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(), "Delete failed: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}