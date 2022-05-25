package com.ifuture.client.accountclient.api;

//Импортируем необходимые классы

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Объявляем фабрику - только статичные поля и методы
public class ApiFactory {

    private static final String ROOT_URL = "http://localhost:8080";

    static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiService getService() {
        return buildRetrofit().create(ApiService.class);
    }
}
