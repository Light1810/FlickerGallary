package com.aakash.gallaryapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.aakash.gallaryapp.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class FullSizeImageActivity extends AppCompatActivity {

    private ImageView ivFullSizeImage;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_size_image);

        url=getIntent().getStringExtra("url");

        ivFullSizeImage=findViewById(R.id.ivFullSizeImage);
        Glide.with(this).load(url).apply(new RequestOptions().override(150,150)).into(ivFullSizeImage);
    }
}
