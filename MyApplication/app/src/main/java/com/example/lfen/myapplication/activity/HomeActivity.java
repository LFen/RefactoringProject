package com.example.lfen.myapplication.activity;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lfen.myapplication.R;
import com.example.lfen.myapplication.app.AppActivityNoToolbar;
import com.example.lfen.myapplication.app.AppActivityToolbar;
import com.example.lfen.myapplication.app.AppBaseActivity;
import com.example.lfen.myapplication.app.AppFragment;
import com.example.lfen.myapplication.app.BaseFragment;
import com.example.lfen.myapplication.fragment.HomeFragment;

public class HomeActivity extends AppActivityToolbar {

    public static void launch(Activity context) {
        startActivity(context, HomeActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("HomeActivity");
    }

    @Override
    protected AppFragment getFirstFragment() {
        return HomeFragment.newInstance();
    }
}
