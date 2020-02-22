package com.aakash.gallaryapp.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FlickerApiInit {

    public static Retrofit retrofit;
    public static final String BASE_URL="https://api.flickr.com/";

    private static synchronized Retrofit getInstance(){

        if(retrofit == null)
        {
            Gson gson =new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;


    }

    public FlickerApi getApi(){
    return (FlickerApiInit.getInstance().create(FlickerApi.class));
    }
}
