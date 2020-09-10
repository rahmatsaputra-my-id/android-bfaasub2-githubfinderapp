package com.example.sub2_rahmatsaputra.views.detail;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.sub2_rahmatsaputra.R;
import com.example.sub2_rahmatsaputra.adapter.SectionsPagerAdapter;
import com.example.sub2_rahmatsaputra.base.BaseActivity;
import com.example.sub2_rahmatsaputra.base.BasePreferences;
import com.example.sub2_rahmatsaputra.models.detail.DetailModel;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity implements DetailContract.View {

    @BindView(R.id.nested_scroll_view_detail)
    NestedScrollView nested_scroll_view_detail;

    @BindView(R.id.text_view_no_data_detail)
    TextView text_view_no_data_detail;

    @BindView(R.id.image_view_detail)
    ImageView image_view_detail;

    @BindView(R.id.progress_bar_detail)
    ProgressBar progress_bar_detail;

    @BindView(R.id.text_view_name_detail)
    TextView text_view_name_detail;

    @BindView(R.id.text_view_followers_detail)
    TextView text_view_followers_detail;

    @BindView(R.id.text_view_following_detail)
    TextView text_view_following_detail;

    @BindView(R.id.text_view_repository_detail)
    TextView text_view_repository_detail;

    @BindView(R.id.text_view_company_detail)
    TextView text_view_company_detail;

    @BindView(R.id.text_view_location_detail)
    TextView text_view_location_detail;

    @BindView(R.id.tab_layout_detail)
    TabLayout tab_layout_detail;

    @BindView(R.id.view_pager_detail)
    ViewPager view_pager_detail;

    private BasePreferences basePreferences;
    private String userName;
    private DetailContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        presenter = new DetailPresenter(this, this);

        basePreferences = new BasePreferences(this);
        userName = basePreferences.getUserName();
        getSupportActionBar().setTitle(userName);

        getDetail(userName);
        initialisationID();

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        view_pager_detail.setAdapter(sectionsPagerAdapter);
        tab_layout_detail.setupWithViewPager(view_pager_detail);
    }

    private void initialisationID() {
        text_view_name_detail = findViewById(R.id.text_view_name_detail);
        text_view_company_detail = findViewById(R.id.text_view_company_detail);
        text_view_location_detail = findViewById(R.id.text_view_location_detail);
        image_view_detail = findViewById(R.id.image_view_detail);
        progress_bar_detail = findViewById(R.id.progress_bar_detail);
        text_view_followers_detail = findViewById(R.id.text_view_followers_detail);
        text_view_following_detail = findViewById(R.id.text_view_following_detail);
        text_view_repository_detail = findViewById(R.id.text_view_repository_detail);
        text_view_no_data_detail = findViewById(R.id.text_view_no_data_detail);
        nested_scroll_view_detail = findViewById(R.id.nested_scroll_view_detail);
        tab_layout_detail = findViewById(R.id.tab_layout_detail);
        view_pager_detail = findViewById(R.id.view_pager_detail);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onFailedDetail(String Message) {
        onDataFound(false);
    }

    @Override
    public void onSuccessDetail(DetailModel detailModel) {
        onDataFound(true);
        basePreferences.setFollowers(detailModel.getFollowers());
        basePreferences.setFollowing(detailModel.getFollowing());

        if (detailModel.getName() == null) {
            text_view_name_detail.setText(userName);
        } else {
            setName(detailModel.getName());
            text_view_name_detail.setText(detailModel.getName());
        }

        if (detailModel.getLocation() == null) {
            text_view_location_detail.setText("-");
        } else {
            text_view_location_detail.setText(detailModel.getLocation());
        }

        if (detailModel.getCompany() == null) {
            text_view_company_detail.setText("-");
        } else {
            text_view_company_detail.setText(detailModel.getCompany());
        }

        text_view_followers_detail.setText(String.valueOf(detailModel.getFollowers()));
        text_view_following_detail.setText(String.valueOf(detailModel.getFollowing()));
        text_view_repository_detail.setText(String.valueOf(detailModel.getPublic_repos()));

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.error(R.drawable.image_not_found);
        requestOptions.centerCrop();
        requestOptions.priority(Priority.HIGH);

        Glide.with(getBaseContext())
                .load(String.valueOf(detailModel.getAvatar_url()))
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progress_bar_detail.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progress_bar_detail.setVisibility(View.GONE);
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(image_view_detail);
    }

    private void getDetail(String username) {
        try {
            presenter.getDetail(username);
        } catch (NullPointerException e) {
        }
    }

    private void onDataFound(boolean found) {
        if (found) {
            nested_scroll_view_detail.setVisibility(View.VISIBLE);
            text_view_no_data_detail.setVisibility(View.GONE);

        } else {
            nested_scroll_view_detail.setVisibility(View.GONE);
            text_view_no_data_detail.setVisibility(View.VISIBLE);
        }
    }
}
