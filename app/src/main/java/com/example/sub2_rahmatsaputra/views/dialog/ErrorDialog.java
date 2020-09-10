package com.example.sub2_rahmatsaputra.views.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sub2_rahmatsaputra.R;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ErrorDialog extends Dialog {

    @BindView(R.id.text_view_tittle_error)
    TextView text_view_tittle_error;
    @BindView(R.id.text_view_description_error)
    TextView text_view_description_error;
    @BindView(R.id.image_view_error)
    ImageView image_view_error;
    private ErrorDialog.OnProceedListener listener;
    private String title;
    private String desc;
    private Boolean isSuccess;
    private Context mContext;
    public ErrorDialog(@NonNull Context context, String title, String desc, Boolean isSuccess) {
        super(context);
        this.mContext = context;
        this.title = title;
        this.desc = desc;
        this.isSuccess = isSuccess;
    }

    /**
     * Proceeding Action
     */
    @OnClick(R.id.button_back_error)
    void proceed() {
        dismiss();
        if (listener != null) {
            listener.onProceed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_dialog);
        setCanceledOnTouchOutside(false);
        setCancelable(true);

        ButterKnife.bind(this);

        Window window = getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setGravity(Gravity.CENTER);
    }

    @Override
    protected void onStart() {
        super.onStart();

        text_view_tittle_error = findViewById(R.id.text_view_tittle_error);
        text_view_description_error = findViewById(R.id.text_view_description_error);
        image_view_error = findViewById(R.id.image_view_error);
        text_view_tittle_error.setText(title);
        text_view_description_error.setText(desc);
        image_view_error.setImageResource(R.drawable.ic_cancel_red_24dp);
        text_view_tittle_error.setTextColor(getContext().getResources().getColor(R.color.colorRed));

    }

    /**
     * Set Listener when you click 'Yes' Button
     *
     * @param listener Interface when you click 'Yes' Button
     */
    public void setOnProceedListener(ErrorDialog.OnProceedListener listener) {
        this.listener = listener;
    }

    public interface OnProceedListener {
        void onProceed();
    }

    /**
     * On Click 'Yes' Button
     */

}