package com.samplepaggingapp.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import com.samplepaggingapp.R;


/**
 * This class is for to create custom progressbar application level.
 */
public class LoadingDialog {
    private static LoadingDialog instance;
    private Dialog dialog;

    public static synchronized LoadingDialog getInstance() {
        if (instance == null) {
            instance = new LoadingDialog();
        }
        return instance;
    }

    public void show(Context context) {
        if (dialog != null && dialog.isShowing()) {
            return;
        }
        dialog = new Dialog(context, R.style.LoadingDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.loading_dialog_layout);
        dialog.setCancelable(false);
        dialog.show();
    }

    public void hide() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
