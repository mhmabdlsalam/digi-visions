package com.arrows.digivisions.data;

import com.arrows.digivisions.model.apiResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface apiInterface {

    @Headers({
            "X-RapidAPI-Key: 3a15a692a4msh87fbd0b981ea92fp1abe5djsnca4e30814789",
            "X-RapidAPI-Host: contextualwebsearch-websearch-v1.p.rapidapi.com"
    })

    @GET("Search/ImageSearchAPI?pageSize=10&q=taylor swift&autoCorrect=true")
    public Single<apiResponse> getPosts(@Query("pageNumber")int page);

}
