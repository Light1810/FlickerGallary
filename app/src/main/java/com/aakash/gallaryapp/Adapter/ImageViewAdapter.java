package com.aakash.gallaryapp.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.aakash.gallaryapp.Activity.FullSizeImageActivity;
import com.aakash.gallaryapp.Model.FlickerResponse;
import com.aakash.gallaryapp.Model.Photo;
import com.aakash.gallaryapp.R;
import com.aakash.gallaryapp.ViewModel.FlickerViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class ImageViewAdapter extends RecyclerView.Adapter<ImageViewAdapter.ImageHolder> {

    Context context;
    List<Photo> photoList;


    public ImageViewAdapter(Context context, List<Photo> photoList) {
        this.context = context;
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.single_image_view,parent,false);
        return new ImageHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {

       final String url = photoList.get(position).getUrlS();
        Glide.with(context).load(url).apply(new RequestOptions().override(150,150)).into(holder.singleImageView);

        holder.singleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FullSizeImageActivity.class);
                intent.putExtra("url",url);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    class ImageHolder extends RecyclerView.ViewHolder{

        ImageView singleImageView;

        public ImageHolder(@NonNull View itemView) {
            super(itemView);

            singleImageView= itemView.findViewById(R.id.singleImage);

        }
    }
}
