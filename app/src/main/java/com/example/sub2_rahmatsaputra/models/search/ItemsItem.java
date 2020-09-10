package com.example.sub2_rahmatsaputra.models.search;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemsItem implements Parcelable {
    public static final Creator<ItemsItem> CREATOR = new Creator<ItemsItem>() {
        @Override
        public ItemsItem createFromParcel(Parcel parcel) {
            return new ItemsItem(parcel);
        }

        @Override
        public ItemsItem[] newArray(int size) {
            return new ItemsItem[size];
        }
    };
    @SerializedName("gists_url")
    @Expose
    private String gists_url;
    @SerializedName("repos_url")
    @Expose
    private String repos_url;
    @SerializedName("following_url")
    @Expose
    private String following_url;
    @SerializedName("starred_url")
    @Expose
    private String starred_url;
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("followers_url")
    @Expose
    private String followers_url;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("subscriptions_url")
    @Expose
    private String subscriptions_url;
    @SerializedName("score")
    @Expose
    private double score;
    @SerializedName("received_events_url")
    @Expose
    private String received_events_url;
    @SerializedName("avatar_url")
    @Expose
    private String avatar_url;
    @SerializedName("events_url")
    @Expose
    private String events_url;
    @SerializedName("html_url")
    @Expose
    private String html_url;
    @SerializedName("site_admin")
    @Expose
    private boolean site_admin;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("gravatar_id")
    @Expose
    private String gravatar_id;
    @SerializedName("node_id")
    @Expose
    private String node_id;
    @SerializedName("organizations_url")
    @Expose
    private String organizations_url;

    protected ItemsItem(Parcel in) {
        login = in.readString();
        id = in.readInt();
        node_id = in.readString();
        avatar_url = in.readString();
        gravatar_id = in.readString();
        url = in.readString();
        html_url = in.readString();
        followers_url = in.readString();
        following_url = in.readString();
        gists_url = in.readString();
        starred_url = in.readString();
        subscriptions_url = in.readString();
        repos_url = in.readString();
        events_url = in.readString();
        received_events_url = in.readString();
        type = in.readString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            site_admin = in.readBoolean();
        }
        score = in.readInt();
    }

    public String getGists_url() {
        return gists_url;
    }

    public void setGists_url(String gists_url) {
        this.gists_url = gists_url;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    public String getFollowing_url() {
        return following_url;
    }

    public void setFollowing_url(String following_url) {
        this.following_url = following_url;
    }

    public String getStarred_url() {
        return starred_url;
    }

    public void setStarred_url(String starred_url) {
        this.starred_url = starred_url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFollowersUrl() {
        return followers_url;
    }

    public void setFollowersUrl(String followers_url) {
        this.followers_url = followers_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSubscriptions_url() {
        return subscriptions_url;
    }

    public void setSubscriptions_url(String subscriptions_url) {
        this.subscriptions_url = subscriptions_url;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getReceived_events_url() {
        return received_events_url;
    }

    public void setReceived_events_url(String received_events_url) {
        this.received_events_url = received_events_url;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getEvents_url() {
        return events_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public boolean isSite_admin() {
        return site_admin;
    }

    public void setSite_admin(boolean site_admin) {
        this.site_admin = site_admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGravatar_id() {
        return gravatar_id;
    }

    public void setGravatar_id(String gravatar_id) {
        this.gravatar_id = gravatar_id;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public String getOrganizations_url() {
        return organizations_url;
    }

    public void setOrganizations_url(String organizations_url) {
        this.organizations_url = organizations_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
