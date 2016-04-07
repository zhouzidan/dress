package com.dudress.dress.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dudress.dress.R;

/**
 * Created by zhou on 16-3-18.
 */
public class SignUpEmailCodeActivity extends Activity implements View.OnClickListener{
    private Context mContext = this;
    private TextView textView;
    private EditText codeEditText;

    public static  String KEY_EMAIL = "key_email";
    public static String KEY_EMAIL_CODE = "key_email_code";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_validate_code);
        findViewById(R.id.img_back).setOnClickListener(this);
        findViewById(R.id.tv_back).setOnClickListener(this);
        findViewById(R.id.btn_next).setOnClickListener(this);
        textView = (TextView) findViewById(R.id.tv_introduce);
        codeEditText = (EditText) findViewById(R.id.ed_email_code);
        String email = "";
        if (getIntent() != null && TextUtils.isEmpty(getIntent().getStringExtra(KEY_EMAIL)) == false){
            email = getIntent().getStringExtra(KEY_EMAIL);
            textView.setText(textView.getText().toString() + email);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
            case R.id.tv_back:
                finish();
                break;
            case R.id.btn_next:
                if (codeEditText.getText().toString().trim().length() > 0){
                    Intent intent = new Intent();
                    intent.putExtra(KEY_EMAIL_CODE,codeEditText.getText().toString().trim());
                    setResult(RESULT_OK,intent);
                }else{
                    Toast.makeText(mContext,R.string.msg_error_code_empty,Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}
