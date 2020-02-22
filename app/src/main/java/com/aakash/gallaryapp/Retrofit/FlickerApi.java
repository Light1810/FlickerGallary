package com.aakash.gallaryapp.Retrofit;

import com.aakash.gallaryapp.Model.FlickerResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FlickerApi {

    String url="/services/rest/?method=flickr.photos.getRecent&per_page=20&page=1&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s";

    @GET(url)
    Call<FlickerResponse> getAllImages();
}

