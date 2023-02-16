package com.arrows.digivisions.ui.postTitle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.arrows.digivisions.R;
import com.arrows.digivisions.adapters.PreferenceManager;
import com.arrows.digivisions.adapters.postRecyclerAdapter;
import com.arrows.digivisions.databinding.ActivityMainBinding;
import com.arrows.digivisions.model.PostModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding ;
    RecyclerView.LayoutManager manger ;
    postRecyclerAdapter adapter ;
    PostTitleViewModel viewModel ;

    int pageNumber = 1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(PostTitleViewModel.class);

        getPostsOffline();


        viewModel.postsList.observe(this, new Observer<List<PostModel>>() {
            @Override
            public void onChanged(List<PostModel> postlist) {
                savePostsOffline(postlist);
                manger = new LinearLayoutManager(MainActivity.this);
                binding.postsRecycler.setLayoutManager(manger);
                adapter = new postRecyclerAdapter(MainActivity.this,postlist);
                binding.postsRecycler.setAdapter(adapter);
            }
        });


        binding.nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()){
                    binding.progressBar.setVisibility(View.VISIBLE);
                    pageNumber++ ;
                    viewModel.getPosts(pageNumber);
                }
            }
        });

    }
    private void savePostsOffline(List<PostModel>postList){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson() ;
        String json =  gson.toJson(postList);
        editor.putString("PostList",json);
        editor.apply();
    }
    private void getPostsOffline() {
        List<PostModel>postList ;
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences",MODE_PRIVATE);
        Gson gson = new Gson() ;
        String json = sharedPreferences.getString("PostList",null);
        Type type = new TypeToken<ArrayList<PostModel>>(){}.getType();
        postList =  gson.fromJson(json,type);



        if (postList == null){
            postList = new ArrayList<>();
            viewModel.getPosts(pageNumber);
        }else {
            manger = new LinearLayoutManager(MainActivity.this);
            binding.postsRecycler.setLayoutManager(manger);
            adapter = new postRecyclerAdapter(MainActivity.this,postList);
            binding.postsRecycler.setAdapter(adapter);
        }

    }
}