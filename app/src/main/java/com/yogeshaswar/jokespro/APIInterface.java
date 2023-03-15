package com.yogeshaswar.jokespro;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("random_joke")
    Call<JokeModel> getJoke();

}
