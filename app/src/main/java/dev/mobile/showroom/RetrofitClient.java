package dev.mobile.showroom;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RetrofitClient {
    private static final String BASE_URL = "http://192.168.1.14:80"; // Replace with your actual base URL

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
