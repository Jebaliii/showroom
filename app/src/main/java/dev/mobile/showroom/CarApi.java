package dev.mobile.showroom;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

import java.util.List;

public interface CarApi {
    @GET("showroom/List.php")
    Call<List<Car>> getCar();

    @FormUrlEncoded
    @POST("showroom/insert.php")
    Call<Car> insertCar(@Field("marque") String marque,
                        @Field("model") String model,
                        @Field("prix") String prix,
                        @Field("paiement") String paiement);

    @FormUrlEncoded
    @POST("showroom/update.php")
    Call<Car> updateCar(@Field("id") int id,
                        @Field("marque") String marque,
                        @Field("model") String model,
                        @Field("prix") String prix,
                        @Field("paiement") String paiement);

    @FormUrlEncoded
    @POST("showroom/delete.php")
    Call<Car> deleteCar(@Field("id") int id);

    @GET("showroom/CarDetails.php")
    Call<Car> getCarDetails(@Query("marque") String marque);
}
