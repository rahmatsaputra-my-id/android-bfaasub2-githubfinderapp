package com.example.sub2_rahmatsaputra.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.ViewGroup;

import com.example.sub2_rahmatsaputra.views.dialog.ErrorDialog;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;

public class BaseActivity extends AppCompatActivity implements BaseView, LifecycleOwner {
    protected static BasePreferences basePreferences;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkIsConnected();
    }

    private void checkPreferences() {
        if (basePreferences == null) {
            basePreferences = new BasePreferences(this);
        }
    }

    public String getName() {
        checkPreferences();
        return basePreferences.getName();
    }

    public void setName(String name) {
        checkPreferences();
        basePreferences.setName(name);
    }

    @Override
    public void onLoading(boolean isLoading) {
        if (isLoading) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(false);
            progressDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            progressDialog.setMessage("Please wait . . .");
            progressDialog.show();
        } else {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

    public void checkIsConnected() {
        if (!isNetworkConnected()) {
            dialogNoConnection("No Internet Connection", "Please make sure you're connect to Internet", false);
        }
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    private void dialogNoConnection(String title, String desc, boolean isSuccess) {
        ErrorDialog dialog = new ErrorDialog(this, title, desc, isSuccess);
        dialog.setOnProceedListener(() -> {
            checkIsConnected();
        });
        dialog.show();
    }

}
