package com.arrows.digivisions.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.arrows.digivisions.BR;
import com.arrows.digivisions.R;
import com.arrows.digivisions.databinding.PostTitleBinding;
import com.arrows.digivisions.model.PostModel;
import com.arrows.digivisions.ui.postDetails.postDetails;


import java.util.List;

public class postRecyclerAdapter extends RecyclerView.Adapter<postRecyclerAdapter.ViewHolder> {

    private List<PostModel> mData;
    private LayoutInflater mInflater;
    private Context context ;


    public postRecyclerAdapter(Context context, List<PostModel> data) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PostTitleBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.post_title, parent, false);

        return new ViewHolder(binding);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bind(mData.get(position));
        holder.post_titleBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,postDetails.class);

                intent.putExtra("title",mData.get(position).getTitle());
                intent.putExtra("url",mData.get(position).getUrl());

                context.startActivity(intent);
            }
        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        public PostTitleBinding post_titleBinding;

        ViewHolder(PostTitleBinding itemView) {
            super(itemView.getRoot());
            this.post_titleBinding = itemView;
        }

        public void bind(Object obj) {
            post_titleBinding.setVariable(BR.model, obj);
            post_titleBinding.executePendingBindings();
        }
    }

    // convenience method for getting data at click position
    PostModel getItem(int id) {
        return mData.get(id);
    }


}
