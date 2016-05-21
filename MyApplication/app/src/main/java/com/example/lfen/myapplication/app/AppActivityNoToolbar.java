package com.example.lfen.myapplication.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.lfen.myapplication.R;

/**
 * Created by LFen on 16/5/22.
 */
public abstract class AppActivityNoToolbar extends AppBaseActivity{

    //获取第一个fragment
    protected abstract AppFragment getFirstFragment();

    //获取intent
    protected void handleIntent(Intent intent) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        if (null != getIntent()) {
            handleIntent(getIntent());
        }
        //避免重复添加fragment
        if (null == getSupportFragmentManager().getFragments()) {
            AppFragment firstFragment = getFirstFragment();
            if (null != firstFragment) {
                addFragment(firstFragment);
            }
        }
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
