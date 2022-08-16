package com.hashinology.retrofitlist;

import com.hashinology.retrofitlist.model.ModelList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    String Base_URL = "https://jsonplaceholder.typicode.com/posts/";
    @GET(Base_URL)
    Call<List<ModelList>> getMyPost();
}
