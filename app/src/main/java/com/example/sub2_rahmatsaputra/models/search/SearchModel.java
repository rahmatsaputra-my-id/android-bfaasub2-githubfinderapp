package com.example.sub2_rahmatsaputra.models.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchModel {
    @SerializedName("total_count")
    @Expose
    private int total_count;

    @SerializedName("incomplete_results")
    @Expose
    private boolean incomplete_results;

    @SerializedName("items")
    @Expose
    private List<ItemsItem> items;

    public int getTotalCount() {
        return total_count;
    }

    public void setTotalCount(int total_count) {
        this.total_count = total_count;
    }

    public boolean isIncompleteResults() {
        return incomplete_results;
    }

    public void setIncompleteResults(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public List<ItemsItem> getItems() {
        return items;
    }

    public void setItems(List<ItemsItem> items) {
        this.items = items;
    }
}