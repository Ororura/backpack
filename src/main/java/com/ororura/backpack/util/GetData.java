package com.ororura.backpack.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ororura.backpack.api.Api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetData {
    public static CompletableFuture<List<String>> getProductRange(int from, int to) {
        return CompletableFuture.supplyAsync(() -> {
            List<String> titleList = new ArrayList<>();
            Gson gson = new Gson();
            for (int i = from; i < to; i++) {
                String data = Api.fetchDataFromApi("https://fakestoreapi.com/products/" + i);
                JsonObject jsonObject = gson.fromJson(data, JsonObject.class);
                titleList.add(jsonObject.get("title").getAsString());
            }
            System.out.println(titleList);
            return titleList;
        }).thenApply((titleList) -> titleList);
    }
}
