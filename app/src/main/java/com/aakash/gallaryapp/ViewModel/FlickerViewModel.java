package com.aakash.gallaryapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.aakash.gallaryapp.Model.FlickerResponse;
import com.aakash.gallaryapp.Repository.FlickerRepository;

public class FlickerViewModel extends ViewModel {

    private static FlickerRepository flickerRepository;

    public FlickerViewModel (){
        if(flickerRepository == null){
            flickerRepository=FlickerRepository.getInstance();
        }
    }

    public LiveData<FlickerResponse> getAllImages(){
        return flickerRepository.getAllImages();
    }

}
