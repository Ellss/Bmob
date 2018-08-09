package com.wudh.study.bmob.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.wudh.study.bmob.R;
import com.wudh.study.bmob.model.User;
import com.wudh.study.bmob.presenter.LoginPresenter;
import com.wudh.study.bmob.ui.base.BaseActivity;
import com.wudh.study.bmob.ui.fragment.LoginFragment;
import com.wudh.study.bmob.ui.fragment.SignUpFragment;

import cn.bmob.v3.BmobUser;

public class LoginActivity extends BaseActivity implements View.OnClickListener{
    private LoginPresenter loginPresenter;
    private LoginPresenter signUpPresenter;
    private LoginFragment loginFragment;
    private SignUpFragment signUpFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        User user= BmobUser.getCurrentUser(User.class);
        if(user != null){
            goToMainactivity();
        }
        //缓存用户对象为空时， 可打开用户注册界面…
        if (savedInstanceState != null) {
            FragmentManager manager = getSupportFragmentManager();
            loginFragment = (LoginFragment) manager.getFragment(savedInstanceState, LoginFragment.class.getSimpleName());
            signUpFragment = (SignUpFragment) manager.getFragment(savedInstanceState, SignUpFragment.class.getSimpleName());
        }else {
            loginFragment = LoginFragment.getInstance();
            signUpFragment = SignUpFragment.getInstance();
        }

        if (!loginFragment.isAdded()){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, loginFragment, LoginFragment.class.getSimpleName())
                    .commit();
        }
        if (!signUpFragment.isAdded()){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, signUpFragment, SignUpFragment.class.getSimpleName())
                    .commit();
        }
        loginPresenter=new LoginPresenter(loginFragment);
        signUpPresenter=new LoginPresenter(signUpFragment);
        showLoginFragment();
    }

    public void showLoginFragment(){
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.right_in,R.anim.right_out)
                .show(loginFragment)
                .hide(signUpFragment)
                .commit();
    }

    public void showSignUpFragment(){
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.left_in,R.anim.left_out)
                .show(signUpFragment)
                .hide(loginFragment)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        FragmentManager manager = getSupportFragmentManager();
        if (loginFragment.isAdded()) {
            manager.putFragment(outState, LoginFragment.class.getSimpleName(), loginFragment);
        }
        if (signUpFragment.isAdded()) {
            manager.putFragment(outState, SignUpFragment.class.getSimpleName(), signUpFragment);
        }
    }
    public void goToMainactivity(){
        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onClick(View view) {

    }
}
