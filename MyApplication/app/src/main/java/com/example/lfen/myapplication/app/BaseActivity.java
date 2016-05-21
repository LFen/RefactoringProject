package com.example.lfen.myapplication.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * 项目中所有activity的基类
 * Created by LFen on 16/5/21.
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * ******* Waiting 相关*********
     */
    private ProgressDialog mWaiting;

    protected static void startActivity(Context context, Bundle bundle, Class cls, int... flags) {
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (flags.length > 0) {
            for (int flag : flags) {
                intent.addFlags(flag);
            }
        }
        context.startActivity(intent);
    }

    protected static void startActivity(Activity context, Bundle bundle, Class cls, int... flags) {
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (flags.length > 0) {
            for (int flag : flags) {
                intent.addFlags(flag);
            }
        }
        context.startActivity(intent);
    }

    protected static void startActivity(Activity context, Class cls) {
        startActivity(context, null, cls);
    }

    protected static void startActivity(Activity context, Bundle bundle, Class cls) {
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    //带Material Design风格的切换方法
    protected static void startActivityWithMD(Activity context, Class cls, View view) {
        startActivityWithMD(context, null, cls, view);
    }

    //带Material Design风格的切换方法
    protected static void startActivityWithMD(Activity context, Bundle bundle, Class cls, View view) {
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        ActivityCompat.startActivity(context, intent, optionsCompat.toBundle());
    }

    //带Material Design风格的切换方法
    protected static void startActivityWithMD(Activity context, Class cls, View view, int... flags) {
        startActivityWithMD(context, null, cls, view, flags);
    }

    //带Material Design风格的切换方法
    protected static void startActivityWithMD(Activity context, Bundle bundle, Class cls, View view, int... flags) {
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (flags.length > 0) {
            for (int flag : flags) {
                intent.addFlags(flag);
            }
        }
        ActivityCompat.startActivity(context, intent, optionsCompat.toBundle());
    }

    protected static void startActivityForResult(Activity context, int requestCode, Class cls) {
        startActivityForResult(context, null, requestCode, cls);
    }

    protected static void startActivityForResult(Activity context, Bundle bundle, int requestCode, Class cls) {
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivityForResult(intent, requestCode);
    }

    protected static void startActivityForResult(Fragment fragment, int requestCode, Class cls) {
        startActivityForResult(fragment, null, requestCode, cls);
    }

    protected static void startActivityForResult(Fragment fragment, Bundle bundle, int requestCode, Class cls) {
        Intent intent = new Intent(fragment.getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        fragment.startActivityForResult(intent, requestCode);
    }

    // 获取布局文件ID
    protected abstract int getContentViewId();

    // 布局中fragment的id
    protected abstract int getFragmentContentId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStackManager.getInstance().addActivity(this);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStackManager.getInstance().removeActivity(this);
    }

    // 添加fragment
    protected void addFragment(BaseFragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(getFragmentContentId(), fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }
    }

    // 移除fragment
    protected void removeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

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
        mWaiting = ProgressDialog.show(this, tile, message, true, cancelable);
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

    /**
     * ******* Toast 相关*********
     */

    public void showToast(@StringRes int resId) {
        showToast(resId, Toast.LENGTH_SHORT);
    }

    public void showToast(@StringRes int resId, int duration) {
        Toast.makeText(this, resId, duration).show();
    }

    public void showToast(String message) {
        showToast(message, Toast.LENGTH_SHORT);
    }

    public void showToast(String message, int duration) {
        Toast.makeText(this, message, duration).show();
    }

    public void showToast(CharSequence message) {
        showToast(message, Toast.LENGTH_SHORT);
    }

    public void showToast(CharSequence message, int duration) {
        Toast.makeText(this, message, duration).show();
    }

    // 隐藏输入法
    protected void hideSoftInput() {
        InputMethodManager im = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        if (im.isActive() && getCurrentFocus() != null) {
            im.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    // 返回键事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
