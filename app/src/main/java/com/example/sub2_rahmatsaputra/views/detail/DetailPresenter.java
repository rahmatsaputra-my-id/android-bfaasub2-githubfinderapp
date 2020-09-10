package com.example.sub2_rahmatsaputra.views.detail;

import android.content.Context;

import com.example.sub2_rahmatsaputra.base.BasePreferences;
import com.example.sub2_rahmatsaputra.helper.ApiClient;
import com.example.sub2_rahmatsaputra.helper.ApiService;
import com.example.sub2_rahmatsaputra.models.detail.DetailModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class DetailPresenter implements DetailContract.Presenter {

    private DetailContract.View view;
    private Context context;
    private ApiService apiService;
    private BasePreferences basePreferences;

    public DetailPresenter(DetailContract.View view, Context context) {
        this.view = view;
        this.context = context;
        this.apiService = ApiClient.getService(context);

        basePreferences = new BasePreferences(context);
    }

    @Override
    public void getDetail(String username) {
        view.onLoading(true);

        apiService.getUserDetail(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DetailModel detailModel) {
                        if (detailModel == null) {
                            view.onFailedDetail("Failed");
                            view.onLoading(false);
                        } else {
                            view.onSuccessDetail(detailModel);
                            view.onLoading(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onFailedDetail(e.getMessage());
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
