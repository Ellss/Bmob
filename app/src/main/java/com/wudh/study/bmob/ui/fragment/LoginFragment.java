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

/**
 * Created by wudh on 2018/8/3.
 **/
public class LoginFragment extends Fragment implements LoginContract.View{

    private TextInputEditText editUserName;
    private TextInputEditText editPassword;
    private AppCompatButton btnLogin;
    private TextView linkSignUp;
    private LoginContract.Presenter presenter;

    public LoginFragment() {
    }
    public static LoginFragment getInstance(){
        return new LoginFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_login,container,false);
        initViews(view);

        linkSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity loginActivity = (LoginActivity) getActivity();
                loginActivity.showSignUpFragment();
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editUserName.getText().toString();
                String password = editPassword.getText().toString();
                presenter.login(username,password);
            }
        });
        return view;
    }

    @Override
    public void goToMainActivity() {
        LoginActivity activity=(LoginActivity) getActivity();
        activity.goToMainactivity();
    }

    @Override
    public void showMsg(String msg) {
        SnackbarUtil.show(linkSignUp,msg);
    }

    @Override
    public void initViews(View view) {
        editUserName = view.findViewById(R.id.edit_username);
        editPassword = view.findViewById(R.id.edit_password);
        btnLogin = view.findViewById(R.id.btn_login);
        linkSignUp = view.findViewById(R.id.text_link_signup);
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

    }

}
