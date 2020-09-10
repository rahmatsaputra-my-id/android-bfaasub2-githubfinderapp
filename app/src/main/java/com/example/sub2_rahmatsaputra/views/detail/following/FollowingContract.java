package com.example.sub2_rahmatsaputra.views.detail.following;

import com.example.sub2_rahmatsaputra.base.BasePresenter;
import com.example.sub2_rahmatsaputra.base.BaseView;
import com.example.sub2_rahmatsaputra.models.follow.FollowModel;

import java.util.ArrayList;

public interface FollowingContract {

    interface View extends BaseView {
        void onFailedFollowing(String Message);

        void onSuccessFollowing(ArrayList<FollowModel> followModel);
    }

    interface Presenter extends BasePresenter<FollowingContract.View> {
        void getFollowing(String username);
    }
}
