package com.sha.gateway.channel.service;

import com.google.gson.JsonElement;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface TransactionServiceCallable
{
    @GET("api/transaction/{userID}")
    Call<List<JsonElement>> getAllTransactionOfAuthorizedUser(@Path("userID") Integer userID);

    @GET("api/transaction")
    Call<List<JsonElement>> getAll();

    @DELETE("api/transaction/{transactionID}")
    Call<Void> deleteByID(@Path("transactionID") Integer id);

    @POST("api/transaction")
    Call<JsonElement> save(@Body JsonElement requestBody);
}
