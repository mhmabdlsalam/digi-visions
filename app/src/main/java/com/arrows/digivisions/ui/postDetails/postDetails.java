package com.arrows.digivisions.ui.postDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.arrows.digivisions.R;
import com.arrows.digivisions.databinding.ActivityMainBinding;
import com.arrows.digivisions.databinding.ActivityPostDetailsBinding;
import com.squareup.picasso.Picasso;

public class postDetails extends AppCompatActivity {
    ActivityPostDetailsBinding binding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_post_details);

        binding.title.setText(getIntent().getStringExtra("title"));

        Picasso.get().load(getIntent().getStringExtra("url")).into(binding.postImage);
    }
}