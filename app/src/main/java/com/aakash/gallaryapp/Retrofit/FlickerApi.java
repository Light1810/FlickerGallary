package com.aakash.gallaryapp.Retrofit;

import com.aakash.gallaryapp.Model.FlickerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickerApi {

    String url="/services/rest/";

    String urlComplete="services/rest/?method=flickr.photos.getRecent&per_page=20&page=1&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s";


    @GET(url)
    Call<FlickerResponse> getImages(
            @Query("method") String method,
            @Query("per_page") int per_page,
            @Query("page") int page,
            @Query("api_key") String api_key,
            @Query("format") String format,
            @Query("nojsoncallback") int noJsonCallback,
            @Query("extras") String extra
    );

    @GET(url)
    Call<FlickerResponse> searchImages(
            @Query("method") String method,
            @Query("per_page") int per_page,
            @Query("page") int page,
            @Query("api_key") String api_key,
            @Query("format") String format,
            @Query("nojsoncallback") int noJsonCallback,
            @Query("extras") String extra,
            @Query("text") String text
    );
}

