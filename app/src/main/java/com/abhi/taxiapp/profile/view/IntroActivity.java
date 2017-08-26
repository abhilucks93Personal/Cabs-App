package com.abhi.taxiapp.profile.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.abhi.taxiapp.R;
import com.abhi.taxiapp.Utils.Constants;
import com.abhi.taxiapp.Utils.Utility;

/**
 * Created by abhi on 02/04/17.
 */

public class IntroActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvLogin, tvSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_intro);

        findViewById();

    }


    private void initData() {
        Boolean isLoggedIn = Utility.getPreferences(this, Constants.IsLogin, false);
        if (isLoggedIn) {
            startActivity(new Intent(IntroActivity.this, ProfileActivity.class));
            finish();
        }
    }

    private void findViewById() {

        tvLogin = (TextView) findViewById(R.id.intro_tv_login);
        tvLogin.setOnClickListener(this);
        tvSignUp = (TextView) findViewById(R.id.intro_tv_signUp);
        tvSignUp.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.intro_tv_login:
                startActivity(new Intent(IntroActivity.this, LoginActivity.class));
                break;

            case R.id.intro_tv_signUp:
                startActivity(new Intent(IntroActivity.this, SignUpActivity.class));
                break;
        }
    }
}
