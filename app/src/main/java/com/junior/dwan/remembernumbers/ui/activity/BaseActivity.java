package com.junior.dwan.remembernumbers.ui.activity;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import dagger.android.AndroidInjection;

abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        injectActivity();
        super.onCreate(savedInstanceState);
        setupRootWindow();
        setContentView(getRootId());
        initData(savedInstanceState);
        initUI();
        setupUI(savedInstanceState);
    }

    private void injectActivity() {
        if (isNeedInject()) {
            AndroidInjection.inject(this);
        }
    }

    protected boolean isNeedInject() {
        return false;
    }


    /**
     * setup window, theme, state etc
     */
    protected void setupRootWindow() {

    }

    /**
     * get layout resources for activity
     *
     * @return layout resource
     */

    @LayoutRes
    abstract int getRootId();

    /**
     * init data for activity
     *
     * @param savedInstanceState
     */
    protected void initData(Bundle savedInstanceState) {

    }

    /**
     * method for init UI elements
     */
    protected void initUI() {
    }

    /**
     * method for setup ui elements
     */
    protected void setupUI(@Nullable Bundle savedInstanceState) {
    }


    protected void showToast(@StringRes int res) {
        this.showToast(getString(res));
    }

    protected void showToast(String message) {
        if (TextUtils.isEmpty(message)) return;
        if (isFinishing()) return;

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @CallSuper
    @Override
    protected void onResume() {
        super.onResume();
        setListeners(true);
    }

    protected void setListeners(boolean enable) {
    }

    @CallSuper
    @Override
    protected void onPause() {
        super.onPause();
        setListeners(false);
    }
}
