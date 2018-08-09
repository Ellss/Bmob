package com.wudh.study.bmob.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wudh.study.bmob.R;
import com.wudh.study.bmob.app.util.SnackbarUtil;
import com.wudh.study.bmob.presenter.contract.LoginContract;
import com.wudh.study.bmob.ui.activity.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wudh on 2018/8/3.
 **/
public class SignUpFragment  extends Fragment implements LoginContract.View{

    private TextInputEditText editUserName;
    private TextInputEditText editPassword;
    private TextInputEditText editRePassword;
    private TextInputEditText editCode;
    private AppCompatButton btnGetCode;
    private AppCompatButton btnSignUp;
    private TextView linkLogin;
    private LoginContract.Presenter presenter;
    private int mSecCount;
    public SignUpFragment() {
    }
    public static SignUpFragment getInstance(){
        return new SignUpFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sign_up,container,false);
        initViews(view);

        btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.getCode(editUserName.getText().toString());
            }
        });
        linkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity loginActivity = (LoginActivity) getActivity();
                loginActivity.showLoginFragment();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editUserName.getText().toString();
                String msgCode = editCode.getText().toString();
                String password = editPassword.getText().toString();
                String rePassword = editRePassword.getText().toString();
                presenter.signUp(username,msgCode,password,rePassword);
            }
        });
        return view;
    }

    @Override
    public void goToMainActivity() {

    }

    @Override
    public void showMsg(String msg) {
        SnackbarUtil.show(linkLogin,msg);
    }


    @Override
    public void initViews(View view) {
        editUserName = view.findViewById(R.id.edit_username);
        editPassword = view.findViewById(R.id.edit_password);
        editRePassword = view.findViewById(R.id.edit_re_password);
        editCode = view.findViewById(R.id.edit_code);
        btnGetCode = view.findViewById(R.id.btn_get_code);
        btnSignUp = view.findViewById(R.id.btn_sign_up);
        linkLogin = view.findViewById(R.id.text_link_login);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter=presenter;
    }


    @Override
    public void showProgressDialog(String msg) {

    }

    @Override
    public void dismissProgressDialog() {

    }
    @Override
    public void setCodeTimeDown() {
        btnGetCode.setEnabled(false);
        final Timer timer = new Timer();
        mSecCount = 60;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                new Runnable() {
                    @Override
                    public void run() {
                        mSecCount--;
                        btnGetCode.setText(mSecCount+" s");
                        if (mSecCount<=0) {
                            timer.cancel();
                            btnGetCode.setText(R.string.reSend);
                            btnGetCode.setEnabled(true);
                        }
                    }
                };
            }
        };
        timer.schedule(timerTask,1000,1000);

    }
}

