package com.example.sub2_rahmatsaputra.views.detail;

import com.example.sub2_rahmatsaputra.base.BasePresenter;
import com.example.sub2_rahmatsaputra.base.BaseView;
import com.example.sub2_rahmatsaputra.models.detail.DetailModel;

public interface DetailContract {

    interface View extends BaseView {
        void onFailedDetail(String Message);

        void onSuccessDetail(DetailModel detailModel);
    }

    interface Presenter extends BasePresenter<DetailContract.View> {
        void getDetail(String username);
    }
}
