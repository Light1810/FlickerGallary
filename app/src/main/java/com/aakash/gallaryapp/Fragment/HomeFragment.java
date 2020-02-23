package com.aakash.gallaryapp.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aakash.gallaryapp.Adapter.ImageViewAdapter;
import com.aakash.gallaryapp.Model.FlickerResponse;
import com.aakash.gallaryapp.Model.Photo;
import com.aakash.gallaryapp.R;
import com.aakash.gallaryapp.ViewModel.FlickerViewModel;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private ImageViewAdapter imageViewAdapter;
    private FlickerViewModel flickerViewModel;
    private List<Photo> photoList = new ArrayList<>();
    private ProgressDialog progressDialog;


    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView=v.findViewById(R.id.rvImageGalleryHome);




        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        flickerViewModel=new ViewModelProvider(getActivity()).get(FlickerViewModel.class);
        imageViewAdapter=new ImageViewAdapter(getContext(),photoList);
        recyclerView.setLayoutManager( new GridLayoutManager(getContext(),3));
        recyclerView.setAdapter(imageViewAdapter);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please Wait..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        flickerViewModel.getAllImages().observe(getViewLifecycleOwner(),new Observer<FlickerResponse>() {
            @Override
            public void onChanged(FlickerResponse flickerResponse) {
                photoList.addAll(flickerResponse.getPhotos().getPhoto());
                imageViewAdapter.notifyDataSetChanged();
                progressDialog.dismiss();

            }
        });


    }
}
