package com.junior.dwan.remembernumbers.ui.windows;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import dagger.android.AndroidInjection;

public abstract class BaseActivity extends AppCompatActivity {

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

    /**
     * bool variable for injecting activity
     *
     * @return true if need inject current activity
     */
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
    public abstract int getRootId();

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

    /**
     * method for show toast with string resources
     *
     * @param res of string for show
     * @see #showToast(String)
     */
    protected void showToast(@StringRes int res) {
        this.showToast(getString(res));
    }

    /**
     * method for show toast with string
     *
     * @param message for showing
     */
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


    /**
     * method for bind listeners and release it
     *
     * @param enable
     */
    protected void setListeners(boolean enable) {
    }

    @CallSuper
    @Override
    protected void onPause() {
        super.onPause();
        setListeners(false);
    }
}
