package com.ifuture.client.accountclient.api;

import com.ifuture.client.accountclient.model.Account;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {
    @Headers("Content-Type: application/json")
    @GET("/getAmount")
    Call<Long> getAmount(
            @Query("id") Integer id);

    @Headers("Content-Type: application/json")
    @POST("/addAmount")
    Call<Void> addAmount(
            @Body Account account);
}
