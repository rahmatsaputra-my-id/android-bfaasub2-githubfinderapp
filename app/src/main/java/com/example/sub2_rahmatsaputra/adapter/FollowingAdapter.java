package com.example.sub2_rahmatsaputra.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.sub2_rahmatsaputra.models.follow.FollowModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.MyFollowingAdapter> {
    private Context context;
    private ArrayList<FollowModel> followModels;

    public FollowingAdapter(Context context, ArrayList<FollowModel> followModels) {
        this.context = context;
        this.followModels = followModels;
    }

    @NonNull
    @Override
    public MyFollowingAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyFollowingAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyFollowingAdapter holder, int position) {
        holder.text_view_name_list.setText(followModels.get(position).getLogin());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.error(R.drawable.image_not_found);
        requestOptions.centerCrop();
        requestOptions.priority(Priority.HIGH);

        Glide.with(context)
                .load(followModels.get(position).getAvatar_url())
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progress_bar_list.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progress_bar_list.setVisibility(View.GONE);
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.image_view_list);
    }

    @Override
    public int getItemCount() {
        try {
            return followModels.size();
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public class MyFollowingAdapter extends RecyclerView.ViewHolder {
        ImageView image_view_list;
        TextView text_view_name_list;
        ProgressBar progress_bar_list;

        public MyFollowingAdapter(@NonNull View itemView) {
            super(itemView);

            progress_bar_list = itemView.findViewById(R.id.progress_bar_list);
            text_view_name_list = itemView.findViewById(R.id.text_view_name_list);
            image_view_list = itemView.findViewById(R.id.image_view_list);
        }
    }
}
