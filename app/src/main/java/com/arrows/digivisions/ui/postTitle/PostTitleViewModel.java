package com.arrows.digivisions.ui.postTitle;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.arrows.digivisions.adapters.PreferenceManager;
import com.arrows.digivisions.data.retrofitClient;
import com.arrows.digivisions.model.PostModel;
import com.arrows.digivisions.model.apiResponse;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PostTitleViewModel extends ViewModel {

    MutableLiveData<List<PostModel>> postsList = new MutableLiveData<>()  ;
    private static final String TAG = "PostTitleViewModel";
    CompositeDisposable disposable = new CompositeDisposable();
    List<PostModel> postTitleList = new ArrayList<>( );



    public void getPosts(int page){


        disposable.add(retrofitClient.getInstance().getApi().getPosts(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Function<apiResponse , List<PostModel>>() {
                    @Override
                    public List<PostModel> apply(apiResponse apiResponse) throws Throwable {

                        for (int i = 0 ; i<apiResponse.getValue().size() ; i++){
                            postTitleList.add(new PostModel(apiResponse.getValue().get(i).getTitle(),apiResponse.getValue().get(i).getUrl()));
                        }
                        return postTitleList;
                    }
                })

                .subscribe(new Consumer<List<PostModel>>() {
                    @Override
                    public void accept(List<PostModel> postTitleList) throws Throwable {
                        postsList.setValue(postTitleList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG,throwable.getMessage());
                    }
                }));

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

}
