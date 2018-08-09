package com.wudh.study.bmob.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.wudh.study.bmob.R;
import com.wudh.study.bmob.ui.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private AppCompatButton btnAdd;
    private AppCompatButton btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd=findViewById(R.id.btn_add);
        btnShow=findViewById(R.id.btn_show);
        btnAdd.setOnClickListener(this);
        btnShow.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add:
                Intent addIntent=new Intent(MainActivity.this,AddLostActivity.class);
                startActivity(addIntent);
                break;
            case R.id.btn_show:
                Intent showIntent=new Intent(MainActivity.this,ShowLostActivity.class);
                startActivity(showIntent);
                break;
        }
    }
}
