package com.aakash.gallaryapp.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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


public class SearchFragment extends Fragment {

    private RecyclerView recyclerView;
    private EditText etSearch;
    private Button btSearch;
    private ImageViewAdapter imageViewAdapter;
    private FlickerViewModel flickerViewModel;
    private List<Photo> photoList = new ArrayList<>();
    private ProgressDialog progressDialog;


    public SearchFragment() {
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
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        etSearch = v.findViewById(R.id.etSearch);
        btSearch = v.findViewById(R.id.btSearch);
        recyclerView=v.findViewById(R.id.rvImageGallery);

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etSearch.getText()!=null){
                    progressDialog.show();

                    flickerViewModel.searchImage(etSearch.getText().toString()).observe(getViewLifecycleOwner(), new Observer<FlickerResponse>() {
                        @Override
                        public void onChanged(FlickerResponse flickerResponse) {
                            photoList.clear();
                            photoList.addAll(flickerResponse.getPhotos().getPhoto());
                            imageViewAdapter.notifyDataSetChanged();
                            progressDialog.dismiss();

                        }
                    });

                }
            }
        });



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







    }
}
