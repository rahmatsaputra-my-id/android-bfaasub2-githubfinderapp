package com.example.sub2_rahmatsaputra.views.detail.following;

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

public class FollowingPresenter implements FollowingContract.Presenter {

    private FollowingContract.View view;
    private Context context;
    private ApiService apiService;
    private BasePreferences basePreferences;

    public FollowingPresenter(FollowingContract.View view, Context context) {
        this.view = view;
        this.context = context;
        this.apiService = ApiClient.getService(context);

        basePreferences = new BasePreferences(context);
    }

    @Override
    public void getFollowing(String username) {
        view.onLoading(true);

        apiService.getUserFollowing(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<FollowModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList<FollowModel> followModels) {
                        if (followModels == null) {
                            view.onFailedFollowing("Failed");
                            view.onLoading(false);
                        } else {
                            view.onSuccessFollowing(followModels);
                            view.onLoading(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onFailedFollowing(e.getMessage());
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
