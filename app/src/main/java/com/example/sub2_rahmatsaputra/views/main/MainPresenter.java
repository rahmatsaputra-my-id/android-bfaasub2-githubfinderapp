package com.example.sub2_rahmatsaputra.views.main;

import android.content.Context;

import com.example.sub2_rahmatsaputra.base.BasePreferences;
import com.example.sub2_rahmatsaputra.helper.ApiClient;
import com.example.sub2_rahmatsaputra.helper.ApiService;
import com.example.sub2_rahmatsaputra.models.search.SearchModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private Context context;
    private ApiService apiService;
    private BasePreferences basePreferences;

    public MainPresenter(MainContract.View view, Context context) {
        this.view = view;
        this.context = context;
        this.apiService = ApiClient.getService(context);

        basePreferences = new BasePreferences(context);
    }

    @Override
    public void getMainList(String keyword) {
        view.onLoading(true);

        apiService.getUserSearchList(keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchModel>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchModel searchModel) {
                        if (searchModel.getTotalCount() > 0) {
                            if (searchModel == null) {
                                view.onFailedMainList("Failed");
                                view.onLoading(false);
                            } else {
                                searchModel.setItems(searchModel.getItems());
                                view.onSuccessMainList(searchModel.getItems());
                                view.onLoading(false);
                            }
                        } else {
                            view.onFailedMainList("Failed");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onFailedMainList(e.getMessage());
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
