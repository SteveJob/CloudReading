package com.kongshanxinyu.cloudreading.debug;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.kongshanxinyu.cloudreading.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Steve on 16/10/16.
 * E-mail: singleframe@aliyun.com
 */
public class DebugActivity extends Activity {

    @BindView(R.id.et)
    private EditText et;
    @BindView(R.id.btn_download)
    private Button btn_download;
    @BindView(R.id.btn_pause)
    private Button btn_pause;
    @BindView(R.id.pb)
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_download)
    public void download(){
    }
}
