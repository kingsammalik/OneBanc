package com.samapps.onebanc;

import com.samapps.onebanc.response.OneBancResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface gitApi {

    @GET("GetTransactionHistory")
    Call<OneBancResponse> loadChanges(@Query("userId") String userId, @Query("recipientId") String recipientId);

}
