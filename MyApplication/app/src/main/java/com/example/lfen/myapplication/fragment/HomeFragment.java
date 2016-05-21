package com.example.lfen.myapplication.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.lfen.myapplication.R;
import com.example.lfen.myapplication.app.AppFragment;

/**
 * Created by LFen on 16/5/22.
 */
public class HomeFragment extends AppFragment{

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
}
