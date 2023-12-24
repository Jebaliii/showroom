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
 * Use the {@link add#newInstance} factory method to
 * create an instance of this fragment.
 */
public class add extends Fragment {

    private EditText Marque,Modele,Prix,Paiement;
    private Button BtnAdd;
    private TextView err;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public add() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment add.
     */
    // TODO: Rename and change types and number of parameters
    public static add newInstance(String param1, String param2) {
        add fragment = new add();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View v = inflater.inflate(R.layout.fragment_add, container, false);

        Marque=v.findViewById(R.id.marque);
        Modele= v.findViewById(R.id.modele);
        Prix=v.findViewById(R.id.price);
        Paiement=v.findViewById(R.id.methode);
        BtnAdd=v.findViewById(R.id.btnAddCar);
        err=v.findViewById(R.id.error);

        BtnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                addCar();
            }
        });

        return v;
    }

    private void addCar()
    {
        String marque = Marque.getText().toString().trim();
        String modele = Modele.getText().toString().trim();
        String prix = Prix.getText().toString().trim();
        String methode = Paiement.getText().toString().trim();

        String URL= getArguments().getString("url", "");
        Retrofit Rf = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();

        CarApi api = Rf.create(CarApi.class);
        Call<Car> addCar = api.insertCar(marque, modele,prix, methode);

        addCar.enqueue(new Callback<Car>()
        {
            @Override
            public void onResponse (Response<Car> response, Retrofit retrofit)
            {
                if (response.isSuccess())
                {
                    Toast.makeText(getActivity(),"Car added", Toast.LENGTH_LONG).show();
                }

            }
            @Override
            public void onFailure (Throwable t)
            {
                Toast.makeText(getActivity(), "failed" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                err.setText(t.getLocalizedMessage());
            }
        });
    }

}