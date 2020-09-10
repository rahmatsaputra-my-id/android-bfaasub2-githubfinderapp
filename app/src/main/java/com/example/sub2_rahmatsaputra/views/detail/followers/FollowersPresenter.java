package com.example.sub2_rahmatsaputra.views.detail.followers;

import android.content.Context;

import com.example.sub2_rahmatsaputra.base.BasePreferences;
import com.example.sub2_rahmatsaputra.helper.ApiClient;
import com.example.sub2_rahmatsaputra.helper.ApiService;
import com.example.sub2_rahmatsaputra.models.follow.FollowModel;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FollowersPresenter implements FollowersContract.Presenter {

    private FollowersContract.View view;
    private Context context;
    private ApiService apiService;
    private BasePreferences basePreferences;

    public FollowersPresenter(FollowersContract.View view, Context context) {
        this.view = view;
        this.context = context;
        this.apiService = ApiClient.getService(context);

        basePreferences = new BasePreferences(context);
    }


    @Override
    public void getFollowers(String username) {
        view.onLoading(true);

        apiService.getUserFollowers(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<FollowModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList<FollowModel> followModels) {
                        if (followModels == null) {
                            view.onFailedFollowers("Failed");
                            view.onLoading(false);
                        } else {
                            view.onSuccessFollowers(followModels);
                            view.onLoading(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onFailedFollowers(e.getMessage());
                        view.onLoading(false);
                    }

                    @Override
                    public void onComplete() {
                        view.onLoading(false);
                    }
                });

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
