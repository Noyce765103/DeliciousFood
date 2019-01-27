package com.example.deliciousfood.Widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.example.deliciousfood.R;

public class MyDialog extends Dialog implements View.OnClickListener{
    private TextView myTvTitle,mTvConfirm;
    private String title,message,confirm;
    private IOnConfirmListener confirmListener;

    public void setConfirm(IOnConfirmListener listener) {
        this.confirmListener = listener;
    }

    public MyDialog(Context context) {
        super(context);
    }

    public MyDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mydialog);
        myTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvConfirm = (TextView) findViewById(R.id.tv_confirm);
        mTvConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(confirmListener != null){
            confirmListener.onConfirm(this);
        }
    }


    public interface IOnConfirmListener{
        void onConfirm(MyDialog dialog);
    }
}
