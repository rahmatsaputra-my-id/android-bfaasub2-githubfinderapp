package com.example.sub2_rahmatsaputra.views.detail.followers;

import com.example.sub2_rahmatsaputra.base.BasePresenter;
import com.example.sub2_rahmatsaputra.base.BaseView;
import com.example.sub2_rahmatsaputra.models.follow.FollowModel;

import java.util.ArrayList;

public interface FollowersContract {

    interface View extends BaseView {
        void onFailedFollowers(String Message);

        void onSuccessFollowers(ArrayList<FollowModel> followModel);
    }

    interface Presenter extends BasePresenter<FollowersContract.View> {
        void getFollowers(String username);
    }
}
