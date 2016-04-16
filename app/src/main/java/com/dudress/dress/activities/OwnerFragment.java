package com.dudress.dress.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.dudress.dress.R;
import com.dudress.dress.db.AccountDBManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhou on 16-3-8.
 */
public class OwnerFragment extends Fragment implements View.OnClickListener{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_me_unlogin,null);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.layout_user)
    void clickUserLayout(){
        //TODO weather in login status
        String userId = AccountDBManager.get().getUserId();
        if (TextUtils.isEmpty(userId) == false){
            startActivity(new Intent(getActivity(),AccountModifyActivity.class));
        }else{
            startActivity(new Intent(getActivity(),LoginActivity.class));
        }
    }

    @OnClick(R.id.tv_setting)
    void clickSetting(){
        startActivity(new Intent(getActivity(),SettingActivity.class));
    }



    @Override
    public void onStart() {
        super.onStart();
//        initView();
    }

    private void initView(){
        getView().findViewById(R.id.tv_login).setOnClickListener(this);
        getView().findViewById(R.id.btn_registe).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_login:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.btn_registe:
                startActivity(new Intent(getActivity(), SignUpActivity.class));
                break;
            case R.id.tv_foeget_pwd:
                startActivity(new Intent(getActivity(),ForgetPwdActivity.class));
                break;
            case R.id.tv_register:
                // 跳转到注册页面
                startActivity(new Intent(getActivity(), SignUpActivity.class));
                break;
            case R.id.img_pwd_visible:
                boolean last_visible = (Boolean)pwdVisibleImg.getTag();
                pwdVisibleImg.setTag(!last_visible);
                if (last_visible){
                    pwdVisibleImg.setImageResource(R.mipmap.hide_blue);
                    passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else{
                    pwdVisibleImg.setImageResource(R.mipmap.display_blue);
                    passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                passwordEditText.setSelection(passwordEditText.getText().toString().length());
                break;
            case R.id.img_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
        }
    }
    ImageView pwdVisibleImg;
    EditText passwordEditText ;
    public void initViewForLogin(){
        getView().findViewById(R.id.tv_register).setOnClickListener(this);
        getView().findViewById(R.id.tv_foeget_pwd).setOnClickListener(this);
        passwordEditText = (EditText) getView().findViewById(R.id.ed_pwd);
        pwdVisibleImg = (ImageView)getView().findViewById(R.id.img_pwd_visible);
        pwdVisibleImg.setOnClickListener(this);
        pwdVisibleImg.setTag(false);
        getView().findViewById(R.id.img_setting).setOnClickListener(this);
    }
}
