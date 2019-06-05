package com.assignment.heady.network;


import com.assignment.heady.db.ResponseDataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataCallback {

    String BASE_URL = "http://stark-spire-93433.herokuapp.com/";

    @GET("json")
    Call<ResponseDataModel> getData();




}
