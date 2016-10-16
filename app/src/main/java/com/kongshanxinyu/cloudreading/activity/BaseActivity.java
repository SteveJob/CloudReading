package com.kongshanxinyu.cloudreading.activity;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.kongshanxinyu.cloudreading.R;
import com.kongshanxinyu.cloudreading.service.PlayService;
import com.kongshanxinyu.cloudreading.util.ContextUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Steve on 16/9/11.
 * E-mail: singleframe@aliyun.com
 */
public abstract class BaseActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;
    protected Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler=new Handler();
        PlayService.addToStack(this);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        init();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        init();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setListener();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PlayService.removeFromStack(this);
    }

    private void init(){
        ButterKnife.bind(this);
        if (mToolbar==null)
            throw new IllegalStateException("The layout xml file must contain ToolBar view and ID is set 'toolbar'.");
        setSupportActionBar(mToolbar);
        if (getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setSystemBarTransparent(){};

    protected abstract void setListener();

    public void showKeyboard(final EditText editText){
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        editText.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager inputMethodManager= (InputMethodManager) ContextUtil.getAppContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(editText,0);

            }
        },100);
    }

    public void hideKeyboard(){
        InputMethodManager inputMethodManager= (InputMethodManager) ContextUtil.getAppContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (getCurrentFocus()!=null)
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

}
