package com.example.sub2_rahmatsaputra.helper;

import com.example.sub2_rahmatsaputra.models.detail.DetailModel;
import com.example.sub2_rahmatsaputra.models.follow.FollowModel;
import com.example.sub2_rahmatsaputra.models.search.SearchModel;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search/users")
    Observable<SearchModel> getUserSearchList(
            @Query("q") String keyword
    );

    @GET("users/{username}")
    Observable<DetailModel> getUserDetail(
            @Path("username") String username
    );

    @GET("users/{username}/followers")
    Observable<ArrayList<FollowModel>> getUserFollowers(
            @Path("username") String username
    );

    @GET("users/{username}/following")
    Observable<ArrayList<FollowModel>> getUserFollowing(
            @Path("username") String username
    );
}
