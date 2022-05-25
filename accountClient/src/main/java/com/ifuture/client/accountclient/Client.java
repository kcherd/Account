package com.ifuture.client.accountclient;

import com.ifuture.client.accountclient.api.ApiFactory;
import com.ifuture.client.accountclient.model.Account;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Client {
    private final int rCount;
    private final int wCount;
    private final List<Integer> idList = new ArrayList<>();

    public Client(String[] args) throws Exception {
        if (args.length < 3) {
            throw new Exception("Неверные параметры");
        }
        this.rCount = Integer.parseInt(args[0]);
        this.wCount = Integer.parseInt(args[1]);
        for (int i = 2; i < args.length; i++) {
            idList.add(Integer.parseInt(args[i]));
        }
    }

    public void callServer() {
        getAmounts();
        addAmounts();
    }

    public void getAmounts() {
        ThreadPoolExecutor executor =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(rCount);
        for (int i = 0; i < rCount || i < idList.size(); i++) {
            int finalI = i;
            executor.submit(() -> {
                getAmount(idList.get(finalI));
                return null;
            });
        }
    }

    public void addAmounts() {
        ThreadPoolExecutor executor =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(rCount);
        for (int i = 0; i < wCount || i < idList.size(); i++) {
            int finalI = i;
            executor.submit(() -> {
                addAmount(idList.get(finalI));
                return null;
            });
        }
    }

    private void addAmount(int id) {
        Random random = new Random();
        Account account = new Account(id, random.nextInt(1000));
        Call<Void> call = ApiFactory.getService().addAmount(account);
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {}

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {}
        });
    }

    private void getAmount(int id) {
        Call<Long> call = ApiFactory.getService().getAmount(id);
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {}

            @Override
            public void onFailure(Call<Long> call, Throwable throwable) {}
        });
    }
}
