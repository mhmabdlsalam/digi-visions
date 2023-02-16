package com.arrows.digivisions.data;

import com.arrows.digivisions.model.apiResponse;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrofitClient {
    private static final String base_Url = "https://contextualwebsearch-websearch-v1.p.rapidapi.com/api/";
    private apiInterface api ;
    private static retrofitClient instance ;

    public retrofitClient (){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_Url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        api = retrofit.create(apiInterface.class);
    }
    public static retrofitClient getInstance(){
        if (instance==null){
            instance = new retrofitClient();
        }
        return instance ;
    }

    public Single<apiResponse> getPosts(){
        return api.getPosts(1);
    }

    public apiInterface getApi() {
        return api;
    }
}
