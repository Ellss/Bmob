package com.wudh.study.bmob.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.wudh.study.bmob.R;
import com.wudh.study.bmob.model.Lost;
import com.wudh.study.bmob.presenter.AddLostPresenter;
import com.wudh.study.bmob.presenter.contract.AddLostContract;
import com.wudh.study.bmob.ui.base.BaseActivity;

/**
 * Created by wudh on 2018/8/7.
 **/
public class AddLostActivity extends BaseActivity implements AddLostContract.View {

    private AppCompatButton btnAdd;
    private TextInputEditText edTitle;
    private TextInputEditText edDscribe;
    private TextInputEditText edPhone;
    private AddLostContract.Presenter presenter;
    private ProgressDialog pd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlost);
        initView();
        presenter=new AddLostPresenter(this);
    }
    private void initView(){
        btnAdd=findViewById(R.id.btn_add);
        edTitle=findViewById(R.id.ed_add_title);
        edPhone=findViewById(R.id.ed_add_phone);
        edDscribe=findViewById(R.id.ed_add_describe);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.upload();
            }
        });
    }

    @Override
    public Lost getLost() {
        Lost lost=new Lost();
        lost.setTitle(edTitle.getText().toString().trim());
        lost.setPhone(edPhone.getText().toString().trim());
        lost.setDescribe(edDscribe.getText().toString().trim());
        return lost;
    }

    @Override
    public boolean isValid() {
        if (edTitle.getText().toString().trim().equals("")){
            showMsg("标题不能为空");
            return false;
        }else if (edPhone.getText().toString().trim().length()!=11){
            showMsg("手机号输入有误");
            return false;
        }else if (edDscribe.getText().toString().trim().equals("")){
            showMsg("描述不能为空");
            return false;
        }
        return true;
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public void setPresenter(AddLostContract.Presenter presenter) {
        this.presenter=presenter;
    }

    @Override
    public void showMsg(String msg) {
        Snackbar.make(edDscribe,msg,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressDialog(String msg) {
        if (pd==null){
            pd=new ProgressDialog(AddLostActivity.this);
            pd.setMessage(msg);
            pd.show();
        }
    }

    @Override
    public void dismissProgressDialog() {
        if (pd!=null){
            pd.dismiss();
        }
    }
}
