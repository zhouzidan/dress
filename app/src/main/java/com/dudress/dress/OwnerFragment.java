package com.dudress.dress;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dudress.dress.account.LoginActivity;
import com.dudress.dress.account.SignUpActivity;

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
        return inflater.inflate(R.layout.fragment_owner,null);
    }

    @Override
    public void onStart() {
        super.onStart();
        initView();
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
        }
    }
}
