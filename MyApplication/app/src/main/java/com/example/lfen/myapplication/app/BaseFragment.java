package com.example.lfen.myapplication.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * 项目中所有fragment的基类
 * Created by LFen on 16/5/21.
 */
public abstract class BaseFragment extends Fragment {

    private long mLastToastTime;

    /**********
     * Waiting 相关
     **********/
    private ProgressDialog mWaiting;

    public void showWaiting(@StringRes int msgResId) {
        String message = getString(msgResId);
        showWaiting(message);
    }

    public void showWaiting(@StringRes int titleResId, @StringRes int msgResId) {
        String title = getString(titleResId);
        String message = getString(msgResId);
        showWaiting(title, message);
    }

    public void showWaiting(String message) {
        showWaiting(null, message);
    }

    public void showWaiting(String title, String message) {
        showWaiting(title, message, true);
    }

    public void showWaiting(String tile, String message, boolean cancelable) {
        if (mWaiting != null) {
            stopWaiting();
        }
        mWaiting = ProgressDialog.show(getActivity(), tile, message, true, cancelable);
    }

    /**
     * 停止Waiting
     */
    public void stopWaiting() {
        if (mWaiting != null) {
            mWaiting.dismiss();
            mWaiting = null;
        }
    }

    /**********
     * Toast 相关
     **********/

    public void showToast(@StringRes int resId) {
        showToast(resId, Toast.LENGTH_SHORT);
    }

    public synchronized void showToast(@StringRes int resId, int duration) {
        if (System.currentTimeMillis() - mLastToastTime < 1000) {
            return;
        }

        Toast.makeText(getActivity(), resId, duration).show();
        mLastToastTime = System.currentTimeMillis();
    }

    public void showToast(String message) {
        showToast(message, Toast.LENGTH_SHORT);
    }

    public synchronized void showToast(String message, int duration) {
        if (System.currentTimeMillis() - mLastToastTime < 1000) {
            return;
        }
        Toast.makeText(getActivity(), message, duration).show();
        mLastToastTime = System.currentTimeMillis();
    }

    // 隐藏输入法
    protected void hideSoftInput() {
        Activity activity = null;
        try {
            activity = getActivity();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (activity != null) {
            InputMethodManager im = (InputMethodManager) activity.getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            if (im.isActive() && activity.getCurrentFocus() != null) {
                im.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }

    }
}
