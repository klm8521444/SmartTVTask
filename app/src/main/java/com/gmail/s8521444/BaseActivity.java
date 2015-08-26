package com.gmail.s8521444;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

public abstract class BaseActivity extends ActionBarActivity {
    protected Toolbar mToolBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceIdentifier());
        loadComponents();
        loadInfoView();
        initializeToolBar();
    }

    private void loadComponents() {
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void loadInfoView() {
        if (mToolBar != null) {
            setSupportActionBar(mToolBar);
        }
    }

    protected void initializeToolBar() {
        if (mToolBar != null) {
            setTitle(getTitleToolBar());
            getSupportActionBar().setDisplayHomeAsUpEnabled(getDisplayHomeAsUp());
            getSupportActionBar().setHomeButtonEnabled(getHomeButtonEnabled());
        }
    }

    protected abstract int getLayoutResourceIdentifier();

    protected abstract String getTitleToolBar();

    protected abstract boolean getDisplayHomeAsUp();

    protected abstract boolean getHomeButtonEnabled();

}