package com.example.lfen.myapplication.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by LFen on 16/5/21.
 */
public abstract class AppFragment extends BaseFragment {

    protected AppBaseActivity mActivity;

    protected abstract void initView(View view, Bundle savedInstanceState);

    //获取布局文件id
    protected abstract int getLayoutId();

    //获取宿主activity
    protected AppBaseActivity getHoldingActivity() {
        return mActivity;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (AppBaseActivity) activity;
    }

    //添加fragment
    protected void addFragment(AppFragment fragment) {
        if (null != fragment) {
            getHoldingActivity().addFragment(fragment);
        }
    }

    //移除fragment
    protected void removeFragment() {
        getHoldingActivity().removeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        initView(view, savedInstanceState);
        return view;
    }
}
