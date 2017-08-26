package com.abhi.taxiapp.profile.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.abhi.taxiapp.R;
import com.abhi.taxiapp.Utils.Constants;
import com.abhi.taxiapp.Utils.Utility;
import com.abhi.taxiapp.booking.view.BookingActivity;
import com.abhi.taxiapp.profile.model.SignUpResponseMain;
import com.abhi.taxiapp.retrofit.RetrofitApi;

/**
 * Created by abhi on 02/04/17.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, RetrofitApi.ResponseListener {

    TextView tvSubmit, tvSignup, tvSkip;
    EditText etNumber, etPassword;
    private String strNumber, strPassword;
    private String deviceId, uniqueDeviceId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utility.setStatusBarTranslucent(this, true);
        setContentView(R.layout.activity_login);

        findViewById();
        initData();

    }

    private void initData() {
        uniqueDeviceId = Utility.getUniqueId(getApplicationContext());
        deviceId = Utility.getPreferences(this, Constants.DeviceId);
    }

    private void findViewById() {

        tvSubmit = (TextView) findViewById(R.id.login_tv_submit);
        tvSubmit.setOnClickListener(this);

        tvSignup = (TextView) findViewById(R.id.login_tv_signup);
        tvSignup.setOnClickListener(this);

        tvSkip = (TextView) findViewById(R.id.login_tv_skip);
        tvSkip.setOnClickListener(this);

        etNumber = (EditText) findViewById(R.id.login_et_number);
        etPassword = (EditText) findViewById(R.id.login_et_password);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.login_tv_submit:
                fetchData();
                break;

            case R.id.login_tv_signup:
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                finish();
                break;

            case R.id.login_tv_skip:
                startActivity(new Intent(LoginActivity.this, BookingActivity.class));
                finish();
                break;

        }
    }

    private void fetchData() {
        strNumber = etNumber.getText().toString().trim();
        strPassword = etPassword.getText().toString().trim();
        if (isValidated()) {
            RetrofitApi.getInstance().login(this, this, uniqueDeviceId, deviceId, strNumber, strPassword);
        }
    }

    private boolean isValidated() {
        if (strNumber.length() == 0) {
            Utility.showToast(LoginActivity.this, "Please input the Mobile Number");
            etNumber.setText("");
            return false;
        }

        if (strNumber.length() < 10) {
            Utility.showToast(LoginActivity.this, "Please  enter a valid Mobile Number");
            etNumber.setText("");
            return false;
        }

        if (strPassword.length() == 0) {
            Utility.showToast(LoginActivity.this, "Please input the Password");
            etPassword.setText("");
            return false;
        }

        if (strPassword.length() < 5) {
            Utility.showToast(LoginActivity.this, "Password must be at least 5 characters long");
            return false;
        }

        return true;
    }

    @Override
    public void _onNext(Object obj) {
        SignUpResponseMain responseMain = (SignUpResponseMain) obj;
        if (responseMain.getStatusCode().equals(Constants.StatusCodeSuccess)) {
            Utility.addPreferences(LoginActivity.this, Constants.ProfileData, responseMain.getData().get(0));
            Utility.addPreferences(LoginActivity.this, Constants.IsLogin, true);
            finish();
        } else {
            Utility.showToast(LoginActivity.this, responseMain.getStatusMsg());
        }
    }

    @Override
    public void _onComplete() {

    }

    @Override
    public void _onError(Throwable e) {

    }
}
