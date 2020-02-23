package com.aakash.gallaryapp.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.aakash.gallaryapp.BuildConfig;
import com.aakash.gallaryapp.Model.FlickerResponse;
import com.aakash.gallaryapp.Retrofit.FlickerApiInit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlickerRepository {

    private static FlickerRepository flickerRepository;

    private static FlickerApiInit flickerApiInit;

    private static String method = "flickr.photos.getRecent";
    private static String API_KEY = BuildConfig.ApiKey;
    private static String format = "json";
    private static int noJsonCallback =1;
    private static String extra = "url_s";
    private static String methodSearch = "flickr.photos.search";

    private MutableLiveData<FlickerResponse> flickerResponseMutableLiveData;
    private MutableLiveData<FlickerResponse> flickerSearchResponseMutableLiveData;

    public static FlickerRepository getInstance(){
        if(flickerRepository == null){
            flickerRepository =new FlickerRepository();
        }

        return flickerRepository;
    }

    private FlickerRepository(){
        flickerApiInit= new FlickerApiInit();
    }

    public MutableLiveData<FlickerResponse> getAllImages(){

        final Call<FlickerResponse> getAllImage = flickerApiInit.getApi().getImages(method,21,1,API_KEY,format,noJsonCallback,extra);

        flickerResponseMutableLiveData=new MutableLiveData<>();
        getAllImage.enqueue(new Callback<FlickerResponse>() {
            @Override
            public void onResponse(Call<FlickerResponse> call, Response<FlickerResponse> response) {
                if(response.isSuccessful()){
                    flickerResponseMutableLiveData.setValue(response.body());
                    Log.d("HOME_FRAG",response.body().toString());
                }
                else
                    flickerResponseMutableLiveData.setValue(null);
            }

            @Override
            public void onFailure(Call<FlickerResponse> call, Throwable t) {

                t.printStackTrace();

                flickerResponseMutableLiveData.setValue(null);

            }
        });
        return flickerResponseMutableLiveData;
    }

    public MutableLiveData<FlickerResponse> getSearchResponse(String imageKeyword){

        final Call<FlickerResponse> searchImage = flickerApiInit.getApi().searchImages(methodSearch,21,1,API_KEY,format,noJsonCallback,extra,imageKeyword);
        flickerSearchResponseMutableLiveData = new MutableLiveData<>();
        searchImage.enqueue(new Callback<FlickerResponse>() {
            @Override
            public void onResponse(Call<FlickerResponse> call, Response<FlickerResponse> response) {
                if(response.isSuccessful())
                    flickerSearchResponseMutableLiveData.setValue(response.body());
                else
                    flickerSearchResponseMutableLiveData.setValue(null);

            }

            @Override
            public void onFailure(Call<FlickerResponse> call, Throwable t) {

                t.printStackTrace();

                flickerSearchResponseMutableLiveData.setValue(null);


            }
        });

       return flickerSearchResponseMutableLiveData;
    }

}
