package com.example.lfen.myapplication.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.lfen.myapplication.R;

/**
 * Created by LFen on 16/5/22.
 */
public abstract class AppActivityNoToolbar extends BaseActivity{

    //获取第一个fragment
    protected abstract BaseFragment getFirstFragment();

    //获取intent
    protected void handleIntent(Intent intent) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(getContentViewId());
        if (null != getIntent()) {
            handleIntent(getIntent());
        }
        //避免重复添加fragment
        if (null == getSupportFragmentManager().getFragments()) {
            BaseFragment firstFragment = getFirstFragment();
            if (null != firstFragment) {
                addFragment(firstFragment);
            }
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_base_no_toolbar;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.fragment_container;
    }
}
