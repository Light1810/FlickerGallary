package com.aakash.gallaryapp.Repository;

import androidx.lifecycle.MutableLiveData;

import com.aakash.gallaryapp.Model.FlickerResponse;
import com.aakash.gallaryapp.Retrofit.FlickerApiInit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlickerRepository {

    private static FlickerRepository flickerRepository;

    private static FlickerApiInit flickerApiInit;

    private MutableLiveData<FlickerResponse> flickerResponseMutableLiveData;

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

        final Call<FlickerResponse> getAllImage = flickerApiInit.getApi().getAllImages();

        flickerResponseMutableLiveData=new MutableLiveData<>();
        getAllImage.enqueue(new Callback<FlickerResponse>() {
            @Override
            public void onResponse(Call<FlickerResponse> call, Response<FlickerResponse> response) {
                if(response.isSuccessful())
                    flickerResponseMutableLiveData.setValue(response.body());
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
}
