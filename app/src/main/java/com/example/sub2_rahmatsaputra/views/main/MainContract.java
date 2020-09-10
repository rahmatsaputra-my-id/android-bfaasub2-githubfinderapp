package com.example.sub2_rahmatsaputra.views.main;

import com.example.sub2_rahmatsaputra.base.BasePresenter;
import com.example.sub2_rahmatsaputra.base.BaseView;
import com.example.sub2_rahmatsaputra.models.search.ItemsItem;

import java.util.List;

public interface MainContract {

    interface View extends BaseView {
        void onFailedMainList(String Message);

        void onSuccessMainList(List<ItemsItem> mainSearchModel);
    }

    interface Presenter extends BasePresenter<MainContract.View> {
        void getMainList(String query);
    }
}
