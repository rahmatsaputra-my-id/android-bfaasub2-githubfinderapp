package com.example.sub2_rahmatsaputra.views.detail.following;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sub2_rahmatsaputra.R;
import com.example.sub2_rahmatsaputra.adapter.FollowingAdapter;
import com.example.sub2_rahmatsaputra.base.BasePreferences;
import com.example.sub2_rahmatsaputra.models.follow.FollowModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FollowingActivity extends Fragment implements FollowingContract.View {

    private BasePreferences basePreferences;
    private String userName;
    private FollowingContract.Presenter presenter;
    private RecyclerView recycler_view_following;
    private TextView text_view_no_data_following;
    private NestedScrollView nested_scroll_view_following;
    private FollowingAdapter followingAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new FollowingPresenter(this, getContext());
        basePreferences = new BasePreferences(getContext());
        userName = basePreferences.getUserName();
        getFollowing(userName);

        recycler_view_following = view.findViewById(R.id.recycler_view_following);
        text_view_no_data_following = view.findViewById(R.id.text_view_no_data_following);
        nested_scroll_view_following = view.findViewById(R.id.nested_scroll_view_following);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false);
    }

    private void onDataFound(boolean found) {
        if (found) {
            if (basePreferences.getFollowing() == 0) {
                nested_scroll_view_following.setVisibility(View.GONE);
                text_view_no_data_following.setVisibility(View.VISIBLE);
            } else {
                nested_scroll_view_following.setVisibility(View.VISIBLE);
                text_view_no_data_following.setVisibility(View.GONE);
            }
        } else {
            nested_scroll_view_following.setVisibility(View.GONE);
            text_view_no_data_following.setVisibility(View.VISIBLE);
        }
    }

    private void getFollowing(String username) {
        presenter.getFollowing(username);
    }

    @Override
    public void onFailedFollowing(String Message) {
        onDataFound(false);
    }

    @Override
    public void onSuccessFollowing(ArrayList<FollowModel> followModel) {
        onDataFound(true);
        followingAdapter = new FollowingAdapter(getContext(), followModel);
        recycler_view_following.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler_view_following.setAdapter(followingAdapter);
    }

    @Override
    public void onLoading(boolean isLoading) {

    }
}
